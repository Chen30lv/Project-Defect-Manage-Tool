package com.cityu.defect.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
public class Person {
    @Id
    private Long id;

    private String userId;

    private String userName;

    private String passwd;

    private String userSex;

    private String extendStr1;

    private String extendStr2;

    private String extendStr3;

    private String extendStr4;

    private String extendFlag1;

    private String extendFlag2;

}
