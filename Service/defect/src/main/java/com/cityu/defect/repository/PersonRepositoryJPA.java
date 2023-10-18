package com.cityu.defect.repository;

import com.cityu.defect.model.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PersonRepositoryJPA extends JpaRepository<Person, Long> {


    @Query(value = "select p from Person p where p.account = ?1")
    List<Person> findByAccount(String account);

    @Modifying
    @Query(value = "update Person p set p.password = ?2 where p.account = ?1")
    void editorPasswd(String account, String passwd);
}
