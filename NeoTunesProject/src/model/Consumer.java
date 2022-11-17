package model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public abstract class Consumer extends User {

    private LocalTime timePlayedSongs;
    private LocalTime timePlayedPodcast;
    private String mostListenedGenre;
    private int playsMostListenedGenre;
    private String mostListenedCategory;
    private int playsMostListenedCategory;
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

    public void calculateMostListenedGenre() {
        int[] reproductions = { playbackHistory.getRock(), playbackHistory.getPop(), playbackHistory.getTrap(),
                playbackHistory.getHouse() };
        String mostReproduced = null;
        int higher = 0;

        for (int x = 0; x < reproductions.length; x++) {
            if (reproductions[x] > higher) {
                higher = reproductions[x];
                if (x == 0)
                    mostReproduced = "Rock";
                else if (x == 1)
                    mostReproduced = "Pop";
                else if (x == 2)
                    mostReproduced = "Trap";
                else if (x == 3)
                    mostReproduced = "House";
            }
        }
        this.mostListenedGenre = mostReproduced;
        this.playsMostListenedGenre = higher;
    }

    public void calculateMostListenedCategory() {
        int[] reproductions = { playbackHistory.getPolitic(), playbackHistory.getEntertainment(),
                playbackHistory.getVideoGame(),
                playbackHistory.getFashion() };
        String mostReproduced = null;
        int higher = 0;

        for (int x = 0; x < reproductions.length; x++) {
            if (reproductions[x] > higher) {
                higher = reproductions[x];
                if (x == 0)
                    mostReproduced = "Politic";
                else if (x == 1)
                    mostReproduced = "Entertainment";
                else if (x == 2)
                    mostReproduced = "Video games";
                else if (x == 3)
                    mostReproduced = "Fashion";
            }
        }
        this.mostListenedCategory = mostReproduced;
        this.playsMostListenedCategory = higher;
    }

    public String getMostListenedGenre() {
        return mostListenedGenre;
    }

    public int getPlaysMostListenedGenre() {
        return playsMostListenedGenre;
    }

    public String getMostListenedCategory() {
        return mostListenedCategory;
    }

    public int getPlaysMostListenedCategory() {
        return playsMostListenedCategory;
    }

}