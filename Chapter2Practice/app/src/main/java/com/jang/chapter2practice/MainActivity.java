package com.jang.chapter2practice;

import android.app.ActionBar;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;

public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 최상단 EditText 부분 객체 생성
        EditText editText = (EditText)findViewById(R.id.editText);

        // 글자 나타내기 버튼 객체 생성
        Button appearBtn = (Button)findViewById(R.id.appearBtn);

        // 홈페이지 열기 버튼 객체 생성
        Button openPageBtn = (Button)findViewById(R.id.openPageBtn);

        // 킷캣 라디오 버튼 객체 생성
        RadioButton kitkatRadio = (RadioButton)findViewById(R.id.kitkatRadio);

        // 롤리팝 라디오 버튼 객체 생성
        RadioButton lolliRadio = (RadioButton)findViewById(R.id.lolliRadio);

        // 이미지 뷰 객체 생성
        ImageView imageView = (ImageView)findViewById(R.id.image);
    }
}
