package com.jth.practice_10_1_p386;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by jang on 16. 5. 13.
 */
public class SecondActivity extends AppCompatActivity {

    EditText edit2;
    public static final String KEY = "SecondActivity.KEY";

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.second);
        setTitle("Second 액티비티");

        edit2 = (EditText) findViewById(R.id.edit2);

        Intent intent = getIntent();
        String value = intent.getStringExtra(MainActivity.MESSAGE);
        Toast.makeText(this, value, Toast.LENGTH_SHORT).show();
    }

    public void finish(View v) {
        Intent intent = new Intent();
        intent.putExtra(KEY, edit2.getText().toString());
        setResult(RESULT_OK, intent);
        finish();
    }
}
