import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class diary {
    public static void main(String ar[]) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Hello!!!");
        int n = 1;
        String pathdiary, pathentry;

        while (n >= 1 && n <= 5) {

            System.out.println("1. Create a diary\n2. Write a new entry\n3. Read an entry\n4. Search a phrase on a given entry\n5.Exit");

            System.out.println("\nEnter option: ");
            n = sc.nextInt();

            if (n == 1) {
                System.out.println("Enter the path where you want to create the diary folder: ");
                pathdiary = sc.next();
                sc.nextLine();
                System.out.println("Enter the name of the diary: ");
                pathdiary = pathdiary + File.separator + sc.next();
                try {
                    File d = new File(pathdiary);
                    if (d.mkdir()) {
                        System.out.println("Diary successfully created");
                    } else {
                        System.out.println("Diary of the same name already exists in the directory.");
                    }
                } catch (Exception e) {
                    System.out.println("An unexpected error occurred." + e);
                }
            } else if (n == 2) {
                System.out.println("Enter the path where you want to create the diary entry: ");
                pathentry = sc.next();
                System.out.println("Enter the date in the format dd-mm-yyyy: ");
                pathentry = pathentry + File.separator + sc.next() + ".txt";
                try {
                    File page = new File(pathentry);
                    if (page.createNewFile()) {
                        System.out.println("Entry successfully created");
                    } else {
                        System.out.println("Entry of the same name already exists in the diary.");
                    }
                } catch (Exception e) {
                    System.out.println("Error occurred." + e);
                }
                sc.nextLine();
                try {
                    FileWriter pagewrite = new FileWriter(pathentry, true);
                    System.out.println("Write your entry: ");
                    String entry = sc.nextLine();
                    pagewrite.write(entry + "\n");
                    System.out.println("Entry successfully saved.");
                    pagewrite.close();
                } catch (Exception e) {
                    System.out.println("Error occurred." + e);
                }
            } else if (n == 3) {
                System.out.println("Enter the path of the diary: ");
                pathentry = sc.next();
                System.out.println("Enter the date of the diary entry to be read: ");
                pathentry = pathentry + File.separator + sc.next() + ".txt";

                try {
                    File f1 = new File(pathentry);
                    Scanner pagereader = new Scanner(f1);
                    System.out.println("The entry is:");
                    while (pagereader.hasNextLine()) {
                        System.out.println(pagereader.nextLine());
                    }
                    pagereader.close();
                } catch (Exception e) {
                    System.out.println("Entry of that date is not found" + e);
                }
            } else if (n == 4) {
                System.out.println("Enter the path of the diary: ");
                pathentry = sc.next();
                System.out.println("Enter the date of the diary entry to be searched: ");
                pathentry = pathentry + File.separator + sc.next() + ".txt";

                sc.nextLine();

                System.out.println("Enter the phrase to be searched");
                String phrase = sc.nextLine();

                try {
                    File f2 = new File(pathentry);
                    Scanner pagesearch = new Scanner(f2);
                    boolean found = false;
                    while (pagesearch.hasNextLine()) {
                        String line = pagesearch.nextLine();
                        if (line.contains(phrase)) {
                            System.out.println("Phrase Found");
                            found = true;
                            break;
                        }
                    }
                    if (!found) {
                        System.out.println("Phrase not found");
                    }
                    pagesearch.close();
                } catch (Exception e) {
                    System.out.println("Entry of that date is not found" + e);
                }
            } else if (n == 5) {
                System.out.println("Bye bye! See you soon!");
                break;
            }
            System.out.println("\n");
        }
    }
}
