package com.example.customlistview;

public class NotesHelper {

    String title;
    String content;
    String date;


    public NotesHelper(String title, String content, String date) {
        this.title = title;
        this.content = content;
        this.date = date;
    }

    //Empty Constructor
    public NotesHelper() {

    }

    //Adding getters and setters
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
