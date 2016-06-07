package com.jth.example_14_8_p524;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.BatteryManager;

/**
 * Created by jang on 16. 6. 4.
 */
public class BatteryChangeReceiver extends BroadcastReceiver {

    OnBatteryChangeListener mOnBatteryChangeListener;

    public void setOnBatteryChangeListener(OnBatteryChangeListener listener) {
        mOnBatteryChangeListener = listener;
    }

    public interface OnBatteryChangeListener {
        void onReceive(int remain);
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        int remain = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, 0);
        mOnBatteryChangeListener.onReceive(remain);
    }
}
