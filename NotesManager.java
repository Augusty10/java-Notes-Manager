
import java.io.*;
import java.util.*;
public class NotesManager {
    private static final String FILE_NAME = "notes.txt";
    private static List<String> notes = new ArrayList<>();

    public static void main(String[] args) {
        loadNotesFromFile();
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n--- Notes Manager ---");
            System.out.println("1. View Notes");
            System.out.println("2. Add Note");
            System.out.println("3. Save Notes");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1 -> viewNotes();
                case 2 -> addNote(scanner);
                case 3 -> saveNotesToFile();
                case 4 -> System.out.println("Exiting...");
                default -> System.out.println("Invalid choice. Try again.");
            }
        } while (choice != 4);

        scanner.close();
    }

    private static void loadNotesFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                notes.add(line);
            }
            System.out.println("Notes loaded successfully.");
        } catch (IOException e) {
            System.out.println("No existing notes found. Starting fresh.");
        }
    }

    private static void viewNotes() {
        if (notes.isEmpty()) {
            System.out.println("No notes available.");
        } else {
            System.out.println("\nYour Notes:");
            for (int i = 0; i < notes.size(); i++) {
                System.out.println((i + 1) + ". " + notes.get(i));
            }
        }
    }

    private static void addNote(Scanner scanner) {
        System.out.print("Enter your note: ");
        String note = scanner.nextLine();
        notes.add(note);
        System.out.println("Note added.");
    }

    private static void saveNotesToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (String note : notes) {
                writer.write(note);
                writer.newLine();
            }
            System.out.println("Notes saved successfully.");
        } catch (IOException e) {
            System.out.println("Error saving notes: " + e.getMessage());
        }
    }
}
