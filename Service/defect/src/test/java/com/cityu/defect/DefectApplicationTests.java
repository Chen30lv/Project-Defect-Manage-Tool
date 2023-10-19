package com.cityu.defect;

import com.cityu.defect.service.impl.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DefectApplicationTests {
    @Autowired
    private UserServiceImpl personServiceImpl;
    @Test
    void testRegister(){
    }

}
