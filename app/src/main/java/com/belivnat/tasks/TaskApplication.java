package com.belivnat.tasks;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.belivnat.tasks.utils.Constants.BASE_URL;

public class TaskApplication extends Application implements Application.ActivityLifecycleCallbacks {
    public static Retrofit retrofit = null;

    @Override
    public void onCreate() {
        super.onCreate();
    }

    public static Retrofit getRetrofitInstance() {
        Gson gson = new GsonBuilder().create();
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();

        }
        return retrofit;
    }

    @Override
    public void onActivityCreated(@NonNull Activity activity, @Nullable Bundle bundle) {
        Log.d("Application::", "onActivityCreated::" + activity);
    }

    @Override
    public void onActivityStarted(@NonNull Activity activity) {
        Log.d("Application::", "onActivityStarted::" + activity);
    }

    @Override
    public void onActivityResumed(@NonNull Activity activity) {
        Log.d("Application::", "onActivityResumed::" + activity);
    }

    @Override
    public void onActivityPaused(@NonNull Activity activity) {
        Log.d("Application::", "onActivityPaused::" + activity);
    }

    @Override
    public void onActivityStopped(@NonNull Activity activity) {
        Log.d("Application::", "onActivityStopped::" + activity);
    }

    @Override
    public void onActivitySaveInstanceState(@NonNull Activity activity, @NonNull Bundle bundle) {
        Log.d("Application::", "onActivitySaveInstanceState::" + activity);
    }

    @Override
    public void onActivityDestroyed(@NonNull Activity activity) {
        Log.d("Application::", "onActivityDestroyed::" + activity);
    }
}
