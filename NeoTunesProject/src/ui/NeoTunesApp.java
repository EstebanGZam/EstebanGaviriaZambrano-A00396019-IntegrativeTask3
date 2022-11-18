package ui;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Scanner;

import model.NeoTunesPlatform;

public class NeoTunesApp {

    // relations
    /**
     * <b>name:</b> input <br>
     * Instance line of the Scanner object.
     */
    private static Scanner input = new Scanner(System.in);
    private static NeoTunesPlatform objController;

    // methods
    /**
     * <b>Name:</b> NeoTunesApp <br>
     * Constructor method of NeoTunesApp class.<br>
     * <b><i>pre: </i></b> <br>
     * <b><i>pos:</i> </b> The NeoTunesApp object is built<br>
     */
    public NeoTunesApp() {
        objController = new NeoTunesPlatform();
    }

    /**
     * <b>Name:</b> main <br>
     * Main method. It is used to run the program.<br>
     * 
     * @param args
     */
    public static void main(String[] args) {
        NeoTunesApp objMain = new NeoTunesApp();
        // Test cases
        System.out.println(
                objController.addUser("Bad bunny", "111", LocalDate.of(2022, 5, 10), "Benito", "soyunaurl.com", 2));
        System.out.println(
                objController.addUser("Cepeda", "222", LocalDate.of(2022, 5, 10), "Andres", "soyunaurl.com", 2));
        System.out.println(objController.addUser("Juan", "123", LocalDate.of(2022, 7, 27), 1));
        System.out.println(objController.addUser("Esteban", "1234", LocalDate.of(2022, 7, 27), 2));
        System.out
                .println(objController.addAudio("Bad bunny", "Callaita", 1, "Soyunaurl.com", LocalTime.of(0, 3, 10),
                        10));
        System.out
                .println(objController.addAudio("Cepeda", "Besos usados", 1, "Soyunaurl.com", LocalTime.of(0, 3, 10),
                        10));
        System.out
                .println(objController.addAudio("Cepeda", "Magia", 1, "Soyunaurl.com", LocalTime.of(0, 3, 10), 15));

        System.out
                .println(objController.addUser("Migala", "123", LocalDate.of(2015, 5, 21), "Jose Luis", "123.img", 1));
        System.out.println(objController.addAudio("Migala", "El sentido de la vida", 2, "42", "Soyunaurlloquisima.com",
                LocalTime.of(1, 20, 15)));

        ArrayList<String> audios = new ArrayList<String>();
        audios.add("Callaita");
        audios.add("Besos usados");
        System.out.println(objController.createPlaylist("Juan", "Salsa", audios));

        ArrayList<String> audiosAdd = new ArrayList<String>(), audiosToRemove = new ArrayList<String>();
        audiosAdd.add("El sentido de la vida");
        System.out.println(objController.editPlaylist("Juan", "Salsa", audiosAdd, audiosToRemove));
        System.out.println(objController.sharePlaylist("Juan", "Salsa") + "\n");
        // End of test cases

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
                            objMain.getTops();
                            break;
                        case 5:
                            break;
                        case 6:
                            break;
                        case 7:
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
     * <b>Description:</b> <br>
     * <b><i>pre:</i></b> info <br>
     * <b><i>pos:</i></b> info <br>
     * 
     * @param type
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
                System.out.print("Type the user's nickname: ");
                nickname = input.nextLine();
                System.out.print("Type the user's name: ");
                name = input.nextLine();
                System.out.print("Type the user ID: ");
                id = input.nextLine();
                System.out.print("Type the date of linking to the platform:\nYear: ");
                linkUpYear = input.nextInt();
                input.nextLine();
                System.out.print("Month: ");
                linkUpMonth = input.nextInt();
                input.nextLine();
                System.out.print("Day: ");
                linkUpDay = input.nextInt();
                input.nextLine();
                System.out.print("Type the URL with the distinctive image of the user: ");
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
                            "\nType the number corresponding to the type of user you want to register:\n1) Standard\n2) Premium users\nOption: ");
                    consumerType = input.nextInt();
                    input.nextLine();
                } while (consumerType < 1 || consumerType > 2);
                System.out.print("Type the user's nickname: ");
                nickname = input.nextLine();
                System.out.print("Type the user ID: ");
                id = input.nextLine();
                System.out.print("Type the date of linking to the platform\nYear: ");
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
     * <b>Description:</b> <br>
     * <b><i>pre:</i></b> info <br>
     * <b><i>pos:</i></b> info <br>
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
                System.out.print("Type the URL with the cover of the album to which it belongs: ");
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
     * <b>Description:</b> <br>
     * <b><i>pre:</i></b> info <br>
     * <b><i>pos:</i></b> info <br>
     */
    public void createPlayList() {
        String audioName;
        ArrayList<String> audiosNames = new ArrayList<String>();
        System.out.print("\nType the nickname of the user to whom the playlist will be registered: ");
        String nickname = input.nextLine();
        System.out.print("Type the name of the playlist: ");
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
                    System.out.println("Name of the podcast: ");
                    audioName = input.nextLine();
                } else {
                    System.out.println("Name of the podcast: ");
                    audioName = input.nextLine();
                }
                if (objController.searchAudio(audioName) != null) {
                    audiosNames.add(audioName);
                    if (typeAudio == 'P')
                        System.out.print("Podcast added successfully!");
                    else
                        System.out.print("Song added successfully!");
                } else {
                    System.out.println("Error. Audio not found.");
                }

                do {
                    if (confirmation != 'Y' && confirmation != 'N')
                        System.out.println("Error. Type a valid option.");
                    System.out.print("Do you want to add other audio? (Y/N): ");
                    confirmation = input.nextLine().toUpperCase().charAt(0);
                } while (confirmation != 'Y' && confirmation != 'N');
            } while (confirmation == 'Y');
            System.out.println("\n" + objController.createPlaylist(nickname, playlistName, audiosNames) + "\n");
        } else {
            System.out.println("\n" + objController.createPlaylist(nickname, playlistName) + "\n");
        }

    }

    /**
     * <b>Name:</b> editPlaylist <br>
     * <b>Description:</b> <br>
     * <b><i>pre:</i></b> info <br>
     * <b><i>pos:</i></b> info <br>
     */

    public void editPlaylist() {
        System.out.print("\nType the nickname of the user who has registered the playlist: ");
        String nickname = input.nextLine();
        System.out.print("Type the playlist name: ");
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
                    System.out.print("Type the letter of the audio you want to add (P for podcast/S for songs): ");
                else if (action == 'R')
                    System.out.print("Type the letter of the audio you want to remove (P for podcast/S for songs): ");
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
                if (action == 'A') {
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
     * <b>Description:</b> <br>
     * <b><i>pre:</i></b> info <br>
     * <b><i>pos:</i></b> info <br>
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
     * <b>Description:</b> <br>
     * <b><i>pre:</i></b> info <br>
     * <b><i>pos:</i></b> info <br>
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
     * <b>Description:</b> <br>
     * <b><i>pre:</i></b> info <br>
     * <b><i>pos:</i></b> info <br>
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

    public void cumulativePlays() {
        System.out.println("\n" + objController.cumulativePlays() + "\n");
    }

    public void mostListenedGenre() {
        System.out.print("\nType the user nickname: ");
        String nickname = input.nextLine();
        System.out.println("\n" + objController.mostListenedGenre(nickname) + "\n");
    }

    public void mostListenedCategory() {
        System.out.print("\nType the user nickname: ");
        String nickname = input.nextLine();
        System.out.println("\n" + objController.mostListenedCategory(nickname) + "\n");
    }

    public void getTops() {
        System.out.println("\n" + objController.getTopFiveArtist());
        System.out.println(objController.getTopFiveContentCreator());
    }

}
