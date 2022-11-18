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

    public int getTopProducer(ArrayList<Producer> users) {
        int position = -1;
        int plays = 0;
        for (int i = 0; i < users.size(); i++) {
            Producer objProducer = users.get(i);
            if (objProducer.getNumberPlays() >= plays) {
                plays = objProducer.getNumberPlays();
                position = i;
            }
        }
        return position;
    }

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

    public int getTopAudio(ArrayList<Audio> audios) {
        int position = -1;
        int plays = 0;
        for (int i = 0; i < audios.size(); i++) {
            Audio objAudio = audios.get(i);
            if (objAudio.getNumberOfPlays() >= plays) {
                plays = objAudio.getNumberOfPlays();
                position = i;
            }
        }
        return position;
    }

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
                report += "\nRock:\n   - Songs sold: " + rockSales + "\n   - Total sales value: " + rockIncome;
            if (popSales > 0)
                report += "\nPop:\n   - Songs sold: " + popSales + "\n   - Total sales value: " + popIncome;
            if (trapSales > 0)
                report += "\nTrap:\n   - Songs sold: " + trapSales + "\n   - Total sales value: " + trapIncome;
            if (houseSales > 0)
                report += "\nHouse:\n   - Songs sold: " + houseSales + "\n   - Total sales value: " + houseIncome;
        } else
            report = "\nError. There are no sales of songs registered on the platform.";

        return report;
    }

}