package model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Random;

/**
 * <b>Name:</b> NeoTunesPlatform <br>
 * NeoTunesPlatform objects class. Controller class.<br>
 */
public class NeoTunesPlatform {

    // relations
    private ArrayList<User> users;
    private ArrayList<Audio> audios;
    private ArrayList<Advertisement> advertisements;
    private Random random = new Random();

    // methods
    /**
     * <b>Name:</b> NeoTunesPlatform <br>
     * <b>Description:</b> Constructor method of NeoTunesPlatform class (Controller
     * class). <br>
     * <b><i>pre:</i></b> <br>
     * <b><i>pos:</i></b> <br>
     */
    public NeoTunesPlatform() {
        users = new ArrayList<User>();
        audios = new ArrayList<Audio>();
        advertisements = new ArrayList<>();
        advertisements.add(new Advertisement("Nike", "Just Do It."));
        advertisements.add(new Advertisement("Coca-Cola", "Open Happiness."));
        advertisements.add(new Advertisement("M&M's", "Melts in Your Mouth, Not in Your Hands."));
    }

    /**
     * <b>Name:</b> addUser <br>
     * <b>Description:</b> Method used to register producer users on the platform.
     * <br>
     * <b><i>pre:</i></b> It must be validated that the user type to be created is
     * valid (1 for content creators/ 2 for artist). <br>
     * <b><i>pos:</i></b> The producer user is registered on the platform. <br>
     * 
     * @param nickname   Unique identifier of the producer.
     * @param id         Producer identification document.
     * @param linkUpDate Date on which the user joined the platform.
     * @param name       Producer's name.
     * @param urlImage   URL with the producer photo or distinctive image.
     * @param type       Type of user to be registered (artist or content creator).
     * 
     * @return String Message indicating the creation of the producing user or the
     *         error occurred in the process.
     */
    public String addUser(String nickname, String id, LocalDate linkUpDate, String name, String urlImage,
            int type) {
        String message = null;

        if (searchUser(nickname) != null) {
            message = "Error. The nickname is already registered on the platform! Try again.";
        } else {
            switch (type) {
                case 1:
                    users.add(new ContentCreator(nickname, id, linkUpDate, name, urlImage));
                    message = "Content creator added successfully!";
                    break;
                case 2:
                    users.add(new Artist(nickname, id, linkUpDate, name, urlImage));
                    message = "Artist added successfully!";
                    break;
                default:
                    message = "Error. The user could not be created because I do not know how to type a valid option. Try again.";
                    break;
            }
        }

        return message;

    }

    /**
     * <b>Name:</b> addUser <br>
     * <b>Description:</b> Method used to register consumer users on the platform.
     * (Standard or premium) <br>
     * <b><i>pre:</i></b> It must be validated that the user type to be created is
     * valid (1 for standard/ 2 for Premium). <br>
     * <b><i>pos:</i></b> The consumer user is registered on the platform. <br>
     * 
     * @param nickname   Unique identifier of the Consumer.
     * @param id         Consumer identification document.
     * @param linkUpDate Date on which the user joined the platform.
     * @param type       Type of user to be registered.
     * @return String Message indicating the creation of the consumer user or
     *         the error occurred in the process.
     */
    public String addUser(String nickname, String id, LocalDate linkUpDate, int type) {
        String message = null;

        if (searchUser(nickname) != null) {
            message = "Error. The nickname is already registered on the platform. Try again.";
        } else {
            switch (type) {
                case 1:
                    users.add(new Standard(nickname, id, linkUpDate));
                    message = "Standard user added successfully!";
                    break;
                case 2:
                    users.add(new Premium(nickname, id, linkUpDate));
                    message = "Premium user added successfully!";
                    break;
                default:
                    message = "Error. The user could not be created because I do not know how to type a valid option. Try again.";
                    break;
            }
        }
        return message;
    }

    /**
     * <b>Name:</b> searchUser <br>
     * <b>Description:</b> Method used to search for a registered user on the
     * platform. <br>
     * <b><i>pre:</i></b> <br>
     * <b><i>pos:</i></b> <br>
     * 
     * @param nickname Unique user identifier
     * 
     * @return User User object found (or null).
     */
    public User searchUser(String nickname) {

        User objUser = null;
        boolean found = false;

        for (int i = 0; i < users.size() && !found; i++) {
            if (users.get(i).getNickname().equals(nickname)) {
                objUser = users.get(i);
                found = true;
            }
        }

        return objUser;

    }

    /**
     * <b>Name:</b> addAudio <br>
     * <b>Description:</b> Method used to register a song on the platform.<br>
     * <b><i>pre:</i></b> <br>
     * <b><i>pos:</i></b> The song is registered on the platform. <br>
     * 
     * @param artistNickname Unique identifier of the song's artist
     * @param name           Song name
     * @param genre          Genre name
     * @param urlAlbum       URL with the album cover of the album it belongs to
     * @param duration       Song duration
     * @param saleValue      Sales value of the song
     * 
     * @return String Message indicating the creation of the song or the error
     *         occurred in the process.
     */
    public String addAudio(String artistNickname, String name, int genre, String urlAlbum, LocalTime duration,
            double saleValue) {

        String message = null;

        if (searchAudio(name) != null)
            message = "The name of the audio is already registered on the platform. Try again.";
        else {
            User objUser = searchUser(artistNickname);
            if (objUser != null && objUser instanceof Artist) {
                Artist objArtist = (Artist) objUser;
                Song objSong = new Song(name, genre, urlAlbum, duration, saleValue);
                objArtist.addSong(objSong);
                audios.add(objSong);
                message = "Song registered on the platform successfully!";
            } else if (objUser != null) {
                message = "Error. The typed nickname doesn't belong to an artist.";
            } else {
                message = "Error. Artist not registered on the platform.";
            }

        }

        return message;

    }

    /**
     * <b>Name:</b> addAudio <br>
     * <b>Description:</b> Method used to register a podcast on the platform. <br>
     * <b><i>pre:</i></b> <br>
     * <b><i>pos:</i></b> The podcast is registered on the platform. <br>
     * 
     * @param producerNickname Unique identifier of the content creator who made the
     *                         podcast.
     * @param name             Podcast name
     * @param category         Podcast category
     * @param description      Podcast description
     * @param urlImage         URL with the distinctive image
     * @param duration         Podcast duration
     * 
     * @return String Message indicating the creation of the podcast or the error
     *         occurred in the process.
     */
    public String addAudio(String producerNickname, String name, int category, String description, String urlImage,
            LocalTime duration) {

        String message = null;

        if (searchAudio(name) != null)
            message = "The name of the audio is already registered on the platform. Try again.";
        else {
            User objUser = searchUser(producerNickname);
            if (objUser != null && objUser instanceof ContentCreator) {
                ContentCreator objContentCreator = (ContentCreator) objUser;
                Podcast objPodcast = new Podcast(name, category, description, urlImage, duration);
                objContentCreator.addPodCast(objPodcast);
                audios.add(objPodcast);
                message = "Podcast registered on the platform successfully!";
            } else if (objUser != null) {
                message = "Error. The typed nickname doesn't belong to an content creator.";
            } else {
                message = "Error. Content creator not registered on the platform.";
            }

        }

        return message;

    }

    /**
     * <b>Name:</b> searchAudio <br>
     * <b>Description:</b> Method used to search for a registered audio on the
     * platform. <br>
     * <b><i>pre:</i></b> <br>
     * <b><i>pos:</i></b> <br>
     * 
     * @param name Audio name
     * 
     * @return Audio Audio object found (or null).
     */
    public Audio searchAudio(String name) {

        Audio objAudio = null;
        boolean found = false;

        for (int i = 0; i < audios.size() && !found; i++) {
            if (audios.get(i).getName().equalsIgnoreCase(name)) {
                objAudio = audios.get(i);
                found = true;
            }
        }

        return objAudio;

    }

    /**
     * <b>Name:</b> createPlaylist <br>
     * <b>Description:</b> Method used to create a playlist. <br>
     * <b><i>pre:</i></b> The list of audios must be made up of audios that are
     * registered in the platform. <br>
     * <b><i>pos:</i></b> The playlist is registered on the platform. <br>
     * 
     * @param userNickname Unique identifier of the consumer user who created the
     *                     playlist.
     * @param playListName Playlist name.
     * @param audiosNames  List of audios added to the playlist.
     * 
     * @return String Message indicating the creation of the playlist or the error
     *         occurred in the process.
     */
    public String createPlaylist(String userNickname, String playListName, ArrayList<String> audiosNames) {

        String message = null;
        User objUser = searchUser(userNickname);

        if (objUser != null && objUser instanceof Consumer) {
            if (searchPlaylist(userNickname, playListName) != null) {
                message = "Error. Playlist not created, because the user already has a registered playlist with this name. Try to create the playlist with a different name";
            } else {
                ArrayList<Audio> audios = new ArrayList<Audio>();
                if (audiosNames != null && audiosNames.size() > 0) {
                    for (int i = 0; i < audiosNames.size(); i++) {
                        audios.add(searchAudio(audiosNames.get(i)));
                    }
                }
                int[][] matrix = generateMatrix();
                if (objUser instanceof Standard) {
                    Standard objStandard = (Standard) objUser;
                    message = objStandard.createPlayList(playListName, matrix, generatePlaylistCode(audios, matrix),
                            audios);
                } else if (objUser instanceof Premium) {
                    Premium objPremium = (Premium) objUser;
                    message = objPremium.createPlayList(playListName, matrix, generatePlaylistCode(audios, matrix),
                            audios);
                }
            }
        } else if (objUser != null) {
            message = "Error. Playlist not created, because only consumer users can create them.";
        } else {
            message = "Error. Playlist not created, because the nickname is not registered with any of the platform users.";
        }

        return message;

    }

    /**
     * <b>Name:</b> searchPlaylist <br>
     * <b>Description:</b> Method used to search for a registered playlist on the
     * platform. <br>
     * <b><i>pre:</i></b> <br>
     * <b><i>pos:</i></b> <br>
     * 
     * @param nickname     Unique identifier of the user who created the playlist.
     * @param playListName Playlist name.
     * 
     * @return PlayList PlayList object found (or null).
     */
    public PlayList searchPlaylist(String nickname, String playListName) {

        boolean found = false;
        Standard objStandard = null;
        Premium objPremium = null;
        PlayList objPlayList = null;

        for (int i = 0; i < users.size() && !found; i++) {
            User objUser = users.get(i);
            if (objUser instanceof Consumer)
                if (objUser.getNickname().equals(nickname)) {
                    if (objUser instanceof Standard) {
                        objStandard = (Standard) objUser;
                        for (int j = 0; j < Standard.PLAYLISTMAX && !found; j++) {
                            PlayList playList = objStandard.getPlayList(j);
                            if (playList != null && playList.getName().equalsIgnoreCase(playListName)) {
                                objPlayList = playList;
                                found = true;
                            }
                        }
                    } else if (objUser instanceof Premium) {
                        objPremium = (Premium) objUser;
                        ArrayList<PlayList> playlists = objPremium.getPlayLists();
                        for (int j = 0; j < playlists.size(); j++) {
                            PlayList playList = playlists.get(j);
                            if (playList.getName().equalsIgnoreCase(playListName)) {
                                objPlayList = playList;
                                found = true;
                            }
                        }
                    }

                }
        }

        return objPlayList;

    }

    /**
     * <b>Name:</b> generateMatrix <br>
     * <b>Description:</b> The method is used to create an array that will be used
     * to generate the code to share a playlist. <br>
     * <b><i>pre:</i></b> <br>
     * <b><i>pos:</i></b> A matrix is generated. <br>
     * 
     * @return int[][] Matrix generated.
     */
    public int[][] generateMatrix() {
        int rows = 6, columns = 6;
        int[][] matrix = new int[rows][columns];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                matrix[i][j] = (int) (random.nextDouble() * 10);
            }
        }
        return matrix;
    }

    /**
     * <b>Name:</b> generatePlaylistCode <br>
     * <b>Description:</b> Method used to generate the code used to share a
     * playlist. <br>
     * <b><i>pre:</i></b> <br>
     * <b><i>pos:</i></b> The playlist code is created. <br>
     * 
     * @param audios List of audios contained in the playlist.
     * @param matrix Matrix from which the code will be generated.
     * 
     * @return String Playlist code
     */
    public String generatePlaylistCode(ArrayList<Audio> audios, int[][] matrix) {
        String code = "";
        boolean songs = false, podcasts = false;

        for (int i = 0; i < audios.size(); i++) {
            if (audios.get(i) instanceof Song)
                songs = true;
            else if (audios.get(i) instanceof Podcast)
                podcasts = true;
        }
        if (songs && !podcasts) {
            for (int i = matrix.length; i > 0; i--) {
                code += matrix[(i - 1)][0] + "";
                if ((i - 1) == 0) {
                    for (int j = 1; j < matrix[0].length; j++) {
                        code += matrix[j][j] + "";
                        if (((j + 1) == matrix[0].length)) {
                            for (int x = (matrix.length - 1); x > 0; x--) {
                                code += matrix[(x - 1)][j] + "";
                            }
                        }
                    }
                }
            }
        } else if (!songs && podcasts) {
            for (int i = 0; i < matrix.length; i++) {
                code += matrix[0][i] + "";
                if ((i + 1) == (matrix.length / 2)) {
                    for (int j = 1; j < matrix[0].length; j++) {
                        code += matrix[j][i] + "";
                        if ((j + 1) == matrix[0].length) {
                            for (int x = j; x > -1; x--) {
                                code += matrix[x][(i + 1)] + "";
                            }
                        }
                    }
                    i++;
                }
            }
        } else if (songs && podcasts) {
            for (int i = matrix.length - 1; i > -1; i--) {
                for (int j = matrix.length - 1; j > -1; j--) {
                    if ((i + j) > 1 && (i + j) % 2 != 0) {
                        code += matrix[i][j] + "";
                    }
                }
            }
        } else {
            code = null;
        }

        return code;
    }

    /**
     * <b>Name:</b> editPlaylist <br>
     * <b>Description:</b> Method used to edit a playlist. <br>
     * <b><i>pre 1:</i></b> The audios that make up the list of audios to be added
     * must be registered in the platform. <br>
     * <b><i>pre 2:</i></b> The audios to be added must not be registered in the
     * playlist (they cannot be repeated).<br>
     * <b><i>pre 3:</i></b> The audios to be removed must be registered in the
     * playlist. <br>
     * <b><i>pos:</i></b> The playlist is edited. <br>
     * 
     * @param nickname       Unique identifier of the user who created the playlist.
     * @param playListName   Playlist name.
     * @param audiosToAdd    Audios to be added to the playlist.
     * @param audiosToRemove Audios to be removed of the playlist.
     * 
     * @return String Message indicating the edit of the playlist or the error
     *         occurred in the process.
     */
    public String editPlaylist(String nickname, String playListName, ArrayList<String> audiosToAdd,
            ArrayList<String> audiosToRemove) {

        String message = "";
        User objUser = searchUser(nickname);

        if (objUser != null && objUser instanceof Consumer) {
            PlayList objPlayList = searchPlaylist(nickname, playListName);
            if (objPlayList != null) {
                if (audiosToAdd != null && audiosToAdd.size() > 0) {
                    for (int i = 0; i < audiosToAdd.size(); i++) {
                        objPlayList.addAudio(searchAudio(audiosToAdd.get(i)));
                    }
                    message = "Audios added successfully!";
                }
                if (audiosToRemove != null && audiosToRemove.size() > 0) {
                    for (int j = 0; j < audiosToRemove.size(); j++) {
                        int position = objPlayList.audioPosition(audiosToRemove.get(j));
                        if (position != -1)
                            objPlayList.removeAudio(position);
                    }
                    message += "Audios removed successfully!";
                }
                if (audiosToAdd != null || audiosToRemove != null) {
                    int[][] matrix = generateMatrix();
                    objPlayList.setMatrix(matrix);
                    objPlayList.setCode(generatePlaylistCode(objPlayList.getAudios(), matrix));
                } else
                    message = "Alert. Nothing changed in the playlist, because their audios were not added or removed.";
            } else {
                message = "Error. This playlist was not registered by this user.";
            }
        } else if (objUser != null) {
            message = "Error. Playlist not edited, because only the user who created it can modify them.";
        } else {
            message = "Error. User not found.";
        }
        return message;
    }

    /**
     * <b>Name:</b> searchAudioInPlaylist <br>
     * <b>Description:</b> Method used to validate if an audio is registered within
     * a playlist. <br>
     * <b><i>pre:</i></b> <br>
     * <b><i>pos:</i></b> <br>
     * 
     * @param nickname     Unique identifier of the user who created the playlist.
     * @param playListName Playlist name.
     * @param audioName    Audio name.
     * 
     * @return boolean Indicates if the audio is registered in a specific playlist.
     */
    public boolean searchAudioInPlaylist(String nickname, String playListName, String audioName) {
        boolean found = false;
        PlayList objPlayList = searchPlaylist(nickname, playListName);
        if (objPlayList != null) {
            if (objPlayList.audioPosition(audioName) != -1) {
                found = true;
            }
        }
        return found;
    }

    /**
     * <b>Name:</b> sharePlaylist <br>
     * <b>Description:</b> Method used to share a playlist. <br>
     * <b><i>pre:</i></b> <br>
     * <b><i>pos:</i></b> <br>
     * 
     * @param nickname     Unique identifier of the user who created the playlist.
     * @param playListName Playlist name.
     * 
     * @return String Message that shows the code of the playlist with its
     *         corresponding matrix or the error for which the process could not be
     *         carried out.
     */
    public String sharePlaylist(String nickname, String playListName) {
        PlayList objPlayList = searchPlaylist(nickname, playListName);
        String message = null;
        if (objPlayList != null) {
            message = "\nOrigin Matrix: \n";
            int[][] matrix = objPlayList.getMatrix();
            for (int i = 0; i < matrix.length; i++) {
                message += "\n  ";
                for (int j = 0; j < matrix[0].length; j++) {
                    message += matrix[i][j] + "  ";
                }
            }
            message += "\n\nPlaylist identifier code: " + objPlayList.getCode();
        } else {
            message = "Error. Playlist not found. Check the data entered and try again.";
        }
        return message;
    }

    /**
     * <b>Name:</b> showSongs <br>
     * <b>Description:</b> Method used to display the list of songs registered on
     * the platform. <br>
     * <b><i>pre:</i></b> <br>
     * <b><i>pos:</i></b> <br>
     * 
     * @return String Message that contains the list of songs registered on the
     *         platform.
     */
    public String showSongs() {
        String songs = "List of songs registered on the platform:\n";
        if (audios.size() > 0) {
            for (int i = 0; i < audios.size(); i++) {
                if (audios.get(i) instanceof Song)
                    songs += "  - " + audios.get(i).getName() + "\n";
            }
        }
        if (songs.equals("List of songs registered on the platform:\n"))
            songs = "There are no songs registered on the platform.";

        return songs;
    }

    /**
     * <b>Name:</b> showPodcasts <br>
     * <b>Description:</b> Method used to display the list of podcasts registered on
     * the platform. <br>
     * <b><i>pre:</i></b> <br>
     * <b><i>pos:</i></b> <br>
     * 
     * @return String Message that contains the list of podcasts registered on the
     *         platform.
     */
    public String showPodcasts() {
        String podcasts = "List of podcasts registered on the platform:\n";
        if (audios.size() > 0) {
            for (int i = 0; i < audios.size(); i++) {
                if (audios.get(i) instanceof Podcast)
                    podcasts += "   - " + audios.get(i).getName() + "\n";
            }
        }
        if (podcasts.equals("List of podcasts registered on the platform:\n"))
            podcasts = "There are no podcasts registered on the platform.";

        return podcasts;
    }

    /**
     * <b>Name:</b> isSong <br>
     * <b>Description:</b> Method used to validate that an audio is a song. <br>
     * <b><i>pre:</i></b> <br>
     * <b><i>pos:</i></b> <br>
     * 
     * @param audioName Audio name.
     * 
     * @return boolean Indicates if the entered audio name corresponds to a song
     *         (the method returns false if the audio does not exist on the
     *         platform).
     */
    public boolean isSong(String audioName) {

        boolean song = false;

        Audio objAudio = searchAudio(audioName);
        if (objAudio != null && objAudio instanceof Song)
            song = true;

        return song;

    }

    /**
     * <b>Name:</b> simulatePlayback <br>
     * <b>Description:</b> Method used to simulate the reproduction of an audio that
     * is registered in the platform. <br>
     * <b><i>pre:</i></b> <br>
     * <b><i>pos:</i></b> The user's playback statistics are updated. <br>
     * 
     * @param nickname  Unique identifier of the user who created the playlist.
     * @param audioName Audio name.
     * @param ad        Indicates whether an ad should be played or not (Only
     *                  applies to songs, since in the case of podcasts the ads are
     *                  always played)
     * 
     * @return String Message showing a simulation of playback.
     */
    public String simulatePlayback(String nickname, String audioName, boolean ad) {
        Audio objAudio = searchAudio(audioName);
        User objUser = searchUser(nickname);
        String playing = null;

        if (objAudio != null && (objUser != null && objUser instanceof Consumer)) {
            Consumer objConsumer = (Consumer) objUser;
            playing = "";
            if ((objConsumer instanceof Standard && objAudio instanceof Podcast)
                    || (objConsumer instanceof Standard && ad)) {
                int numAd = random.nextInt(2 + 1);
                playing += "\n" + advertisements.get(numAd).play();
            }
            playing += objAudio.play() + "\n";
            objAudio.setNumberOfPlays(objAudio.getNumberOfPlays() + 1);

            if (objAudio instanceof Podcast) {
                Podcast objPodcast = (Podcast) objAudio;
                objConsumer.addPlayback(objPodcast.getCategory());
                objConsumer.calculateMostListenedCategory();
            } else if (objAudio instanceof Song) {
                Song objSong = (Song) objAudio;
                objConsumer.addPlayback(objSong.getGenre());
                objConsumer.calculateMostListenedGenre();
            }
        } else if (objUser == null) {
            playing = "Error. User not found.\n";
        } else if (objAudio == null) {
            playing = "Error. Audio not found. Please check the name you entered and try again.";
        } else {
            playing = "Error. Only consumer users can play audios.";
        }
        return playing;
    }

    /**
     * <b>Name:</b> buySong <br>
     * <b>Description:</b> Method used to purchase a song. <br>
     * <b><i>pre:</i></b> <br>
     * <b><i>pos:</i></b> The purchase is added to the corresponding user. <br>
     * 
     * @param nickname  Unique identifier of the user who created the playlist.
     * @param audioName Audio name.
     * @param date      Purchase date of the song.
     * 
     * @return String Message indicating the final status of the purchase.
     */
    public String buySong(String nickname, String audioName, LocalDate date) {

        Audio objAudio = searchAudio(audioName);
        User objUser = searchUser(nickname);
        String message = null;

        if (objUser != null && objUser instanceof Consumer && objAudio != null && objAudio instanceof Song) {
            Song objSong = (Song) objAudio;
            message = "";
            if ((objUser instanceof Standard) && !((Standard) objUser).searchBuy(audioName))
                message = ((Standard) objUser).buySong(objSong, date);
            else if (objUser instanceof Premium && !((Premium) objUser).searchBuy(audioName))
                message = ((Premium) objUser).buySong(objSong, date);
            if (message.equals(""))
                message = "Error. The user already bought this song.";
        } else if (!(objUser instanceof Consumer) || objUser == null) {
            message = "Error. Consumer user not found.";
        } else if (objAudio instanceof Podcast) {
            message = "Error. A user can only buy songs.";
        } else {
            message = "Error. Song not found. Please check the name you entered and try again.";
        }
        return message;
    }

    /**
     * <b>Name:</b> cumulativePlays <br>
     * <b>Description:</b> Method used to report the total accumulated plays across
     * the platform. <br>
     * <b><i>pre:</i></b> <br>
     * <b><i>pos:</i></b> <br>
     * 
     * @return String Report of the total accumulated plays across
     *         the platform.
     */
    public String cumulativePlays() {
        int songsPlays = 0, podcastsPlays = 0;
        String report = "There are no reproductions on the platform.";
        for (int i = 0; i < audios.size(); i++) {
            Audio objAudio = audios.get(i);
            if (objAudio instanceof Song)
                songsPlays += objAudio.getNumberOfPlays();
            else if (objAudio instanceof Podcast)
                podcastsPlays += objAudio.getNumberOfPlays();
        }
        if (songsPlays > 0 || podcastsPlays > 0)
            report = "Total songs plays: " + songsPlays + "\nTotal podcasts plays: " + podcastsPlays;
        return report;
    }

    /**
     * <b>Name:</b> mostListenedGenre <br>
     * <b>Description:</b> Method used to report the most listened song genre (name
     * and number of plays) for a specific user and for the entire platform. <br>
     * <b><i>pre:</i></b> <br>
     * <b><i>pos:</i></b> <br>
     * 
     * @param nickname User nickname
     * @return String Report of the most listened song genre (name
     *         and number of plays) for a specific user and for the entire platform.
     */
    public String mostListenedGenre(String nickname) {
        User objUser = searchUser(nickname);
        String report = null, mostListenedGenre = null;
        int playsMostListenedGenre = 0;

        if (objUser != null && objUser instanceof Consumer) {
            report = "User has no plays.";
            String mostListenedGenreUser = ((Consumer) objUser).getMostListenedGenre();
            int playsMostListenedGenreUser = ((Consumer) objUser).getPlaysMostListenedGenre();
            if (mostListenedGenreUser != null) {
                report = "Genre most listened by user " + nickname + ": " + mostListenedGenreUser
                        + "\nNumber of reproductions: " + playsMostListenedGenreUser;
            }
        }
        int rock = 0, pop = 0, trap = 0, house = 0;
        for (int i = 0; i < audios.size(); i++) {
            Audio objAudio = audios.get(i);
            if (objAudio instanceof Song) {
                switch (((Song) objAudio).getGenre()) {
                    case ROCK:
                        rock += objAudio.getNumberOfPlays();
                        break;
                    case POP:
                        pop += objAudio.getNumberOfPlays();
                        break;
                    case TRAP:
                        trap += objAudio.getNumberOfPlays();
                        break;
                    case HOUSE:
                        house += objAudio.getNumberOfPlays();
                        break;
                }
            }
        }

        int[] reproductions = { rock, pop, trap, house };
        for (int x = 0; x < reproductions.length; x++) {
            if (reproductions[x] > playsMostListenedGenre) {
                playsMostListenedGenre = reproductions[x];
                if (x == 0)
                    mostListenedGenre = "Rock";
                else if (x == 1)
                    mostListenedGenre = "Pop";
                else if (x == 2)
                    mostListenedGenre = "Trap";
                else if (x == 3)
                    mostListenedGenre = "House";
            }
        }
        if (mostListenedGenre == null)
            report = "There are no reproductions on the platform.";
        else
            report += "\nGenre most listened on the platform: " + mostListenedGenre
                    + "\nNumber of reproductions: " + playsMostListenedGenre;

        return report;
    }

    /**
     * <b>Name:</b> mostListenedCategory <br>
     * <b>Description:</b> Method used to report the most listened podcast category
     * (name and number of plays) for a specific user and for the entire platform.
     * <br>
     * <b><i>pre:</i></b> <br>
     * <b><i>pos:</i></b> <br>
     * 
     * @param nickname User nickname
     * @return String Report of the most listened podcast category
     *         (name and number of plays) for a specific user and for the entire
     *         platform.
     */
    public String mostListenedCategory(String nickname) {
        User objUser = searchUser(nickname);
        String report = null, mostListenedCategory = null;
        int playsMostListenedCategory = 0;

        if (objUser != null && objUser instanceof Consumer) {
            report = "User has no plays.";
            String mostListenedCategoryUser = ((Consumer) objUser).getMostListenedCategory();
            int playsMostListenedCategoryUser = ((Consumer) objUser).getPlaysMostListenedCategory();
            if (mostListenedCategoryUser != null) {
                report = "Category most listened by user " + nickname + ": " + mostListenedCategoryUser
                        + "\nNumber of reproductions: " + playsMostListenedCategoryUser;
            }
        }
        int politic = 0, entertainment = 0, videoGame = 0, fashion = 0;
        for (int i = 0; i < audios.size(); i++) {
            Audio objAudio = audios.get(i);
            if (objAudio instanceof Podcast) {
                switch (((Podcast) objAudio).getCategory()) {
                    case POLITIC:
                        politic += objAudio.getNumberOfPlays();
                        break;
                    case ENTERTAINMENT:
                        entertainment += objAudio.getNumberOfPlays();
                        break;
                    case VIDEO_GAME:
                        videoGame += objAudio.getNumberOfPlays();
                        break;
                    case FASHION:
                        fashion += objAudio.getNumberOfPlays();
                        break;
                }
            }
        }

        int[] reproductions = { politic, entertainment, videoGame, fashion };
        for (int x = 0; x < reproductions.length; x++) {
            if (reproductions[x] > playsMostListenedCategory) {
                playsMostListenedCategory = reproductions[x];
                if (x == 0)
                    mostListenedCategory = "Politic";
                else if (x == 1)
                    mostListenedCategory = "Entertainment";
                else if (x == 2)
                    mostListenedCategory = "Video games";
                else if (x == 3)
                    mostListenedCategory = "Fashion";
            }
        }
        if (mostListenedCategory == null)
            report = "There are no reproductions on the platform.";
        else
            report += "\nCategory most listened on the platform: " + mostListenedCategory
                    + "\nNumber of reproductions: " + playsMostListenedCategory;

        return report;
    }

    /**
     * <b>Name:</b> getTopFiveArtist <br>
     * <b>Description:</b> Method used to report the name and number of total plays
     * of each of the members of the Top 5 artists. <br>
     * <b><i>pre:</i></b> <br>
     * <b><i>pos:</i></b> <br>
     * 
     * @return String Report of the name and number of total plays of each of the
     *         members of the Top 5 artists.
     */
    public String getTopFiveArtist() {
        ArrayList<Producer> artists = new ArrayList<Producer>();
        String report = "There are no artists registered on the platform.";

        for (int i = 0; i < users.size(); i++) {
            User objUser = users.get(i);
            if (objUser instanceof Artist) {
                Artist objArtist = (Artist) objUser;
                objArtist.calculatePlays();
                artists.add(objArtist);
            }
        }
        if (artists.size() > 0) {
            Artist[] topArtists = new Artist[5];

            for (int x = 0; x < topArtists.length; x++) {

                int pos = getTopProducer(artists);
                if (pos != -1) {
                    topArtists[x] = (Artist) artists.get(pos);
                    artists.remove(pos);
                }

            }

            if (topArtists.length > 0) {
                report = "Artists top five: \n";
                for (int m = 0; m < topArtists.length; m++) {
                    if (topArtists[m] != null) {
                        report += "[ " + (m + 1) + " ] " + topArtists[m].getNickname() + " ("
                                + topArtists[m].getNumberPlays()
                                + " plays)\n";
                    } else {
                        report += "There are no more registered artists.\n";
                        m = topArtists.length;
                    }
                }
            } else {
                report = "There are no reproductions on the platform.";
            }
        }

        return report;

    }

    /**
     * <b>Name:</b> getTopFiveContentCreator <br>
     * <b>Description:</b> Method used to report the name and number of total plays
     * of each of the members of the Top 5 content creator. <br>
     * <b><i>pre:</i></b> <br>
     * <b><i>pos:</i></b> <br>
     * 
     * @return String Report of the name and number of total plays of each of the
     *         members of the Top 5 content creator.
     */
    public String getTopFiveContentCreator() {
        ArrayList<Producer> contentCreators = new ArrayList<Producer>();
        String report = "There are no content creators registered on the platform.";

        for (int i = 0; i < users.size(); i++) {
            User objUser = users.get(i);
            if (objUser instanceof ContentCreator) {
                ContentCreator objContentCreator = (ContentCreator) objUser;
                objContentCreator.calculatePlays();
                contentCreators.add(objContentCreator);
            }
        }
        if (contentCreators.size() > 0) {
            ContentCreator[] topContentCreators = new ContentCreator[5];

            for (int x = 0; x < topContentCreators.length; x++) {

                int pos = getTopProducer(contentCreators);
                if (pos != -1) {
                    topContentCreators[x] = (ContentCreator) contentCreators.get(pos);
                    contentCreators.remove(pos);
                }

            }

            report = "Content creators top five: \n";
            for (int m = 0; m < topContentCreators.length; m++) {
                if (topContentCreators[m] != null) {
                    report += "[ " + (m + 1) + " ] " + topContentCreators[m].getNickname() + " ("
                            + topContentCreators[m].getNumberPlays()
                            + " plays)\n";
                } else {
                    report += "There are no more content creators registered on the platform.\n";
                    m = topContentCreators.length;
                }
            }
            if (report.equalsIgnoreCase(
                    "Content creators top five: \nThere are no more content creators registered on the platform.\n"))
                report = "There are no content creator registered on the platform.";

        }

        return report;

    }

    /**
     * <b>Name:</b> getTopProducer <br>
     * <b>Description:</b> Method used to obtain the position of the producer with
     * more number of reproductions in the list of producers. <br>
     * <b><i>pre:</i></b> <br>
     * <b><i>pos:</i></b> <br>
     * 
     * @param producers List of producer users.
     * @return int Position of the producer with more number of reproductions in the
     *         list of producers.
     */
    public int getTopProducer(ArrayList<Producer> producers) {
        int position = -1;
        int plays = 0;
        for (int i = 0; i < producers.size(); i++) {
            Producer objProducer = producers.get(i);
            if (objProducer.getNumberPlays() >= plays) {
                plays = objProducer.getNumberPlays();
                position = i;
            }
        }

        return position;

    }

    /**
     * <b>Name:</b> getTopTenSongs <br>
     * <b>Description:</b> Method used to report the name, genre and total number of
     * plays for each of the members of the Top 10 Songs. <br>
     * <b><i>pre:</i></b> <br>
     * <b><i>pos:</i></b> <br>
     * 
     * @return String Report of the name, genre and total number of plays for each
     *         of the members of the Top 10 Songs.
     */
    public String getTopTenSongs() {
        ArrayList<Audio> songs = new ArrayList<Audio>();
        String report = "There are no songs registered on the platform.";

        for (int i = 0; i < audios.size(); i++) {
            Audio objAudio = audios.get(i);
            if (objAudio instanceof Song) {
                songs.add((Song) objAudio);
            }
        }
        if (songs.size() > 0) {
            Song[] topSongs = new Song[10];

            for (int x = 0; x < topSongs.length; x++) {

                int pos = getTopAudio(songs);
                if (pos != -1) {
                    topSongs[x] = (Song) songs.get(pos);
                    songs.remove(pos);
                }

            }

            report = "Songs top ten: \n";
            for (int m = 0; m < topSongs.length; m++) {
                if (topSongs[m] != null) {
                    report += "[ " + (m + 1) + " ] " + topSongs[m].toString() + "\n";
                } else {
                    report += "There are no more songs registered on the platform.\n";
                    m = topSongs.length;
                }
            }
            if (report.equalsIgnoreCase(
                    "Songs top ten: \nThere are no more songs registered on the platform.\n"))
                report = "There are no songs registered on the platform.";

        }

        return report;

    }

    /**
     * <b>Name:</b> getTopTenPodcasts <br>
     * <b>Description:</b> Method used to report the name, category and
     * total number of plays for each of the members of the top 10 podcasts. <br>
     * <b><i>pre:</i></b> <br>
     * <b><i>pos:</i></b> <br>
     * 
     * @return String Report of the name, category and total number of plays for
     *         each of the members of the Top 10 podcasts.
     */
    public String getTopTenPodcasts() {
        ArrayList<Audio> podcasts = new ArrayList<Audio>();
        String report = "There are no podcasts registered on the platform.";

        for (int i = 0; i < audios.size(); i++) {
            Audio objAudio = audios.get(i);
            if (objAudio instanceof Podcast) {
                podcasts.add((Podcast) objAudio);
            }
        }
        if (podcasts.size() > 0) {
            Podcast[] topPodcasts = new Podcast[10];

            for (int x = 0; x < topPodcasts.length; x++) {

                int pos = getTopAudio(podcasts);
                if (pos != -1) {
                    topPodcasts[x] = (Podcast) podcasts.get(pos);
                    podcasts.remove(pos);
                }

            }

            report = "Podcasts top ten: \n";
            for (int m = 0; m < topPodcasts.length; m++) {
                if (topPodcasts[m] != null) {
                    report += "[ " + (m + 1) + " ] " + topPodcasts[m].toString() + "\n";
                } else {
                    report += "There are no more podcasts registered on the platform.\n";
                    m = topPodcasts.length;
                }
            }
            if (report.equalsIgnoreCase(
                    "Podcasts top ten: \nThere are no more podcasts registered on the platform.\n"))
                report = "There are no podcasts registered on the platform.";

        }

        return report;

    }

    /**
     * <b>Name:</b> getTopAudio <br>
     * <b>Description:</b> Method used to obtain the position of the audio with
     * more number of reproductions in the array list of audios. <br>
     * <b><i>pre:</i></b> <br>
     * <b><i>pos:</i></b> <br>
     * 
     * @param audiosList List of audios.
     * @return int Position of the audio with more number of reproductions in the
     *         list of audios.
     */
    public int getTopAudio(ArrayList<Audio> audiosList) {
        int position = -1;
        int plays = 0;
        for (int i = 0; i < audiosList.size(); i++) {
            Audio objAudio = audiosList.get(i);
            if (objAudio.getNumberOfPlays() >= plays) {
                plays = objAudio.getNumberOfPlays();
                position = i;
            }
        }

        return position;

    }

    /**
     * <b>Name:</b> reportByGenre <br>
     * <b>Description:</b> Method used to report the number of songs sold and the
     * total sales value ($) of each genre. <br>
     * <b><i>pre:</i></b> <br>
     * <b><i>pos:</i></b> <br>
     * 
     * @return String Report of the number of songs sold and the total sales value
     *         ($) of each genre.
     */
    public String reportByGenre() {
        String report = null;
        int rockSales = 0, popSales = 0, trapSales = 0, houseSales = 0;
        int rockIncome = 0, popIncome = 0, trapIncome = 0, houseIncome = 0;
        for (int i = 0; i < audios.size(); i++) {
            Audio objAudio = audios.get(i);
            if (objAudio instanceof Song) {
                Song objSong = (Song) objAudio;
                switch (objSong.getGenre()) {
                    case ROCK:
                        rockSales += objSong.getNumberSales();
                        rockIncome += (objSong.getNumberSales() * objSong.getSaleValue());
                        break;
                    case POP:
                        popSales += objSong.getNumberSales();
                        popIncome += (objSong.getNumberSales() * objSong.getSaleValue());
                        break;
                    case TRAP:
                        trapSales += objSong.getNumberSales();
                        trapIncome += (objSong.getNumberSales() * objSong.getSaleValue());
                        break;
                    case HOUSE:
                        houseSales += objSong.getNumberSales();
                        houseIncome += (objSong.getNumberSales() * objSong.getSaleValue());
                        break;
                }
            }
        }

        if ((rockSales + popSales + trapSales + houseSales) > 0) {
            report = "\n";
            if (rockSales > 0)
                report += "\nRock:\n   - Songs sold: " + rockSales + "\n   - Total sales value: " + rockIncome + "$";
            if (popSales > 0)
                report += "\nPop:\n   - Songs sold: " + popSales + "\n   - Total sales value: " + popIncome + "$";
            if (trapSales > 0)
                report += "\nTrap:\n   - Songs sold: " + trapSales + "\n   - Total sales value: " + trapIncome + "$";
            if (houseSales > 0)
                report += "\nHouse:\n   - Songs sold: " + houseSales + "\n   - Total sales value: " + houseIncome + "$";
        } else
            report = "\nError. There are no sales of songs registered on the platform.";

        return report;

    }

    /**
     * <b>Name:</b> bestSellingSong <br>
     * <b>Description:</b> Method used to report the total number of sales and the
     * total sales value ($) of the top-selling song on the platform. <br>
     * <b><i>pre:</i></b> <br>
     * <b><i>pos:</i></b> <br>
     * 
     * @return String Report of the total number of sales and the total sales value
     *         ($) of the top-selling song on the platform.
     */
    public String bestSellingSong() {
        Song bestSeller = null;
        int moreSales = 0;
        String report = null;

        for (int i = 0; i < audios.size(); i++) {
            if (audios.get(i) instanceof Song) {
                if (((Song) audios.get(i)).getNumberSales() >= moreSales) {
                    bestSeller = (Song) audios.get(i);
                    moreSales = bestSeller.getNumberSales();
                }
            }
        }
        if (bestSeller != null && moreSales > 0) {
            report = "Best selling song name: " + bestSeller.getName() + "\nNumber of sales: "
                    + bestSeller.getNumberSales() + "\nTotal sales value: "
                    + (bestSeller.getNumberSales() * bestSeller.getSaleValue()) + "$";
        } else if (moreSales == 0) {
            report = "There are no songs sold.";
        } else {
            report = "Error. There are no songs registered on the platform.";
        }

        return report;

    }

}