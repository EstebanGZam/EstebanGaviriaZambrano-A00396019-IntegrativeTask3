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

    // relations
    private PlaybackHistory playbackHistory;

    public Consumer(String name, String id, LocalDate linkUpDate) {
        super(name, id, linkUpDate);
        playbackHistory = new PlaybackHistory();
    }

    public abstract String createPlayList(String name, int[][] matrix, String code, ArrayList<Audio> audios);

    public abstract String buySong(Song song, LocalDate date);

    /**
     * @param genre
     */
    public void addPlayback(Genre genre) {
        switch (genre) {
            case ROCK:
                playbackHistory.setRock(playbackHistory.getRock() + 1);
                break;
            case TRAP:
                playbackHistory.setTrap(playbackHistory.getTrap() + 1);
                break;
            case POP:
                playbackHistory.setPop(playbackHistory.getPop() + 1);
                break;
            case HOUSE:
                playbackHistory.setHouse(playbackHistory.getHouse() + 1);
                break;
        }
    }

    /**
     * @param category
     */
    public void addPlayback(Category category) {
        switch (category) {
            case POLITIC:
                playbackHistory.setPolitic(playbackHistory.getPolitic() + 1);
                break;
            case ENTERTAINMENT:
                playbackHistory.setEntertainment(playbackHistory.getEntertainment() + 1);
                break;
            case VIDEO_GAME:
                playbackHistory.setVideoGame(playbackHistory.getVideoGame() + 1);
                break;
            case FASHION:
                playbackHistory.setFashion(playbackHistory.getFashion() + 1);
                break;
        }
    }

}