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

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * defectName
     */
    private String defectName;

    /**
     * defectStatus
     */
    private String defectStatus;

    /**
     * isToDo
     */
    private String isToDo;

    /**
     * defectDetail
     */
    private String defectDetail;

    /**
     * defectType
     */
    private String defectType;

    /**
     * defectLevel
     */
    private String defectLevel;

    /**
     * userId
     */
    private Long userId;

    /**
     * projectId
     */
    private Long projectId;

    /**
     * defectComment
     */
    private String defectComment;


    /**
     * createTime
     */
    private Timestamp createTime;

    /**
     * updateTime
     */
    private Timestamp updateTime;


}
