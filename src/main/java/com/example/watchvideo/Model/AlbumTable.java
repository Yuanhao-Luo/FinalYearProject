package com.example.watchvideo.Model;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "album")
public class AlbumTable {
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "title", length = 40)
    private String title;

    @OneToMany(mappedBy = "album")
    private Set<com.example.watchvideo.Model.MusicTable> music = new LinkedHashSet<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "image_id")
    private ImageTable image;

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

    public Set<com.example.watchvideo.Model.MusicTable> getMusic() {
        return music;
    }

    public void setMusic(Set<com.example.watchvideo.Model.MusicTable> music) {
        this.music = music;
    }

}