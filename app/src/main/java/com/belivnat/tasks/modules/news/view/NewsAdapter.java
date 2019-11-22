package com.belivnat.tasks.modules.news.view;

import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.belivnat.tasks.R;
import com.belivnat.tasks.networkingmodel.ArticlesModel;
import com.belivnat.tasks.networkingmodel.NewsModel;
import com.bumptech.glide.Glide;

import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.MyViewHolder> {
    List<ArticlesModel> newsList;
    Context context;

    public NewsAdapter(List<ArticlesModel> newsList, Context context) {
        this.newsList = newsList;
        this.context = context;
    }

    @NonNull
    @Override
    public NewsAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_news_row, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsAdapter.MyViewHolder holder, int position) {
        holder.newsText.setText(newsList.get(position).getContent());
        holder.newsTitle.setText(newsList.get(position).getTitle());
        Glide.with(context).load(newsList.get(position).getUrlToImage()).placeholder(R.mipmap.ic_launcher).into(holder.newsImageView);

    }

    @Override
    public int getItemCount() {
        return newsList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView newsTitle, newsText;
        ImageView newsImageView;
        MyViewHolder(@NonNull View itemView) {
            super(itemView);
            this.newsTitle = itemView.findViewById(R.id.txt_news_title);
            this.newsText = itemView.findViewById(R.id.txt_news_text);
            this.newsImageView = itemView.findViewById(R.id.img_news_image);
        }
    }
}
