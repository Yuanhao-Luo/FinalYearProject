package com.example.watchvideo.DAO;

import com.example.watchvideo.Model.CreatorTable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CreatorTableRepository extends JpaRepository<CreatorTable, Integer> {
    CreatorTable save(CreatorTable entity);

    CreatorTable findByNameLikeIgnoreCase(String name);

    List<CreatorTable> findByNameContainsIgnoreCase(String name);

    CreatorTable findByIdEquals(Integer id);
}