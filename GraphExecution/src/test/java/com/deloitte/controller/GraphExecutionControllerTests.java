package com.deloitte.controller;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import javax.inject.Inject;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import com.deloitte.model.json.GraphTaskJson;

@SpringBootTest
class GraphExecutionControllerTests {

	@Inject
	private ExecutionController executeController;

	@Test
	public void executeTest() {
		ResponseEntity<GraphTaskJson> response = executeController.executeGraph(112L);
		assertThat(response.getStatusCode().value(), is(200));
	}
	
	@Test
	public void executeFailTest() {
		ResponseEntity<GraphTaskJson> response = executeController.executeGraph(2L);
		assertThat(response.getStatusCode().value(), is(404));
	}
}
