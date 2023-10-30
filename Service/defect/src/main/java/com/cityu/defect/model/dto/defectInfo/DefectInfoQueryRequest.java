package com.cityu.defect.model.dto.defectInfo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

/**
 * 缺陷查询请求
 */
@Data
public class DefectInfoQueryRequest implements Serializable {


    private static final long serialVersionUID = 1L;
    /**
     * id
     */
    @ApiModelProperty(value="id",example="1")
    private Long id;

    /**
     * 缺陷名称
     */
    @ApiModelProperty(value="defectName",example="defect1")
    private String defectName;

    /**
     * 缺陷状态
     */

    @ApiModelProperty(value="defectStatus",example="Open,Fixed,Pending Retest,ReOpened,Closed,Deffered,NotABug,Duplicate")
    private String defectStatus;

    /**
     * 缺陷详情
     */
    @ApiModelProperty(value="defectDetail",example="test")
    private String defectDetail;

    /**
     * 缺陷类型
     */

    @ApiModelProperty(value="defectType",example="Functional Defect,Logical Defect,Workflow Defect,Unit Level Defect,System-level Integration Defect,Security Defect")
    private String defectType;

    /**
     * 缺陷等级
     */

    @ApiModelProperty(value="defectLevel",example="Low Medium High Critical")
    private String defectLevel;

    /**
     * 缺陷所有者userid
     */
    @ApiModelProperty(value="userId",example="1")
    @NotNull
    private Long userId;
    /**
     * 缺陷所属项目project id
     */
    @ApiModelProperty(value="projectId",example="1")
    private Long projectId;

    /**
     * 缺陷注释 长文本类型
     */
    @ApiModelProperty(value="defectComment",example="no comment")
    private String defectComment;

    /**
     * 缺陷是否已完成
     */
    @ApiModelProperty(value="isToDo",example="todo,finished")
    private String isToDo;

}