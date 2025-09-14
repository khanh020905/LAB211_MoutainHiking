package Views;

import Controllers.StudentController;
import java.util.Scanner;

public class Main {

    private static final Scanner SC = new Scanner(System.in);

    public static void main(String[] args) {
        StudentController controller = new StudentController().loadRegistrationsFromDatFile();
        boolean unsavedChanges = false;
        while (true) {
            int choice = showMenuAndGetChoice();
            switch (choice) {
                case 1:
                    controller.addStudent();
                    unsavedChanges = true;
                    break;
                case 2:
                    controller.updateStudent();
                    unsavedChanges = true;
                    break;
                case 3:
                    controller.displayStudentList();
                    break;
                case 4:
                    controller.deleteStudent();
                    unsavedChanges = true;
                    break;
                case 5:
                    controller.searchStudentByName();
                    break;
                case 6:
                    controller.filterStudentsByCampus();
                    break;
                case 7:
                    controller.statisticsByMountainPeak();
                    break;
                case 8:
                    controller.saveRegistrationsToDatFile();
                    unsavedChanges = false;
                    break;
                case 9:
                    if (unsavedChanges) {
                        System.out.print("You have unsaved changes. Do you want to save the changes before exiting? (Y/N): ");
                        String ans = SC.nextLine().trim();
                        if (ans.equalsIgnoreCase("Y")) {
                            controller.saveRegistrationsToDatFile();
                            System.out.println("Data saved. Exiting program...");
                        } else if (ans.equalsIgnoreCase("N")) {
                            System.out.print("Are you sure you want to exit without saving? (Y/N): ");
                            String confirm = SC.nextLine().trim();
                            if (confirm.equalsIgnoreCase("Y")) {
                                System.out.println("Exiting program without saving...");
                            } else {
                                System.out.println("Returning to main menu.");
                                break;
                            }
                        } else {
                            System.out.println("Invalid input. Returning to main menu.");
                            break;
                        }
                    } else {
                        System.out.println("Exiting program...");
                    }
                    System.exit(0);
                    break;
                default:
                    System.out.println("This function is not available.");
                    break;
            }
        }
    }

    public static int showMenuAndGetChoice() {
        System.out.println("========= STUDENT REGISTRATION MENU =========");
        System.out.println("1. New Registration.");
        System.out.println("2. Update Registration Information.");
        System.out.println("3. Display Registered List.");
        System.out.println("4. Delete Registration Information.");
        System.out.println("5. Search Participants by Name.");
        System.out.println("6. Filter Data by Campus.");
        System.out.println("7. Statistics of Registration Numbers by Location.");
        System.out.println("8. Save Data to File.");
        System.out.println("9. Exit the Program.");
        System.out.println("=============================================");
        int choice = -1;
        while (true) {
            System.out.print("Please enter your choice (1-9): ");
            String input = SC.nextLine().trim();
            try {
                choice = Integer.parseInt(input);
                if (choice < 1 || choice > 9) {
                    System.out.println("Invalid choice! Please enter a number between 1 and 9.");
                } else {
                    break;
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Please enter a number between 1 and 9.");
            }
        }
        return choice;
    }
}
