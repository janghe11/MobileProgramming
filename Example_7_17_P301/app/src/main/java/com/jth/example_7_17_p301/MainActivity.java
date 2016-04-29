package com.jth.example_7_17_p301;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    TextView text1, text2;
    View dialogView;
    EditText editName, editEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        text1 = (TextView) findViewById(R.id.text1);
        text2 = (TextView) findViewById(R.id.text2);
    }

    public void openDialog(View v) {
        new MyDialogBuiler(this).show();
    }

    class MyDialogBuiler extends AlertDialog.Builder {
        //String[] versionArray = {"젤리빈", "킷캣", "롤리팝"};
        //String selectedItem = versionArray[0];
        public MyDialogBuiler(Context context) {
            super(context);
        }
        @Override
        public AlertDialog create() {
            setTitle("제목입니다");
            dialogView = View.inflate(MainActivity.this, R.layout.dialog, null);
            /*
            * setSingleChoiceItems(versionArray, 0, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    selectedItem = versionArray[which];
                }
            });*/
            /*setItems(versionArray, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    text1.setText(versionArray[which]);
                }
            });*/
            //setMessage("이곳이 내용입니다");
            setIcon(R.mipmap.ic_launcher);
            setView(dialogView);
            editName = (EditText) dialogView.findViewById(R.id.editName);
            editEmail = (EditText) dialogView.findViewById(R.id.editEmail);
            setPositiveButton("확인", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    //Log.d("DIALOG", String.valueOf(which));
                    text1.setText(editName.getText());
                    text2.setText(editEmail.getText());
                }
            });
            setNegativeButton("취소", null);
            return super.create();
        }
    }
}
