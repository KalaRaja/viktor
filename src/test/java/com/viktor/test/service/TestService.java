package com.viktor.test.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.viktor.model.Status;
import com.viktor.repo.StatusRepo;
import com.viktor.service.StatusService;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = com.viktor.Application.class)
public class TestService {

	@Autowired
	private StatusService statusService;

	@MockBean
	private StatusRepo statusRepo;

	@Test
	public void getStatusesTest() {
		when(statusRepo.findByTag("testTag")).thenReturn(Stream.of(new Status("testUrl", 200, "abc", "abc", "test-tag"),
				new Status("testUrl1", 200, "abc", "abc", "test-tag")).collect(Collectors.toList()));
		
		when(statusRepo.findAll()).thenReturn(Stream.of(new Status("testUrl", 200, "abc", "abc", "test-tag1"),
				new Status("testUrl1", 200, "abc", "abc", "test-tag2")).collect(Collectors.toList()));
		
		assertEquals("Should return list of 2 statuses", 2, statusService.getStatuses().size());
		assertEquals("Should return list of 2 statuses", 2, statusService.getStatusBy("testTag").size());
	}
}
