package com.jth.activityandintent;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    RadioGroup radioMain;
    RadioButton btnLogin, btnFavorite, btnDial;

    public static final int REQUEST_CODE_ONE = 1;
    public static final int REQUEST_CODE_TWO = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("ActivityAndIntent_2반_장태희");

        radioMain = (RadioGroup) findViewById(R.id.radioMain);
        btnLogin = (RadioButton) findViewById(R.id.btnLogin);
        btnFavorite = (RadioButton) findViewById(R.id.btnFavorite);
        btnDial = (RadioButton) findViewById(R.id.btnDial);

        /*
        radioMain.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                //Intent mainIntent = new Intent();
                switch (checkedId) {
                    case R.id.btnLogin:

                        break;
                    case R.id.btnFavorite:
                        startActivityForResult(mainIntent, REQUEST_CODE_TWO);
                        break;
                    case R.id.btnDial:
                        mainIntent.addCategory(Intent.ACTION_DIAL);
                        //Intent mcIntent = new Intent(Intent.ACTION_DIAL);
                        startActivity(mainIntent);
                        break;
                    default:
                }
            }
        });
        */
    }

    public void startActivity(View v) {
        if(btnLogin.isChecked()) {
            Intent mainIntent = new Intent(this, LoginActivity.class);
            startActivityForResult(mainIntent, REQUEST_CODE_ONE);
        } else if(btnFavorite.isChecked()) {
            Intent mainIntent = new Intent(this, FavoriteActivity.class);
            startActivityForResult(mainIntent, REQUEST_CODE_TWO);
        } else if(btnDial.isChecked()) {
            Intent mainIntent = new Intent(Intent.ACTION_DIAL);
            startActivity(mainIntent);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == REQUEST_CODE_ONE) {
            if(resultCode == RESULT_OK) {
                String messageId = data.getStringExtra(LoginActivity.LOGIN_ID);
                String messagePw = data.getStringExtra(LoginActivity.LOGIN_PW);
                Toast.makeText(this, " Login ID : " + messageId + " , Password : " + messagePw, Toast.LENGTH_SHORT).show();
            }
        } else if(requestCode == REQUEST_CODE_TWO) {
            if(resultCode == RESULT_OK) {
                String messageRadio = data.getStringExtra(FavoriteActivity.FV_RADIO);
                Toast.makeText(this, "당신은 " + messageRadio + "을(를) 좋아합니다.", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
