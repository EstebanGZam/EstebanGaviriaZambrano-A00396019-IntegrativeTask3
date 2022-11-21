package model;

import java.time.LocalDate;

/**
 * <b>Name:</b> Buy <br>
 * Buy objects class.<br>
 */
public class Buy {

	// attributes
	private Song song;
	private LocalDate date;

	// methods
	/**
	 * <b>Name:</b> Buy <br>
	 * <b>Description:</b> Constructor method of Buy class. <br>
	 * <b><i>pre:</i></b> Songs must be registered on the platform. <br>
	 * <b><i>pos:</i></b> Buy object is constructed. <br>
	 * 
	 * @param song Audio purchased.
	 * @param date Date of purchase.
	 */
	public Buy(Song song, LocalDate date) {
		this.song = song;
		this.date = date;
	}

	/**
	 * <b>Name:</b> getSong <br>
	 * <b>Description:</b> Getter method of the attribute corresponding to the song
	 * registered in the purchase. <br>
	 * <b><i>pre:</i></b> <br>
	 * <b><i>pos:</i></b> <br>
	 * 
	 * @return Song Object song registered to purchase.
	 */
	public Song getSong() {
		return song;
	}

}