package com.jth.advancedwidget_6_p247;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Layout;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RadioGroup;
import android.widget.RatingBar;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.TimePicker;

public class MainActivity extends ActionBarActivity {
    LinearLayout reserveLayout, prgLayout;

    RadioGroup radioGroup;
    Button btnComplete, reserveBtn, prgBtn;
    TextView textTime;
    TimePicker timePicker;
    DatePicker datePicker;

    ProgressBar progressBar;
    RatingBar ratingBar;
    SeekBar seekBar;
    TextView textView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        reserveLayout = (LinearLayout) findViewById(R.id.reserveLayout);
        prgLayout = (LinearLayout) findViewById(R.id.prgLayout);

        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        btnComplete = (Button) findViewById(R.id.btnComplete);
        reserveBtn = (Button) findViewById(R.id.reserveBtn);
        prgBtn = (Button) findViewById(R.id.prgBtn);
        textTime = (TextView) findViewById(R.id.textTime);
        timePicker = (TimePicker) findViewById(R.id.timePicker);
        datePicker = (DatePicker) findViewById(R.id.datePicker);

        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        ratingBar = (RatingBar) findViewById(R.id.ratingBar);
        seekBar = (SeekBar) findViewById(R.id.seekBar);
        textView = (TextView) findViewById(R.id.text);

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

        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                int progress = (int)(rating * 20);
                progressBar.setProgress(progress);
                textView.setText("진행율 :" + progress + "%");
                seekBar.setProgress(progress);
            }
        });

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                progressBar.setProgress(progress);
                textView.setText("진행율 :" + progress + "%");
                ratingBar.setRating(progress / 20.0f);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    public void reserveTime (View v) {
        textTime.setText(String.format("예약시간: %4d-%02d-%02d %02d:%02d",
                datePicker.getYear(), datePicker.getMonth(), datePicker.getDayOfMonth(), timePicker.getCurrentHour(), timePicker.getCurrentMinute()));
    }

    public void setReserveBtn (View v) {
        reserveLayout.setVisibility(View.VISIBLE);
        prgLayout.setVisibility(View.INVISIBLE);
    }

    public void setPrgBtn (View v) {
        reserveLayout.setVisibility(View.INVISIBLE);
        prgLayout.setVisibility(View.VISIBLE);
    }
}
