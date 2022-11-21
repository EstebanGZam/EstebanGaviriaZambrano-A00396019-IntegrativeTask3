package model;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 * <b>Name:</b> Artist <br>
 * Artist objects class.<br>
 */
public class Artist extends Producer {

	// relations
	private ArrayList<Song> songs;

	// methods
	/**
	 * <b>Name:</b> Artist <br>
	 * <b>Description:</b> Constructor method of Artist class.<br>
	 * <b><i>pre:</i></b> <br>
	 * <b><i>pos:</i></b> The Artist object is built.<br>
	 * 
	 * @param nickname   Unique artist identifier.
	 * @param id         Artist identification document.
	 * @param linkUpDate Date on which the user joined the platform.
	 * @param name       Artist's name.
	 * @param urlImage   URL with the artist photo or distinctive image.
	 */

	public Artist(String nickname, String id, LocalDate linkUpDate, String name, String urlImage) {
		super(nickname, id, linkUpDate, name, urlImage);
		songs = new ArrayList<Song>();
	}

	/**
	 * <b>Name:</b> addSong <br>
	 * <b>Description:</b> Method used to add a song to the artist. <br>
	 * <b><i>pre:</i></b> <br>
	 * <b><i>pos:</i></b> The song is added to the artist. <br>
	 * 
	 * @param song Object song that has been created by the artist.
	 */
	public void addSong(Song song) {
		songs.add(song);
	}

	/**
	 * <b>Name:</b> calculatePlays <br>
	 * <b>Description:</b> Method used to calculate the total number of plays of an
	 * artist's songs. <br>
	 * <b><i>pre:</i></b> <br>
	 * <b><i>pos:</i></b> The number of reproductions of an artist in the
	 * corresponding attribute is changed.<br>
	 */
	@Override
	public void calculatePlays() {
		int plays = 0;
		for (int i = 0; i < songs.size(); i++) {
			plays += songs.get(i).getNumberOfPlays();
		}
		super.setNumberPlays(plays);
	}

}