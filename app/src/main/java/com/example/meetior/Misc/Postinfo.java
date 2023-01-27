package com.example.meetior.Misc;

public class Postinfo {
    String articletitle,articledes;

    public Postinfo(String articletitle, String articledes) {
        this.articletitle = articletitle;
        this.articledes = articledes;
    }

    public String getArticletitle() {
        return articletitle;
    }

    public void setArticletitle(String articletitle) {
        this.articletitle = articletitle;
    }

    public String getArticledes() {
        return articledes;
    }

    public void setArticledes(String articledes) {
        this.articledes = articledes;
    }
}
