package com.cityu.defect.controller;

import com.cityu.defect.exception.BusinessException;
import com.cityu.defect.model.dto.defectInfo.DefectInfoQueryRequest;
import com.cityu.defect.model.dto.defectInfo.DefectInfoUpdateRequest;
import com.cityu.defect.model.enums.DefectStatusEnum;
import com.cityu.defect.model.vo.DefectInfoVO;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
class DefectInfoControllerTests {
    @Resource
    private DefectInfoController defectInfoController;
    @Test
    void testUpdate(){
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = servletRequestAttributes.getRequest();
        DefectInfoUpdateRequest defectInfoUpdateRequest = new DefectInfoUpdateRequest();
        //do not exist
        defectInfoUpdateRequest.setId(0L);
        defectInfoUpdateRequest.setDefectComment("ooo");
        BusinessException businessException = assertThrows(BusinessException.class,() ->{
            defectInfoController.UpdateDefectInfo(defectInfoUpdateRequest,request);
        });
        System.out.println("Test1: " + businessException.getMessage());
        //invalid
        defectInfoUpdateRequest.setId(1L);
        defectInfoUpdateRequest.setDefectStatus("wow");
        businessException = assertThrows(BusinessException.class,() ->{
            defectInfoController.UpdateDefectInfo(defectInfoUpdateRequest,request);
        });
        System.out.println("Test2: " + businessException.getMessage());
        //success
        defectInfoUpdateRequest.setId(1L);
        defectInfoUpdateRequest.setDefectStatus(DefectStatusEnum.DEFERRED.getValue());
        defectInfoUpdateRequest.setDefectComment("deferred!");
        System.out.println("Test3: " + defectInfoController.UpdateDefectInfo(defectInfoUpdateRequest,request).getMessage());
    }
    @Test
    void testSearch(){
        DefectInfoQueryRequest defectInfoQueryRequest = new DefectInfoQueryRequest();
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = servletRequestAttributes.getRequest();
        //user doesn't login
        BusinessException businessException = assertThrows(BusinessException.class,() ->{
            defectInfoController.searchDefectInfoVO(defectInfoQueryRequest,request);
        });
        System.out.println(businessException.getMessage());
        //success
        defectInfoQueryRequest.setDefectStatus(DefectStatusEnum.OPEN.getValue());
        defectInfoQueryRequest.setUserId(1L);
        System.out.println(defectInfoController.searchDefectInfoVO(defectInfoQueryRequest,request));
    }
    @Test
    void testMyDefectInfoProVOList(){
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = servletRequestAttributes.getRequest();
//        System.out.println(defectInfoController.listMyDefectInfoProVO(1L,request));
    }
}
