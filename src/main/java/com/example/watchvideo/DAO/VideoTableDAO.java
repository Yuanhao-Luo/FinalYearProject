package com.example.watchvideo.DAO;

import com.example.watchvideo.Model.VideoTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VideoTableDAO extends JpaRepository<VideoTable, Integer> {
    List<VideoTable> findBySeries_IdEquals(Integer id);
    VideoTable findByIdEquals(Integer id);

    @Override
    List<VideoTable> findAll();
}