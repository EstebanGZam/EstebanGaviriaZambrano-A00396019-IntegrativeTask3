package model;

import java.time.LocalTime;

/**
 * <b>Name:</b> Audio <br>
 * Audio objects class.<br>
 */
public class Audio implements Playable {

	// attributes
	private String name;
	private LocalTime duration;
	private int numberOfPlays = 0;

	// methods
	/**
	 * <b>Name:</b> Audio <br>
	 * <b>Description:</b> Constructor method of Audio class. <br>
	 * <b><i>pre:</i></b> <br>
	 * <b><i>pos:</i></b> Audio object is constructed <br>
	 * 
	 * @param name     Audio name.
	 * @param duration Audio duration.
	 */
	public Audio(String name, LocalTime duration) {
		this.name = name;
		this.duration = duration;
	}

	/**
	 * <b>Name:</b> play <br>
	 * <b>Description:</b> Method used to simulate the playback of an audio.
	 * <br>
	 * <b><i>pre:</i></b> <br>
	 * <b><i>pos:</i></b> <br>
	 * 
	 * @return String Message indicating that the audio is playing.
	 */
	@Override
	public String play() {
		String message = "\nPlaying...";
		return message;
	}

	/**
	 * <b>Name:</b> getName <br>
	 * <b>Description:</b> Getter method of the attribute corresponding to the audio
	 * name. <br>
	 * <b><i>pre:</i></b> <br>
	 * <b><i>pos:</i></b> <br>
	 * 
	 * @return String Audio name
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * <b>Name:</b> getNumberOfPlays <br>
	 * <b>Description:</b> Getter method of the attribute corresponding to number of
	 * times the audio has been played back. <br>
	 * <b><i>pre:</i></b> <br>
	 * <b><i>pos:</i></b> <br>
	 * 
	 * @return int Number of times the audio has been played back.
	 */
	public int getNumberOfPlays() {
		return this.numberOfPlays;
	}

	/**
	 * <b>Name:</b> setNumberOfPlays <br>
	 * <b>Description:</b> Setter method of the attribute corresponding to number of
	 * times the audio has been played back. <br>
	 * <b><i>pre:</i></b> <br>
	 * <b><i>pos:</i></b> <br>
	 * 
	 * @param numberOfPlays New number of times the audio has been played back.
	 */
	public void setNumberOfPlays(int numberOfPlays) {
		this.numberOfPlays = numberOfPlays;
	}

}