package com.jth.example_14_8_p524;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    EditText edtBattery;
    ListView listView;
    List<String> smsList;
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("배터리 상태 체크");

        //edtBattery = (EditText) findViewById(R.id.editBattery);
        listView = (ListView) findViewById(R.id.listView);

        smsList = new ArrayList<>();
        adapter = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_expandable_list_item_1, smsList);
        listView.setAdapter(adapter);


        IntentFilter filter = new IntentFilter();
        filter.addAction("android.provider.Telephony.SMS_RECEIVED");
        registerReceiver(smsReceiver, filter);
    }

    BroadcastReceiver smsReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            Bundle bundle = intent.getExtras();
            if(bundle != null) {
                Object[] pdus = (Object[]) bundle.get("pdus");
                SmsMessage smsMessage = SmsMessage.createFromPdu((byte[]) pdus[0]);
                //Toast.makeText(context, smsMessage.getDisplayMessageBody(), Toast.LENGTH_SHORT).show();
                smsList.add(smsMessage.getDisplayMessageBody());
                adapter.notifyDataSetChanged();
            }
            /*
            String action = intent.getAction();

            int remain = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, 0);
            edtBattery.setText("현재 충전량 : " + remain + " %\n");
            */
        }
    };

    @Override
    protected void onPause() {
        super.onPause();
        //unregisterReceiver(br);
    }

    @Override
    protected void onResume() {
        super.onResume();
        /*
        IntentFilter iFilter = new IntentFilter();
        iFilter.addAction(Intent.ACTION_BATTERY_CHANGED);
        registerReceiver(br, iFilter);
        */
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(smsReceiver);
    }
}
