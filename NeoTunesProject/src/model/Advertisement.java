package model;

/**
 * <b>Name:</b> Advertisement <br>
 * Advertisement objects class.<br>
 */
public class Advertisement implements Playable {

	private String sponsor;
	private String announcementMessage;

	// methods
	/**
	 * <b>Name:</b> Advertisement <br>
	 * <b>Description:</b> Constructor method of Advertisement class.<br>
	 * <b><i>pre:</i></b> <br>
	 * <b><i>pos:</i> </b> The Advertisement object is built.<br>
	 *
	 * @param sponsor             Name of the company sponsoring the advertisement.
	 * @param announcementMessage Announcement message.
	 */
	public Advertisement(String sponsor, String announcementMessage) {
		this.sponsor = sponsor;
		this.announcementMessage = announcementMessage;
	}

	/**
	 * <b>Name:</b> play <br>
	 * <b>Description:</b> Method used to simulate the playback of an advertisement.
	 * <br>
	 * <b><i>pre:</i></b> <br>
	 * <b><i>pos:</i></b> <br>
	 * 
	 * @return String Message indicating that the advertisement is playing.
	 */
	@Override
	public String play() {
		String advertisements = "Playing advertisement...\n" + this.sponsor + " - " + this.announcementMessage + "\n";
		return advertisements;
	}

}