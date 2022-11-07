package model;

import java.time.LocalDate;

public class Producer extends User {

	private String name;
	private String url;
	private int numberViews;
	private int totalTimePlayed;

	public Producer(String nickName, String id, LocalDate linkUpDate, String name, String url) {
		super(nickName, id, linkUpDate);
		this.name = name;
		this.url = url;
	}

}