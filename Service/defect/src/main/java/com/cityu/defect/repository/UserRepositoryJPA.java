package com.cityu.defect.repository;

import com.cityu.defect.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepositoryJPA extends JpaRepository<User, Long> {


    @Query(value = "select p from User p where p.account = ?1")
    List<User> findByAccount(String account);

    @Modifying
    @Query(value = "update User p set p.password = ?2 where p.account = ?1")
    void editorPasswd(String account, String passwd);
}
