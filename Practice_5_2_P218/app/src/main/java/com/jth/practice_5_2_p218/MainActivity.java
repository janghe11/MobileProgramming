package com.jth.practice_5_2_p218;

import android.app.ActionBar;
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
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends ActionBarActivity {
    EditText edit1, edit2;
    Button btnAdd, btnSub, btnMul, btnDiv;
    TextView textResult;
    String num1, num2;
    Integer result;
    Button[] numButtons = new Button[10];
    Integer[] numBtnIDs = {R.id.btnNum0, R.id.btnNum1, R.id.btnNum2, R.id.btnNum3, R.id.btnNum4, R.id.btnNum5,
        R.id.btnNum6, R.id.btnNum7, R.id.btnNum8, R.id.btnNum9};
    int i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("2반_2012041067_장태희");

        edit1 = (EditText) findViewById(R.id.edit1);
        edit2 = (EditText) findViewById(R.id.edit2);
        btnAdd = (Button) findViewById(R.id.btnAdd);
        btnSub = (Button) findViewById(R.id.btnSub);
        btnMul = (Button) findViewById(R.id.btnMul);
        btnDiv = (Button) findViewById(R.id.btnDiv);

        textResult = (TextView) findViewById(R.id.textResult);

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
    }

    public int calculating (View v) {
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
            default:
                Toast.makeText(getApplicationContext(), "계산 오류거나 0으로 나눌 수 없습니다", Toast.LENGTH_SHORT).show();
        }

        return 0;
    }
}
