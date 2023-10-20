package com.cityu.defect.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.cityu.defect.model.dto.defectInfo.DefectInfoQueryRequest;
import com.cityu.defect.model.entity.DefectInfo;
import com.cityu.defect.model.vo.DefectInfoVO;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface DefectInfoService extends IService<DefectInfo> {
    /**
     * 获取查询条件
     */
    List<DefectInfo> getQueryWrapper(DefectInfoQueryRequest defectInfoQueryRequest);
    /**
     * 校验
     */
    void validDefectInfo(DefectInfo defectInfo);
    /**
     * 根据用户id查询对应缺陷列表
     */
    List<DefectInfo> findByUserId(Long userId);
    /**
     * 根据缺陷列表获取前端展示列表
     */
    List<DefectInfoVO> getDefectInfoVO(List<DefectInfo> defectInfoList);
}
