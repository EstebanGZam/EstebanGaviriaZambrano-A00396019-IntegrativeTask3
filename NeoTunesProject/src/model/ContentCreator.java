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
	public void addPodCast(Podcast podcast) {
		podcasts.add(podcast);
	}

	@Override
	public void calculatePlays() {
		int plays = 0;
		for (int i = 0; i < podcasts.size(); i++) {
			plays += podcasts.get(i).getNumberOfPlays();
		}
		super.setNumberPlays(plays);
	}

}