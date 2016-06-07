package com.jth.lastproject;

import android.content.Intent;
import android.database.Cursor;
import android.provider.ContactsContract;
import android.provider.Telephony;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    static final String ADDR_ID = "com.jth.listview.id";
    static final String DISP_NAME = "com.jth.listview.displayName";
    String[] addrSet = new String[] {"_id", "display_name"};
    List<String> addrList;
    ListView listName;
    ArrayAdapter<String> adapterName;
    Intent addrIntent;
    String addrId, addrName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("LastProject_2반_장태희");

        listName = (ListView) findViewById(R.id.listName);

        listName.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                try(Cursor addrCursor = getContentResolver().query(ContactsContract.Contacts.CONTENT_URI, addrSet, null, null, null)) {
                    addrCursor.moveToPosition(position);
                    addrId = addrCursor.getString(0);

                    addrIntent = new Intent(MainActivity.this, AddrActivity.class);
                    addrIntent.putExtra(DISP_NAME, listName.getItemAtPosition(position).toString());
                    addrIntent.putExtra(ADDR_ID, addrId);
                    startActivity(addrIntent);
                } catch (Exception e) {
                    Log.d("Main", e.toString());
                }
            }
        });
    }

    public void getAddrList(View v) {
        try(Cursor addrCursor = getContentResolver().query(ContactsContract.Contacts.CONTENT_URI, addrSet, null, null, null)) {
            if(addrCursor != null) {
                addrList = new ArrayList<>();

                while (addrCursor.moveToNext()) {
                    addrId = addrCursor.getString(0);
                    addrName = addrCursor.getString(1);
                    addrList.add(String.format("%s %s\n", addrId, addrName));
                }

                adapterName = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, addrList);
                listName.setAdapter(adapterName);
            }
        } catch (SecurityException e) {
            Log.d("Main", e.toString());
        } catch (NullPointerException e) {
            Log.d("Main", e.toString());
        } catch (Exception e) {
            Log.d("Main", e.toString());
        }
    }

}
