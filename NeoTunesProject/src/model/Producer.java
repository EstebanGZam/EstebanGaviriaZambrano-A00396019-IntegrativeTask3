package model;

import java.time.LocalDate;

/**
 * <b>Name:</b> Producer <br>
 * Producer objects class.<br>
 */
public abstract class Producer extends User {

	private String name;
	private String urlImage;
	private int numberPlays;
	private int totalTimePlayed;

	/**
	 * <b>Name:</b> Producer <br>
	 * <b>Description:</b> Constructor method of Producer users class. <br>
	 * <b><i>pre:</i></b> <br>
	 * <b><i>pos:</i></b> <br>
	 *
	 * @param nickName   Producer user nickname.
	 * @param id         Identity document of the producer user.
	 * @param linkUpDate Date of user's link to the platform.
	 * @param name       Producer username.
	 * @param urlImage   URL with your distinctive photo or image.
	 */
	public Producer(String nickName, String id, LocalDate linkUpDate, String name, String urlImage) {
		super(nickName, id, linkUpDate);
		this.name = name;
		this.urlImage = urlImage;
	}

	/**
	 * <b>Name:</b> getNumberPlays <br>
	 * <b>Description:</b> Plays number getter method. <br>
	 * 
	 * @return int Number of reproductions.
	 */
	public int getNumberPlays() {
		return numberPlays;
	}

	/**
	 * <b>Name:</b> calculatePlays <br>
	 * <b>Description:</b> Method used to calculate the reproductions of the works
	 * of a producer user. <br>
	 */
	public abstract void calculatePlays();

	/**
	 * <b>Name:</b> setNumberPlays <br>
	 * <b>Description:</b> Plays number setter method. <br>
	 * 
	 * @param numberPlays New number of reproductions.
	 */
	public void setNumberPlays(int numberPlays) {
		this.numberPlays = numberPlays;
	}

}