package com.jth.practice_6_2_p237;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.TimePicker;

import java.sql.Time;

public class MainActivity extends ActionBarActivity {
    RadioGroup radioGroup;
    Button btnComplete;
    TextView textTime;
    TimePicker timePicker;
    DatePicker datePicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        btnComplete = (Button) findViewById(R.id.btnComplete);
        textTime = (TextView) findViewById(R.id.textTime);
        timePicker = (TimePicker) findViewById(R.id.timePicker);
        datePicker = (DatePicker) findViewById(R.id.datePicker);

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(checkedId == R.id.radioBtnDate) {
                    timePicker.setVisibility(View.INVISIBLE);
                    datePicker.setVisibility(View.VISIBLE);
                } else if(checkedId == R.id.radioBtnTime) {
                    timePicker.setVisibility(View.VISIBLE);
                    datePicker.setVisibility(View.INVISIBLE );
                }
            }
        });
    }

    public void reserveTime (View v) {
        textTime.setText(String.format("예약시간: %4d-%02d-%02d %02d:%02d",
                datePicker.getYear(), datePicker.getMonth(), datePicker.getDayOfMonth(), timePicker.getCurrentHour(), timePicker.getCurrentMinute()));
    }
}
