package model;

import java.time.LocalDateTime;

public class Buy {

	// attributes
	private LocalDateTime date;

	// relations
	private Buyable buy;

	/**
	 * 
	 * @param date
	 */
	public Buy(LocalDateTime date) {
		this.date = date;
	}

}