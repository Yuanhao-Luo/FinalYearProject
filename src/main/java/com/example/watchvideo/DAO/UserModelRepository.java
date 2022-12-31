package com.example.watchvideo.DAO;

import com.example.watchvideo.Model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface UserModelRepository extends JpaRepository<UserModel, Integer> {
    UserModel save(UserModel entity);
    UserModel findByUserName(String userName);
}