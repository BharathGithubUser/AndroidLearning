package com.belivnat.tasks;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

public class SplashActivity extends BaseActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    @Override
    public void onResume() {
        super.onResume();
        Log.d("SplashActivity::","onResume()");
    }
    @Override
    public void onPause() {
        super.onPause();
        Log.d("SplashActivity::","onPause()");
    }
    @Override
    public void onStop() {
        super.onStop();
        Log.d("SplashActivity::","onStop()");
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("SplashActivity::","onDestroy()");
    }
}
