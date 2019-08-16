package com.application.app;

import com.application.app.Entity.Product;
import com.application.app.Pojo.Operation;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.Assert.assertTrue;import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AppApplicationTests  extends AbstractTest{

	@Test
	public void contextLoads() {
	}

	@Override
	@Before
	public void setUp() {
		super.setUp();
	}

	String uri = "/api/v1/products";
	@Test
	public void getProducts() throws Exception {
		MvcResult mvcResult = mvc.perform(
				MockMvcRequestBuilders.get(this.uri)
						.accept(MediaType.APPLICATION_JSON_UTF8)
		).andReturn();
		int status = mvcResult.getResponse().getStatus();
		Product[] products = super.mapFromJson(mvcResult.getResponse().getContentAsString(), Product[].class);
		assertTrue(products.length > 0);
	}

	@Test
	public void saveProduct() throws Exception {
		Product product = Product.builder()
				.name("Sample Product")
				.price((float) 100)
				.quantity(20)
				.description("Description")
				.build();
		String input = super.mapToJson(product);
		MvcResult mvcResult = mvc.perform(
				MockMvcRequestBuilders
						.post(this.uri)
						.accept(MediaType.APPLICATION_JSON_UTF8)
						.contentType(MediaType.APPLICATION_JSON)
						.content(input)
		).andReturn();
		int status = mvcResult.getResponse().getStatus();
		String content = mvcResult.getResponse().getContentAsString();
		assertEquals(201, status);
	}

//	@Test
//	public void deleteProduct() throws Exception{
//		MvcResult mvcResult = mvc.perform(
//				MockMvcRequestBuilders.delete(this.uri + "/21")
//		).andReturn();
//		int status = mvcResult.getResponse().getStatus();
//		String content = mvcResult.getResponse().getContentAsString();
//		Operation operation = super.mapFromJson(content, Operation.class);
//
//
//		assertTrue(operation.getIsSuccess());
//		assertEquals(200, status);
//	}

	@Test
	public void wrongProductId_whenDeletingProduct() throws Exception{
		MvcResult mvcResult = mvc.perform(
				MockMvcRequestBuilders.delete(this.uri + "/10")
		).andReturn();
		int status = mvcResult.getResponse().getStatus();
		String content = mvcResult.getResponse().getContentAsString();
		Operation operation = super.mapFromJson(content, Operation.class);


		assertTrue(!operation.getIsSuccess());
		assertEquals(404, status);
	}

//	@Test
//	public void updateProduct() throws Exception{
//		Product product = Product.builder()
//							.description("Updated Description")
//							.quantity(10)
//							.price(Float.valueOf(10))
//							.name("Updated Name")
//							.id(Long.valueOf(22))
//							.build();
//
//		String content = super.mapToJson(product);
//		MvcResult mvcResult = mvc.perform(
//				MockMvcRequestBuilders
//						.put(this.uri)
//						.contentType(MediaType.APPLICATION_JSON)
//						.content(content)
//		).andReturn();
//		int status = mvcResult.getResponse().getStatus();
//		String responseContent = mvcResult.getResponse().getContentAsString();
//		Operation operation = super.mapFromJson(responseContent, Operation.class);
//
//		assertEquals(200, status);
//		assertTrue(operation.getIsSuccess());
//	}

}
