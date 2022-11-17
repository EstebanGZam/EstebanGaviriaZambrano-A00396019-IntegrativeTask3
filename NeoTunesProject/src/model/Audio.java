package model;

import java.time.LocalTime;

public class Audio implements Playable {

	private String name;
	private LocalTime duration;
	private int numberOfPlays = 0;

	/**
	 * 
	 * @param name
	 * @param duration
	 */
	public Audio(String name, LocalTime duration) {
		this.name = name;
		this.duration = duration;
	}

	
	/** 
	 * @return String
	 */
	@Override
	public String play() {
		String message = "\nPlaying...";
		return message;
	}

	/**
	 * @return String
	 */
	public String getName() {
		return this.name;
	}

	
	/** 
	 * @return int
	 */
	public int getNumberOfPlays() {
		return this.numberOfPlays;
	}

	
	/** 
	 * @param numberOfPlays
	 */
	public void setNumberOfPlays(int numberOfPlays) {
		this.numberOfPlays = numberOfPlays;
	}

}