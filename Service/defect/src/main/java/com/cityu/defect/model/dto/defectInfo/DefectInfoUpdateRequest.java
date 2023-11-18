package com.cityu.defect.model.dto.defectInfo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 缺陷查询请求
 */
@Data
public class DefectInfoUpdateRequest implements Serializable {
    /**
     * id
     */
    @ApiModelProperty(value="id",example="1")
    @NotNull
    private Long id;

    /**
     * 缺陷状态
     */
    @ApiModelProperty(value="defectStatus",example="Fixed,Deferred,NotABug,Duplicate")
    private String defectStatus;

    /**
     * 缺陷所有者userid
     */
    @ApiModelProperty(value="userId",example="1")
//    @NotNull
    private Long userId;

    /**
     * 缺陷注释 长文本类型
     */
    @ApiModelProperty(value="defectComment",example="no comment")
    private String defectComment;


    private static final long serialVersionUID = 1L;
}