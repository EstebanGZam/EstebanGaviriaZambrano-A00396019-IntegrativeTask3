package model;

import java.time.LocalTime;

import javax.swing.MenuElement;

/**
 * <b>Name:</b> Podcast <br>
 * Podcast objects class.<br>
 */
public class Podcast extends Audio {

	// attributes
	private String description;
	private String urlImage;

	// relations
	private Category category;

	// methods
	/**
	 * <b>Name:</b> Podcast <br>
	 * <b>Description:</b> Constructor method of Podcast class. <br>
	 * <b><i>pre:</i></b> <br>
	 * <b><i>pos:</i></b> <br>
	 *
	 * @param name        Podcast name.
	 * @param category    Podcast category.
	 * @param description Podcast description.
	 * @param urlImage    URL with the distinctive image.
	 * @param duration    Podcast duration.
	 */
	public Podcast(String name, int category, String description, String urlImage, LocalTime duration) {
		super(name, duration);
		switch (category) {
			case 1:
				this.category = Category.POLITIC;
				break;
			case 2:
				this.category = Category.ENTERTAINMENT;
				break;
			case 3:
				this.category = Category.VIDEO_GAME;
				break;
			case 4:
				this.category = Category.FASHION;
				break;

		}
		this.urlImage = urlImage;
		this.description = description;
	}

	/**
	 * <b>Name:</b> play <br>
	 * <b>Description:</b> Method used to simulate the playback of a podcast. <br>
	 * <b><i>pre:</i></b> <br>
	 * <b><i>pos:</i></b> <br>
	 * 
	 * @return String Message indicating that the podcast is playing.
	 */
	@Override
	public String play() {
		String message = super.play() + "\nPodcast: " + super.getName();
		return message;
	}

	/**
	 * <b>Name:</b> getCategory <br>
	 * <b>Description:</b> Podcast category attribute getter method. <br>
	 * 
	 * @return Category Podcast category.
	 */
	public Category getCategory() {
		return category;
	}

	/**
	 * <b>Name:</b> toString <br>
	 * <b>Description:</b> Method used to display the podcast information. <br>
	 * 
	 * @return String Podcast information.
	 */
	@Override
	public String toString() {
		String message = super.getName() + " (Category: " + category + ", Number of plays: " + super.getNumberOfPlays()
				+ ") ";
		return message;
	}

}