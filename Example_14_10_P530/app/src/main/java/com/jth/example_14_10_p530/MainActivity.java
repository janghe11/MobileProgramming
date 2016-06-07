package com.jth.example_14_10_p530;

import android.database.Cursor;
import android.provider.CallLog;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telecom.Call;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    ListView listView;
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView) findViewById(R.id.listView);
    }

    public void getCallHistory(View v) {
        //String[] callSet = new String[] {CallLog.Calls.DATE, CallLog.Calls.TYPE, CallLog.Calls.NUMBER, CallLog.Calls.DURATION };
        String[] callSet = new String[] {"_id", "display_name"};
        try {
            //Cursor c = getContentResolver().query(CallLog.Calls.CONTENT_URI, callSet, null, null, null);
            Cursor c = getContentResolver().query(ContactsContract.Contacts.CONTENT_URI, callSet, null, null, null);
            if(c != null) {
                List<String> callList = new ArrayList<>();
                while(c.moveToNext()) {
                    /*
                    Calendar cal = Calendar.getInstance();
                    cal.setTimeInMillis(c.getLong(0));
                    callList.add(String.format("%1$tF %1$tT, Type=%2$d\n%3$s (%4$d초)", cal, c.getInt(1), c.getString(2), c.getInt(3)));
                    */
                    StringBuilder stringBuilder = new StringBuilder();
                    String id = c.getString(0);
                    String name = c.getString(1);
                    //callList.add(String.format("_id : %s, name : %s\n", id, name));
                    stringBuilder.append(String.format("%s %s\n", id, name));

                    Cursor telCursor = getContentResolver().query(
                            ContactsContract.Data.CONTENT_URI,
                            new String[] {"data1"},
                            "contact_id=? and mimetype='vnd.android.cursor.item/phone_v2'",
                            new String[] {id},
                            null
                    );

                    while(telCursor.moveToNext()) {
                        stringBuilder.append(String.format("%s\n", telCursor.getString(0)));
                    }
                    telCursor.close();

                    Cursor emailCursor = getContentResolver().query(
                            ContactsContract.Data.CONTENT_URI,
                            new String[] {"data1"},
                            "contact_id=? and mimetype='vnd.android.cursor.item/email_v2'",
                            new String[] {id},
                            null
                    );
                    while(emailCursor.moveToNext()) {
                        stringBuilder.append(String.format("%s\n", emailCursor.getString(0)));
                    }
                    emailCursor.close();

                    callList.add(stringBuilder.toString());
                }
                c.close();
                adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, callList);
                listView.setAdapter(adapter);
            }
        } catch (SecurityException e) {
            Log.e("Main", e.toString());
        }
    }
}
