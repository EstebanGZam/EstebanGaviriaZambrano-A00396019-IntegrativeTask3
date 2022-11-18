package model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class Artist extends Producer {

	private ArrayList<Song> songs;

	public Artist(String nickName, String id, LocalDate linkUpDate, String name, String url) {
		super(nickName, id, linkUpDate, name, url);
		songs = new ArrayList<Song>();
	}

	/**
	 * @param name
	 * @param genre
	 * @param urlAlbum
	 * @param duration
	 * @param saleValue
	 */
	public void addSong(Song song) {
		songs.add(song);
	}

	@Override
	public void calculatePlays() {
		int plays = 0;
		for (int i = 0; i < songs.size(); i++) {
			plays += songs.get(i).getNumberOfPlays();
		}
		super.setNumberPlays(plays);
	}

}