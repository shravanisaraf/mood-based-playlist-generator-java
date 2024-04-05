import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

class Media {
    protected String title;
    protected String artist;

    public Media(String title, String artist) {
        this.title = title;
        this.artist = artist;
    }

    public String getTitle() {
        return title;
    }

    public String getArtist() {
        return artist;
    }
}

class Song extends Media {
    private String mood;
    private List<String> synonyms;

    public Song(String title, String artist, String mood, List<String> synonyms) {
        super(title, artist);
        this.mood = mood;
        this.synonyms = synonyms;
    }

    public String getMood() {
        return mood;
    }

    public List<String> getSynonyms() {
        return synonyms;
    }
}

class MusicLibrary {
    public List<Song> loadMusicData(String filename) {
        List<Song> songs = new ArrayList<>();
        try {
            Scanner scanner = new Scanner(new File(filename));
            // Skip header
            if (scanner.hasNextLine()) {
                scanner.nextLine();
            }
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split(",");
                String title = parts[0].trim();
                String artist = parts[1].trim();
                String mood = parts[2].trim();
                List<String> synonyms = new ArrayList<>();
                // Read potential alternatives for mood from s1 to s6 columns
                for (int i = 3; i < Math.min(parts.length, 9); i++) { // Ensure we don't go beyond the specified columns
                    synonyms.add(parts[i].trim());
                }
                songs.add(new Song(title, artist, mood, synonyms));
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.err.println("Error: File not found - " + filename);
            e.printStackTrace();
        }
        return songs;
    }
}

public class MusicDataSource {
    public static void main(String[] args) {
        System.out.println("Welcome to the Music Mood Selector!");

        String filename = "D:/Ankita Jha/4th sem/oop/moodplaylist.csv";
        MusicLibrary musicLibrary = new MusicLibrary();
        List<Song> allSongs = musicLibrary.loadMusicData(filename);

        Scanner scanner = new Scanner(System.in);
        String userChoice;
        do {
            System.out.println("***** Music Mood Selector *****");
            String userMood = getUserMood(scanner);

            int numSongsToShow = getNumberOfSongsToShow(scanner);

            List<Song> matchingSongs = filterSongsByMoodAndSynonyms(allSongs, userMood, numSongsToShow);
            if (matchingSongs.isEmpty()) {
                System.out.println("No songs found for mood: " + userMood + ". Try a different mood!");
            } else {
                displaySongs(matchingSongs);
            }

            userChoice = getContinueChoice(scanner);
        } while (userChoice.equals("yes"));

        System.out.println("Exiting the program Ankita...... Goodbye!");
        scanner.close();
    }

    public static String getUserMood(Scanner scanner) {
        String mood;
        while (true) {
            System.out.print("What is your mood, Ankita? ");
            mood = scanner.nextLine().trim().toLowerCase(); // Convert input to lowercase
            if (!mood.matches("[0-9]+")) {
                break;
            } else {
                System.out.println("Invalid input! Please enter characters instead of numbers.");
            }
        }
        return mood;
    }

    public static int getNumberOfSongsToShow(Scanner scanner) {
        int numSongsToShow = 0;
        while (true) {
            try {
                System.out.print("How many songs would you like to see? ");
                numSongsToShow = scanner.nextInt();
                scanner.nextLine(); // Consume newline
                if (numSongsToShow <= 0) {
                    System.out.println("Please enter a positive number.");
                } else {
                    break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input! Please enter a valid integer.");
                scanner.nextLine(); // Consume invalid input
            }
        }
        return numSongsToShow;
    }
    public static List<Song> filterSongsByMoodAndSynonyms(List<Song> songs, String userMood, int numSongsToShow) {
        List<Song> matchingSongs = new ArrayList<>();
        String userMoodLower = userMood.toLowerCase(); // Convert user's mood to lowercase for case-insensitive comparison
        int count = 0;
        for (Song song : songs) {
            String songMoodLower = song.getMood().toLowerCase(); // Convert song's mood to lowercase for case-insensitive comparison
            if ((songMoodLower.equals(userMoodLower) || song.getSynonyms().stream().map(String::toLowerCase).anyMatch(userMoodLower::equals)) && count < numSongsToShow) {
                matchingSongs.add(song);
                count++;
            }
        }
        return matchingSongs;
    }
    

    public static void displaySongs(List<Song> songs) {
        System.out.println("===== Matching Songs =====");
        for (Song song : songs) {
            System.out.println("Title: " + song.getTitle() + ", Artist: " + song.getArtist() + ", Mood: " + song.getMood());
        }
    }
     
    public static String getContinueChoice(Scanner scanner) {
        String choice;
        while (true) {
            System.out.print("Do you want to search again? (yes/no): ");
            choice = scanner.nextLine().trim().toLowerCase();
            if (choice.equals("yes") || choice.equals("no")) {
                break;
            } else {
                System.out.println("Invalid choice! Please enter 'yes' or 'no'.");
            }
        }
        return choice;
    }
}