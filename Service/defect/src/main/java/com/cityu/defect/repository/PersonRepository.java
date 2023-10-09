package com.cityu.defect.repository;

import com.cityu.defect.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@EnableJpaAuditing
public interface PersonRepository extends PersonRepositoryJPA {

}

