package com.example.watchvideo.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "video")
public class VideoTable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "title", nullable = false, length = 40)
    private String title;

    @Column(name = "file_name", length = 120)
    private String fileName;

    @Column(name = "file_path", length = 120)
    private String filePath;

    @Column(name = "date")
    private LocalDate date;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "series_id")
    @JsonIgnore
    private com.example.watchvideo.Model.SeriesTable series;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "image_id")
    @JsonIgnore
    private com.example.watchvideo.Model.ImageTable image;

    public ImageTable getImage() {
        return image;
    }

    public void setImage(ImageTable image) {
        this.image = image;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public com.example.watchvideo.Model.SeriesTable getSeries() {
        return series;
    }

    public void setSeries(com.example.watchvideo.Model.SeriesTable series) {
        this.series = series;
    }

}