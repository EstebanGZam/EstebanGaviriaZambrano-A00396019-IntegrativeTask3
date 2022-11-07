package ui;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Scanner;

import model.NeoTunesPlatform;

public class NeoTunesApp {

    // relations
    private static Scanner input = new Scanner(System.in);
    private static NeoTunesPlatform controller = new NeoTunesPlatform();

    /**
     * @param args
     */
    public static void main(String[] args) {
        int option = 0;
        do {
            menu();
            option = input.nextInt();
            input.nextLine();
            switch (option) {
                case 1:
                    addUser(option);
                    break;
                case 2:
                    addUser(option);
                    break;
                case 3:
                    addAudio();
                    break;
                case 4:
                    createPlayList();
                    break;
                case 5:
                    editPlaylist();
                    break;
                case 6:
                    sharePlaylist();
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

    public static void menu() {
        System.out.print("Select an option:\n" +
                "1) Register producer users.\n" +
                "2) Register consumer.\n" +
                "3) Register songs and podcasts.\n" +
                "4) Create a playlist.\n" +
                "5) Edit a playlist.\n" +
                "6) Share a playlist.\n" +
                "0) Exit.\n" +
                "Option: ");
    }

    /**
     * @param type
     */
    public static void addUser(int type) {
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

                System.out.println("\n" + controller.addUser(nickname, id, LocalDate.of(linkUpYear,
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
                        "\n" + controller.addUser(nickname, id, LocalDate.of(linkUpYear, linkUpMonth, linkUpDay),
                                consumerType) + "\n");
                break;

        }
    }

    public static void addAudio() {
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
                System.out.println("\n" + controller.addAudio(artistNickname, name, genre, urlAlbum,
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
                            "Enter a number that corresponds to the category of the podcast\n1) Politics\n2) Entertainment\n3) Video Games\n4) Fashion\nOption: ");
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
                        "\n" + controller.addAudio(producerNickname, name, category, description, urlImage,
                                LocalTime.of(hours, minutes, seconds)) + "\n");
                break;
        }

    }

    public static void createPlayList() {
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
                if (controller.searchAudio(audioName) != null) {
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
            System.out.println("\n" + controller.createPlaylist(nickname, playlistName, audiosNames) + "\n");
        } else {
            System.out.println("\n" + controller.createPlaylist(nickname, playlistName) + "\n");
        }

    }

    public static void editPlaylist() {
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
            if (controller.searchAudio(audioName) != null) {
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
        System.out.println("\n" + controller.editPlaylist(nickname, playlistName, audiosToAdd, audiosToRemove) + "\n");
    }

    public static void sharePlaylist() {

    }

}
