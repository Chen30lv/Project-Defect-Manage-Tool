package com.cityu.defect.model.vo;

import com.cityu.defect.model.entity.Project;
import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * DefectInfoVO，which return to the frontend
 */
@Data
public class DefectInfoVO implements Serializable {

    private Long id;

    private UserVO userVO;

    private Project project;

    private String defectName;

    private String defectStatus;

    private String defectDetail;

    private String defectType;

    private String defectLevel;

    private String isToDo;

    private Long userId;

    private Long projectId;

    private String defectComment;

    private Timestamp createTime;

    private Timestamp updateTime;

    private static final long serialVersionUID = 1L;
}
