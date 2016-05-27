package com.jth.batterystatusandsmsreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    EditText editBatt;
    ListView listView;
    List<String> smsList;
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("BatteryStatusAndSmsReceiver_2반_장태희");

        editBatt = (EditText) findViewById(R.id.editBatt);
        listView = (ListView) findViewById(R.id.listView);

        smsList = new ArrayList<>();
        adapter = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_single_choice, smsList);
        listView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        listView.setAdapter(adapter);

        IntentFilter smsFilter = new IntentFilter();
        smsFilter.addAction("android.provider.Telephony.SMS_RECEIVED");
        registerReceiver(smsReceiver, smsFilter);
    }

    BroadcastReceiver smsReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            Bundle bundle = intent.getExtras();
            if(bundle != null) {
                Object[] pdus = (Object[]) bundle.get("pdus");
                SmsMessage smsMessage = SmsMessage.createFromPdu((byte[]) pdus[0]);
                smsList.add(smsMessage.getDisplayMessageBody());
                adapter.notifyDataSetChanged();
            }
        }
    };

    BroadcastReceiver battReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            int remain = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, 0);
            editBatt.setText("현재 충전량 : " + remain + " %\n");
        }
    };

    public void removeMsg (View v) throws Exception {
        try {
            smsList.remove(listView.getCheckedItemPosition());
            adapter.notifyDataSetChanged();
        } catch (Exception e) {
            Log.d("JthDegug", e.toString());
            Toast.makeText(this, "선택 된 메시지가 없습니다.\n 메시지 선택 후 다시 시도하여 주세요.", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(battReceiver);
    }

    @Override
    protected void onResume() {
        super.onResume();
        IntentFilter battFileter = new IntentFilter();
        battFileter.addAction(Intent.ACTION_BATTERY_CHANGED);
        registerReceiver(battReceiver, battFileter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(smsReceiver);
    }
}
