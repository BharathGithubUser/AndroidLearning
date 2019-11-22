package com.belivnat.tasks.modules.news.view;

import retrofit2.Response;

public interface INewsView {
    void onSuccessResponse(Response response);
    void onFailureResponse();
}
