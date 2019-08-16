package com.application.app;

import com.application.app.Entity.Product;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductServiceControllerTest extends AbstractTest {
    @Override
    @Before
    public void setUp() {
        super.setUp();
    }

    String uri = "/api/v1/products";
    @Test
    public void getProducasdsadts() throws Exception {
        MvcResult mvcResult = mvc.perform(
                MockMvcRequestBuilders.get(this.uri)
                .accept(MediaType.APPLICATION_JSON_UTF8)
        ).andReturn();

        int status = mvcResult.getResponse().getStatus();
        String content = mvcResult.getRequest().getContentAsString();
        Product[] products = super.mapFromJson(content, Product[].class);
        System.out.println("zxczxcxzcxasdasd");
        assertTrue(products.length > 0);

    }
}
