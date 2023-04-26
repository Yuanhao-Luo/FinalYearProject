package com.example.watchvideo.DAO;

import com.example.watchvideo.Model.VideoTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VideoTableRepository extends JpaRepository<VideoTable, Integer> {
    List<VideoTable> findBySeries_IdEquals(Integer id);
    VideoTable findByIdEquals(Integer id);

    @Override
    List<VideoTable> findAll();

    VideoTable save(VideoTable videoTable);

    @Query("select max(v.id) from VideoTable v")
    int getMaxId();

    List<VideoTable> findByTitleContainsIgnoreCase(String title);

    @Query("select v from VideoTable v order by v.id DESC")
    List<VideoTable> findByOrderByIdDesc();
}