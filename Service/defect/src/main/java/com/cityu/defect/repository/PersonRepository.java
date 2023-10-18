package com.cityu.defect.repository;

import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.stereotype.Component;

@Component
@EnableJpaAuditing
public interface PersonRepository extends PersonRepositoryJPA {

}

