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
     * @param matriz
     * @param code
     * @param audios
     * @return String
     */
    @Override
    public String createPlayList(String name, int[][] matriz, String code, ArrayList<Audio> audios) {
        playlists.add(new PlayList(name, matriz, code, audios));
        String message = "Playlist added successfully!";
        return message;
    }

    
    /** 
     * @return ArrayList<PlayList>
     */
    public ArrayList<PlayList> getPlayList() {
        return playlists;
    }

}
