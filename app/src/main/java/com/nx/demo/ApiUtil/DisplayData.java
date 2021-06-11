package com.nx.demo.ApiUtil;

public class DisplayData {


    String id;
    String title;
    String message;
    String like;

    public DisplayData(String id, String title, String message, String like) {
        this.id = id;
        this.title = title;
        this.message = message;
        this.like = like;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getLike() {
        return like;
    }

    public void setLike(String like) {
        this.like = like;
    }




}
