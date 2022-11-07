package model;

import java.time.LocalTime;

public class Audio implements Playable {

	private String name;
	private LocalTime duration;
	private int numberOfPlays;

	/**
	 * 
	 * @param name
	 * @param duration
	 */
	public Audio(String name, LocalTime duration) {
		this.name = name;
		this.duration = duration;
	}

	@Override
	public void play() {
	}

	
	/** 
	 * @return String
	 */
	public String getName() {
		return this.name;
	}

}