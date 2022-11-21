package model;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 * <b>Name:</b> ContentCreator <br>
 * ContentCreator objects class.<br>
 */
public class ContentCreator extends Producer {

	// relations
	private ArrayList<Podcast> podcasts;

	// methods
	/**
	 * <b>Name:</b> ContentCreator <br>
	 * <b>Description:</b> Constructor method of ContentCreator class. <br>
	 * <b><i>pre:</i></b> <br>
	 * <b><i>pos:</i></b> Content creator object is constructed <br>
	 * 
	 * @param nickName   Unique identifier of the content creator.
	 * @param id         Content creator identification document.
	 * @param linkUpDate Date on which the user joined the platform.
	 * @param name       Content creator's name.
	 * @param url        URL with the content creator photo or distinctive image.
	 */

	public ContentCreator(String nickName, String id, LocalDate linkUpDate, String name, String url) {
		super(nickName, id, null, name, url);
		podcasts = new ArrayList<Podcast>();
	}

	/**
	 * <b>Name:</b> addPodcast <br>
	 * <b>Description:</b> Method used to add a podcast to the content creator. <br>
	 * <b><i>pre:</i></b> <br>
	 * <b><i>pos:</i></b> The podcast is added to the content creator. <br>
	 * 
	 * @param podcast Podcast created by the content creator.
	 */
	public void addPodCast(Podcast podcast) {
		podcasts.add(podcast);
	}

	/**
	 * <b>Name:</b> calculatePlays <br>
	 * <b>Description:</b> Method used to calculate the total number of plays of
	 * a content creator's podcasts. <br>
	 * <b><i>pre:</i></b> <br>
	 * <b><i>pos:</i></b> The number of reproductions of a content creator in the
	 * corresponding attribute is changed.<br>
	 */
	@Override
	public void calculatePlays() {
		int plays = 0;
		for (int i = 0; i < podcasts.size(); i++) {
			plays += podcasts.get(i).getNumberOfPlays();
		}
		super.setNumberPlays(plays);
	}

}