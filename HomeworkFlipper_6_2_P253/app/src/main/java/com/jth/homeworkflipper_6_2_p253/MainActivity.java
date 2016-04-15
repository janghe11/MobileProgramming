package com.jth.homeworkflipper_6_2_p253;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MotionEvent;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RadioGroup;
import android.widget.RatingBar;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;
import android.widget.ViewFlipper;

public class MainActivity extends ActionBarActivity {
    LinearLayout reserveLayout, progressLayout;
    EditText edit1, edit2;
    Button btnAdd, btnSub, btnMul, btnDiv, btnComplete, reserveBtn, prgBtn;
    Button[] numButtons = new Button[10];
    RadioGroup radioGroup;
    TextView textResult, textTime, textView;
    String num1, num2;
    Integer result;
    Integer[] numBtnIDs = {R.id.btnNum0, R.id.btnNum1, R.id.btnNum2, R.id.btnNum3, R.id.btnNum4, R.id.btnNum5,
            R.id.btnNum6, R.id.btnNum7, R.id.btnNum8, R.id.btnNum9};
    TimePicker timePicker;
    DatePicker datePicker;
    ProgressBar progressBar;
    RatingBar ratingBar;
    SeekBar seekBar;
    ViewFlipper viewFlipper;

    int i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewFlipper = (ViewFlipper) findViewById(R.id.viewFlipper);

        edit1 = (EditText) findViewById(R.id.edit1);
        edit2 = (EditText) findViewById(R.id.edit2);
        textResult = (TextView) findViewById(R.id.textResult);
        reserveLayout = (LinearLayout) findViewById(R.id.reserveLayout);
        progressLayout = (LinearLayout) findViewById(R.id.progressLayout);

        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        btnComplete = (Button) findViewById(R.id.btnComplete);
        textTime = (TextView) findViewById(R.id.textTime);
        timePicker = (TimePicker) findViewById(R.id.timePicker);
        datePicker = (DatePicker) findViewById(R.id.datePicker);

        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        ratingBar = (RatingBar) findViewById(R.id.ratingBar);
        seekBar = (SeekBar) findViewById(R.id.seekBar);
        textView = (TextView) findViewById(R.id.text);

        for (i = 0; i < numBtnIDs.length; i++) {
            numButtons[i] = (Button) findViewById(numBtnIDs[i]);
        }

        for (i = 0; i < numBtnIDs.length; i++) {
            final int index;
            index = i;

            numButtons[index].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (edit1.isFocused() == true) {
                        num1 = edit1.getText().toString() + numButtons[index].getText().toString();
                        edit1.setText(num1);
                    } else if (edit2.isFocused() == true) {
                        num2 = edit2.getText().toString() + numButtons[index].getText().toString();
                        edit2.setText(num2);
                    } else {
                        Toast.makeText(getApplicationContext(), "먼저 에디트텍스트를 선택하세요", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(checkedId == R.id.radioBtnDate) {
                    timePicker.setVisibility(View.GONE);
                    datePicker.setVisibility(View.VISIBLE);
                } else if(checkedId == R.id.radioBtnTime) {
                    timePicker.setVisibility(View.VISIBLE);
                    datePicker.setVisibility(View.GONE);
                }
            }
        });

        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                if(fromUser) {
                    int progress = (int)(rating * 20);
                    progressBar.setProgress(progress);
                    textView.setText("진행율 :" + progress + "%");
                    seekBar.setProgress(progress);
                }
            }
        });

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if(fromUser) {
                    progressBar.setProgress(progress);
                    textView.setText("진행율 :" + progress + "%");
                    ratingBar.setRating(progress / 20.0f);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });
    }

    public void calculating (View v) {
        try{
            num1 = edit1.getText().toString();
            num2 = edit2.getText().toString();

            switch (v.getId()) {
                case R.id.btnAdd:
                    result = Integer.parseInt(num1) + Integer.parseInt(num2);
                    textResult.setText("계산 결과 :" + result.toString());
                    break;
                case R.id.btnSub:
                    result = Integer.parseInt(num1) - Integer.parseInt(num2);
                    textResult.setText("계산 결과 :" + result.toString());
                    break;
                case R.id.btnMul:
                    result = Integer.parseInt(num1) * Integer.parseInt(num2);
                    textResult.setText("계산 결과 :" + result.toString());
                    break;
                case R.id.btnDiv:
                    result = Integer.parseInt(num1) / Integer.parseInt(num2);
                    textResult.setText("계산 결과 :" + result.toString());
                    break;
            }
        } catch (NumberFormatException e) {
            Toast.makeText(getApplicationContext(), "숫자만 넣어주시거나 빈칸은 계산할 수 없습니다.", Toast.LENGTH_SHORT).show();
        }
    }

    public void reserveTime (View v) {
        textTime.setText(String.format("예약시간: %4d-%02d-%02d %02d:%02d",
                datePicker.getYear(), datePicker.getMonth(), datePicker.getDayOfMonth(), timePicker.getCurrentHour(), timePicker.getCurrentMinute()));
    }

    public void showPrev(View v) {
        viewFlipper.showPrevious();
    }

    public void showNext(View v) {
        viewFlipper.showNext();
    }
}
