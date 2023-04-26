package com.example.watchvideo.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "music")
public class MusicTable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "title", length = 40)
    private String title;

    @Column(name = "file_name", length = 120)
    private String fileName;

    @Column(name = "file_path", length = 120)
    private String filePath;

    @Column(name = "date")
    private LocalDate date;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "album_id")
    @JsonIgnore
    private com.example.watchvideo.Model.AlbumTable album;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "image_id")
    @JsonIgnore
    private ImageTable image;

    @ManyToMany(mappedBy = "vocals")
    @JsonIgnore
    private Set<CreatorTable> vocals = new LinkedHashSet<>();

    @ManyToMany(mappedBy = "music")
    @JsonIgnore
    private Set<MusiclistTable> musiclists = new LinkedHashSet<>();

    @ManyToMany(mappedBy = "composers")
    @JsonIgnore
    private Set<CreatorTable> composers = new LinkedHashSet<>();

    @ManyToMany(mappedBy = "lyricists")
    @JsonIgnore
    private Set<CreatorTable> lyricists = new LinkedHashSet<>();

    public Set<CreatorTable> getLyricists() {
        return lyricists;
    }

    public void setLyricists(Set<CreatorTable> creators) {
        this.lyricists = creators;
    }

    public Set<CreatorTable> getComposers() {
        return composers;
    }

    public void setComposers(Set<CreatorTable> creators) {
        this.composers = creators;
    }

    public Set<MusiclistTable> getMusiclists() {
        return musiclists;
    }

    public void setMusiclists(Set<MusiclistTable> musiclists) {
        this.musiclists = musiclists;
    }

    public Set<CreatorTable> getVocals() {
        return vocals;
    }

    public void setVocals(Set<CreatorTable> creators) {
        this.vocals = creators;
    }

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

    public com.example.watchvideo.Model.AlbumTable getAlbum() {
        return album;
    }

    public void setAlbum(com.example.watchvideo.Model.AlbumTable album) {
        this.album = album;
    }

}