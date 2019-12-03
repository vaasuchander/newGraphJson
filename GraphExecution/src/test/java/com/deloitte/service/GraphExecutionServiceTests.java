package com.deloitte.service;

import static org.hamcrest.CoreMatchers.any;
import static org.hamcrest.MatcherAssert.assertThat;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.deloitte.model.PlayBook;
import com.deloitte.model.PredecessorConstraint;
import com.deloitte.model.PredecessorConstraintType;
import com.deloitte.model.PlaybookTask;
import com.deloitte.service.JsonService;

@SpringBootTest
class GraphExecutionServiceTests {

	@Inject
	private JsonService jsonService;

	@Test
	void predFS() {

		PlaybookTask task1 = new PlaybookTask();
		task1.setPlaybookTaskId(1L);
		PlaybookTask task2 = new PlaybookTask();
		task2.setPlaybookTaskId(2L);
		
		List<PlaybookTask> tasks = new ArrayList<>();
		tasks.add(task1);
		tasks.add(task2);

		PlayBook playBook = new PlayBook();
		playBook.setId(232L);
		playBook.setTasks(tasks);

		assertThat(jsonService.exec(playBook), any(String.class));

	}
	
	@Test
	void predFSLag() {
		
		PlaybookTask task1 = new PlaybookTask();
		PredecessorConstraint preCon =  new PredecessorConstraint();
		task1.setPlaybookTaskId(3L);
		preCon.setLag(5000);
		preCon.setId(4);
		PredecessorConstraintType pct =  new PredecessorConstraintType();
		pct.setId(23);
		pct.setName("finish-to-start");
		preCon.setPredecessorConstraintType(pct);
		task1.setPredecessorConstraint(preCon);
		PlaybookTask task2 = new PlaybookTask();
		task2.setPlaybookTaskId(4L);
		
		List<PlaybookTask> tasks = new ArrayList<>();
		tasks.add(task1);
		tasks.add(task2);

		PlayBook playBook = new PlayBook();
		playBook.setId(132L);
		playBook.setTasks(tasks);

		assertThat(jsonService.exec(playBook), any(String.class));
		
	}
	

	@Test
	void predSSNoLag() {
		
		PlaybookTask task1 = new PlaybookTask();
		PredecessorConstraint preCon =  new PredecessorConstraint();
		task1.setPlaybookTaskId(3L);
		preCon.setId(4);
		PredecessorConstraintType pct =  new PredecessorConstraintType();
		pct.setId(23);
		pct.setName("start-to-start");
		preCon.setPredecessorConstraintType(pct);
		task1.setPredecessorConstraint(preCon);
		PlaybookTask task2 = new PlaybookTask();
		task2.setPlaybookTaskId(4L);
		
		List<PlaybookTask> tasks = new ArrayList<>();
		tasks.add(task1);
		tasks.add(task2);

		PlayBook playBook = new PlayBook();
		playBook.setId(132L);
		playBook.setTasks(tasks);

		assertThat(jsonService.exec(playBook), any(String.class));
		
	}
	
	@Test
	void predSSLag() {
		
		PlaybookTask task1 = new PlaybookTask();
		PredecessorConstraint preCon =  new PredecessorConstraint();
		task1.setPlaybookTaskId(3L);
		preCon.setLag(5000);
		preCon.setId(4);
		PredecessorConstraintType pct =  new PredecessorConstraintType();
		pct.setId(23);
		pct.setName("start-to-start");
		preCon.setPredecessorConstraintType(pct);
		task1.setPredecessorConstraint(preCon);
		PlaybookTask task2 = new PlaybookTask();
		task2.setPlaybookTaskId(4L);
		
		List<PlaybookTask> tasks = new ArrayList<>();
		tasks.add(task1);
		tasks.add(task2);

		PlayBook playBook = new PlayBook();
		playBook.setId(132L);
		playBook.setTasks(tasks);

		assertThat(jsonService.exec(playBook), any(String.class));
		
	}
	
	@Test
	void startOnTime() {
		
		PlaybookTask task1 = new PlaybookTask();
		task1.setPlaybookTaskId(3L);
		task1.setConstraintDateTime(LocalDateTime.now().plusDays(1));
		List<PlaybookTask> tasks = new ArrayList<>();
		tasks.add(task1);

		PlayBook playBook = new PlayBook();
		playBook.setId(132L);
		playBook.setTasks(tasks);

		assertThat(jsonService.exec(playBook), any(String.class));
		
	}
	
	@Test
	void parentChild() {
		
		PlaybookTask task1 = new PlaybookTask();
		task1.setPlaybookTaskId(3L);
		PlaybookTask task2 = new PlaybookTask();
		task2.setPlaybookTaskId(4L);
		task2.setParent(true);
		
		List<PlaybookTask> tasks = new ArrayList<>();
		tasks.add(task1);
		tasks.add(task2);

		PlayBook playBook = new PlayBook();
		playBook.setId(132L);
		playBook.setTasks(tasks);

		assertThat(jsonService.exec(playBook), any(String.class));
		
	}

}
