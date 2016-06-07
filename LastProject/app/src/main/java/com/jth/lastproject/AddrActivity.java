package com.jth.lastproject;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jang on 16. 6. 4.
 */
public class AddrActivity extends AppCompatActivity {
    Intent addrIntent;
    String addrId, addrName, addrPhone, addrEmail;
    TextView textAddrName;

    List<String> addrList;
    ListView listPhone, listEmail;
    ScrollView scrollView;
    ArrayAdapter<String> adapterPhone, adapterEmail;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        try {
            setContentView(R.layout.addr_activity);
            setTitle("LastProject_2반_장태희");

            listPhone = (ListView) findViewById(R.id.listPhone);
            listEmail = (ListView) findViewById(R.id.listEmail);
            textAddrName = (TextView) findViewById(R.id.textAddrName);


            addrIntent = getIntent();
            addrName = addrIntent.getStringExtra(MainActivity.DISP_NAME);
            addrId = addrIntent.getStringExtra(MainActivity.ADDR_ID);
            textAddrName.setText(addrName);
            Log.d("Main", "DISP_NAME : " + addrId);

            Cursor telCursor = getContentResolver().query(
                    ContactsContract.Data.CONTENT_URI,
                    new String[]{"data1"},
                    "contact_id=? and mimetype='vnd.android.cursor.item/phone_v2'",
                    new String[]{addrId},
                    null
            );

            if (telCursor != null) {
                addrList = new ArrayList<>();

                while (telCursor.moveToNext()) {
                    addrPhone = telCursor.getString(0);
                    addrList.add(String.format("%s\n", addrPhone));
                }

                adapterPhone = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, addrList);
                listPhone.setAdapter(adapterPhone);
            }

            telCursor.close();

            Cursor emailCursor = getContentResolver().query(
                    ContactsContract.Data.CONTENT_URI,
                    new String[]{"data1"},
                    "contact_id=? and mimetype='vnd.android.cursor.item/email_v2'",
                    new String[]{addrId},
                    null
            );

            if (emailCursor != null) {
                addrList = new ArrayList<>();

                while (emailCursor.moveToNext()) {
                    addrEmail = emailCursor.getString(0);
                    addrList.add(String.format("%s\n", addrEmail));
                }

                adapterEmail = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, addrList);
                listEmail.setAdapter(adapterEmail);
            }

            emailCursor.close();
        } catch (Exception e) {
            Log.e("Main", e.toString());
        }
    }

    public void btnClose(View v) {
        finish();
    }
}
