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
     * @param matrix
     * @param code
     * @param audios
     * @return String
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
     * @param position
     * @return PlayList
     */
    public PlayList getPlayList(int position) {
        return playlists[position];
    }

    /**
     * @param nameSong
     * @return String
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

}
