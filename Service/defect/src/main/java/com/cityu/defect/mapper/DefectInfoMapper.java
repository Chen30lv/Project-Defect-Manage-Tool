package com.cityu.defect.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cityu.defect.model.entity.DefectInfo;
import com.cityu.defect.model.vo.StatisticVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DefectInfoMapper extends BaseMapper<DefectInfo> {
    List<DefectInfo> selectByUserId(Long userId);

    List<StatisticVO> listCountByProject(Long userId);

    List<StatisticVO> listCountByType(Long userId);

    List<StatisticVO> listCountByLevel(Long userId);

    List<StatisticVO> listCountByIsToDo(Long userId);


}
