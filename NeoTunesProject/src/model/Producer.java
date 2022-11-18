package model;

import java.time.LocalDate;

public abstract class Producer extends User {

	private String name;
	private String url;
	private int numberPlays;
	private int totalTimePlayed;

	public Producer(String nickName, String id, LocalDate linkUpDate, String name, String url) {
		super(nickName, id, linkUpDate);
		this.name = name;
		this.url = url;
	}

	public int getNumberPlays() {
		return numberPlays;
	}

	public abstract void calculatePlays();

	public void setNumberPlays(int numberPlays) {
		this.numberPlays = numberPlays;
	}

}