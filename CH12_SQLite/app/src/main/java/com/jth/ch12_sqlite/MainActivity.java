package com.jth.ch12_sqlite;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    myDBHelper myHelper;
    EditText editName, editPeople;
    SQLiteDatabase sqlDB;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editName = (EditText) findViewById(R.id.editName);
        editPeople = (EditText) findViewById(R.id.editPeople);
        listView = (ListView) findViewById(R.id.listView);

        myHelper = new myDBHelper(this);

    }

    public class myDBHelper extends SQLiteOpenHelper {
        public myDBHelper(Context context) {
            super(context, "groupDB", null, 1);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL("CREATE TABLE groupTBL (gName CHAR(20) PRIMARY KEY, gNumber INTEGER);");
            Log.d("Main", "Start myDBHelper onCreate");
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS groupTBL");
            onCreate(db);
        }
    }

    public void btnResetClick (View v) {
        sqlDB = myHelper.getWritableDatabase();
        myHelper.onUpgrade(sqlDB, 1, 2);
        sqlDB.close();
    }

    public void btnInsertClick (View v) {
        try(SQLiteDatabase sqlDB = myHelper.getWritableDatabase()) {
            sqlDB.execSQL("INSERT INTO groupTBL VALUES ( '"
                    + editName.getText().toString() + "' , "
                    + editPeople.getText().toString() + ");");
            sqlDB.close();
            Toast.makeText(this, "입력됨", Toast.LENGTH_SHORT).show();
            btnViewClick(v);
        } catch (SQLiteException e) {
            Toast.makeText(this, "이미 입력한 가수입니다", Toast.LENGTH_SHORT).show();
        }
    }

    public void btnViewClick(View v) {
        try(SQLiteDatabase sqlDB = myHelper.getReadableDatabase();
            Cursor dbCursor = sqlDB.rawQuery("select * from groupTBL;", null)) {
            List<Singer> sqlList = new ArrayList<>();
            ArrayAdapter<Singer> adapter = new ArrayAdapter<Singer>(this, android.R.layout.simple_list_item_1, sqlList) {
                @Override
                public View getView(int position, View convertView, ViewGroup parent) {
                    View view = getLayoutInflater().inflate(R.layout.list_item, null, false);
                    TextView textName = (TextView) view.findViewById(R.id.textName);
                    TextView textNumber = (TextView) view.findViewById(R.id.textNumber);
                    textName.setText(getItem(position).getName());
                    textNumber.setText(getItem(position).getNumber());

                    return view;
                }
            };
            listView.setAdapter(adapter);

            while(dbCursor.moveToNext()) {
                //String line = String.format("가수 : %s, 인원 : %s명", dbCursor.getString(0), dbCursor.getString(1));
                adapter.add(new Singer(dbCursor.getString(0), dbCursor.getString(1)));
            }

            //dbCursor.close();
            //sqlDB.close();
        } catch (Exception e) {
            Log.e("Main", e.toString());
        }
    }

    class Singer {
        String name;
        String number;

        public Singer(String name, String number) {
            this.name = name;
            this.number = number;
        }

        public String getName() {
            return name;
        }

        public String getNumber() {
            return number;
        }
    }
}
