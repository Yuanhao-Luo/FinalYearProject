package com.example.watchvideo.Model;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "series")
public class SeriesTable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "name", length = 120)
    private String name;

    @Column(name = "image_id")
    private Integer imageId;

    @OneToMany(mappedBy = "series")
    private Set<VideoTable> videos = new LinkedHashSet<>();

    public Set<VideoTable> getVideos() {
        return videos;
    }

    public void setVideos(Set<VideoTable> videos) {
        this.videos = videos;
    }

    public Integer getImageId() {
        return imageId;
    }

    public void setImageId(Integer imageId) {
        this.imageId = imageId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}