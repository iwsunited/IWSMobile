package com.iws.mobile.notification;

public class Data {
    private String id_notification, title, body, id_user;

    public Data(String id_notification, String title, String body, String id_user) {
        this.id_notification = id_notification;
        this.title = title;
        this.body = body;
        this.id_user = id_user;
    }

    public String getId_notification() {
        return id_notification;
    }

    public void setId_notification(String id_notification) {
        this.id_notification = id_notification;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getId_user() {
        return id_user;
    }

    public void setId_user(String id_user) {
        this.id_user = id_user;
    }
}
