package com.example.watchvideo.DAO;

import com.example.watchvideo.Model.SeriesTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SeriesTableRepository extends JpaRepository<SeriesTable, Integer> {
    List<SeriesTable> findByNameContains(String name);
    SeriesTable findByIdEquals(Integer id);
    SeriesTable save(SeriesTable entity);

    SeriesTable findByNameLikeIgnoreCase(String name);

    @Query("select s from SeriesTable s where upper(s.name) like upper(?1)")
    List<SeriesTable> search_series(String name);

    List<SeriesTable> findByNameContainingIgnoreCase(String name);
}