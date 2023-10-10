package com.cityu.defect;

import com.cityu.defect.service.impl.PersonServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DefectApplicationTests {
    @Autowired
    private PersonServiceImpl personServiceImpl;
    @Test
    void testRegister(){
        long id = personServiceImpl.personRegister("dingyi","12345678","12345678");
        System.out.println(id);
    }

}
