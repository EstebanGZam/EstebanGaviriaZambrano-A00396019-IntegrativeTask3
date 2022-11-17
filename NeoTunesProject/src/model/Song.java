package model;

import java.time.LocalTime;

public class Song extends Audio implements Salable {

	// attributes
	private String urlAlbum;
	private double saleValue;
	private int numberSales = 0;

	// relations
	private Genre genre;

	public Song(String name, int genre, String urlAlbum, LocalTime duration, double saleValue) {
		super(name, duration);
		switch (genre) {
			case 1:
				this.genre = Genre.ROCK;
				break;
			case 2:
				this.genre = Genre.POP;
				break;
			case 3:
				this.genre = Genre.TRAP;
				break;
			case 4:
				this.genre = Genre.HOUSE;
				break;
		}
		this.urlAlbum = urlAlbum;
		this.saleValue = saleValue;
	}

	@Override
	public void sale() {
		this.numberSales++;
	}

	/**
	 * @return String
	 */
	@Override
	public String play() {
		String message = super.play() + "\nSong: " + super.getName();
		return message;
	}

	/**
	 * @return Genre
	 */
	public Genre getGenre() {
		return genre;
	}

}