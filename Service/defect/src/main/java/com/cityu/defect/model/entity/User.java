package com.cityu.defect.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @author Ding Yi
 * @CreateDate: 2023年10月7日14:44:47
 */
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Data
@Table(name = "USER")
public class User implements Serializable {

    @Id
    @Column(name = "user_id",columnDefinition = "bigint(20) COMMENT '用户id'",nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    /**
     * 账户
     */
    @Column(name="user_account",nullable = false,columnDefinition = "varchar(32) COMMENT '账户名称'")
    private String account;

    /**
     * 密码
     */
    @Column(name="password",nullable = false,columnDefinition = "varchar(32) COMMENT '账户密码'")
    private String password;

    /**
     * 用户创建时间
     */
    @Column(name = "create_time", updatable = false, nullable = false)
    private Timestamp createTime;

    public User(String account, String password, Timestamp createTime) {
        super();
        this.account = account;
        this.password = password;
        this.createTime = createTime;
    }

    private static final long serialVersionUID = 1L;
}
