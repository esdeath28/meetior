package com.example.meetior.Models;

public class MyPostModel {
    String articletopic,articletitle,articleauthor,articledes;

    public MyPostModel() {
    }

    public MyPostModel(String articletopic, String articletitle, String articleauthor, String articledes) {
        this.articletopic = articletopic;
        this.articletitle = articletitle;
        this.articleauthor = articleauthor;
        this.articledes = articledes;
    }

    public String getArticledes() {
        return articledes;
    }

    public void setArticledes(String articledes) {
        this.articledes = articledes;
    }

    public String getArticletitle() {
        return articletitle;
    }

    public void setArticletitle(String articletitle) {
        this.articletitle = articletitle;
    }

    public String getArticleauthor() {
        return articleauthor;
    }

    public void setArticleauthor(String articleauthor) {
        this.articleauthor = articleauthor;
    }

    public String getArticletopic() {
        return articletopic;
    }

    public void setArticletopic(String articletopic) {
        this.articletopic = articletopic;
    }
}
