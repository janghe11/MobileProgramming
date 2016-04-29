package com.jth.alertdialogimageviewer;

import android.app.AlertDialog;
import android.content.Context;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    RadioGroup radioGroup;
    String dialogName;
    int imgName = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("AlertDialogImageViewer_2반_장태희");
        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
    }

    public void openDialog(View v) {
            new MyDialogBuilder(this).show();
    }

    class MyDialogBuilder extends AlertDialog.Builder {
        public MyDialogBuilder(Context context) {
            super(context);
        }
        @Override
        public AlertDialog create() {
            setTitle(dialogName);
            ImageView imageView = new ImageView(MainActivity.this);
            radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup group, int checkedId) {
                    switch (checkedId) {
                        case R.id.radioDog:
                            dialogName = "강아지";
                            imgName = R.drawable.img_dog;
                            break;
                        case R.id.radioCat:
                            dialogName = "고양이";
                            imgName = R.drawable.img_cat;
                            break;
                        case R.id.radioRabbit:
                            dialogName = "토끼";
                            imgName = R.drawable.img_rabbit;
                            break;
                        case R.id.radioHorse:
                            dialogName = "말";
                            imgName = R.drawable.img_horse;
                            break;
                        default:
                    }
                }
            });
            imageView.setImageResource(imgName);
            setView(imageView);
            setPositiveButton("닫기", null);
            return super.create();
        }
    }
}
