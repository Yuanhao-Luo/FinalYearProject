package com.example.watchvideo.DAO;

import com.example.watchvideo.Model.ImageTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ImageTableRepository extends JpaRepository<ImageTable, Integer> {
    @Override
    Optional<ImageTable> findById(Integer integer);

    ImageTable findByIdEquals(Integer id);

    ImageTable save(ImageTable entity);

    @Query("select max(i.id) from ImageTable i")
    int getMaxId();
}