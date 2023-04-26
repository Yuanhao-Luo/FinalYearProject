package com.example.watchvideo.Model;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "image")
public class ImageTable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "file_name", length = 120)
    private String fileName;

    @Column(name = "file_path", length = 120)
    private String filePath;

    @OneToMany(mappedBy = "image")
    private Set<MusicTable> music = new LinkedHashSet<>();

    @OneToMany(mappedBy = "image")
    private Set<VideoTable> videos = new LinkedHashSet<>();

    @OneToMany(mappedBy = "image")
    private Set<CreatorTable> creators = new LinkedHashSet<>();

    @OneToMany(mappedBy = "image")
    private Set<AlbumTable> albums = new LinkedHashSet<>();

    public Set<AlbumTable> getAlbums() {
        return albums;
    }

    public void setAlbums(Set<AlbumTable> albums) {
        this.albums = albums;
    }

    public Set<CreatorTable> getCreators() {
        return creators;
    }

    public void setCreators(Set<CreatorTable> creators) {
        this.creators = creators;
    }

    public Set<VideoTable> getVideos() {
        return videos;
    }

    public void setVideos(Set<VideoTable> videos) {
        this.videos = videos;
    }

    public Set<MusicTable> getMusic() {
        return music;
    }

    public void setMusic(Set<MusicTable> music) {
        this.music = music;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

}