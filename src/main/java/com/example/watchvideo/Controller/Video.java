package com.example.watchvideo.Controller;

import lombok.Data;

@Data
public class Video {
    int id;
    String title;
    int image;


    public Video(int id, String title){
        this.id = id;
        this.title = title;
    }

    public void setImage(int image) {
        this.image = image;
    }
}