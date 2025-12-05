package Music_PlayList_Manager;
import java.util.Scanner;

// Class representing a single song
class Song {
    private String title;
    private String artist;
    private String album;
    private double duration;

    // Constructor
    public Song(String title, String artist, String album, double duration) {
        this.title = title;
        this.artist = artist;
        this.album = album;
        this.duration = duration;
    }

    // Getters
    public String getTitle() {
        return title;
    }

    public String getArtist() {
        return artist;
    }

    // Simulate playing a song
    public void play() {
        System.out.println("Playing: " + title + " - " + artist);
    }

    // Display song details (optional for listing)
    @Override
    public String toString() {
        return title + " - " + artist;
    }
}

// Class that manages the playlist
class Playlist {
    private Song[] songs;
    private int count;

    // Constructor
    public Playlist(int size) {
        songs = new Song[size];
        count = 0;
    }

    // Add a new song
    public void addSong(Song song) {
        if (count < songs.length) {
            songs[count++] = song;
            System.out.println("Song added to the playlist!");
        } else {
            System.out.println("Playlist is full! Cannot add more songs.");
        }
    }

    // Remove a song by title
    public void removeSong(String title) {
        boolean found = false;
        for (int i = 0; i < count; i++) {
            if (songs[i].getTitle().equalsIgnoreCase(title)) {
                // Shift remaining songs left
                for (int j = i; j < count - 1; j++) {
                    songs[j] = songs[j + 1];
                }
                songs[count - 1] = null;
                count--;
                System.out.println("Song removed from the playlist!");
                found = true;
                break;
            }
        }
        if (!found) {
            System.out.println("Song not found!");
        }
    }

    // List all songs
    public void listSongs() {
        if (count == 0) {
            System.out.println("Playlist is empty!");
        } else {
            System.out.println("Playlist:");
            for (int i = 0; i < count; i++) {
                System.out.println((i + 1) + ". " + songs[i]);
            }
        }
    }

    // Play a song by title
    public void playSong(String title) {
        boolean found = false;
        for (int i = 0; i < count; i++) {
            if (songs[i].getTitle().equalsIgnoreCase(title)) {
                songs[i].play();
                found = true;
                break;
            }
        }
        if (!found) {
            System.out.println("Song not found in the playlist!");
        }
    }
}

// Main class with menu-driven interface
public class MusicPlayListManager {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Playlist playlist = new Playlist(100); // maximum 100 songs
        int choice;

        do {
            System.out.println("\nMusic Playlist Manager");
            System.out.println("1. Add Song");
            System.out.println("2. Remove Song");
            System.out.println("3. List Songs");
            System.out.println("4. Play Song");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();
            sc.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter song title: ");
                    String title = sc.nextLine();
                    System.out.print("Enter artist name: ");
                    String artist = sc.nextLine();
                    System.out.print("Enter album name: ");
                    String album = sc.nextLine();
                    System.out.print("Enter song duration (in minutes): ");
                    double duration = sc.nextDouble();
                    sc.nextLine();

                    Song newSong = new Song(title, artist, album, duration);
                    playlist.addSong(newSong);
                    break;

                case 2:
                    System.out.print("Enter title of the song to remove: ");
                    String removeTitle = sc.nextLine();
                    playlist.removeSong(removeTitle);
                    break;

                case 3:
                    playlist.listSongs();
                    break;

                case 4:
                    System.out.print("Enter title of the song to play: ");
                    String playTitle = sc.nextLine();
                    playlist.playSong(playTitle);
                    break;

                case 5:
                    System.out.println("Exiting Music Playlist Manager...");
                    break;

                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        } while (choice != 5);

        sc.close();
    }
}
