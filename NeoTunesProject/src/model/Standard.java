package model;

import java.time.LocalDate;
import java.util.ArrayList;

public class Standard extends Consumer {

    // relations
    private PlayList[] playlists;
    private Buy[] buys;

    // constants
    public static final int PLAYLISTMAX = 20;
    public static final int BUYS = 100;

    // relations
    public Standard(String name, String id, LocalDate linkUpDate) {
        super(name, id, linkUpDate);
        playlists = new PlayList[PLAYLISTMAX];
        buys = new Buy[BUYS];
    }

    
    /** 
     * @param name
     * @param matriz
     * @param code
     * @param audios
     * @return String
     */
    @Override
    public String createPlayList(String name, int[][] matriz, String code, ArrayList<Audio> audios) {

        String message = null;
        boolean stop = false;

        for (int i = 0; i < PLAYLISTMAX && !stop; i++) {
            if (playlists[i] == null) {
                playlists[i] = new PlayList(name, matriz, code, audios);
                message = "Playlist added successfully!";
                stop = true;
            }
        }
        if (!stop)
            message = "Error. Standard user " + this.getNickname() + " has reached the maximum number of playlists.";

        return message;

    }

    
    /** 
     * @param position
     * @return PlayList
     */
    public PlayList getPlayList(int position) {
        return playlists[position];
    }

}
