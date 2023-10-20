package com.cityu.defect.service.impl;

import com.cityu.defect.mapper.ProjectMapper;
import com.cityu.defect.model.entity.Project;
import com.cityu.defect.service.ProjectService;
import lombok.extern.slf4j.Slf4j;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ProjectServiceImpl extends ServiceImpl<ProjectMapper, Project> implements ProjectService {
}
