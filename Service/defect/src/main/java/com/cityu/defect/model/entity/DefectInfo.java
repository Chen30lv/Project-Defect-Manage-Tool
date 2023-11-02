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
     * 缺陷名称
     */
    private String defectName;

    /**
     * 缺陷状态
     */
    private String defectStatus;

    /**
     * 是否完成
     */
    private String isToDo;

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
     * 缺陷所有者 userid列表
     */
    private Long userId;

    /**
     * 缺陷对应项目
     */
    private Long projectId;

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


}
