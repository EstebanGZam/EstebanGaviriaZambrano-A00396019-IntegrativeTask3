package model;

import java.util.ArrayList;

public class PlayList {

	// attributes
	private String name;
	private String code;
	private int[][] matrix;

	// relations
	private ArrayList<Audio> audios;

	/**
	 * 
	 * @param name
	 * @param code
	 */
	public PlayList(String name, int[][] matrix, String code, ArrayList<Audio> audios) {
		this.name = name;
		this.matrix = matrix;
		this.code = code;
		this.audios = audios;
	}

	/**
	 * @param name
	 * @return int
	 */
	public int audioPosition(String name) {

		int position = -1;
		boolean found = false;

		for (int i = 0; i < audios.size() && !found; i++) {
			if (audios.get(i) != null && audios.get(i).getName().equalsIgnoreCase(name)) {
				position = i;
				found = true;
			}
		}
		return position;
	}

	/**
	 * @param audio
	 */
	public void addAudio(Audio audio) {
		audios.add(audio);
	}

	/**
	 * @param position
	 */
	public void removeAudio(int position) {
		audios.remove(position);
	}

	/**
	 * @return ArrayList<Audio>
	 */
	public ArrayList<Audio> getAudios() {
		return this.audios;
	}

	/**
	 * @return String
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * @return String
	 */
	public String getCode() {
		return this.code;
	}

	/**
	 * @param code
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * @param matrix
	 */
	public void setmatrix(int[][] matrix) {
		this.matrix = matrix;
	}

	/**
	 * @return int[][]
	 */
	public int[][] getmatrix() {
		return this.matrix;
	}
}