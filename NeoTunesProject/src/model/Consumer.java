package model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public abstract class Consumer extends User {

    private LocalTime timePlayedSongs;
    private LocalTime timePlayedPodcast;
    private String mostListenedGenre;
    private String mostListenedCategory;
    private String mostStreamedArtist;
    private String mostStreamedContentCreator;

    public Consumer(String name, String id, LocalDate linkUpDate) {
        super(name, id, linkUpDate);
    }

    public abstract String createPlayList(String name, int[][] matriz, String code, ArrayList<Audio> audios);

}