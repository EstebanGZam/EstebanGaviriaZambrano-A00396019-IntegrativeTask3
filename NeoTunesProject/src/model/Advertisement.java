package model;

public class Advertisement implements Playable {

	private String sponsor;
	private String announcementMessage;

	/**
	 * 
	 * @param sponsor
	 * @param announcementMessage
	 */
	public Advertisement(String sponsor, String announcementMessage) {
		this.sponsor = sponsor;
		this.announcementMessage = announcementMessage;
	}

	@Override
	public void play() {

	}

}