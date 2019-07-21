package com.viktor.test.controller;

import static org.mockito.Mockito.when;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.viktor.model.Status;
import com.viktor.service.StatusService;
import com.viktor.text.Text;

@RunWith(SpringRunner.class)
@WebMvcTest(com.viktor.controller.StatusController.class)
public class TestController {

	@MockBean
	private StatusService statusService;
	
	@Autowired
	MockMvc mockMvc;
	
	@MockBean
	Text text;
	
	@Before
	public void setup() {
		when(text.getALL_RUNNING()).thenReturn("All of your web services are running");
		when(text.getNONE_RUNNING()).thenReturn("None of your web services are running");
		when(text.getRUNNING()).thenReturn("Your web service is running");
		when(text.getNOT_RUNNING()).thenReturn("Your web service is not running");
		when(text.getPARTIAL_RUNNING()).thenReturn("1 / 2 of your web services are running");	
	}
	
	@Test
	public void testGetStatus() throws Exception{
		when(statusService.getStatuses()).thenReturn(Stream.of(new Status("testUrl", 200, "abc", "abc", "test-tag"),
				new Status("testUrl1", 200, "abc", "abc", "test-tag")).collect(Collectors.toList()));
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/status");
		mockMvc.perform(requestBuilder)
		.andExpect(MockMvcResultMatchers.status().is(200))
		.andExpect(MockMvcResultMatchers.content().string(text.getALL_RUNNING()));
		
		when(statusService.getStatuses()).thenReturn(Stream.of(new Status("testUrl", 404, "abc", "abc", "test-tag"),
				new Status("testUrl1", 200, "abc", "abc", "test-tag")).collect(Collectors.toList()));
		
		requestBuilder = MockMvcRequestBuilders.get("/status");
		mockMvc.perform(requestBuilder)
		.andExpect(MockMvcResultMatchers.status().is(200))
		.andExpect(MockMvcResultMatchers.content().string("1 / 2 " + text.getPARTIAL_RUNNING()));
		
		when(statusService.getStatuses()).thenReturn(Stream.of(new Status("testUrl", 404, "abc", "abc", "test-tag"),
				new Status("testUrl1", 404, "abc", "abc", "test-tag")).collect(Collectors.toList()));
		
		requestBuilder = MockMvcRequestBuilders.get("/status");
		mockMvc.perform(requestBuilder)
		.andExpect(MockMvcResultMatchers.status().is(200))
		.andExpect(MockMvcResultMatchers.content().string(text.getNONE_RUNNING()));
		
		
		
		when(statusService.getStatusBy("tag")).thenReturn(Stream.of(new Status("testUrl", 200, "abc", "abc", "test-tag"),
				new Status("testUrl1", 200, "abc", "abc", "test-tag")).collect(Collectors.toList()));
		
		requestBuilder = MockMvcRequestBuilders.get("/status?search=tag");
		mockMvc.perform(requestBuilder)
		.andExpect(MockMvcResultMatchers.status().is(200))
		.andExpect(MockMvcResultMatchers.content().string(text.getALL_RUNNING()));
		
		when(statusService.getStatusBy("tag")).thenReturn(Stream.of(new Status("testUrl", 404, "abc", "abc", "test-tag"),
				new Status("testUrl1", 200, "abc", "abc", "test-tag")).collect(Collectors.toList()));
		
		requestBuilder = MockMvcRequestBuilders.get("/status?search=tag");
		mockMvc.perform(requestBuilder)
		.andExpect(MockMvcResultMatchers.status().is(200))
		.andExpect(MockMvcResultMatchers.content().string("1 / 2 " +  text.getPARTIAL_RUNNING()));
		
		when(statusService.getStatusBy("tag")).thenReturn(Stream.of(new Status("testUrl", 404, "abc", "abc", "test-tag"),
				new Status("testUrl1", 404, "abc", "abc", "test-tag")).collect(Collectors.toList()));
		
		requestBuilder = MockMvcRequestBuilders.get("/status?search=tag");
		mockMvc.perform(requestBuilder)
		.andExpect(MockMvcResultMatchers.status().is(200))
		.andExpect(MockMvcResultMatchers.content().string(text.getNONE_RUNNING()));
		
	}
	

}
