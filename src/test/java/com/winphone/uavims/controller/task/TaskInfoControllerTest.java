package com.winphone.xjwrj.controller.task;

import com.alibaba.fastjson.JSONObject;
import com.winphone.xjwrj.xjwrjApplication;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Date;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = xjwrjApplication.class)
public class TaskInfoControllerTest {
    @Autowired
    private WebApplicationContext wac;
    private MockMvc mockMvc;
    private static final String token = "eyJhbGciOiJIUzUxMiJ9.eyJBQ0NFU1MtVE9LRU4iOnsidXNlcklkIjoxLCJ0b2tlbklkIjoiNmRhZjVmODYtYmRiOS00MWUzLTlmNjktMDMzNDgyMjc3NDA5In0sImV4cCI6MTUyNDE5NDUxNX0.r37AzoIxSCgtKCvwgg5bVoVfbyXrJkyxkZne9eklr7cIxvJhCK21DHZ1V_c55YxNcGtEBCHN4Ywuja2neUCm0A";

    @Before
    public void setup(){
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }

    @Test
    public void allParam() throws Exception {

        JSONObject param = new JSONObject() ;
        param.put("taskTitle", "");
        String json = param.toString() ;
        System.out.println("================================请求入参："+json);
        RequestBuilder request = MockMvcRequestBuilders.get("/taskInfo/allParam")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(json) ;

        MvcResult mvcResult = mockMvc.perform(request).andReturn();

        int status = mvcResult.getResponse().getStatus();
        String content = mvcResult.getResponse().getContentAsString();

        System.out.println("返回结果："+status);
        System.out.println(content);

        Assert.assertTrue("正确", status == 200);
        Assert.assertFalse("错误", status != 200);

    }

    @Test
    public void save() throws Exception {
        JSONObject param = new JSONObject() ;
        param.put("executorId", 2);
        param.put("taskTitle", "test");
        param.put("taskContent", "content");
        param.put("taskAddress", "test");
        param.put("taskStartTime", new Date());
        param.put("taskEndTime",  new Date());
        String json = param.toString() ;
        System.out.println("================================请求入参："+json);
        RequestBuilder request = MockMvcRequestBuilders.post("/taskInfo/save")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .header("ACCESS-TOKEN",token)
                .content(json) ;

        MvcResult mvcResult = mockMvc.perform(request).andReturn();

        int status = mvcResult.getResponse().getStatus();
        String content = mvcResult.getResponse().getContentAsString();

        System.out.println("======返回结果："+status);
        System.out.println(content);
    }

    @Test
    public void updateExecutor() throws Exception {

    }

    @Test
    public void startTask() throws Exception {
    }

    @Test
    public void finishTask() throws Exception {
    }

    @Test
    public void getById() throws Exception {
    }

    @Test
    public void deleteById() throws Exception {
    }

    @Test
    public void delBatchIds() throws Exception {
    }

    @Test
    public void allParam1() throws Exception {
    }

}