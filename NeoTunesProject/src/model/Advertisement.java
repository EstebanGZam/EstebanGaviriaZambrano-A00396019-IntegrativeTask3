package model;

public class Advertisement implements Playable {

	private String sponsor;
	private String announcementMessage;

	// methods
	/**
	 * 
	 * @param sponsor
	 * @param announcementMessage
	 */
	public Advertisement(String sponsor, String announcementMessage) {
		this.sponsor = sponsor;
		this.announcementMessage = announcementMessage;
	}

	
	/** 
	 * @return String
	 */
	@Override
	public String play() {
		String advertisements = "Playing advertisement...\n" + this.sponsor + " - " + this.announcementMessage + "\n";
		return advertisements;
	}

}