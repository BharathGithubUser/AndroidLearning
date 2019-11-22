package com.belivnat.tasks.model;

import android.content.Intent;

public class HomeMenuOptions {
    String name;
    String imgUrl;
    Intent intent;

    public HomeMenuOptions(String name, String imgUrl, Intent intent) {
        this.name = name;
        this.imgUrl = imgUrl;
        this.intent = intent;
    }

    public String getName() {
        return name;
    }

    public Intent getIntent() {
        return intent;
    }

    public void setIntent(Intent intent) {
        this.intent = intent;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }
}
