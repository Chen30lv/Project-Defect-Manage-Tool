package com.cityu.defect.model.dto.defectInfo;

import lombok.Data;

import java.io.Serializable;

/**
 * 缺陷查询请求
 */
@Data
public class DefectInfoUpdateRequest implements Serializable {
    /**
     * id
     */
    private Long id;

    /**
     * 缺陷名称
     */
    private String defectName;

    /**
     * 缺陷状态
     */
    private String defectStatus;

    /**
     * 缺陷详情
     */
    private String defectDetail;

    /**
     * 缺陷类型
     */
    private String defectType;

    /**
     * 缺陷等级
     */
    private String defectLevel;

    /**
     * 缺陷所有者userid
     */
    private Long userId;
    /**
     * 缺陷所属项目project id
     */
    private Long projectId;

    /**
     * 缺陷注释 长文本类型
     */
    private String defectComment;

    private static final long serialVersionUID = 1L;
}