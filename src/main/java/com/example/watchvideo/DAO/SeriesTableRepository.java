package com.example.watchvideo.DAO;

import com.example.watchvideo.Model.SeriesTable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SeriesTableRepository extends JpaRepository<SeriesTable, Integer> {
    SeriesTable findByIdEquals(Integer id);
}