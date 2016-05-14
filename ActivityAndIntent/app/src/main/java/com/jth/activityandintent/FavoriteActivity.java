package com.jth.activityandintent;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

/**
 * Created by jang on 16. 5. 13.
 */
public class FavoriteActivity extends AppCompatActivity {

    RadioGroup radioFv;
    RadioButton btnBNS, btnCivilWar, btnGok, btnHong;

    public static final String FV_RADIO = "FavoriteActivity.radio";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.favorite);
        setTitle("ActivityAndIntent_2반_장태희");

        radioFv = (RadioGroup) findViewById(R.id.radioFv);
        btnBNS = (RadioButton) findViewById(R.id.btnBNS);
        btnCivilWar = (RadioButton) findViewById(R.id.btnCivilWar);
        btnGok = (RadioButton) findViewById(R.id.btnGok);
        btnHong = (RadioButton) findViewById(R.id.btnHong);

    }

    public void favorite(View v) {
        Intent fvIntent = new Intent();
        if(btnBNS.isChecked()) {
            fvIntent.putExtra(FV_RADIO, btnBNS.getText().toString());
            setResult(RESULT_OK, fvIntent);
        } else if(btnCivilWar.isChecked()) {
            fvIntent.putExtra(FV_RADIO, btnCivilWar.getText().toString());
            setResult(RESULT_OK, fvIntent);
        } else if(btnGok.isChecked()) {
            fvIntent.putExtra(FV_RADIO, btnGok.getText().toString());
            setResult(RESULT_OK, fvIntent);
        } else if(btnHong.isChecked()) {
            fvIntent.putExtra(FV_RADIO, btnHong.getText().toString());
            setResult(RESULT_OK, fvIntent);
        }
        finish();
    }

    public void fvCancel(View v) {
        finish();
    }

}
