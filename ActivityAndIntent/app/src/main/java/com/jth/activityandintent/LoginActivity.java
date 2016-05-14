package com.jth.activityandintent;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

/**
 * Created by jang on 16. 5. 13.
 */
public class LoginActivity extends AppCompatActivity {

    EditText editId, editPasswd;
    public static final String LOGIN_ID = "LoginActivity.login";
    public static final String LOGIN_PW = "LoginActivity.login";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        setTitle("ActivityAndIntent_2반_장태희");

        editId = (EditText) findViewById(R.id.editId);
        editPasswd = (EditText) findViewById(R.id.editPasswd);
    }

    public void login(View v) {
        Intent lgIntent = new Intent();
        lgIntent.putExtra(LOGIN_ID, editId.getText().toString());
        lgIntent.putExtra(LOGIN_PW, editPasswd.getText().toString());
        setResult(RESULT_OK, lgIntent);
        finish();
    }

    public void loginCancel(View v) {
        finish();
    }
}
