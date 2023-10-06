package com.cityu.defect.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
public class Flaw {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, unique = true)
    private Long id;
    /**
     * 漏洞状态
     */
    @Column(nullable = false)
    private int status;
    /**
     * 漏洞名称
     */
    @Column(nullable = false)
    private String FlawName;
    /**
     * 漏洞详情
     */
    @Column(nullable = false)
    private String FlawDetail;
    /**
     * 漏洞留言
     */
    @OneToMany(targetEntity = Comment.class)
    private Set<Comment> CommentList = new HashSet<>();
    /**
     * 漏洞归属成员表
     */
    @ManyToMany(targetEntity = User.class)
    private Set<User> UserList = new HashSet<>();
}
