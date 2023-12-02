package com.cityu.defect.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.cityu.defect.model.dto.defectInfo.DefectInfoQueryRequest;
import com.cityu.defect.model.dto.statisticInfo.StatisticQueryRequest;
import com.cityu.defect.model.entity.DefectInfo;
import com.cityu.defect.model.vo.DefectInfoProVO;
import com.cityu.defect.model.vo.DefectInfoVO;
import com.cityu.defect.model.vo.StatisticVO;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface DefectInfoService extends IService<DefectInfo> {
    /**
     * get QueryWrapper
     */
    List<DefectInfo> getQueryWrapper(DefectInfoQueryRequest defectInfoQueryRequest);
    /**
     * valid DefectInfo
     */
    void validDefectInfo(DefectInfo defectInfo);
    /**
     * find the list of defectInfo By UserId
     */
    List<DefectInfo> findByUserId(Long userId);
    /**
     * get DefectInfoVO By DefectInfo
     */
    List<DefectInfoVO> getDefectInfoVO(List<DefectInfo> defectInfoList);
    /**
     * get DefectInfoProVO By DefectInfo
     */
    List<DefectInfoProVO> getDefectInfoProVO(List<DefectInfo> defectInfoList);
    /**
     * Multi field statistics
     * @param statisticQueryRequest
     * @return
     */
    List<StatisticVO> listStatistic(StatisticQueryRequest statisticQueryRequest);

    /**
     * Update DefectInfo
     * @param defectInfo
     * @return
     */
    Boolean UpdateDefectInfo(DefectInfo defectInfo);
}
