package com.viktor.test.controller;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.viktor.model.Status;
import com.viktor.service.StatusService;

@RunWith(SpringRunner.class)
@WebMvcTest(com.viktor.controller.StatusController.class)
@ContextConfiguration(classes={com.viktor.application.Application.class})
public class TestController {

	@MockBean
	private StatusService statusService;
	
	@Autowired
	MockMvc mockMvc;
	
	
	@Test
	public void testGetStatus() {
		when(statusService.getStatuses()).thenReturn(Stream.of(new Status("testUrl", 200, "abc", "abc", "test-tag"),
				new Status("testUrl1", 200, "abc", "abc", "test-tag")).collect(Collectors.toList()));
		assertEquals("Should return all running message", "All of your web services are running", statusService.getStatuses());
	}

}
