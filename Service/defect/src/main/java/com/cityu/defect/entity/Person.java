package com.cityu.defect.entity;

import lombok.Data;

import javax.persistence.*;

/**
 * @author Ding Yi
 * @
 */
@Entity
@Data
@Table(name = "PERSON")
public class Person {

    @Id
    @Column(name = "person_id",columnDefinition = "bigint(20) COMMENT '用户id'",nullable = false)
    private Long id;
    /**
     * 账户
     */
    @Column(name="person_account",nullable = false,columnDefinition = "varchar(32) COMMENT '账户名称'")
    private String account;
    /**
     * 密码
     */
//    @Column(name="account_passwd",nullable = false,columnDefinition = "varchar(32) COMMENT '账户密码'")
//    private String password;
//    /**
//     * 对应漏洞列表
//     */
//    @ManyToMany(targetEntity = Flaw.class)
//    private Set<Flaw> FlawList = new HashSet<>();
}
