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

    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * account
     */
    private String account;

    /**
     * password
     */
    private String password;

    /**
     * createTime
     */
    private Timestamp createTime;

    /**
     * updateTime
     */
    private Timestamp updateTime;


}
