package com.example.watchvideo.DAO;

import com.example.watchvideo.Model.MusicTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MusicTableRepository extends JpaRepository<MusicTable, Integer> {
    MusicTable save(MusicTable entity);

    //select 20 latest music
    @Query("select m from MusicTable m order by m.id desc")
    List<MusicTable> getNewMusic();

    MusicTable findByIdEquals(Integer id);

    List<MusicTable> findByTitleContainsIgnoreCase(String title);

//    @Query("""
//            select m from MusicTable m left join m.vocals vocals
//            where upper(m.title) like upper(concat('%', ?1, '%')) or upper(vocals.name) like upper(concat('%', ?2, '%'))""")
//    List<MusicTable> search(String title, String name);

    @Query("select m from MusicTable m left join m.vocals vocals " +
            "where upper(m.title) like upper(concat('%', ?1, '%')) or upper(vocals.name) like upper(concat('%', ?2, '%'))")
    List<MusicTable> search(String title, String name);
}