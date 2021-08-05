package com.example.demo;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.UUID;

import com.example.demo.dto.DemoDTO;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@SpringBootTest
class DemoApplicationTests {
	@Autowired
	private WebApplicationContext webApplicationContext;
	@Autowired
	private ObjectMapper objectMapper;
	private MockMvc mvc; // 創建MockMvc類的物件

	@BeforeEach
	void setUp() {
		mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}

	@Test
	void testSuccess() throws Exception {
		String uri = "/demo";
		DemoDTO dto = new DemoDTO();
		dto.setTransactionId(UUID.randomUUID().toString());
		dto.setPointAmount(10);
		dto.setBusinessTime(LocalDateTime.now());
		String json = objectMapper.writeValueAsString(dto);
		System.out.println(json);
		MvcResult result = mvc
				.perform(MockMvcRequestBuilders.post(uri).contentType(MediaType.APPLICATION_JSON).content(json))
				.andReturn();
		int status = result.getResponse().getStatus();
		Assertions.assertEquals(200, status);
	}

	@Test
	void testBusinessTimeFail() throws Exception {
		String uri = "/demo";
		DemoDTO dto = new DemoDTO();
		dto.setTransactionId(UUID.randomUUID().toString());
		dto.setPointAmount(10);
		dto.setBusinessTime(LocalDateTime.now().plus(10, ChronoUnit.HOURS));
		String json = objectMapper.writeValueAsString(dto);
		System.out.println(json);
		MvcResult result = mvc
				.perform(MockMvcRequestBuilders.post(uri).contentType(MediaType.APPLICATION_JSON).content(json))
				.andReturn();
		int status = result.getResponse().getStatus();
		Assertions.assertEquals(400, status);
		String content = result.getResponse().getContentAsString();
		System.out.println(content);
		Assertions.assertEquals("datetime must be less now", content);
	}

	@Test
	void testPointAmountFail() throws Exception {
		String uri = "/demo";
		DemoDTO dto = new DemoDTO();
		dto.setTransactionId(UUID.randomUUID().toString());
		dto.setPointAmount(-1);
		dto.setBusinessTime(LocalDateTime.now());
		String json = objectMapper.writeValueAsString(dto);
		System.out.println(json);
		MvcResult result = mvc
				.perform(MockMvcRequestBuilders.post(uri).contentType(MediaType.APPLICATION_JSON).content(json))
				.andReturn();
		int status = result.getResponse().getStatus();
		Assertions.assertEquals(400, status);
		String content = result.getResponse().getContentAsString();
		System.out.println(content);
		Assertions.assertEquals("Point Amount not be less than 0", content);
		dto.setPointAmount(11);
		json = objectMapper.writeValueAsString(dto);
		System.out.println(json);
		result = mvc.perform(MockMvcRequestBuilders.post(uri).contentType(MediaType.APPLICATION_JSON).content(json))
				.andReturn();
		status = result.getResponse().getStatus();
		Assertions.assertEquals(400, status);
		content = result.getResponse().getContentAsString();
		System.out.println(content);
		Assertions.assertEquals("Point Amount not be greater than 10", content);
	}

	@Test
	void testTransactionId() throws Exception {
		String uri = "/demo";
		DemoDTO dto = new DemoDTO();
		dto.setTransactionId(null);
		dto.setPointAmount(10);
		dto.setBusinessTime(LocalDateTime.now());
		String json = objectMapper.writeValueAsString(dto);
		System.out.println(json);
		MvcResult result = mvc
				.perform(MockMvcRequestBuilders.post(uri).contentType(MediaType.APPLICATION_JSON).content(json))
				.andReturn();
		int status = result.getResponse().getStatus();
		Assertions.assertEquals(400, status);
		String content = result.getResponse().getContentAsString();
		System.out.println(content);
		Assertions.assertEquals("Transaction ID can't empty", content);
	}

}
