package com.example.watchvideo.DAO;

import com.example.watchvideo.Model.UserTable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserModelRepository extends JpaRepository<UserTable, Integer> {
    UserTable save(UserTable entity);
    UserTable findByUserName(String userName);

    UserTable findByIdEquals(Integer id);
}