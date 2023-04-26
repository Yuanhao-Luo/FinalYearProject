package com.example.watchvideo.Model;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "musiclist")
public class MusiclistTable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "title", length = 40)
    private String title;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private UserTable user;

    @ManyToMany
    @JoinTable(name = "musiclist_music",
            joinColumns = @JoinColumn(name = "musiclist_id"),
            inverseJoinColumns = @JoinColumn(name = "music_id"))
    private Set<MusicTable> music = new LinkedHashSet<>();

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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public UserTable getUser() {
        return user;
    }

    public void setUser(UserTable user) {
        this.user = user;
    }

}