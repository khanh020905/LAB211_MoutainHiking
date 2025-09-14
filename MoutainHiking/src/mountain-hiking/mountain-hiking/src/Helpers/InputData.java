/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Helpers;

import Models.Student;
import java.util.Scanner;
import java.util.List;

public class InputData {

    private static final Scanner SC = new Scanner(System.in);

    public static String inputStudentId() {
        String studentId;
        while (true) {
            System.out.print("Please enter student id: ");
            studentId = SC.nextLine().trim();
            if (!Validator.checkStudentIdFormat(studentId)) {
                System.out.println("Invalid format! Example: SE123456");
            } else {
                break;
            }
        }
        return studentId;
    }

    public static String inputStudentName() {
        String studentName;
        while (true) {
            System.out.print("Please enter student name: ");
            studentName = SC.nextLine().trim();
            if (!Validator.checkStudentNameFormat(studentName)) {
                System.out.println("Name must be 2-20 characters and not empty!");
            } else {
                break;
            }
        }
        return studentName;
    }

    public static String inputStudentPhoneNumber() {
        String phoneNumber;
        while (true) {
            System.out.print("Please enter student phone number: ");
            phoneNumber = SC.nextLine().trim();
            if (!Validator.checkStudentPhoneNumberFormat(phoneNumber)) {
                System.out.println("Phone number must be 10 digits and belong to a valid Vietnamese operator!");
            } else {
                break;
            }
        }
        return phoneNumber;
    }

    public static String inputStudentEmail() {
        String email;
        while (true) {
            System.out.print("Please enter student email: ");
            email = SC.nextLine().trim();
            if (!Validator.checkStudentEmailFormat(email)) {
                System.out.println("Invalid email format!");
            } else {
                break;
            }
        }
        return email;
    }

    public static int inputMountainCode(List<Integer> validCodes) {
        int code = -1;
        while (true) {
            System.out.print("Please enter mountain code: ");
            String input = SC.nextLine().trim();
            if (!Validator.checkMountainCode(input, validCodes)) {
                if (!isInteger(input)) {
                    System.out.println("Mountain code must be a number!");
                } else {
                    System.out.println("Invalid mountain code! Please choose from the available codes.");
                }
                continue;
            }
            code = Integer.parseInt(input);
            break;
        }
        return code;
    }

    private static boolean isInteger(String s) {
        try {
            Integer.parseInt(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
