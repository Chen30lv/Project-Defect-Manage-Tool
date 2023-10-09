package com.cityu.defect.service;

import com.cityu.defect.entity.Person;
import com.cityu.defect.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PersonService {

    @Autowired
    PersonRepository personRepository;

    @Transactional(isolation= Isolation.SERIALIZABLE)
    public int addPerson(String account, String password){

        try {
            personRepository.save(new Person(account, password));
        }catch (Exception e){
            e.printStackTrace();

        }
        return 1;

    }
}
