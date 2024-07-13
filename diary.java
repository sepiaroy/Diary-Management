import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class diary {

    private static final Scanner SCANNER = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("Hello!!!");
        int option;

        do {
            displayMenu();
            option = SCANNER.nextInt();
            SCANNER.nextLine();

            switch (option) {
                case 1:
                    createDiary();
                    break;
                case 2:
                    writeNewEntry();
                    break;
                case 3:
                    readEntry();
                    break;
                case 4:
                    searchPhraseInEntry();
                    break;
                case 5:
                    listEntries();
                    break;
                case 6:
                    System.out.println("Bye bye! See you soon!");
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
            System.out.println();
        } while (option != 6);

        SCANNER.close();
    }

    private static void displayMenu() {
        System.out.println("1. Create a diary");
        System.out.println("2. Write a new entry");
        System.out.println("3. Read an entry");
        System.out.println("4. Search a phrase in a given entry");
        System.out.println("5. List all entries");
        System.out.println("6. Exit");
        System.out.print("\nEnter option: ");
    }

    private static void createDiary() {
        System.out.print("Enter the path where you want to create the diary folder: ");
        String diaryPath = SCANNER.next();
        SCANNER.nextLine(); 
        System.out.print("Enter the name of the diary: ");
        diaryPath = diaryPath + File.separator + SCANNER.next();
        
        File diary = new File(diaryPath);
        if (diary.mkdir()) {
            System.out.println("Diary successfully created");
        } else {
            System.out.println("Diary of the same name already exists in the directory.");
        }
    }

    private static void writeNewEntry() {
        System.out.print("Enter the path where you want to create the diary entry: ");
        String entryPath = SCANNER.next();
        System.out.print("Enter the date in the format dd-mm-yyyy: ");
        entryPath = entryPath + File.separator + SCANNER.next() + ".txt";
        SCANNER.nextLine();

        try (FileWriter writer = new FileWriter(entryPath, true)) {
            System.out.println("Write your entry: ");
            String entry = SCANNER.nextLine();
            writer.write(entry + "\n");
            System.out.println("Entry successfully saved.");
        } catch (IOException e) {
            System.out.println("An error occurred while saving the entry: " + e.getMessage());
        }
    }

    private static void readEntry() {
        System.out.print("Enter the path of the diary: ");
        String entryPath = SCANNER.next();
        System.out.print("Enter the date of the diary entry to be read (dd-mm-yyyy): ");
        entryPath = entryPath + File.separator + SCANNER.next() + ".txt";

        try (Scanner reader = new Scanner(new File(entryPath))) {
            System.out.println("The entry is:");
            while (reader.hasNextLine()) {
                System.out.println(reader.nextLine());
            }
        } catch (IOException e) {
            System.out.println("An error occurred while reading the entry: " + e.getMessage());
        }
    }

    private static void searchPhraseInEntry() {
        System.out.print("Enter the path of the diary: ");
        String entryPath = SCANNER.next();
        System.out.print("Enter the date of the diary entry to be searched (dd-mm-yyyy): ");
        entryPath = entryPath + File.separator + SCANNER.next() + ".txt";
        SCANNER.nextLine(); 
        System.out.print("Enter the phrase to be searched: ");
        String phrase = SCANNER.nextLine();

        try (Scanner reader = new Scanner(new File(entryPath))) {
            boolean found = false;
            while (reader.hasNextLine()) {
                if (reader.nextLine().contains(phrase)) {
                    System.out.println("Phrase Found");
                    found = true;
                    break;
                }
            }
            if (!found) {
                System.out.println("Phrase not found");
            }
        } catch (IOException e) {
            System.out.println("An error occurred while searching the entry: " + e.getMessage());
        }
    }

    private static void listEntries() {
        System.out.print("Enter the path of the diary: ");
        String diaryPath = SCANNER.next();
        SCANNER.nextLine(); 

        File diaryFolder = new File(diaryPath);
        if (diaryFolder.isDirectory()) {
            File[] entries = diaryFolder.listFiles((dir, name) -> name.endsWith(".txt"));
            if (entries != null && entries.length > 0) {
                System.out.println("Diary entries:");
                for (File entry : entries) {
                    System.out.println(entry.getName());
                }
            } else {
                System.out.println("No diary entries found.");
            }
        } else {
            System.out.println("Invalid diary path.");
        }
    }
}
