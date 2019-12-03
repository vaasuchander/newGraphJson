/**
 * 
 */
package com.deloitte.controller;

import java.util.Objects;

import javax.inject.Inject;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.deloitte.model.PlayBook;
import com.deloitte.model.json.GraphModel;
import com.deloitte.model.json.GraphTaskJson;
import com.deloitte.model.json.PlayBookService;
import com.deloitte.service.GraphService;
import com.deloitte.service.JsonService;

/**
 * @author vbejjanki
 *
 */

@RestController
public class ExecutionController {

	@Inject
	private GraphService graphService;

	@Inject
	private PlayBookService playBookService;

	@Inject
	private JsonService jService;

	/**
	 * Rest Service to Call the Execution Graph based on the playbook Id
	 * @param playBookId
	 * @return
	 */
	@GetMapping(value = "/executeGraph/{playBookId}",produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<GraphTaskJson> executeGraph(@PathVariable long playBookId) {
			PlayBook playBook = playBookService.getPlayBook(playBookId);
			if (Objects.nonNull(playBook)) {
				GraphModel graphModel = graphService.buildGraphModel(playBook);
				GraphTaskJson gJson = jService.processJsonTasks(graphModel, playBook.getId());
				return new ResponseEntity<>(gJson, HttpStatus.OK);
			}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
}
