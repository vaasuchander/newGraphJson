/**
 * 
 */
package com.deloitte.model.json;

import java.util.HashSet;
import java.util.Set;

/**
 * @author vbejjanki
 *
 */
public class EdgeJson {

	private long from;

	private Set<Long> to = new HashSet<>();

	public long getFrom() {
		return from;
	}

	public void setFrom(long from) {
		this.from = from;
	}

	public Set<Long> getTo() {
		return to;
	}

	public void setTo(Set<Long> to) {
		this.to = to;
	}

}
