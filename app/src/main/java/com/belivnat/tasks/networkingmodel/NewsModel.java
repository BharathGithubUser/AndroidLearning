package com.belivnat.tasks.networkingmodel;


import android.app.SearchableInfo;

import java.util.List;

public class NewsModel
{
    private String totalResults;

    private List<ArticlesModel> articles;

    private String status;

    public String getTotalResults ()
    {
        return totalResults;
    }

    public void setTotalResults (String totalResults)
    {
        this.totalResults = totalResults;
    }

    public List<ArticlesModel> getArticles ()
    {
        return articles;
    }

    public void setArticles (List<ArticlesModel> articles)
    {
        this.articles = articles;
    }

    public String getStatus ()
    {
        return status;
    }

    public void setStatus (String status)
    {
        this.status = status;
    }
    public NewsModel() {}
    public NewsModel(String status,List<ArticlesModel> articles, String totalResults){
        this.status = status;
        this.articles = articles;
        this.totalResults = totalResults;
    }
    @Override
    public String toString()
    {
        return "ClassPojo [totalResults = "+totalResults+", articles = "+articles+", status = "+status+"]";
    }
}