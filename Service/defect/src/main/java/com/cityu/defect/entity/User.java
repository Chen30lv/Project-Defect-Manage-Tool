package com.cityu.defect.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@Table(name = "User")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, unique = true)
    private Long id;
    /**
     * 账户
     */
    @Column(nullable = false, unique = true)
    private String account;
    /**
     * 密码
     */
    @Column(nullable = false)
    private String password;
    /**
     * 对应漏洞列表
     */
    @ManyToMany(targetEntity = Flaw.class)
    private Set<Flaw> FlawList = new HashSet<>();
}
