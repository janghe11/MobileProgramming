package com.jth.practice_10_1_p386;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.ViewFlipper;

public class MainActivity extends AppCompatActivity {

    EditText editString;

    public static final int REQUEST_CODE_ONE = 1;
    public static final String MESSAGE = "MainActivity.MESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("메인 액티비티");

        editString = (EditText) findViewById(R.id.editString);
    }


    public void startActivity(View v) {
        Intent intent = new Intent(this, SecondActivity.class);
        intent.putExtra(MESSAGE, editString.getText().toString());
        startActivityForResult(intent, REQUEST_CODE_ONE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode == RESULT_OK) {
            String message = data.getStringExtra(SecondActivity.KEY);
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        }
    }
}
