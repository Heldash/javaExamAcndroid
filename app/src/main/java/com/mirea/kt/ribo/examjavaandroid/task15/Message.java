package com.mirea.kt.ribo.examjavaandroid.task15;

public class Message {
    private int id;
    private String text,datetime;
    private int watched;

    public Message() {
    }

    public Message(String text, String datetime, int watched) {
        this.text = text;
        this.datetime = datetime;
        this.watched = watched;
    }

    public Message(int id, String text, String datetime, int watched) {
        this.id = id;
        this.text = text;
        this.datetime = datetime;
        this.watched = watched;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }

    public int getWatched() {
        return watched;
    }

    public void setWatched(int watched) {
        this.watched = watched;
    }
}
