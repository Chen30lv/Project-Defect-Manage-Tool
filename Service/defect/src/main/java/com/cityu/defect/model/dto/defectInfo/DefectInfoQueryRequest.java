package com.cityu.defect.model.dto.defectInfo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

/**
 * DefectInfoQueryRequest
 */
@Data
public class DefectInfoQueryRequest implements Serializable {


    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value="id",example="1")
    private Long id;

    @ApiModelProperty(value="defectName",example="defect1")
    private String defectName;

    @ApiModelProperty(value="defectStatus",example="Open,Fixed,Pending Retest,ReOpened,Closed,Deffered,NotABug,Duplicate")
    private String defectStatus;

    @ApiModelProperty(value="defectDetail",example="test")
    private String defectDetail;

    @ApiModelProperty(value="defectType",example="Functional Defect,Logical Defect,Workflow Defect,Unit Level Defect,System-level Integration Defect,Security Defect")
    private String defectType;

    @ApiModelProperty(value="defectLevel",example="Low Medium High Critical")
    private String defectLevel;

    @ApiModelProperty(value="userId",example="1")
    @NotNull
    private Long userId;

    @ApiModelProperty(value="projectId",example="1")
    private Long projectId;

    @ApiModelProperty(value="defectComment",example="no comment")
    private String defectComment;

    @ApiModelProperty(value="isToDo",example="TODO,FINISHED")
    private String isToDo;

}