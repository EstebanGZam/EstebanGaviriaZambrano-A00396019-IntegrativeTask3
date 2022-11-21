package model;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 * <b>Name:</b> Standard <br>
 * <b>Description:</b> Standard objects class. <br>
 */
public class Standard extends Consumer {

    // relations
    private PlayList[] playlists;
    private Buy[] buys;

    // constants
    /**
     * <b>name:</b> PLAYLISTMAX <br>
     * Limit of playlist that can be created. <br>
     */
    public static final int PLAYLISTMAX = 20;
    /**
     * <b>name:</b> BUYS <br>
     * Limit of purchases that a standard user can make. <br>
     */
    public static final int BUYS = 100;

    // methods
    /**
     * <b>Name:</b> Standard <br>
     * <b>Description:</b> Constructor method of Standard users class. <br>
     * <b><i>pre:</i></b> <br>
     * <b><i>pos:</i></b> <br>
     * 
     * @param nickname   Standard user nickname
     * @param id         Identity document of the standard user.
     * @param linkUpDate Date of user's link to the platform.
     */
    public Standard(String nickname, String id, LocalDate linkUpDate) {
        super(nickname, id, linkUpDate);
        playlists = new PlayList[PLAYLISTMAX];
        buys = new Buy[BUYS];
    }

    /**
     * <b>Name:</b> createPlayList <br>
     * <b>Description:</b> Method used to create a playlist. <br>
     * <b><i>pre:</i></b> The list of audios must be registered in the platform.
     * <br>
     * <b><i>pos:</i></b> The playlist is registered on the platform. <br>
     * 
     * @param name   Playlist name.
     * @param matrix Matrix from which the code is generated.
     * @param code   Code used to share the playlist.
     * @param audios List of audios with which the playlist is created.
     * 
     * @return String Message indicating that the playlist was created successfully.
     */
    @Override
    public String createPlayList(String name, int[][] matrix, String code, ArrayList<Audio> audios) {

        String message = null;
        boolean created = false;

        for (int i = 0; i < PLAYLISTMAX && !created; i++) {
            if (playlists[i] == null) {
                playlists[i] = new PlayList(name, matrix, code, audios);
                message = "Playlist created successfully!";
                created = true;
            }
        }
        if (!created)
            message = "Error. Standard user " + this.getNickname() + " has reached the maximum number of playlists.";

        return message;

    }

    /**
     * <b>Name:</b> getPlayList <br>
     * <b>Description:</b> Method used to access a playlist created by the standard
     * user. <br>
     * <b><i>pre:</i></b> The playlist arraylist must have at least one playlist
     * added. <br>
     * <b><i>pos:</i></b> <br>
     * 
     * @param position Playlist position.
     * 
     * @return PlayList Playlist found in the requested position.
     */
    public PlayList getPlayList(int position) {
        return playlists[position];
    }

    /**
     * <b>Name:</b> buySong <br>
     * <b>Description:</b> Method used by the premium user to purchase a song. <br>
     * <b><i>pre:</i></b> The purchased song must be registered on the platform.
     * <br>
     * <b><i>pos:</i></b> The song is purchased by the premium user. <br>
     * 
     * @param song Object that corresponds to the purchased song.
     * @param date Purchase date.
     * 
     * @return String Message indicating the final status of the purchase.
     */
    @Override
    public String buySong(Song song, LocalDate date) {

        String message = null;
        boolean bought = false;

        for (int i = 0; i < BUYS && !bought; i++) {
            if (buys[i] == null) {
                buys[i] = new Buy(song, date);
                song.sale();
                message = "Song bought successfully!";
                bought = true;
            }
        }
        if (!bought)
            message = "Error. Standard user " + this.getNickname() + " has reached the purchase limit.";

        return message;

    }

    /**
     * <b>Name:</b> searchBuy <br>
     * <b>Description:</b> Method used to find out if the user has already purchased
     * a song. <br>
     * <b><i>pre:</i></b> <br>
     * <b><i>pos:</i></b> <br>
     * 
     * @param songName Name of the song that user want to know if it is already
     *                 purchased.
     * 
     * @return boolean Indicates whether the user has already purchased the song or
     *         not.
     */
    @Override
    public boolean searchBuy(String songName) {
        boolean bought = false;
        for (int i = 0; i < BUYS && !bought; i++) {
            if (buys[i] != null && buys[i].getSong().getName().equalsIgnoreCase(songName))
                bought = true;
        }
        return bought;
    }

}
