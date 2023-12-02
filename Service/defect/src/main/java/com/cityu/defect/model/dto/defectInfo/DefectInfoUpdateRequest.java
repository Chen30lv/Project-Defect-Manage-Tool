package com.cityu.defect.model.dto.defectInfo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * DefectInfoUpdateRequest
 */
@Data
public class DefectInfoUpdateRequest implements Serializable {
    @ApiModelProperty(value="id",example="1")
    @NotNull
    private Long id;

    @ApiModelProperty(value="defectStatus",example="Fixed,Deferred,NotABug,Duplicate")
    private String defectStatus;

    @ApiModelProperty(value="userId",example="1")
//    @NotNull
    private Long userId;

    @ApiModelProperty(value="defectComment",example="no comment")
    private String defectComment;


    private static final long serialVersionUID = 1L;
}