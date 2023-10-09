package com.cityu.defect.entity;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;

@Entity
@Data
@Table(name = "DEFECT_INFO")
public class DefectInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "defect_id",columnDefinition = "bigint(20) COMMENT '缺陷id'",nullable = false)
    private Long id;

    /**
     * 缺陷名称
     */
    @Column(name = "defect_name",columnDefinition = "varchar(32) COMMENT '缺陷名称'",nullable = false)
    private String defectName;

    /**
     * 缺陷状态 New Open Fixed Reopened Deffered Duplicate Not a bug ....
     */
    @Column(name = "defect_status",columnDefinition = "varchar(8) COMMENT '缺陷状态'",nullable = false)
    private int defectStatus;

    /**
     * 缺陷详情
     */
    @Column(name = "defect_detail",columnDefinition = "varchar(256) COMMENT '缺陷详情'",nullable = false)
    private String defectDetail;

    /**
     * 缺陷类型 功能未实现-1 通用异常未处理-2 界面优化-3 安全相关-4 性能问题-5 兼容性-6
     */
    @Column(name = "defect_type", columnDefinition = "varchar(4) COMMENT '缺陷类型 功能未实现-1 通用异常未处理-2 界面优化-3 安全相关-4 性能问题-5 兼容性-6'"
            , nullable = false)
    private String defectType;

    /**
     * 缺陷等级 Low-1 Medium-2 High-3 Critical-4
     */
    @Column(name = "defect_level", columnDefinition = "varchar(4) COMMENT '缺陷等级 Low-1 Medium-2 High-3 Critical-4'"
            , nullable = false)
    private String defectLevel;

    /**
     * 缺陷所有者 多人用，连接
     */
    @Column(name = "defect_owner", columnDefinition = "varchar(128) COMMENT '缺陷所有者 用逗号连接'", nullable = false)
    private String defectOwner;

    /**
     * 缺陷注释 长文本类型
     */
    @Lob
    @Column(name = "defect_comment")
    private String defectComment;


    /**
     * 缺陷创建时间
     */
    @Column(name = "createTime", updatable = false , nullable = false)
    private Timestamp createTime;

    /**
     * 最后修改时间
     */
    @Column(name = "last_edited_time", nullable = false)
    private Timestamp lastEditedTime;

}
