package model;

import java.time.LocalTime;

/**
 * <b>Name:</b> Song <br>
 * <b>Description:</b> Song objects class. <br>
 */
public class Song extends Audio implements Salable {

	// attributes
	private String urlAlbum;
	private double saleValue;
	private int numberSales = 0;

	// relations
	private Genre genre;

	// methods
	/**
	 * <b>Name:</b> Song <br>
	 * <b>Description:</b> Constructor method of Song class. <br>
	 * <b><i>pre:</i></b> <br>
	 * <b><i>pos:</i></b> <br>
	 * 
	 * @param name      Song name.
	 * @param genre     Song genre.
	 * @param urlAlbum  URL with the album art to which it belongs.
	 * @param duration  Song duration.
	 * @param saleValue Song sale value.
	 */
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

	/**
	 * <b>Name:</b> sale <br>
	 * <b>Description:</b> Method used to increase the number of sales of a song.
	 * <br>
	 */
	@Override
	public void sale() {
		this.numberSales++;
	}

	/**
	 * <b>Name:</b> play <br>
	 * <b>Description:</b> Method used to simulate the playing of the song. <br>
	 * 
	 * @return String Message indicating that the song is playing.
	 */
	@Override
	public String play() {
		String message = super.play() + "\nSong: " + super.getName();
		return message;
	}

	/**
	 * <b>Name:</b> toString <br>
	 * <b>Description:</b> Method used to display the song information. <br>
	 * 
	 * @return String Song information.
	 */
	@Override
	public String toString() {
		String message = super.getName() + " (Genre: " + genre + ", Number of plays: " + super.getNumberOfPlays()
				+ ") ";
		return message;
	}

	/**
	 * <b>Name:</b> getGenre <br>
	 * <b>Description:</b> Song genre getter method. <br>
	 * 
	 * @return Genre Song genre attribute.
	 */
	public Genre getGenre() {
		return genre;
	}

	/**
	 * <b>Name:</b> getNumberSales <br>
	 * <b>Description:</b> Number sales getter method. <br>
	 * 
	 * @return int Number sales attribute.
	 */
	public int getNumberSales() {
		return numberSales;
	}

	/**
	 * <b>Name:</b> getSaleValue <br>
	 * <b>Description:</b> Sale value getter method. <br>
	 * 
	 * @return double Sale value attribute.
	 */
	public double getSaleValue() {
		return saleValue;
	}

}