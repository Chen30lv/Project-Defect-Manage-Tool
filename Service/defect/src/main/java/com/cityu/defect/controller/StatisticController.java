package com.cityu.defect.controller;

import com.cityu.defect.common.BaseResponse;
import com.cityu.defect.common.ErrorCode;
import com.cityu.defect.common.ResultUtils;
import com.cityu.defect.exception.BusinessException;
import com.cityu.defect.model.dto.statisticInfo.StatisticQueryRequest;
import com.cityu.defect.model.entity.User;
import com.cityu.defect.model.vo.StatisticVO;
import com.cityu.defect.service.DefectInfoService;
import com.cityu.defect.service.UserService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/statistic")
@Slf4j
public class StatisticController {

    @Resource
    private UserService userService;

    @Resource
    private DefectInfoService defectInfoService;

    @ApiOperation("多字段统计")
    @PostMapping("/list")
    public BaseResponse<List<StatisticVO>> list(@RequestBody StatisticQueryRequest statisticQueryRequest
            , HttpServletRequest request) {

        User loginUser = userService.getLoginUser(request);
        //用户未登录
        if(loginUser == null){
            throw new BusinessException(ErrorCode.NOT_LOGIN_ERROR);
        }
        Long loginUserId = loginUser.getId();
        //登录用户不能请求其他用户的统计信息
        if(!Objects.equals(loginUserId, statisticQueryRequest.getUserId())){
            throw new BusinessException(ErrorCode.NOT_LOGIN_ERROR);
        }

        List<StatisticVO> defectInfoList = defectInfoService.listStatistic(statisticQueryRequest);
        return ResultUtils.success(defectInfoList);

    }

}
