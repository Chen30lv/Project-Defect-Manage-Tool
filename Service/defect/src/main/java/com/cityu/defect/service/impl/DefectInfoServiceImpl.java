package com.cityu.defect.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cityu.defect.common.ErrorCode;
import com.cityu.defect.exception.BusinessException;
import com.cityu.defect.mapper.DefectInfoMapper;
import com.cityu.defect.model.dto.defectInfo.DefectInfoQueryRequest;
import com.cityu.defect.model.dto.statisticInfo.StatisticQueryRequest;
import com.cityu.defect.model.entity.DefectInfo;
import com.cityu.defect.model.enums.DefectLevelEnum;
import com.cityu.defect.model.enums.DefectStatusEnum;
import com.cityu.defect.model.enums.DefectToDoEnum;
import com.cityu.defect.model.enums.DefectTypeEnum;
import com.cityu.defect.model.vo.DefectInfoVO;
import com.cityu.defect.model.vo.StatisticVO;
import com.cityu.defect.service.DefectInfoService;
import com.cityu.defect.service.ProjectService;
import com.cityu.defect.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

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
    public List<DefectInfo> getQueryWrapper(DefectInfoQueryRequest defectInfoQueryRequest) {
        QueryWrapper<DefectInfo> queryWrapper = new QueryWrapper<>();
        if (defectInfoQueryRequest == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "请求参数不能为空");
        }

        DefectInfo defectInfo = new DefectInfo();
        BeanUtils.copyProperties(defectInfoQueryRequest, defectInfo);

        this.validDefectInfo(defectInfo);
        //校验成功则进行查询
        Long id = defectInfoQueryRequest.getId();
        String defectName = defectInfoQueryRequest.getDefectName();
        String defectStatus = defectInfoQueryRequest.getDefectStatus();
        String defectDetail = defectInfoQueryRequest.getDefectDetail();
        String defectType = defectInfoQueryRequest.getDefectType();
        String defectLevel = defectInfoQueryRequest.getDefectLevel();
        String isToDo = defectInfoQueryRequest.getIsToDo();
        Long userId = defectInfoQueryRequest.getUserId();
        Long projectId = defectInfoQueryRequest.getProjectId();
        String defectComment = defectInfoQueryRequest.getDefectComment();

        queryWrapper.like(StringUtils.isNotBlank(defectName), "defect_name", defectName);
        queryWrapper.like(StringUtils.isNotBlank(defectStatus), "defect_status", defectStatus);
        queryWrapper.like(StringUtils.isNotBlank(defectDetail), "defect_detail", defectDetail);
        queryWrapper.like(StringUtils.isNotBlank(defectType), "defect_type", defectType);
        queryWrapper.like(StringUtils.isNotBlank(isToDo), "is_to_do", isToDo);
        queryWrapper.like(StringUtils.isNotBlank(defectLevel), "defect_level", defectLevel);
        queryWrapper.like(StringUtils.isNotBlank(defectComment), "defect_comment", defectComment);
        queryWrapper.ne(ObjectUtils.isNotEmpty(projectId), "project_id", projectId);
        queryWrapper.eq(ObjectUtils.isNotEmpty(id), "id", id);
        queryWrapper.eq(ObjectUtils.isNotEmpty(userId), "user_id", userId);
        return defectInfoMapper.selectList(queryWrapper);
    }
    @Override
    public void validDefectInfo(DefectInfo defectInfo) {
        if (defectInfo == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }

        String defectStatus = defectInfo.getDefectStatus();
        String defectType = defectInfo.getDefectType();
        String defectLevel = defectInfo.getDefectLevel();
        String isToDo = defectInfo.getIsToDo();
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
        if(StringUtils.isNotBlank(isToDo) && DefectToDoEnum.getEnumByValue(isToDo) == null){
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "isToDo不符合规范");
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

    @Override
    public List<StatisticVO> listStatistic(StatisticQueryRequest statisticQueryRequest){

        if (statisticQueryRequest == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "请求参数不能为空");
        }
        String key = statisticQueryRequest.getKey();
        Long userId = statisticQueryRequest.getUserId();
        List<StatisticVO> statisticVOList;

        switch(key){
            case "project":
                statisticVOList = defectInfoMapper.listCountByProject(userId);
                break;
            case "type":
                statisticVOList = defectInfoMapper.listCountByType(userId);
                break;
            case "level":
                statisticVOList = defectInfoMapper.listCountByLevel(userId);
                break;
            case "todo":
                statisticVOList = defectInfoMapper.listCountByIsToDo(userId);
                break;
            default:
                throw new BusinessException(ErrorCode.PARAMS_ERROR, "invalid key");
        }
        return statisticVOList;

    }

    @Override
    public Boolean UpdateDefectInfo(DefectInfo defectInfo) {
        DefectInfo oldDefectInfo = this.getById(defectInfo.getId());
        String defectStatus = defectInfo.getDefectStatus();
        String defectComment = defectInfo.getDefectComment();
        String defectCommentOld = oldDefectInfo.getDefectComment();
        DefectStatusEnum statusEnum = DefectStatusEnum.getEnumByValueUpdate(defectStatus);
        if(StringUtils.isNotBlank(defectStatus) && statusEnum == null){
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "defectStatus不符合规范");
        }
        else{
            oldDefectInfo.setDefectStatus(defectStatus);
            if(!statusEnum.equals(DefectStatusEnum.DEFERRED)){
                oldDefectInfo.setIsToDo(DefectToDoEnum.FINISHED.getValue());
            }
        }
        if(StringUtils.isNotBlank(defectCommentOld) && StringUtils.isNotBlank(defectComment)){
            oldDefectInfo.setDefectComment(defectCommentOld.concat(" => ").concat(defectComment));
        }
        if(StringUtils.isBlank(defectCommentOld) && StringUtils.isNotBlank(defectComment)){
            oldDefectInfo.setDefectComment(defectComment);
        }
        return this.updateById(oldDefectInfo);
    }
}
