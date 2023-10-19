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

@NoArgsConstructor
@AllArgsConstructor
@Data
@TableName(value = "defect_info")
public class DefectInfo implements Serializable {

    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 缺陷名称
     */
    private String defectName;

    /**
     * 缺陷状态 New Open Fixed Reopened Deffered Duplicate Not a bug ....
     */
    private int defectStatus;

    /**
     * 缺陷详情
     */
    private String defectDetail;

    /**
     * 缺陷类型 功能未实现-1 通用异常未处理-2 界面优化-3 安全相关-4 性能问题-5 兼容性-6
     */
    private String defectType;

    /**
     * 缺陷等级 Low-1 Medium-2 High-3 Critical-4
     */
    private String defectLevel;

    /**
     * 缺陷所有者 多人用，连接
     */
    private String defectOwner;

    /**
     * 缺陷注释 长文本类型
     */
    private String defectComment;


    /**
     * 缺陷创建时间
     */
    private Timestamp createTime;

    /**
     * 最后修改时间
     */
    private Timestamp updateTime;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}
