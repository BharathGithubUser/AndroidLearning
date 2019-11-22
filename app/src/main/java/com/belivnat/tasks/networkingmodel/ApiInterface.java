package com.belivnat.tasks.networkingmodel;

import com.belivnat.tasks.utils.Constants;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {
    @GET(Constants.NEWS_END_POINT)
    Call<NewsModel> getNews();
}
