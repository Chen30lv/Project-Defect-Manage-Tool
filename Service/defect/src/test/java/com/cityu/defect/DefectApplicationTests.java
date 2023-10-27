package com.cityu.defect;

import com.cityu.defect.mapper.DefectInfoMapper;
import com.cityu.defect.mapper.UserMapper;
import com.cityu.defect.model.entity.DefectInfo;
import com.cityu.defect.model.enums.DefectLevelEnum;
import com.cityu.defect.model.vo.DefectInfoVO;
import com.cityu.defect.service.UserService;
import com.cityu.defect.service.impl.UserServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;

@SpringBootTest
class DefectApplicationTests {
    @Resource
    private UserService userService;
    @Resource
    private UserMapper userMapper;
    @Test
    void test(){
        userService.addUser("dingyi","12345678");
        userService.addUser("wangyuchen","12345678");
    }

}
