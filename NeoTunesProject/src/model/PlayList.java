package model;

import java.util.ArrayList;

/**
 * <b>Name:</b> PlayList <br>
 * PlayList objects class.<br>
 */
public class PlayList {

	// attributes
	private String name;
	private String code;
	private int[][] matrix;

	// relations
	private ArrayList<Audio> audios;

	// methods
	/**
	 * <b>Name:</b> PlayList <br>
	 * <b>Description:</b> Constructor method of PlayList class. <br>
	 * <b><i>pre:</i></b> <br>
	 * <b><i>pos:</i></b> <br>
	 * 
	 * @param name   Playlist name.
	 * @param matrix Matrix from which the code is generated.
	 * @param code   Code used to share the playlist.
	 * @param audios List of audios with which the playlist is created.
	 */
	public PlayList(String name, int[][] matrix, String code, ArrayList<Audio> audios) {
		this.name = name;
		this.matrix = matrix;
		this.code = code;
		this.audios = audios;
	}

	/**
	 * <b>Name:</b> audioPosition <br>
	 * <b>Description:</b> Method used to find the position of an audio within a
	 * playlist. <br>
	 * <b><i>pre:</i></b> <br>
	 * <b><i>pos:</i></b> <br>
	 * 
	 * @param name Playlist name.
	 * @return int Audio position within the playlist (The method returns -1 if the
	 *         song was not found or if it is not even registered on the platform).
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
	 * <b>Name:</b> addAudio <br>
	 * <b>Description:</b> Method used to add an audio to the playlist. <br>
	 * <b><i>pre:</i></b> The audio object must be registered in the platform. <br>
	 * <b><i>pos:</i></b> <br>
	 * 
	 * @param audio Audio object to add to the playlist
	 */
	public void addAudio(Audio audio) {
		audios.add(audio);
	}

	/**
	 * <b>Name:</b> removeAudio <br>
	 * <b>Description:</b> Method used to remove an audio of the playlist. <br>
	 * <b><i>pre:</i></b> <br>
	 * <b><i>pos:</i></b> <br>
	 * 
	 * @param position Position in which the audio is located within the playlist.
	 */
	public void removeAudio(int position) {
		audios.remove(position);
	}

	/**
	 * <b>Name:</b> getAudios <br>
	 * <b>Description:</b> Method used to access the list of audios registered in
	 * the playlist. <br>
	 * <b><i>pre:</i></b> <br>
	 * <b><i>pos:</i></b> <br>
	 * 
	 * @return ArrayList Audio list of the playlist.
	 */
	public ArrayList<Audio> getAudios() {
		return this.audios;
	}

	/**
	 * <b>Name:</b> getName <br>
	 * <b>Description:</b> Name attribute getter method. <br>
	 * 
	 * @return String Playlist name.
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * <b>Name:</b> getCode <br>
	 * <b>Description:</b> Code attribute getter method. <br>
	 * 
	 * @return String Playlist code.
	 */
	public String getCode() {
		return this.code;
	}

	/**
	 * <b>Name:</b> setCode <br>
	 * <b>Description:</b> Code attribute setter method. <br>
	 * 
	 * @param code New code attribute.
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * <b>Name:</b> getMatrix <br>
	 * <b>Description:</b> Matrix attribute getter method. <br>
	 * 
	 * @return int[][] Playlist matrix.
	 */
	public int[][] getMatrix() {
		return this.matrix;
	}

	/**
	 * <b>Name:</b> setMatrix <br>
	 * <b>Description:</b> Matrix attribute setter method. <br>
	 * 
	 * @param matrix New matrix attribute.
	 */
	public void setMatrix(int[][] matrix) {
		this.matrix = matrix;
	}

}