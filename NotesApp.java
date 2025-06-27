import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class NotesApp {
    private static final String NOTES_FILE = "notes.txt";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        System.out.println("Welcome to the Java Notes Manager!");

        while (running) {
            
            System.out.println("\n--- Menu ---");
            System.out.println("1. Write a new note");
            System.out.println("2. Read all notes");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");

            
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    writeNote(scanner);
                    break;
                case "2":
                    readNotes();
                    break;
                case "3":
                    running = false;
                    System.out.println("Exiting Notes Manager. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }

        scanner.close();
    }

    
    private static void writeNote(Scanner scanner) {
        System.out.print("Enter your note (press Enter when done): ");
        String note = scanner.nextLine();

        
        try (FileWriter writer = new FileWriter(NOTES_FILE, true)) { 
            writer.write(note);
            writer.write(System.lineSeparator()); 
            System.out.println("Note successfully saved!");
        } catch (IOException e) {
            System.err.println("Error writing note to file: " + e.getMessage());
        }
    }

    
    private static void readNotes() {
        System.out.println("\n--- Your Notes ---");
        try (BufferedReader reader = new BufferedReader(new FileReader(NOTES_FILE))) {
            String line;
            boolean hasNotes = false;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
                hasNotes = true;
            }
            if (!hasNotes) {
                System.out.println("No notes found yet. Start by writing one!");
            }
        } catch (IOException e) {
            System.err.println("Error reading notes from file: " + e.getMessage());
            System.out.println("It seems the notes file does not exist or is inaccessible. " +
                               "Try writing a note first to create it.");
        }
        System.out.println("------------------");
    }
}