package com.asdf.revenuerecognition.beans;

import java.io.Serializable;

public class AbstractBean implements Serializable {

	/**
	 *
	 */
	private Long id;

	/**
	 *
	 * @return
	 */
	public Long getId() {
		return id;
	}

	/**
	 *
	 * @param id
	 */
	public void setId(Long id) {
		this.id = id;
		
	}

}
