package model;

import java.time.LocalTime;

public class Podcast extends Audio {

	// attributes
	private String description;
	private String urlImage;

	// relations
	private Category category;

	/**
	 * 
	 * @param name
	 * @param description
	 * @param urlImage
	 * @param duration
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
	 * @return String
	 */
	@Override
	public String play() {
		String message = super.play() + "\nPodcast: " + super.getName();
		return message;
	}

	/**
	 * @return Category
	 */
	public Category getCategory() {
		return category;
	}

	@Override
	public String toString() {
		return super.getName() + " (Category: " + category + ", Number of plays: " + super.getNumberOfPlays() + ") ";
	}

}