package com.asdf.revenuerecognition.models;

import java.io.Serializable;

public class AbstractModel implements Serializable {

	/**
	 *
	 */
	private Long ID;

	/**
	 *
	 * @return
	 */
	public Long getID() {
		return ID;
	}

	/**
	 *
	 * @param id
	 */
	public void setID(Long id) {
		this.ID = id;
		
	}

}
