package model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Random;

public class NeoTunesPlatform {

    // relations
    private ArrayList<User> users;
    private ArrayList<Audio> audios;
    private Random random = new Random();

    // methods
    public NeoTunesPlatform() {
        users = new ArrayList<User>();
        audios = new ArrayList<Audio>();
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
                int[][] matriz = generateMatriz();
                if (objUser instanceof Standard) {
                    Standard objStandard = (Standard) objUser;
                    message = objStandard.createPlayList(playListName, matriz, generatePlaylistCode(audios, matriz),
                            audios);
                } else if (objUser instanceof Premium) {
                    Premium objPremium = (Premium) objUser;
                    message = objPremium.createPlayList(playListName, matriz, generatePlaylistCode(audios, matriz),
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
    public int[][] generateMatriz() {
        int[][] matriz = new int[6][6];

        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 6; j++) {
                matriz[i][j] = (int) (random.nextDouble() * 10);
            }
        }
        return matriz;
    }

    /**
     * @param audios
     * @param matriz
     * @return String
     */
    public String generatePlaylistCode(ArrayList<Audio> audios, int[][] matriz) {
        String code = "";
        boolean songs = false, podcasts = false;

        for (int i = 0; i < audios.size(); i++) {
            if (audios.get(i) instanceof Song)
                songs = true;
            else if (audios.get(i) instanceof Podcast)
                podcasts = true;
        }
        if (songs && !podcasts) {
            for (int i = 6; i > 0; i--) {
                code += matriz[(i - 1)][0] + "";
                if ((i - 1) == 0) {
                    for (int j = 1; j < 6; j++) {
                        code += matriz[j][j] + "";
                        if (((j + 1) == 6)) {
                            for (int x = 5; x > 0; x--) {
                                code += matriz[(x - 1)][j] + "";
                            }
                        }
                    }
                }
            }
        } else if (!songs && podcasts) {
            for (int i = 0; i < 6; i++) {
                code += matriz[0][i] + "";
                if ((i + 1) == 3) {
                    for (int j = 1; j < 6; j++) {
                        code += matriz[j][i] + "";
                        if ((j + 1) == 6) {
                            for (int x = j; x > -1; x--) {
                                code += matriz[x][(i + 1)] + "";
                            }
                        }
                    }
                    i = 3;
                }
            }
        } else if (songs && podcasts) {
            for (int i = 5; i > -1; i--) {
                for (int j = 5; j > -1; j--) {
                    if ((i + j) > 1 && (i + j) % 2 != 0) {
                        code += matriz[i][j] + "";
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
                    int[][] matriz = generateMatriz();
                    objPlayList.setMatriz(matriz);
                    objPlayList.setCode(generatePlaylistCode(objPlayList.getAudios(), matriz));
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
            int[][] matriz = objPlayList.getMatriz();
            for (int i = 0; i < 6; i++) {
                for (int j = 0; j < 6; j++) {
                    if ((i + j) == 0)
                        message += "[ " + matriz[i][j] + ", ";
                    else if (i == 5 && j == 5)
                        message += matriz[i][j] + " ]\n";
                    else if (j == 5)
                        message += matriz[i][j] + "\n  ";
                    else
                        message += matriz[i][j] + ", ";
                }
            }
            message += "\nPlaylist identifier code: " + objPlayList.getCode();
        } else {
            message = "Error. Playlist not found. Check the data entered and try again.";
        }
        return message;
    }

}
