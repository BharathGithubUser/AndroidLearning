package com.belivnat.tasks;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

public abstract class BaseActivity extends AppCompatActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("BASEACTIVITY::","onCreate()");
    }
    @Override
    public void onResume() {
        super.onResume();
        Log.d("BASEACTIVITY::","onResume()");
    }
    @Override
    public void onPause() {
        super.onPause();
        Log.d("BASEACTIVITY::","onPause()");
    }
    @Override
    public void onStop() {
            super.onStop();
            Log.d("BASEACTIVITY::","onStop()");
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("BASEACTIVITY::","onDestroy()");
    }
}
