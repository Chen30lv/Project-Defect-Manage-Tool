package com.cityu.defect.model.dto.statisticInfo;

import lombok.Data;

@Data
public class StatisticQueryRequest {

    /**
     * 分组关键字
     */
    private String key;

    /**
     * 用户id
     */
    private Long userId;
}
