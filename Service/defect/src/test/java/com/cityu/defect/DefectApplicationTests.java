package com.cityu.defect;

import com.cityu.defect.controller.HelloController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DefectApplicationTests {
    @Autowired
    private HelloController helloController;
    @Test
    void contextLoads() {
        System.out.print(helloController.get());
    }

}
