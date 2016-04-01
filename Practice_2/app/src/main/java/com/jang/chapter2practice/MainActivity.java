package com.jang.chapter2practice;

import android.app.ActionBar;
import android.content.Intent;
import android.net.Uri;
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
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle("좀 그럴듯한 응용 프로그램");

        // 글자 나타내기 버튼 객체 생성
        Button appearBtn = (Button)findViewById(R.id.appearBtn);
        appearBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText editText = (EditText)findViewById(R.id.editText);
                String saveEditText = editText.getText().toString();
                Toast.makeText(MainActivity.this, saveEditText, Toast.LENGTH_SHORT).show();
            }
        });

        // 홈페이지 열기 버튼 객체 생성
        Button openPageBtn = (Button)findViewById(R.id.openPageBtn);
        openPageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText editText = (EditText)findViewById(R.id.editText);
                String saveEditText = editText.getText().toString();
                Intent mIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(saveEditText));
                startActivity(mIntent);
            }
        });

        // 라디오 그룹 생성
        RadioGroup radioGroup = (RadioGroup)findViewById(R.id.radioGroup);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            ImageView imageView = (ImageView)findViewById(R.id.image);
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.kitkatRadio) {
                    imageView.setImageResource(R.drawable.android_kitkat);
                }
                else {
                    imageView.setImageResource(R.drawable.android_lollipop);
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
}
