package com.example.meetior.Models;

public class PostModel {
    String posttitle,postcover;

    public PostModel() {
    }

    public PostModel(String posttitle, String postcover) {
        this.posttitle = posttitle;
        this.postcover = postcover;
    }


    public String getPosttitle() {
        return posttitle;
    }

    public void setPosttitle(String posttitle) {
        this.posttitle = posttitle;
    }

    public String getPostcover() {
        return postcover;
    }

    public void setPostcover(String postcover) {
        this.postcover = postcover;
    }
}
