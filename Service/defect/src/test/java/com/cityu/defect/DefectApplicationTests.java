package com.cityu.defect;

import com.cityu.defect.mapper.DefectInfoMapper;
import com.cityu.defect.model.entity.DefectInfo;
import com.cityu.defect.model.enums.DefectLevelEnum;
import com.cityu.defect.model.vo.DefectInfoVO;
import com.cityu.defect.service.impl.UserServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class DefectApplicationTests {
    @Test
    void test(){
        DefectInfoVO defectInfoVO = new DefectInfoVO();
        System.out.println(StringUtils.isBlank(defectInfoVO.getDefectLevel()));
    }

}
