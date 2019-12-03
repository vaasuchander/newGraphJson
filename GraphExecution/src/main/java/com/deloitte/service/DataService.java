/**
 * 
 */
package com.deloitte.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;

import com.deloitte.model.PlayBook;
import com.deloitte.model.PredecessorConstraint;
import com.deloitte.model.PredecessorConstraintType;
import com.deloitte.model.PlaybookTask;

/**
 * @author vbejjanki
 *
 */

@Service
public class DataService {

	private Map<Long, HashMap<Long, PlaybookTask>> playBookTasksMap = new HashMap<>();

	private Map<Long, PlayBook> tasksMap = new HashMap<>();

	/**
	 * Initalizing the Playbook at Startup
	 */
	@PostConstruct
	public void setTasksList() {

		/**
		 * 2 Tasks task1 and Task2 added to taskList1 Object TaskList1 is added to the
		 * Playbook. Constraint : Predecessor FS No Lag task
		 */
		PlaybookTask task1 = new PlaybookTask();
		task1.setPlaybookTaskId(1L);
		PlaybookTask task2 = new PlaybookTask();
		task2.setPlaybookTaskId(2L);
		List<PlaybookTask> tasksList1 = new ArrayList<>();
		tasksList1.add(task1);
		tasksList1.add(task2);
		PlayBook playBook1 = new PlayBook();
		playBook1.setId(112);
		playBook1.setTasks(tasksList1);
		setPlayBook(playBook1);

		/**
		 * 2 Tasks task3 and Task4 added to taskList2 Object TaskList2 is added to the
		 * Playbook. task4 set as aParent . Constraint : Predecessor FS No Lag task
		 */
		PlaybookTask task3 = new PlaybookTask();
		task3.setPlaybookTaskId(5L);
		PlaybookTask task4 = new PlaybookTask();
		task4.setPlaybookTaskId(6L);
		task4.setParent(true);
		List<PlaybookTask> tasksList2 = new ArrayList<>();
		tasksList2.add(task3);
		tasksList2.add(task4);
		PlayBook playBook2 = new PlayBook();
		playBook2.setId(113L);
		playBook2.setTasks(tasksList2);
		setPlayBook(playBook2);

		/**
		 * 2 Tasks task5 and Task6 added to taskList3 Object TaskList3 is added to the
		 * Playbook. tas5 has the predecessor Constraint with time lag of 5000
		 * millisconds predecessor constarint Type of Finish to start Constraint :
		 * Predecessor FS Lag Task
		 */
		PlaybookTask task5 = new PlaybookTask();
		PredecessorConstraint preCon = new PredecessorConstraint();
		task5.setPlaybookTaskId(3L);
		preCon.setLag(5000);
		preCon.setId(4);
		PredecessorConstraintType pct = new PredecessorConstraintType();
		pct.setId(23);
		pct.setName("finish-to-start");
		preCon.setPredecessorConstraintType(pct);
		task5.setPredecessorConstraint(preCon);
		PlaybookTask task6 = new PlaybookTask();
		task6.setPlaybookTaskId(4L);
		List<PlaybookTask> tasksList3 = new ArrayList<>();
		tasksList3.add(task5);
		tasksList3.add(task6);
		PlayBook playBook3 = new PlayBook();
		playBook3.setId(114L);
		playBook3.setTasks(tasksList3);
		setPlayBook(playBook3);

		/**
		 * 2 Tasks task6 and Task7 added to taskList4 Object and TaskList4 is added to
		 * the Playbook. task7 has the predecessor Constraint predecessor constarint
		 * Type of start to start Constraint : Predecessor FS No Lag Task
		 */
		PlaybookTask task7 = new PlaybookTask();
		PredecessorConstraint preCon1 = new PredecessorConstraint();
		task7.setPlaybookTaskId(3L);
		preCon1.setId(4);
		PredecessorConstraintType pct1 = new PredecessorConstraintType();
		pct1.setId(23);
		pct1.setName("start-to-start");
		preCon1.setPredecessorConstraintType(pct1);
		task7.setPredecessorConstraint(preCon1);
		PlaybookTask task8 = new PlaybookTask();
		task8.setPlaybookTaskId(4L);
		List<PlaybookTask> tasksList4 = new ArrayList<>();
		tasksList4.add(task7);
		tasksList4.add(task8);
		PlayBook playBook4 = new PlayBook();
		playBook4.setId(115L);
		playBook4.setTasks(tasksList4);
		setPlayBook(playBook4);

		/**
		 * 2 Tasks task9 and Task10 added to taskList3 Object TaskList5 is added to the
		 * Playbook. task9 has the predecessor Constraint with time lag of 5000
		 * millisconds predecessor constarint Type of start to start Constraint :
		 * Predecessor FS Lag Task
		 */
		PlaybookTask task9 = new PlaybookTask();
		PredecessorConstraint preCon2 = new PredecessorConstraint();
		task9.setPlaybookTaskId(3L);
		preCon2.setLag(5000);
		preCon2.setId(4);
		PredecessorConstraintType pct2 = new PredecessorConstraintType();
		pct2.setId(23);
		pct2.setName("start-to-start");
		preCon2.setPredecessorConstraintType(pct2);
		task9.setPredecessorConstraint(preCon2);
		PlaybookTask task10 = new PlaybookTask();
		task10.setPlaybookTaskId(4L);
		List<PlaybookTask> tasksList5 = new ArrayList<>();
		tasksList5.add(task9);
		tasksList5.add(task10);
		PlayBook playBook5 = new PlayBook();
		playBook5.setId(116L);
		playBook5.setTasks(tasksList5);
		setPlayBook(playBook5);

		/**
		 * Task 11 is the Must start on Time Task which is added to the taskList6 and to
		 * the Playbook
		 */
		PlaybookTask task11 = new PlaybookTask();
		task11.setPlaybookTaskId(3L);
		task11.setConstraintDateTime(LocalDateTime.now().plusDays(1));
		List<PlaybookTask> tasksList6 = new ArrayList<>();
		tasksList6.add(task11);
		PlayBook playBook6 = new PlayBook();
		playBook6.setId(117L);
		playBook6.setTasks(tasksList6);
		setPlayBook(playBook6);
	}

	/**
	 * adds the Playbook to the playbooks Map
	 * 
	 * @param playBook
	 */
	public void setPlayBookTasks(PlayBook playBook) {
		if (Objects.nonNull(playBook)) {
			playBookTasksMap.computeIfAbsent(playBook.getId(), tasksMap -> new HashMap<>())
					.putAll(playBook.getTasks().stream().filter(Objects::nonNull)
							.collect(Collectors.toMap(PlaybookTask::getPlaybookTaskId, Function.identity(), (t1, t2) -> t1)));
		}
	}

	/**
	 * adds the PlaybookTasks to the playbook Map
	 * 
	 * @param playBook
	 */
	public void setPlayBook(PlayBook playBook) {
		tasksMap.computeIfAbsent(playBook.getId(), p -> playBook);
	}

	/**
	 * fetches the PlaybookTasks Map based on the playbook id
	 * 
	 * @param playBookId
	 * @return
	 */
	public Map<Long, PlaybookTask> getMapTaskList(long playBookId) {
		return playBookTasksMap.getOrDefault(playBookId, new HashMap<>());
	}

	/**
	 * fetches the Playbook based on the playbook id
	 * 
	 * @param playBookId
	 * @return
	 */
	public PlayBook getTaskList(long playBookId) {
		return tasksMap.getOrDefault(playBookId, null);
	}

}
