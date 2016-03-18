package com.jth.simplepetchoice;

import android.app.Notification;
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
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends ActionBarActivity {
    //CheckBox checkBox;
    Switch switchBtn;
    TextView text2;
    RadioGroup radioGroup;
    RadioButton radioBtnDog, radioBtnCat, radioBtnRab;
    //Button btnOK;
    Button btnExit, btnFirst;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //checkBox = (CheckBox) findViewById(R.id.checkbox);
        switchBtn = (Switch) findViewById(R.id.switchBtn);
        text2 = (TextView) findViewById(R.id.text2);
        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        radioBtnDog = (RadioButton) findViewById(R.id.radioBtnDog);
        radioBtnCat = (RadioButton) findViewById(R.id.radioBtnCat);
        radioBtnRab = (RadioButton) findViewById(R.id.radioBtnRab);
        //btnOK = (Button) findViewById(R.id.btnOK);
        btnExit = (Button) findViewById(R.id.btnExit);
        btnFirst = (Button) findViewById(R.id.btnFirst);
        imageView = (ImageView) findViewById(R.id.image);

        switchBtn.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    text2.setVisibility(View.VISIBLE);
                    radioGroup.setVisibility(View.VISIBLE);
                    //btnOK.setVisibility(View.VISIBLE);
                    imageView.setVisibility(View.VISIBLE);
                } else {
                    text2.setVisibility(View.INVISIBLE);
                    radioGroup.setVisibility(View.INVISIBLE);
                    //btnOK.setVisibility(View.INVISIBLE);
                    imageView.setVisibility(View.INVISIBLE);
                }
            }
        });

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.radioBtnDog:
                        imageView.setImageResource(R.drawable.imgdog);
                        imageView.setAdjustViewBounds(true);
                        break;
                    case R.id.radioBtnCat:
                        imageView.setImageResource(R.drawable.imgcat);
                        imageView.setAdjustViewBounds(true);
                        break;
                    case R.id.radioBtnRab:
                        imageView.setImageResource(R.drawable.imgrab);
                        imageView.setAdjustViewBounds(true);
                        break;
                    default:
                        Toast.makeText(getApplicationContext(), "동물 먼저 선택하세요", Toast.LENGTH_SHORT).show();
                }
            }
        });
/*
        btnOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (radioGroup.getCheckedRadioButtonId()) {
                    case R.id.radioBtnDog:
                        imageView.setImageResource(R.drawable.imgdog);
                        break;
                    case R.id.radioBtnCat:
                        imageView.setImageResource(R.drawable.imgcat);
                        break;
                    case R.id.radioRab:
                        imageView.setImageResource(R.drawable.imgrab);
                        break;
                    default:
                        Toast.makeText(getApplicationContext(), "동물 먼저 선택하세요", Toast.LENGTH_SHORT).show();
                }
            }
        });
*/

        btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btnFirst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchBtn.setChecked(false);
                radioGroup.clearCheck();
                imageView.setImageResource(0);
            }
        });
    }
}
