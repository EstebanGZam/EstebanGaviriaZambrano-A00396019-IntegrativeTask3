package model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class ContentCreator extends Producer {

	// relations
	private ArrayList<Podcast> podcasts;

	public ContentCreator(String nickName, String id, LocalDate linkUpDate, String name, String url) {
		super(nickName, id, null, name, url);
		podcasts = new ArrayList<Podcast>();
	}

	
	/** 
	 * @param name
	 * @param category
	 * @param description
	 * @param urlImage
	 * @param duration
	 */
	public void addPodCast(String name, int category, String description, String urlImage, LocalTime duration) {
		podcasts.add(new Podcast(name, category, description, urlImage, duration));
	}

}