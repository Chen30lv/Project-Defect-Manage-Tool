package com.cityu.defect.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cityu.defect.model.entity.DefectInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DefectInfoMapper extends BaseMapper<DefectInfo> {
    List<DefectInfo> selectByUserId(Long userId);
}
