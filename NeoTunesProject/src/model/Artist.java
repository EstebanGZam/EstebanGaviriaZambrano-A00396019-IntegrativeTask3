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
	public void addSong(String name, int genre, String urlAlbum, LocalTime duration, double saleValue) {
		songs.add(new Song(name, genre, urlAlbum, duration, saleValue));
	}

}