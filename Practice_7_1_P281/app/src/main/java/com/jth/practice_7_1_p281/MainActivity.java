package com.jth.practice_7_1_p281;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MainActivity extends ActionBarActivity {
    RelativeLayout layout;
    TextView text1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        layout = (RelativeLayout) findViewById(R.id.layout);
        text1 = (TextView) findViewById(R.id.text1);
        registerForContextMenu(text1);
        registerForContextMenu(layout);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu1, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return changeColor(item);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        if(v.getId() == R.id.text1) {
            getMenuInflater().inflate(R.menu.menu2, menu);
        } else if(v.getId() == R.id.layout) {
            getMenuInflater().inflate(R.menu.menu1, menu);
        }
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.itemBig:
                text1.setTextSize(20);
                break;
            case R.id.itemSmall:
                text1.setTextSize(10);
                break;
            case R.id.itemRed:
            case R.id.itemGreen:
            case R.id.itemBlue:
                return changeColor(item);
            default:
                return false;
        }

        return true;
    }

    private boolean changeColor(MenuItem item) {
        if(item.getItemId() == R.id.itemRed) {
            layout.setBackgroundColor(Color.RED);
        } else if(item.getItemId() == R.id.itemGreen) {
            layout.setBackgroundColor(Color.GREEN);
        } else if(item.getItemId() == R.id.itemBlue) {
            layout.setBackgroundColor(Color.BLUE);
        } else
            return false;

        return true;
    }
}
