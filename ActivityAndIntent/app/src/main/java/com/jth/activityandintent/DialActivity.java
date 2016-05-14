package com.jth.activityandintent;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * Created by jang on 16. 5. 13.
 */
public class DialActivity extends AppCompatActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dial);
        setTitle("ActivityAndIntent_2반_장태희");
    }

    public void dialCancel(View v) {
        finish();
    }
}
