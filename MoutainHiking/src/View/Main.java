package View;

import Controller.MoutainList;
import Controller.StudentList;
import java.util.Scanner;

public class Main {

    static StudentList sl = new StudentList();
    static boolean hasUnsavedChange = false;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        MoutainList ml = new MoutainList();
        
        ml.readFile("MountainList.csv");
        
        while (true) {
            showMenu();

            System.out.println("Enter choice: ");
            int choice = sc.nextInt();
            sc.nextLine();
            
            switch (choice) {
                case 1:
                    sl.addStudent();
                    hasUnsavedChange = true;
                    break;
                case 2:
                    System.out.println("Enter student ID to update: ");
                    String studentId = sc.nextLine();
                    sl.updateStudent(studentId);
                    hasUnsavedChange = true;
                    break;
                case 3:
                    System.out.println("Enter student ID to delete: ");
                    String studentIdDelete = sc.nextLine();
                    sl.deleteRegister(studentIdDelete);
                    hasUnsavedChange = true;
                    break;
                case 4:
                    System.out.println("Enter Student or Part of student name to search: ");
                    String studentName = sc.nextLine();
                    sl.searchByName(studentName);
                    hasUnsavedChange = true;
                    break;
                case 5:
                    sl.saveFile();
                    hasUnsavedChange = false;
                    break;
                case 9: exitProgram(); break;

                default:
                    System.out.println("This function is not available.");
            }
        }
    }

    public static void showMenu() {
        System.out.println("\n===== MAIN MENU =====");
        System.out.println("1. Add Student");
        System.out.println("2. Update Student");
        System.out.println("3. Delete Student");
        System.out.println("4. Search Student by Name");
        System.out.println("5. Save Data");
        System.out.println("9. Exit");
        System.out.println("=====================");
    }

    public static void exitProgram() {
        Scanner sc = new Scanner(System.in);

        if (hasUnsavedChange) {
            System.out.println("You have unsaved changes. Do you want to save before exiting? (Y/N)");
            String choice = sc.nextLine().toUpperCase();

            if (choice.equals("Y")) {
                sl.saveFile();
                System.out.println("Changes saved. Exiting...");
                System.exit(0);
            } else if (choice.equals("N")) {
                System.out.println("Are you sure you want to exit without saving? (Y/N)");
                String confirm = sc.nextLine().trim().toUpperCase();
                if (confirm.equals("Y")) {
                    System.out.println("Exiting program without saving...");
                    System.exit(0);
                } else if (confirm.equals("N")) {
                    System.out.println("Exit canceled. Returning to main menu...");
                    return;
                } else {
                    System.out.println("Invalid choice! return to main menu");
                    return;
                }
            }
        } else {
            System.out.println("No unsaved changes. Exiting program...");
            System.exit(0);
        }
    }
}
