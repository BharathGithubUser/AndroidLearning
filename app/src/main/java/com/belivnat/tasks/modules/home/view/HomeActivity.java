package com.belivnat.tasks.modules.home.view;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.belivnat.tasks.R;
import com.belivnat.tasks.model.HomeMenuOptions;
import com.belivnat.tasks.modules.crud.CrudActivity;
import com.belivnat.tasks.modules.invoicer.InvoicerActivity;
import com.belivnat.tasks.modules.multisearch.MultiSelectActivity;
import com.belivnat.tasks.modules.news.view.NewsActivity;
import com.belivnat.tasks.modules.scheduler.SchedulerActivity;
import com.belivnat.tasks.modules.scribble.ScribbleActivity;
import com.belivnat.tasks.modules.simplegame.GameMainActivity;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity implements HomeAdapter.HomeMenuOnClickListener {
    RecyclerView homeRecyclerView;
    List<HomeMenuOptions> homeMenuOptions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        homeMenuOptions = new ArrayList<>();
        homeMenuOptions.add(
                new HomeMenuOptions("News",
                        "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQFzzJiD5tW8R-YND-wS1BlhNLBhC4x9NqNmqDYQ4kh3mJCngaeFQ",
                        new Intent(HomeActivity.this, NewsActivity.class)));
        homeMenuOptions.add(
                new HomeMenuOptions("Scheduler", "https://www.opencodez.com/wp-content/uploads/2017/03/Oracle-Scheduler.jpg",
                        new Intent(HomeActivity.this, SchedulerActivity.class))
        );
        homeMenuOptions.add(
                new HomeMenuOptions("Scribble", "https://images.squarespace-cdn.com/content/5979bd6e46c3c439412b34ed/1502343887695-DRVKESLLJG2IC169TCWD/Scribble+Me+This_LOGO-02.png?format=1500w&content-type=image%2Fpng",
                        new Intent(HomeActivity.this, ScribbleActivity.class))
        );
        homeMenuOptions.add(
                new HomeMenuOptions("MultiSelectList", "https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcS-IfctbCPLqapk5NB8irYYKQQEA17bGyO66duSovMOpGwIMeC4",
                        new Intent(HomeActivity.this, MultiSelectActivity.class))
        );
        homeMenuOptions.add(
                new HomeMenuOptions("SimpleGame", "https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcQuSnalX3XIl0ELYL2l_z7dLhd1rc8bYDXXp1q9ZJDaY1UJ0TOJ",
                        new Intent(HomeActivity.this, GameMainActivity.class))
        );
        homeMenuOptions.add(
                new HomeMenuOptions("CRUD", "https://cdn3.iconfinder.com/data/icons/search-engine-optimization-plus/64/crud_create_read_update_delete-512.png",
                        new Intent(HomeActivity.this, CrudActivity.class))
        );
        homeMenuOptions.add(
                new HomeMenuOptions("Invoicer", "https://previews.123rf.com/images/ahasoft2000/ahasoft20001608/ahasoft2000160800164/61032789-invoice-icon-vector-style-is-flat-iconic-symbol-with-rounded-angles-black-color-white-background-.jpg",
                        new Intent(HomeActivity.this, InvoicerActivity.class))
        );
        homeRecyclerView = findViewById(R.id.rv_home_list);
        homeRecyclerView.setMotionEventSplittingEnabled(false);
        if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT){
            homeRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        }
        else{
            homeRecyclerView.setLayoutManager(new GridLayoutManager(this, 3));
        }
        homeRecyclerView.setAdapter(new HomeAdapter(this, homeMenuOptions, this));

    }

    @Override
    public void onMenuClicked(int item) {
        Intent intent = homeMenuOptions.get(item).getIntent();
        startActivity(intent);
//        overridePendingTransition(R.anim.anim_left_to_right, R.anim.anim_right_to_left);
    }
}
