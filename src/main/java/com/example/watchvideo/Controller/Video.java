package com.example.watchvideo.Controller;

import lombok.Data;

@Data
public class Video {
    int id;
    String title;


    public Video(int id, String title){
        this.id = id;
        this.title = title;
    }
}