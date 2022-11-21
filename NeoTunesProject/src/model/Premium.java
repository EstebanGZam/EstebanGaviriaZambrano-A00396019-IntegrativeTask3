package model;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 * <b>Name:</b> Premium <br>
 * Premium objects class.<br>
 */
public class Premium extends Consumer {
    // relations
    private ArrayList<PlayList> playlists;
    private ArrayList<Buy> buys;

    // methods
    /**
     * <b>Name:</b> Premium <br>
     * <b>Description:</b> Constructor method of Premium users class. <br>
     * <b><i>pre:</i></b> <br>
     * <b><i>pos:</i></b> <br>
     *
     * @param nickname   Premium user nickname.
     * @param id         Identity document of the premium user.
     * @param linkUpDate Date of user's link to the platform.
     */
    public Premium(String nickname, String id, LocalDate linkUpDate) {
        super(nickname, id, linkUpDate);
        playlists = new ArrayList<PlayList>();
        buys = new ArrayList<Buy>();
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
        playlists.add(new PlayList(name, matrix, code, audios));
        String message = "Playlist created successfully!";
        return message;
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
        buys.add(new Buy(song, date));
        String message = "Song bought successfully";
        song.sale();
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
        for (int i = 0; i < buys.size() && !bought; i++) {
            if (buys.get(i).getSong().getName().equalsIgnoreCase(songName))
                bought = true;
        }
        return bought;
    }

    /**
     * <b>Name:</b> getPlayLists <br>
     * <b>Description:</b> Getter method of playlists created by the user. <br>
     * 
     * @return ArrayList Playlists created by the user.
     */
    public ArrayList<PlayList> getPlayLists() {
        return playlists;
    }

}
