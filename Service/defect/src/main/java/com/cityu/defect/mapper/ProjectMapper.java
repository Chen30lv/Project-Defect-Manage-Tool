package com.cityu.defect.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cityu.defect.model.entity.DefectInfo;
import com.cityu.defect.model.entity.Project;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * project数据库操作
 */
@Mapper
public interface ProjectMapper extends BaseMapper<Project> {

    Map<Long, String> selectAllProject();
}
