package com.belivnat.tasks.modules.news.presenter;

import com.belivnat.tasks.modules.news.model.NewsApiModel;
import com.belivnat.tasks.modules.news.view.INewsView;
import com.belivnat.tasks.networkingmodel.NewsModel;

import retrofit2.Response;

public class NewsPresenter implements  INewsPresenter{
    INewsView newsView;
    NewsApiModel newsModel;
    public NewsPresenter(INewsView newsPresenter) {
        this.newsView = newsPresenter;
        newsModel = new NewsApiModel(this);
    }

    @Override
    public void presenterSuccessResponse(Response<NewsModel> response) {
        newsView.onSuccessResponse(response);
    }

    @Override
    public void presenterFailureResponse() {
        newsView.onFailureResponse();
    }
}
