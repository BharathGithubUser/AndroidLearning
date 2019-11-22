package com.belivnat.tasks.modules.news.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.belivnat.tasks.R;
import com.belivnat.tasks.TaskApplication;
import com.belivnat.tasks.modules.news.model.NewsApiModel;
import com.belivnat.tasks.modules.news.presenter.NewsPresenter;
import com.belivnat.tasks.networkingmodel.ApiInterface;
import com.belivnat.tasks.networkingmodel.ArticlesModel;
import com.belivnat.tasks.networkingmodel.NewsModel;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewsActivity extends AppCompatActivity {
    RecyclerView newsRecyclerView;
    NewsModel newsModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);
        newsModel = new NewsModel();
        newsRecyclerView = findViewById(R.id.rv_news_recyclerview);
        newsRecyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL,false));
        INewsView iNewsView = new INewsView() {
            @Override
            public void onSuccessResponse(Response response) {
                if (response.isSuccessful()) {
                    // Do your success stuff...
                    Log.d("onSuccess", response.body().toString());
                    newsModel = (NewsModel) response.body();
                    newsRecyclerView.setAdapter(new NewsAdapter(newsModel.getArticles(), NewsActivity.this));
                } else {
                    try {
                        JSONObject jObjError = new JSONObject(response.errorBody().string());
                        Toast.makeText(NewsActivity.this, jObjError.getString("message"), Toast.LENGTH_LONG).show();
                    } catch (Exception e) {
                        Toast.makeText(NewsActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }
            }
            @Override
            public void onFailureResponse() {
                Log.d("onSuccess","Api call failed");
            }
        };
        NewsPresenter presenter = new NewsPresenter(iNewsView);
    }
}
