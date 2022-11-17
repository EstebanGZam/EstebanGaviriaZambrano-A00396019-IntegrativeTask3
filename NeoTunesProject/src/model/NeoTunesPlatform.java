package model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Random;

public class NeoTunesPlatform {

    // relations
    private ArrayList<User> users;
    private ArrayList<Audio> audios;
    private ArrayList<Advertisement> advertisements;
    private Random random = new Random();

    // methods
    public NeoTunesPlatform() {
        users = new ArrayList<User>();
        audios = new ArrayList<Audio>();
        advertisements = new ArrayList<>();
        advertisements.add(new Advertisement("Nike", "Just Do It."));
        advertisements.add(new Advertisement("Coca-Cola", "Open Happiness."));
        advertisements.add(new Advertisement("M&M's", "Melts in Your Mouth, Not in Your Hands."));
    }

    /**
     * @param nickname
     * @param id
     * @param linkUpDate
     * @param name
     * @param urlImage
     * @param type
     * @return String
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
     * @param nickname
     * @param id
     * @param linkUpDate
     * @param type
     * @return String
     */
    public String addUser(String nickname, String id, LocalDate linkUpDate, int type) {
        String message = null;

        if (searchUser(id) != null) {
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
     * @param nickname
     * @return User
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
     * @param artistNickname
     * @param name
     * @param genre
     * @param urlAlbum
     * @param duration
     * @param saleValue
     * @return String
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
                objArtist.addSong(name, genre, urlAlbum, duration, saleValue);
                audios.add(new Song(name, genre, urlAlbum, duration, saleValue));
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
     * @param producerNickname
     * @param name
     * @param category
     * @param description
     * @param urlImage
     * @param duration
     * @return String
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
                objContentCreator.addPodCast(name, category, description, urlImage, duration);
                audios.add(new Podcast(name, category, description, urlImage, duration));
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
     * @param name
     * @return Audio
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
     * @param userNickname
     * @param playListName
     * @param audiosNames
     * @return String
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
     * @param userNickname
     * @param playListName
     * @return String
     */
    public String createPlaylist(String userNickname, String playListName) {
        String message = createPlaylist(userNickname, playListName, null);
        return message;
    }

    /**
     * @param nickname
     * @param playListName
     * @return PlayList
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
                        ArrayList<PlayList> playlists = objPremium.getPlayList();
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
     * @return int[][]
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
     * @param audios
     * @param matrix
     * @return String
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
     * @param nickname
     * @param playListName
     * @param audiosToAdd
     * @param audiosToRemove
     * @return String
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
                    objPlayList.setmatrix(matrix);
                    objPlayList.setCode(generatePlaylistCode(objPlayList.getAudios(), matrix));
                } else
                    message = "Alert. Nothing changed in the playlist, because their audios were not added or removed.";
            } else {
                message = "Error. This playlist was not registered by this user.";
            }
        } else if (objUser != null) {
            message = "Error. Playlist not edited, because only consumer users can edit them.";
        } else {
            message = "Error. Playlist not created, because the nickname is not registered with any of the platform users.";
        }
        return message;
    }

    /**
     * @param nickname
     * @param playListName
     * @param songName
     * @return boolean
     */
    public boolean searchSongInPlaylist(String nickname, String playListName, String songName) {
        boolean icontinue = false;
        PlayList objPlayList = searchPlaylist(nickname, playListName);
        if (objPlayList != null) {
            if (objPlayList.audioPosition(songName) != -1) {
                icontinue = true;
            }
        }
        return icontinue;
    }

    /**
     * @param nickname
     * @param playListName
     * @return String
     */
    public String sharePlaylist(String nickname, String playListName) {
        PlayList objPlayList = searchPlaylist(nickname, playListName);
        String message = null;
        if (objPlayList != null) {
            message = "\nOrigin Matrix: \n";
            int[][] matrix = objPlayList.getmatrix();
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
     * @return String
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
     * @return String
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
     * @param audioName
     * @return boolean
     */
    public boolean isSong(String audioName) {

        boolean song = false;

        Audio objAudio = searchAudio(audioName);
        if (objAudio != null && objAudio instanceof Song)
            song = true;
        return song;

    }

    /**
     * @param option
     * @return String
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
            } else if (objAudio instanceof Song) {
                Song objSong = (Song) objAudio;
                objConsumer.addPlayback(objSong.getGenre());
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
     * @param nickname
     * @param audioName
     * @return String
     */
    public String buySong(String nickname, String audioName, LocalDate date) {

        Audio objAudio = searchAudio(audioName);
        User objUser = searchUser(nickname);
        String message = null;

        if (objUser != null && objUser instanceof Consumer && objAudio != null && objAudio instanceof Song) {
            Song objSong = (Song) objAudio;
            message = "";
            if ((objUser instanceof Standard)) {
                message = ((Standard) objUser).buySong(objSong, date);
            } else if (objUser instanceof Premium) {
                message = ((Premium) objUser).buySong(objSong, date);
            }
        } else if (!(objUser instanceof Consumer) || objUser == null) {
            message = "Error. Consumer user not found.";
        } else if (objAudio instanceof Podcast) {
            message = "Error. A user can only buy songs.";
        } else {
            message = "Error. Song not found. Please check the name you entered and try again.";
        }
        return message;
    }

}