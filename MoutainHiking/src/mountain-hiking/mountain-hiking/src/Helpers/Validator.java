/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Helpers;

import Models.Student;
import java.util.List;

public class Validator {

    private static final String REGEX_STUDENT_ID = "^(SE|se|HE|he|DE|de|QE|qe|CE|ce)\\d{6}$";
    private static final String REGEX_STUDENT_NAME = "^.{2,20}$";
    private static final String REGEX_STUDENT_PHONE_NUMBER = "^(0(32|33|34|35|36|37|38|39|86|96|97|98|81|82|83|84|85|88|91|94|70|76|77|78|79|89|90|93|56|58|92|59|99))\\d{7}$";
    private static final String REGEX_STUDENT_EMAIL = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";

    public static boolean checkStudentIdFormat(String studentId) {
        return studentId.matches(REGEX_STUDENT_ID);
    }

    public static boolean checkStudentNameFormat(String studentName) {
        return studentName.matches(REGEX_STUDENT_NAME);
    }

    public static boolean checkStudentPhoneNumberFormat(String phoneNumber) {
        return phoneNumber.matches(REGEX_STUDENT_PHONE_NUMBER);
    }

    public static boolean checkStudentEmailFormat(String email) {
        return email.matches(REGEX_STUDENT_EMAIL);
    }

    public static boolean isUniqueStudentId(String studentId, List<Student> studentList) {
        if (studentList == null) {
            return true;
        }
        for (Student s : studentList) {
            if (studentId.equals(s.getStudentId())) {
                return false;
            }
        }
        return true;
    }

    public static boolean checkMountainCode(String input, List<Integer> validCodes) {
        try {
            int code = Integer.parseInt(input);
            return validCodes.contains(code);
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
