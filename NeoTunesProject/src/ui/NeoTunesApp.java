package ui;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Scanner;

import model.NeoTunesPlatform;

/**
 * <b>Name:</b> NeoTunesApp <br>
 * <b>Description:</b> NeoTunesApp objects class. Program interface class. <br>
 * <b>Objective:</b> The objective of the program is to manage a platform
 * oriented to the streaming service.
 * 
 * @author Esteban Gaviria Zambrano
 * @version 1.0
 */
public class NeoTunesApp {

    // relations
    /**
     * <b>name:</b> input <br>
     * Instance line of the Scanner object.
     */
    private static Scanner input = new Scanner(System.in);

    /**
     * <b>name:</b> objController <br>
     * Instance line of the NeoTunesPlatform object (Controller).
     */
    private static NeoTunesPlatform objController;

    // methods
    /**
     * <b>Name:</b> NeoTunesApp <br>
     * <b>Description:</b> Constructor method of NeoTunesApp class.<br>
     * <b><i>pre: </i></b> <br>
     * <b><i>pos:</i> </b> The NeoTunesApp object is built.<br>
     */
    public NeoTunesApp() {
        objController = new NeoTunesPlatform();
    }

    /**
     * <b>Name:</b> main <br>
     * <b>Description:</b> Main method. It is used to run the program.<br>
     * 
     * @param args String[]
     */
    public static void main(String[] args) {
        NeoTunesApp objMain = new NeoTunesApp();

        System.out.println("----- Welcome to the neoTunes streaming platform! -----\n");
        int option = 0;
        do {
            objMain.menu();
            option = input.nextInt();
            input.nextLine();
            switch (option) {
                case 1:
                    objMain.addUser(option);
                    break;
                case 2:
                    objMain.addUser(option);
                    break;
                case 3:
                    objMain.addAudio();
                    break;
                case 4:
                    objMain.createPlayList();
                    break;
                case 5:
                    objMain.editPlaylist();
                    break;
                case 6:
                    objMain.sharePlaylist();
                    break;
                case 7:
                    objMain.simulatePlayback();
                    break;
                case 8:
                    objMain.buySong();
                    break;
                case 9:
                    System.out.print("\n1) Total accumulated reproductions on the platform.\n"
                            + "2) Most listened song genre for a specific user and for the platform.\n"
                            + "3) Most listened podcast category for a specific user and for the platform.\n"
                            + "4) Name and number of total reproductions of the members of the Top 5 artists and the Top 5 content creators.\n"
                            + "5) Name, genre or category and total number of reproductions of each of the members of the Top 10 songs and the Top 10 podcast.\n"
                            + "6) Number of songs sold and total sales value ($) of each genre.\n"
                            + "7) Total number of sales and total sales value ($) of the best-selling song on the platform.\n"
                            + "Option: ");
                    int optionReport = input.nextInt();
                    input.nextLine();
                    switch (optionReport) {
                        case 1:
                            objMain.cumulativePlays();
                            break;
                        case 2:
                            objMain.mostListenedGenre();
                            break;
                        case 3:
                            objMain.mostListenedCategory();
                            break;
                        case 4:
                            objMain.getTopsProducers();
                            break;
                        case 5:
                            objMain.getTopsAudios();
                            break;
                        case 6:
                            objMain.reportGenreSales();
                            break;
                        case 7:
                            objMain.bestSellingSong();
                            break;
                        default:
                            System.out.println("\nError. Type a valid option.");
                            break;
                    }
                    break;
                case 0:
                    System.out.println("\nThank you for using the program. Until next time!");
                    break;
                default:
                    System.out.println("\nError. Type a valid option.");
                    break;
            }
        } while (option != 0);
    }

    /**
     * <b>Name:</b> menu <br>
     * <b>Description:</b> Method used to display the main menu. <br>
     * <b><i>pre:</i></b> info <br>
     * <b><i>pos:</i></b> info <br>
     */

    public void menu() {
        System.out.print("Select an option:\n" +
                "1) Register producer users.\n" +
                "2) Register consumer.\n" +
                "3) Register songs and podcasts.\n" +
                "4) Create a playlist.\n" +
                "5) Edit a playlist.\n" +
                "6) Share a playlist.\n" +
                "7) Simulate playback of a song or podcast.\n" +
                "8) Buy a song.\n" +
                "9) Generate reports.\n" +
                "0) Exit.\n" +
                "Option: ");
    }

    /**
     * <b>Name:</b> addUser <br>
     * <b>Description:</b> Method used to add users (consumers and producers) <br>
     * <b><i>pre:</i></b> Only 1 or 2 must be entered to select the type of user to
     * be registered. <br>
     * <b><i>pos:</i></b> The user is registered on the platform <br>
     * 
     * @param type If the program user selects option one, then the process to
     *             register a producer user will begin. If the number entered is a
     *             2, a consumer user will be registered.
     */
    public void addUser(int type) {
        String nickname, name, id, urlImage;
        int linkUpYear, linkUpMonth, linkUpDay, counter = 0;

        switch (type) {
            case 1:
                int producerType = 0;
                do {
                    if (counter > 0)
                        System.out.println("Error. Type a valid option.");
                    counter++;
                    System.out.print(
                            "\nType the number corresponding to the type of producer user you want to register:\n1) Content creator\n2) Artist\nOption: ");
                    producerType = input.nextInt();
                    input.nextLine();
                } while (producerType < 1 || producerType > 2);
                System.out.print("\nUser's nickname: ");
                nickname = input.nextLine();
                System.out.print("User's name: ");
                name = input.nextLine();
                System.out.print("User ID: ");
                id = input.nextLine();
                System.out.print("Date of linking to the platform:\nYear: ");
                linkUpYear = input.nextInt();
                input.nextLine();
                System.out.print("Month: ");
                linkUpMonth = input.nextInt();
                input.nextLine();
                System.out.print("Day: ");
                linkUpDay = input.nextInt();
                input.nextLine();
                System.out.print("URL with the distinctive image of the user: ");
                urlImage = input.nextLine();

                System.out.println("\n" + objController.addUser(nickname, id, LocalDate.of(linkUpYear,
                        linkUpMonth, linkUpDay), name, urlImage, producerType) + "\n");
                break;
            case 2:
                int consumerType = 0;
                do {
                    if (counter > 0)
                        System.out.println("Error. Type a valid option.");
                    counter++;
                    System.out.print(
                            "\nType the number corresponding to the type of user you want to register:\n1) Standard\n2) Premium\nOption: ");
                    consumerType = input.nextInt();
                    input.nextLine();
                } while (consumerType < 1 || consumerType > 2);
                System.out.print("\nUser's nickname: ");
                nickname = input.nextLine();
                System.out.print("User ID: ");
                id = input.nextLine();
                System.out.print("Date of linking to the platform\nYear: ");
                linkUpYear = input.nextInt();
                input.nextLine();
                System.out.print("Month: ");
                linkUpMonth = input.nextInt();
                input.nextLine();
                System.out.print("Day: ");
                linkUpDay = input.nextInt();
                input.nextLine();

                System.out.println(
                        "\n" + objController.addUser(nickname, id, LocalDate.of(linkUpYear, linkUpMonth, linkUpDay),
                                consumerType) + "\n");
                break;

        }
    }

    /**
     * <b>Name:</b> addAudio <br>
     * <b>Description:</b> Method used to register an audio on the platform (Song or
     * podcast). <br>
     * <b><i>pre:</i></b> <br>
     * <b><i>pos:</i></b> The audio is registered on the platform. <br>
     */

    public void addAudio() {
        System.out.print(
                "\nType the number that corresponds to the type of audio you want to register on the platform\n1) Song\n2) Podcast\nOption: ");
        int typeAudio = input.nextInt();
        input.nextLine();

        String name;
        int counter = 0, genre = 0, category = 0, hours = 0, minutes, seconds;

        switch (typeAudio) {
            case 1:
                System.out.print("\nNickname of the producing artist: ");
                String artistNickname = input.nextLine();
                System.out.print("Name of the song: ");
                name = input.nextLine();
                do {
                    if (counter > 0)
                        System.out.println("Error. Type a valid option.");
                    System.out.print(
                            "Enter a number that corresponds to the genre of the podcast\n1) Rock\n2) Pop\n3) Trap\n4) House\nOption: ");
                    counter++;
                    genre = input.nextInt();
                    input.nextLine();
                } while (genre < 1 || genre > 4);
                System.out.print("URL with the cover of the album to which it belongs: ");
                String urlAlbum = input.nextLine();
                System.out.print("Duration of the song\nMinutes: ");
                minutes = input.nextInt();
                input.nextLine();
                System.out.print("Seconds: ");
                seconds = input.nextInt();
                input.nextLine();
                System.out.print("Sale value of the song: ");
                double saleValue = input.nextDouble();
                input.nextLine();
                System.out.println("\n" + objController.addAudio(artistNickname, name, genre, urlAlbum,
                        LocalTime.of(hours, minutes, seconds), saleValue) + "\n");
                break;
            case 2:
                System.out.print("\nNickname of the producing content creator: ");
                String producerNickname = input.nextLine();
                System.out.print("Name of the podcast: ");
                name = input.nextLine();
                do {
                    if (counter > 0)
                        System.out.println("Error. Type a valid option.");
                    System.out.print(
                            "Enter a number that corresponds to the category of the podcast\n1) POLITIC\n2) Entertainment\n3) Video Games\n4) Fashion\nOption: ");
                    counter++;
                    category = input.nextInt();
                    input.nextLine();
                } while (category < 1 || category > 4);
                System.out.print("Description of the podcast: ");
                String description = input.nextLine();
                System.out.print("Enter a URL with a distinctive image for the podcast: ");
                String urlImage = input.nextLine();
                System.out.print("Duration of the podcast\nHours: ");
                hours = input.nextInt();
                input.nextLine();
                System.out.print("Seconds: ");
                minutes = input.nextInt();
                input.nextLine();
                System.out.print("Seconds: ");
                seconds = input.nextInt();
                input.nextLine();

                System.out.println(
                        "\n" + objController.addAudio(producerNickname, name, category, description, urlImage,
                                LocalTime.of(hours, minutes, seconds)) + "\n");
                break;
        }

    }

    /**
     * <b>Name:</b> createPlayList <br>
     * <b>Description:</b> Method used to create a playlist. <br>
     * <b><i>pre:</i></b> <br>
     * <b><i>pos:</i></b> The playlist is registered on the platform. <br>
     */
    public void createPlayList() {
        String audioName;
        ArrayList<String> audiosNames = new ArrayList<String>();
        System.out.print("\nNickname of the user to whom the playlist will be registered: ");
        String nickname = input.nextLine();
        System.out.print("Name of the playlist: ");
        String playlistName = input.nextLine();
        char confirmation = 'N';
        do {
            if (confirmation != 'Y' && confirmation != 'N')
                System.out.println("Error. Type a valid option.");
            System.out.print("Do you want to add audio to your playlist before creating it? (Y/N): ");
            confirmation = input.nextLine().toUpperCase().charAt(0);
        } while (confirmation != 'Y' && confirmation != 'N');
        if (confirmation == 'Y') {
            do {
                char typeAudio = 'S';
                do {
                    if (typeAudio != 'P' && typeAudio != 'S')
                        System.out.println("Error. Type a valid option.");
                    System.out.print("Type the letter of the audio you want to add (P for podcast/S for songs): ");
                    typeAudio = input.nextLine().toUpperCase().charAt(0);
                } while (typeAudio != 'P' && typeAudio != 'S');
                if (typeAudio == 'P') {
                    System.out.print("Name of the podcast: ");
                    audioName = input.nextLine();
                } else {
                    System.out.print("Name of the song: ");
                    audioName = input.nextLine();
                }
                if (objController.searchAudio(audioName) != null) {
                    audiosNames.add(audioName);
                    if (typeAudio == 'P')
                        System.out.print("\nPodcast added successfully!");
                    else
                        System.out.print("\nSong added successfully!");
                } else {
                    System.out.println("\nError. Audio not found.");
                }

                do {
                    if (confirmation != 'Y' && confirmation != 'N')
                        System.out.println("\nError. Type a valid option.");
                    System.out.print("\n\nDo you want to add other audio? (Y/N): ");
                    confirmation = input.nextLine().toUpperCase().charAt(0);
                } while (confirmation != 'Y' && confirmation != 'N');
            } while (confirmation == 'Y');
            System.out.println("\n" + objController.createPlaylist(nickname, playlistName, audiosNames) + "\n");
        } else {
            System.out.println("\n" + objController.createPlaylist(nickname, playlistName, audiosNames) + "\n");
        }

    }

    /**
     * <b>Name:</b> editPlaylist <br>
     * <b>Description:</b> Method used to edit a playlist (add and remove audios).
     * <br>
     * <b><i>pre:</i></b> <br>
     * <b><i>pos:</i></b> <br>
     */

    public void editPlaylist() {
        System.out.print("\nNickname of the user who has registered the playlist: ");
        String nickname = input.nextLine();
        System.out.print("Playlist name: ");
        String playlistName = input.nextLine();
        String audioName = null;
        char confirmation = 'Y';
        ArrayList<String> audiosToAdd = new ArrayList<String>();
        ArrayList<String> audiosToRemove = new ArrayList<String>();
        do {
            char action = 'A';
            do {
                if (action != 'A' && action != 'R')
                    System.out.println("Error. Type a valid option.");
                System.out.print("Do you want to add or remove an audio? (A to add/R to remove): ");
                action = input.nextLine().toUpperCase().charAt(0);
            } while (action != 'A' && action != 'R');

            char typeAudio = 'S';
            do {
                if (typeAudio != 'P' && typeAudio != 'S')
                    System.out.println("Error. Type a valid option.");
                if (action == 'A')
                    System.out.print("Type the letter of the audio you want to add (P for podcast/ S for songs): ");
                else if (action == 'R')
                    System.out.print("Type the letter of the audio you want to remove (P for podcast/ S for songs): ");
                typeAudio = input.nextLine().toUpperCase().charAt(0);
            } while (typeAudio != 'P' && typeAudio != 'S');

            if (typeAudio == 'P') {
                System.out.print("Name of the podcast: ");
                audioName = input.nextLine();
            } else if (typeAudio == 'S') {
                System.out.print("Name of the song: ");
                audioName = input.nextLine();
            }
            if (objController.searchAudio(audioName) != null) {
                if (objController.searchAudioInPlaylist(nickname, playlistName, audioName) && action != 'R') {
                    System.out.print("Alert. The audio is already registered in the playlist.");
                } else if (action == 'A') {
                    audiosToAdd.add(audioName);
                    if (typeAudio == 'P')
                        System.out.print("Podcast added successfully!");
                    else
                        System.out.print("Song added successfully!");
                } else if (action == 'R') {
                    audiosToRemove.add(audioName);
                    if (typeAudio == 'P')
                        System.out.print("Podcast removed successfully!");
                    else
                        System.out.print("Song removed successfully!");
                }
            } else {
                System.out.println("Error. Audio not found.");
            }

            do {
                if (confirmation != 'Y' && confirmation != 'N')
                    System.out.println("Error. Type a valid option.");
                System.out.print("\nDo you want to add or remove other audio? (Y/N): ");
                confirmation = input.nextLine().toUpperCase().charAt(0);
            } while (confirmation != 'Y' && confirmation != 'N');
        } while (confirmation == 'Y');
        System.out
                .println("\n" + objController.editPlaylist(nickname, playlistName, audiosToAdd, audiosToRemove) + "\n");
    }

    /**
     * <b>Name:</b> sharePlaylist <br>
     * <b>Description:</b> Method used to share a playlist with its code and its
     * matrix. <br>
     * <b><i>pre:</i></b> <br>
     * <b><i>pos:</i></b> <br>
     */

    public void sharePlaylist() {
        System.out.print("\nPlayer nickname: ");
        String nickname = input.nextLine();
        System.out.print("Playlist name: ");
        String playlistName = input.nextLine();
        System.out.println("\n" + objController.sharePlaylist(nickname, playlistName) + "\n");

    }

    /**
     * <b>Name:</b> simulatePlayback <br>
     * <b>Description:</b> Method used to simulate the playback of audio files
     * recorded on the platform (songs, podcasts and ads). <br>
     * <b><i>pre:</i></b> There must be audios registered in the platform. <br>
     * <b><i>pos:</i></b> Increased statistics of played audio. <br>
     */
    public void simulatePlayback() {
        String songs = objController.showSongs();
        System.out.print("\n" + songs);

        String podcasts = objController.showPodcasts();
        System.out.print("\n" + podcasts);

        if (!songs.equalsIgnoreCase("There are no songs registered on the platform.")
                || !podcasts.equalsIgnoreCase("There are no podcasts registered on the platform.")) {
            System.out.print("\nEnter the consumer nickname that will play the audios: ");
            String nickname = input.nextLine();
            String playing = null;
            int counter = 0;
            char action = 'Y';
            do {
                if (action != 'Y') {
                    System.out.println("Error. Type a valid option\n");
                } else {
                    System.out.print("Name of the audio that user want to play: ");
                    String audioName = input.nextLine();
                    if (objController.isSong(audioName))
                        counter++;
                    if (counter == 3) {
                        playing = objController.simulatePlayback(nickname, audioName, true);
                        counter = 0;
                    } else
                        playing = objController.simulatePlayback(nickname, audioName, false);
                    System.out.println(playing);
                }
                if (!playing.equalsIgnoreCase("Error. User not found.\n")) {
                    System.out.print("Do you want to play other audio? (Y/N) ");
                    action = input.nextLine().toUpperCase().charAt(0);
                    System.out.println();
                } else {
                    action = 'N';
                }
            } while (action != 'N');

        }
    }

    /**
     * <b>Name:</b> buySong <br>
     * <b>Description:</b> Method used for a consumer user to purchase songs. <br>
     * <b><i>pre:</i></b> There must be songs registered in the platform. <br>
     * <b><i>pos:</i></b> Consumer user purchases a song. <br>
     */
    public void buySong() {
        String songs = objController.showSongs();
        System.out.print("\n" + songs);
        if (!songs.equalsIgnoreCase("There are no songs registered on the platform.")) {
            System.out.print("Nickname of the user who will buy the song: ");
            String nickname = input.nextLine();
            System.out.print("Name of the song that user want to buy: ");
            String songName = input.nextLine();
            System.out.print("Enter the date of purchase\nYear: ");
            int year = input.nextInt();
            input.nextLine();
            System.out.print("Month: ");
            int month = input.nextInt();
            input.nextLine();
            System.out.print("Day: ");
            int day = input.nextInt();
            input.nextLine();
            System.out.println("\n" + objController.buySong(nickname, songName,
                    LocalDate.of(year, month, day)) + "\n");
        }

    }

    /**
     * <b>Name:</b> cumulativePlays <br>
     * <b>Description:</b> Method used to report the total accumulated plays across
     * the platform. <br>
     * <b><i>pre:</i></b> <br>
     * <b><i>pos:</i></b> <br>
     */

    public void cumulativePlays() {
        System.out.println("\n" + objController.cumulativePlays() + "\n");
    }

    /**
     * <b>Name:</b> mostListenedGenre <br>
     * <b>Description:</b> Method used to report the most listened song genre (name
     * and number of plays) for a specific user and for the entire platform. <br>
     * <b><i>pre:</i></b> <br>
     * <b><i>pos:</i></b> <br>
     */

    public void mostListenedGenre() {
        System.out.print("\nType the user nickname: ");
        String nickname = input.nextLine();
        System.out.println("\n" + objController.mostListenedGenre(nickname) + "\n");
    }

    /**
     * <b>Name:</b> mostListenedCategory <br>
     * <b>Description:</b> Method used to report the most listened podcast category
     * (name and number of plays) for a specific user and for the entire platform.
     * <br>
     * <b><i>pre:</i></b> <br>
     * <b><i>pos:</i></b> <br>
     */

    public void mostListenedCategory() {
        System.out.print("\nType the user nickname: ");
        String nickname = input.nextLine();
        System.out.println("\n" + objController.mostListenedCategory(nickname) + "\n");
    }

    /**
     * <b>Name:</b> getTopsProducers <br>
     * <b>Description:</b> Method used to report the name and number of total plays
     * of each of the members of the Top 5 artists and Top 5 content creators. <br>
     * <b><i>pre:</i></b> <br>
     * <b><i>pos:</i></b> <br>
     */

    public void getTopsProducers() {
        System.out.println("\n" + objController.getTopFiveArtist());
        System.out.println(objController.getTopFiveContentCreator());
    }

    /**
     * <b>Name:</b> getTopsAudios <br>
     * <b>Description:</b> Method used to report the name, genre or category and
     * total number of plays for each of the members of the Top 10 Songs and Top 10
     * Podcasts. <br>
     * <b><i>pre:</i></b> <br>
     * <b><i>pos:</i></b> <br>
     */

    public void getTopsAudios() {
        System.out.println("\n" + objController.getTopTenSongs());
        System.out.println(objController.getTopTenPodcasts());
    }

    /**
     * <b>Name:</b> reportGenreSales <br>
     * <b>Description:</b> Method used to report the number of songs sold and the
     * total sales value ($) of each genre. <br>
     * <b><i>pre:</i></b> info <br>
     * <b><i>pos:</i></b> info <br>
     */

    public void reportGenreSales() {
        System.out.println(objController.reportByGenre() + "\n");
    }

    /**
     * <b>Name:</b> bestSellingSong <br>
     * <b>Description:</b> Method used to report the total number of sales and the
     * total sales value ($) of the top-selling song on the platform. <br>
     * <b><i>pre:</i></b> info <br>
     * <b><i>pos:</i></b> info <br>
     */

    public void bestSellingSong() {
        System.out.println("\n" + objController.bestSellingSong() + "\n");
    }

}
