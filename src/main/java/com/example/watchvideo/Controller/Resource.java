package com.example.watchvideo.Controller;

import java.util.ArrayList;
import java.util.List;

public class Resource {
    private String file_name = "";
    private String title = "";
    private String series = "";
    private int type = 0;
    private String date = "";
    private List<String> vocal = null;
    private List<String> composer = null;
    private List<String> lyricist = null;

    public String getFile_name() {
        return file_name;
    }

    public void setFile_name(String file_name) {
        this.file_name = file_name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSeries() {
        return series;
    }

    public void setSeries(String series) {
        this.series = series;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<String> getVocal() {
        return vocal;
    }

    public void setVocal(List<String> vocal) {
        this.vocal = vocal;
    }

    public List<String> getComposer() {
        return composer;
    }

    public void setComposer(List<String> composer) {
        this.composer = composer;
    }

    public List<String> getLyricist() {
        return lyricist;
    }

    public void setLyricist(List<String> lyricist) {
        this.lyricist = lyricist;
    }
}
