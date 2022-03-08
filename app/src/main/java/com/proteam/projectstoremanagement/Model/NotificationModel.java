package com.proteam.projectstoremanagement.Model;

public class NotificationModel {

    private String description,title,date;

    public NotificationModel(String description, String title, String date) {
        this.description = description;
        this.title = title;
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
