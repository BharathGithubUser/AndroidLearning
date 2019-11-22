package com.belivnat.tasks.modules.news.model;

import com.belivnat.tasks.TaskApplication;
import com.belivnat.tasks.modules.news.presenter.INewsPresenter;
import com.belivnat.tasks.modules.news.view.NewsActivity;
import com.belivnat.tasks.modules.news.view.NewsAdapter;
import com.belivnat.tasks.networkingmodel.ApiInterface;
import com.belivnat.tasks.networkingmodel.NewsModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewsApiModel implements INewsModel {
    INewsPresenter newsPresenter;
    public NewsApiModel(INewsPresenter iNewsPresenter) {
        this.newsPresenter = iNewsPresenter;
        callApi();
    }
    public void callApi() {
        TaskApplication.getRetrofitInstance().create(ApiInterface.class).getNews().enqueue(new Callback<NewsModel>() {
            @Override
            public void onResponse(Call<NewsModel> call, Response<NewsModel> response) {
               newsPresenter.presenterSuccessResponse(response);
            }

            @Override
            public void onFailure(Call<NewsModel> call, Throwable t) {
                newsPresenter.presenterFailureResponse();
            }
        });
    }
}
