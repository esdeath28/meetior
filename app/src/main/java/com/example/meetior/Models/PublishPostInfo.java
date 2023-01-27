package com.example.meetior.Models;

public class PublishPostInfo {

    public String posttitle;
    public String postcover;


    public PublishPostInfo(String name, String url) {
        this. posttitle = name;
        this.postcover = url;
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
