package com.zxsample.demo.api.controller;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.zxsample.demo.api.model.Result;
import com.zxsample.demo.application.model.InventoryDTO;
import com.zxsample.demo.application.model.PagingQueryResult;
import com.zxsample.demo.test.WithSpringTestBase;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import java.net.URI;

import static org.junit.Assert.*;

@Transactional
@AutoConfigureMockMvc
public class InventoryManagerControllerTest extends WithSpringTestBase {

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void listInventories() throws Exception {
        String testUri = "/inventories?pageNumber=1&pageSize=10";
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get(testUri))
                .andReturn();

        Assertions.assertThat(result).isNotNull();

        String content = result.getResponse().getContentAsString();
        Result<PagingQueryResult<InventoryDTO>> response = new Gson().fromJson(content,
                new TypeToken<Result<PagingQueryResult<InventoryDTO>>>(){}.getType());
        Assertions.assertThat(content).isNotEmpty();
        Assertions.assertThat(response.isSuccess()).isEqualTo(true);
        Assertions.assertThat(response.getData()).isNotNull();
        Assertions.assertThat(response.getData().getTotal()).isEqualTo(0);
        Assertions.assertThat(response.getData().getPageNumber()).isEqualTo(1);
        Assertions.assertThat(response.getData().getPageSize()).isEqualTo(10);
        Assertions.assertThat(response.getData().getData()).isNull();
    }
}