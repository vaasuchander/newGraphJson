/**
 * 
 */
package com.deloitte.model;

import java.time.LocalDateTime;

/**
 * @author vbejjanki
 *
 */
public class ReleaseToEnvironment {

	private long id;

	private LocalDateTime startDateAndTime;

	private LocalDateTime endDateAndTime;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public LocalDateTime getStartDateAndTime() {
		return startDateAndTime;
	}

	public void setStartDateAndTime(LocalDateTime startDateAndTime) {
		this.startDateAndTime = startDateAndTime;
	}

	public LocalDateTime getEndDateAndTime() {
		return endDateAndTime;
	}

	public void setEndDateAndTime(LocalDateTime endDateAndTime) {
		this.endDateAndTime = endDateAndTime;
	}

}
