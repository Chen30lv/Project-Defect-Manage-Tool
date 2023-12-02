package com.cityu.defect.controller;

import com.cityu.defect.model.vo.DefectInfoVO;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
class DefectInfoControllerTests {
    @Resource
    private DefectInfoController defectInfoController;
    @Test
    void testUpdate(){
        DefectInfoVO defectInfoVO = new DefectInfoVO();
        System.out.println(StringUtils.isBlank(defectInfoVO.getDefectLevel()));
    }
    @Test
    void testSearch(){
        DefectInfoVO defectInfoVO = new DefectInfoVO();
        System.out.println(StringUtils.isBlank(defectInfoVO.getDefectLevel()));
    }
    @Test
    void testMyDefectInfoProVOList(){
        DefectInfoVO defectInfoVO = new DefectInfoVO();
        System.out.println(StringUtils.isBlank(defectInfoVO.getDefectLevel()));
    }
}
