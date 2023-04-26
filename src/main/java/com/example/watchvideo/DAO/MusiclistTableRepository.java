package com.example.watchvideo.DAO;

import com.example.watchvideo.Model.MusiclistTable;
import com.example.watchvideo.Model.UserTable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MusiclistTableRepository extends JpaRepository<MusiclistTable, Integer> {
    MusiclistTable findByIdEquals(Integer id);

    MusiclistTable save(MusiclistTable entity);

    List<MusiclistTable> findByUserEquals(UserTable user);
}