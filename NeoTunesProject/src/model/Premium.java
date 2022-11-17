package model;

import java.time.LocalDate;
import java.util.ArrayList;

public class Premium extends Consumer {

    private ArrayList<PlayList> playlists;
    private ArrayList<Buy> buys;

    public Premium(String name, String id, LocalDate linkUpDate) {
        super(name, id, linkUpDate);
        playlists = new ArrayList<PlayList>();
        buys = new ArrayList<Buy>();
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
        playlists.add(new PlayList(name, matrix, code, audios));
        String message = "Playlist created successfully!";
        return message;
    }

    /**
     * @return ArrayList<PlayList>
     */
    public ArrayList<PlayList> getPlayList() {
        return playlists;
    }

    @Override
    public String buySong(Song song, LocalDate date) {
        buys.add(new Buy(song, date));
        String message = "Song bought successfully";
        song.sale();
        return message;
    }

}
