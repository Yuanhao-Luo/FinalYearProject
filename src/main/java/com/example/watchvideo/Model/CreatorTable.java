package com.example.watchvideo.Model;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "creator")
public class CreatorTable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "name", length = 40)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "image_id")
    private ImageTable image;

    @ManyToMany
    @JoinTable(name = "vocal",
            joinColumns = @JoinColumn(name = "creator_id"),
            inverseJoinColumns = @JoinColumn(name = "music_id"))
    private Set<MusicTable> vocals = new LinkedHashSet<>();

    @ManyToMany
    @JoinTable(name = "composer",
            joinColumns = @JoinColumn(name = "creator_id"),
            inverseJoinColumns = @JoinColumn(name = "music_id"))
    private Set<MusicTable> composers = new LinkedHashSet<>();

    @ManyToMany
    @JoinTable(name = "lyricist",
            joinColumns = @JoinColumn(name = "creator_id"),
            inverseJoinColumns = @JoinColumn(name = "music_id"))
    private Set<MusicTable> lyricists = new LinkedHashSet<>();

    public Set<MusicTable> getLyricists() {
        return lyricists;
    }

    public void setLyricists(Set<MusicTable> music) {
        this.lyricists = music;
    }

    public Set<MusicTable> getComposers() {
        return composers;
    }

    public void setComposers(Set<MusicTable> music) {
        this.composers = music;
    }

    public Set<MusicTable> getVocals() {
        return vocals;
    }

    public void setVacals(Set<MusicTable> music) {
        this.vocals = music;
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

    public ImageTable getImage() {
        return image;
    }

    public void setImage(ImageTable image) {
        this.image = image;
    }

}