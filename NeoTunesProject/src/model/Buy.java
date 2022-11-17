package model;

import java.time.LocalDate;

public class Buy {

	// attributes
	private Song song;
	private LocalDate date;

	/**
	 * 
	 * @param date
	 */
	public Buy(Song song, LocalDate date) {
		this.song = song;
		this.date = date;
	}

}