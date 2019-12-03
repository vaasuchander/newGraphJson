/**
 * 
 */
package com.deloitte.model.json;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.deloitte.model.PlayBook;
import com.deloitte.service.DataService;

/**
 * @author vbejjanki
 *
 */

@Service
public class PlayBookService {

	@Inject
	private DataService cService;

	public PlayBook getPlayBook(final long playBookId) {
		return cService.getTaskList(playBookId);
	}

}
