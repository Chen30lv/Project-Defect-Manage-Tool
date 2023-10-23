package com.cityu.defect.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @author Ding Yi
 * @CreateDate: 2023年10月7日14:44:47
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@TableName(value = "user")
public class User implements Serializable {

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    @TableId(type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 账户
     */
    private String account;

    /**
     * 密码
     */
    private String password;

    /**
     * 用户创建时间
     */
    private Timestamp createTime;

    /**
     * 用户更新时间
     */
    private Timestamp updateTime;


}
