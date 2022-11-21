package model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

/**
 * <b>Name:</b> Consumer <br>
 * Consumer objects class.<br>
 */
public abstract class Consumer extends User {

    // attributes
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

    // methods
    /***
     * <b>Name:</b> Consumer <br>
     * <b>Description:</b> Constructor method of Consumer class. <br>
     * <b><i>pre:</i></b> <br>
     * <b><i>pos:</i></b> Consumer object is constructed <br>
     * 
     * @param nickname       Consumer user nickname.
     * @param id         Identity document of the consumer user
     * @param linkUpDate Date of user's link to the platform.
     */
    public Consumer(String nickname, String id, LocalDate linkUpDate) {
        super(nickname, id, linkUpDate);
        playbackHistory = new PlaybackHistory();
    }

    /**
     * <b>Name:</b> createPlayList <br>
     * <b>Description:</b> Method used for consumer users to create a playlist. <br>
     * <b><i>pre:</i></b> <br>
     * <b><i>pos:</i></b> <br>
     * 
     * @param name   Playlist name.
     * @param matrix Matrix used to generate the playlist code.
     * @param code   Playlist code.
     * @param audios Audios contained in the playlist.
     * 
     * @return String Indicates that the playlist has been created.
     */
    public abstract String createPlayList(String name, int[][] matrix, String code, ArrayList<Audio> audios);

    /**
     * <b>Name:</b> buySong <br>
     * <b>Description:</b> Method used by users to buy a song. <br>
     * <b><i>pre:</i></b> There must be songs registered on the platform. <br>
     * <b><i>pos:</i></b> <br>
     * 
     * @param song Song purchased.
     * @param date Date of purchase.
     * 
     * @return String Message indicating the final status of the purchase.
     */
    public abstract String buySong(Song song, LocalDate date);

    /**
     * <b>Name:</b> addPlayback <br>
     * <b>Description:</b> Method used to add a playback to the playback history.
     * <br>
     * <b><i>pre:</i></b> <br>
     * <b><i>pos:</i></b> Playback is added to the playback history. <br>
     * 
     * @param genre Genre of the song played.
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
     * <b>Name:</b> addPlayback <br>
     * <b>Description:</b> Method used to add a playback to the playback history.
     * <br>
     * <b><i>pre:</i></b> <br>
     * <b><i>pos:</i></b> Playback is added to the playback history. <br>
     * 
     * @param category Category of the podcast played.
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

    /**
     * <b>Name:</b> calculateMostListenedGenre <br>
     * <b>Description:</b> Method used to calculate the genre most listened to by a
     * consumer user.
     * <br>
     * <b><i>pre:</i></b> <br>
     * <b><i>pos:</i></b> The most listened genre and its number of reproductions is
     * updated. <br>
     */
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

    /**
     * <b>Name:</b> calculateMostListenedCategory <br>
     * <b>Description:</b> Method used to calculate the category most listened to by
     * a consumer user. <br>
     * <b><i>pre:</i></b> <br>
     * <b><i>pos:</i></b> The most listened category and its number of reproductions
     * is updated. <br>
     */

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

    /**
     * <b>Name:</b> getMostListenedGenre <br>
     * <b>Description:</b> Getter method of the attribute corresponding to genre
     * most listened by the consumer user. <br>
     * <b><i>pre:</i></b> <br>
     * <b><i>pos:</i></b> <br>
     * 
     * @return String Name of the genre most listened by the consumer user.
     */
    public String getMostListenedGenre() {
        return mostListenedGenre;
    }

    /**
     * <b>Name:</b> getPlaysMostListenedGenre <br>
     * <b>Description:</b> Getter method of the attribute corresponding to the
     * number of plays of the genre most listened by the consumer user. <br>
     * <b><i>pre:</i></b><br>
     * <b><i>pos:</i></b><br>
     * 
     * @return int Number of plays of the genre most listened by the consumer
     *         user.
     */
    public int getPlaysMostListenedGenre() {
        return playsMostListenedGenre;
    }

    /**
     * <b>Name:</b> getMostListenedCategory <br>
     * <b>Description:</b> Getter method of the attribute corresponding to category
     * most listened by the consumer user. <br>
     * <b><i>pre:</i></b> <br>
     * <b><i>pos:</i></b> <br>
     * 
     * @return String Name of the category most listened by the consumer user.
     */
    public String getMostListenedCategory() {
        return mostListenedCategory;
    }

    /**
     * <b>Name:</b> getPlaysMostListenedCategory <br>
     * <b>Description:</b> Getter method of the attribute corresponding to the
     * number of plays of the category most listened by the consumer user. <br>
     * <b><i>pre:</i></b> <br>
     * <b><i>pos:</i></b> <br>
     * 
     * @return int Number of plays of the category most listened by the consumer
     *         user.
     */
    public int getPlaysMostListenedCategory() {
        return playsMostListenedCategory;
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
     * @return boolean Indicates whether the user has already purchased the song or
     *         not.
     */
    public abstract boolean searchBuy(String songName);

}