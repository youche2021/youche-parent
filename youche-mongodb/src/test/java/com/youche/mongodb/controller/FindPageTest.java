package com.youche.mongodb.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.youche.mongodb.model.Product;
import com.youche.mongodb.utils.Pagination;
import com.youche.mongodb.utils.Result;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

@Log4j2
public class FindPageTest extends AbstractControllerTest {

    @Test
    public void findPageTest() throws Exception {
        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.get("/product/findPage");
        accept(builder);
        int page = 2;
        int size = 2;
        builder.param("page", String.valueOf(page))
                .param("size", String.valueOf(size));
        MvcResult mvcResult = getMockMvc().perform(builder).andReturn();

        MockHttpServletResponse mvcResponse = mvcResult.getResponse();

        int status = mvcResponse.getStatus();
        String content = mvcResponse.getContentAsString();

        log.error(status);
        log.error(content);

        Result<Pagination<Product>> result = (Result<Pagination<Product>>) JSON.parseObject(content, new TypeReference<Result<Pagination<Product>>>(){}.getType());

        List<Product> products = result.getData().getContent();
        log.error(products.get(0).get_id());

        log.error(JSON.toJSONString(result, SerializerFeature.WriteMapNullValue)); // 保留空值，默认 fastjson 会过滤null值的属性

        log.error(result);

//        Assertions.assertEquals(200, status);
//        Assertions.assertEquals(200, result.getCode());
//        Assertions.assertNotEquals(null, result.getData());
//        Assertions.assertEquals(Product.class, result.getData().getContent().get(0).getClass());
        // Assertions.assertEquals(2.5, result.getData().get(0).getCcc());
    }
}
