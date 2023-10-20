package com.cityu.defect.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cityu.defect.common.ErrorCode;
import com.cityu.defect.exception.BusinessException;
import com.cityu.defect.mapper.DefectInfoMapper;
import com.cityu.defect.model.dto.defectInfo.DefectInfoQueryRequest;
import com.cityu.defect.model.entity.DefectInfo;
import com.cityu.defect.model.enums.DefectLevelEnum;
import com.cityu.defect.model.enums.DefectStatusEnum;
import com.cityu.defect.model.enums.DefectTypeEnum;
import com.cityu.defect.model.vo.DefectInfoVO;
import com.cityu.defect.service.DefectInfoService;
import com.cityu.defect.service.ProjectService;
import com.cityu.defect.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Slf4j
public class DefectInfoServiceImpl extends ServiceImpl<DefectInfoMapper,DefectInfo> implements DefectInfoService {
    @Resource
    private UserService userService;
    @Resource
    private ProjectService projectService;
    @Resource
    private DefectInfoMapper defectInfoMapper;
    @Override
    public QueryWrapper<DefectInfo> getQueryWrapper(DefectInfoQueryRequest defectInfoQueryRequest) {
        QueryWrapper<DefectInfo> queryWrapper = new QueryWrapper<>();
        if (defectInfoQueryRequest == null) {
            return queryWrapper;
        }
        Long id = defectInfoQueryRequest.getId();
        String defectName = defectInfoQueryRequest.getDefectName();
        String defectStatus = defectInfoQueryRequest.getDefectStatus();
        String defectDetail = defectInfoQueryRequest.getDefectDetail();
        String defectType = defectInfoQueryRequest.getDefectType();
        String defectLevel = defectInfoQueryRequest.getDefectLevel();
        Long userId = defectInfoQueryRequest.getUserId();
        Long projectId = defectInfoQueryRequest.getProjectId();
        String defectComment = defectInfoQueryRequest.getDefectComment();
        // 拼接查询条件
        queryWrapper.like(StringUtils.isNotBlank(defectName), "defectName", defectName);
        queryWrapper.like(StringUtils.isNotBlank(defectStatus), "defectStatus", defectStatus);
        queryWrapper.like(StringUtils.isNotBlank(defectDetail), "defectDetail", defectDetail);
        queryWrapper.like(StringUtils.isNotBlank(defectType), "defectType", defectType);
        queryWrapper.like(StringUtils.isNotBlank(defectLevel), "defectLevel", defectLevel);
        queryWrapper.like(StringUtils.isNotBlank(defectComment), "defectComment", defectComment);
        queryWrapper.ne(ObjectUtils.isNotEmpty(projectId), "projectId", projectId);
        queryWrapper.eq(ObjectUtils.isNotEmpty(id), "id", id);
        queryWrapper.eq(ObjectUtils.isNotEmpty(userId), "userId", userId);
        return queryWrapper;
    }
    @Override
    public void validDefectInfo(DefectInfo defectInfo) {
        if (defectInfo == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }

        String defectStatus = defectInfo.getDefectStatus();
        String defectType = defectInfo.getDefectType();
        String defectLevel = defectInfo.getDefectLevel();
        Long userId = defectInfo.getUserId();
        Long projectId = defectInfo.getProjectId();

        if(StringUtils.isNotBlank(defectLevel) && DefectLevelEnum.getEnumByValue(defectLevel) == null){
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "defectLevel不符合规范");
        }
        if(StringUtils.isNotBlank(defectType) && DefectTypeEnum.getEnumByValue(defectType) == null){
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "defectType不符合规范");
        }
        if(StringUtils.isNotBlank(defectStatus) && DefectStatusEnum.getEnumByValue(defectStatus) == null){
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "defectStatus不符合规范");
        }
        if (userId != null && userService.getById(userId) == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "user不存在");
        }
        if (projectId != null && projectService.getById(projectId) == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "project不存在");
        }
    }

    @Override
    public List<DefectInfo> findByUserId(Long userId) {
        return defectInfoMapper.selectByUserId(userId);
    }

    @Override
    public List<DefectInfoVO> getDefectInfoVO(List<DefectInfo> defectInfoList) {
        List<DefectInfoVO> defectInfoVOList = new ArrayList<>();
        if (CollectionUtils.isEmpty(defectInfoList)) {
            return defectInfoVOList;
        }
        for(DefectInfo defectInfo : defectInfoList){
            DefectInfoVO defectInfoVO = new DefectInfoVO();
            BeanUtils.copyProperties(defectInfo,defectInfoVO);
            defectInfoVO.setProject(projectService.getById(defectInfo.getProjectId()));
            defectInfoVO.setUserVO(userService.getUserVO(userService.getById(defectInfo.getUserId())));
            defectInfoVOList.add(defectInfoVO);
        }
        return defectInfoVOList;
    }
}
