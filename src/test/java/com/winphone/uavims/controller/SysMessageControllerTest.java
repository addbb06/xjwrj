package com.winphone.xjwrj.controller;

import com.winphone.xjwrj.xjwrjApplication;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = xjwrjApplication.class)
public class SysMessageControllerTest {

    private MockMvc mockMvc;
    @Autowired
    private WebApplicationContext wac;

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    }


    @Test
    public void allParam() throws Exception {
        String token = "eyJhbGciOiJIUzUxMiJ9.eyJBQ0NFU1MtVE9LRU4iOnsidXNlcklkIjoxLCJ0b2tlbklkIjoiNmRhZjVmODYtYmRiOS00MWUzLTlmNjktMDMzNDgyMjc3NDA5In0sImV4cCI6MTUyNDE5NDUxNX0.r37AzoIxSCgtKCvwgg5bVoVfbyXrJkyxkZne9eklr7cIxvJhCK21DHZ1V_c55YxNcGtEBCHN4Ywuja2neUCm0A";
        String url = "/sysMsg/allParam";
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders
                .get(url)
                .header("ACCESS-TOKEN",token)
                .accept(MediaType.APPLICATION_JSON))
                .andReturn();// 返回执行请求的结果

        System.out.println(result.getResponse().getContentAsString());
    }

}