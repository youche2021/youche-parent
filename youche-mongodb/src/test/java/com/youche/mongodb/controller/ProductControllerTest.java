package com.youche.mongodb.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.youche.mongodb.model.Product;
import com.youche.mongodb.utils.Result;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

@Log4j2
public class ProductControllerTest extends AbstractControllerTest {

    @Test
    public void saveTest() throws Exception {
        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post("/product/save");
        accept(builder);
        builder.param("name", "恒驰5");
        builder.param("user", "恒大");
        builder.param("ccc", "0.0");

        MvcResult mvcResult = getMockMvc().perform(builder).andReturn();
        MockHttpServletResponse mvcResponse = mvcResult.getResponse();
        int status = mvcResponse.getStatus();
        String content = mvcResponse.getContentAsString();
        log.error(status);
        log.error(content);
    }

    @Test
    public void findAllTest() throws Exception {
        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.get("/product/findAll");
        accept(builder);
        MvcResult mvcResult = getMockMvc().perform(builder).andReturn();

        MockHttpServletResponse mvcResponse = mvcResult.getResponse();

        int status = mvcResponse.getStatus();
        String content = mvcResponse.getContentAsString();

        log.error(status);
        log.error(content);

        Result<List<Product>> result = (Result<List<Product>>) JSON.parseObject(content, new TypeReference<Result<List<Product>>>(){}.getType());

        log.error(result.getData().get(0).getName());

        Assertions.assertEquals(200, status);
        Assertions.assertEquals(200, result.getCode());
        Assertions.assertNotEquals(null, result.getData());
        Assertions.assertEquals(Product.class, result.getData().get(0).getClass());
        // Assertions.assertEquals(2.5, result.getData().get(0).getCcc());
    }
}
