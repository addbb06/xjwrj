package com.winphone.xjwrj.service;

import com.winphone.xjwrj.xjwrjApplication;
import com.winphone.xjwrj.service.task.IFlyLineService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Map;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = xjwrjApplication.class)
public class IFlyLineServiceTest {
    @Autowired
    IFlyLineService flyLineService;


    @Test
    public void sumMileageAndFlyTimeByDeviceId() throws Exception {
        Map<String,Double> num = flyLineService.sumMileageAndFlyTimeByDeviceId(1L);
        num.forEach((key, value) -> System.out.println("-------------" + key + "==========" + value));
    }

    @Test
    public void countFlyTimesByDeviceId() throws Exception {
        Integer result = flyLineService.countFlyTimesByDeviceId(1L);
        System.out.println("--------------COUNT FLY TIMES :"+result);
    }

}