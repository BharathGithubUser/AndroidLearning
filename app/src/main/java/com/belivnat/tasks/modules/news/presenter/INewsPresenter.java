package com.belivnat.tasks.modules.news.presenter;

import com.belivnat.tasks.networkingmodel.NewsModel;

import retrofit2.Response;

public interface INewsPresenter {
     void presenterSuccessResponse(Response<NewsModel> response);
     void presenterFailureResponse();
}
