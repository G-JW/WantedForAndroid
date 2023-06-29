package com.example.wanted;

import java.io.Serializable;

public class WantedBean implements Serializable {
    private int index;
    private String title;
    private String imageUrl;
    private String avatarUrl;
    private String username;
    private int num;

    public WantedBean(int index, String imageUrl, String title, String avatarUrl, String username, int num) {
        this.index = index;
        this.imageUrl = imageUrl;
        this.title = title;
        this.avatarUrl = avatarUrl;
        this.username = username;
        this.num = num;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

}
