package com.cityu.defect.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cityu.defect.model.entity.Project;
import org.apache.ibatis.annotations.Mapper;

/**
 * project数据库操作
 */
@Mapper
public interface ProjectMapper extends BaseMapper<Project> {
}
