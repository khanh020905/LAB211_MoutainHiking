/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controllers;

import Helpers.InputData;
import Helpers.TuitionCalculator;
import Helpers.Validator;
import Models.Mountain;
import Models.Student;
import java.util.ArrayList;

public class StudentController extends ArrayList<Student> {

    private ArrayList<Mountain> mountainList = new MountainController().uploadFile();

    private ArrayList getValidMountainCodes() {
        ArrayList<Integer> validCodes = new ArrayList<Integer>();
        for (Mountain m : mountainList) {
            validCodes.add(m.getCode());
        }
        return validCodes;
    }

    private String getMountainPeakCode(int code) {
        return "MT" + code;
    }

    public String getUniqueStudentId() {
        String studentId = InputData.inputStudentId();
        while (true) {
            if (this != null && !Validator.isUniqueStudentId(studentId, this)) {
                System.out.println("Student id already exists! Please enter another id.");
                continue;
            }
            break;
        }
        return studentId;
    }

    private Student findStudentById(String studentId) {
        for (Student s : this) {
            if (s.getStudentId().equalsIgnoreCase(studentId)) {
                return s;
            }
        }
        return null;
    }

    public Student getStudentByAction(String action) {
        String studentId, studentName, studentPhoneNumber, studentEmail = "";
        int mountainCode = -1;

        if ("ADD".equalsIgnoreCase(action)) {
            studentId = getUniqueStudentId();
        } else {
            studentId = InputData.inputStudentId();
            Student student = findStudentById(studentId);
            if (student == null) {
                System.out.println("This student has not registered yet.");
                return null;
            }
            System.out.println("Enter new information (leave blank to keep current):");
        }

        studentName = InputData.inputStudentName();
        studentPhoneNumber = InputData.inputStudentPhoneNumber();
        studentEmail = InputData.inputStudentEmail();
        mountainCode = InputData.inputMountainCode(getValidMountainCodes());
        double tuition = TuitionCalculator.calculateTuition(studentPhoneNumber);
        Student student = new Student(studentId, studentName, studentPhoneNumber, studentEmail, mountainCode, tuition);
        return student;
    }

    public void addStudent() {
        Student student = getStudentByAction("ADD");
        if (student == null) {
            System.out.println("Add student failed !!!");
            return;
        }

        this.add(student);
        System.out.println("Add student successfully !!!");
    }

    public void updateStudent() {
        if (this.isEmpty()) {
            System.out.println("No students in the system!");
            return;
        }
        Student student = getStudentByAction("UPDATE");
        if (student == null) {
            System.out.println("Update failed!");
            return;
        }
        Student oldStudent = findStudentById(student.getStudentId());

        oldStudent.setName(student.getName());
        oldStudent.setPhoneNumber(student.getPhoneNumber());
        oldStudent.setEmail(student.getEmail());
        oldStudent.setMountianCode(student.getMountianCode());
        oldStudent.setTuitionFee(student.getTuitionFee());
        System.out.println("Update successful!");
    }

    public void deleteStudent() {
        if (this.isEmpty()) {
            System.out.println("No students to delete!");
            return;
        }
        String studentId = InputData.inputStudentId();
        Student student = findStudentById(studentId);
        if (student == null) {
            System.out.println("This student has not registered yet.");
            return;
        }
        System.out.println("Student Details:");
        System.out.println("-----------------------------------------------------");
        System.out.println("Student ID: " + student.getStudentId());
        System.out.println("Name       : " + student.getName());
        System.out.println("Phone      : " + student.getPhoneNumber());
        System.out.println("Mountain   : " + getMountainPeakCode(student.getMountianCode()));
        System.out.println("Fee        : " + String.format("%,.0f", student.getTuitionFee()));
        System.out.println("-----------------------------------------------------");
        System.out.print("Are you sure you want to delete this registration? (Y/N): ");
        String confirm = new java.util.Scanner(System.in).nextLine().trim();
        if (confirm.equalsIgnoreCase("Y")) {
            this.remove(student);
            System.out.println("\nThe registration has been successfully deleted.");
        } else {
            System.out.println("\nDeletion cancelled. Returning to main menu.");
        }
    }

    public void displayStudentList() {
        if (this.isEmpty()) {
            System.out.println("No students have registered yet.");
            return;
        }
        System.out.println("Registered Students:");
        System.out.println("----------------------------------------------------------------");
        System.out.printf("%-12s | %-20s | %-11s | %-9s | %-10s\n",
                "Student ID", "Name", "Phone", "Peak Code", "Fee");
        System.out.println("----------------------------------------------------------------");
        for (Student s : this) {
            String peakCode = getMountainPeakCode(s.getMountianCode());
            System.out.printf("%-12s | %-20s | %-11s | %-9s | %,10.0f\n",
                    s.getStudentId(),
                    s.getName(),
                    s.getPhoneNumber(),
                    peakCode,
                    s.getTuitionFee());
        }
        System.out.println("----------------------------------------------------------------");
    }

    public void searchStudentByName() {
        if (this.isEmpty()) {
            System.out.println("No students in the system!");
            return;
        }
        System.out.print("Enter name or partial name to search: ");
        String keyword = new java.util.Scanner(System.in).nextLine().trim().toLowerCase();
        ArrayList<Student> results = new ArrayList<>();
        for (Student student : this) {
            if (student.getName().toLowerCase().contains(keyword)) {
                results.add(student);
            }
        }
        if (results.isEmpty()) {
            System.out.println("No one matches the search criteria!");
            return;
        }
        System.out.println("Matching Students:");
        System.out.println("----------------------------------------------------------------");
        System.out.printf("%-12s | %-20s | %-11s | %-9s | %-10s\n",
                "Student ID", "Name", "Phone", "Peak Code", "Fee");
        System.out.println("----------------------------------------------------------------");
        for (Student student : results) {
            String peakCode = getMountainPeakCode(student.getMountianCode());
            System.out.printf("%-12s | %-20s | %-11s | %-9s | %,10.0f\n",
                    student.getStudentId(),
                    student.getName(),
                    student.getPhoneNumber(),
                    peakCode,
                    student.getTuitionFee());
        }
        System.out.println("----------------------------------------------------------------");
    }

    public void filterStudentsByCampus() {
        if (this.isEmpty()) {
            System.out.println("No students have registered yet.");
            return;
        }
        System.out.print("Enter campus code (CE, DE, HE, SE, QE): ");
        String campusCode = new java.util.Scanner(System.in).nextLine().trim().toUpperCase();

        String campusLocation;
        switch (campusCode) {
            case "CE":
                campusLocation = "Can Tho";
                break;
            case "DE":
                campusLocation = "Da Nang";
                break;
            case "HE":
                campusLocation = "Ha Noi";
                break;
            case "SE":
                campusLocation = "Sai Gon";
                break;
            case "QE":
                campusLocation = "Quy Nhon";
                break;
            default:
                campusLocation = "Unknown";
                break;
        }

        ArrayList<Student> filtered = new ArrayList<>();
        for (Student s : this) {
            if (s.getStudentId().toUpperCase().startsWith(campusCode)) {
                filtered.add(s);
            }
        }

        if (filtered.isEmpty()) {
            System.out.println("No students have registered under this campus.");
            return;
        }
        System.out.printf("Registered Students Under %s Campus (%s):\n", campusLocation, campusCode);
        System.out.println("-----------------------------------------------------------------------------");
        System.out.printf("%-12s | %-20s | %-11s | %-9s | %-10s\n",
                "Student ID", "Name", "Phone", "Mountain", "Fee");
        System.out.println("-----------------------------------------------------------------------------");
        for (Student s : filtered) {
            String peakCode = getMountainPeakCode(s.getMountianCode());
            System.out.printf("%-12s | %-20s | %-11s | %-9s | %,10.0f\n",
                    s.getStudentId(),
                    s.getName(),
                    s.getPhoneNumber(),
                    peakCode,
                    s.getTuitionFee());
        }
        System.out.println("-----------------------------------------------------------------------------");
    }

    public void statisticsByMountainPeak() {
        if (this.isEmpty()) {
            System.out.println("No students have registered yet.");
            return;
        }
        System.out.println("Statistics of Registration by Mountain Peak:");
        System.out.println("-----------------------------------------------------------------");
        System.out.printf("%-12s | %-22s | %-10s\n", "Peak Name", "Number of Participants", "Total Cost");
        System.out.println("-----------------------------------------------------------------");
        for (Mountain m : mountainList) {
            int code = m.getCode();
            int count = 0;
            double totalFee = 0;
            for (Student s : this) {
                if (s.getMountianCode() == code) {
                    count++;
                    totalFee += s.getTuitionFee();
                }
            }
            if (count > 0) {
                String peakName = getMountainPeakCode(code);
                System.out.printf("%-12s | %-22d | %,10.0f\n", peakName, count, totalFee);
            }
        }
        System.out.println("-----------------------------------------------------------------");
    }

    public void saveRegistrationsToDatFile() {
        try (java.io.ObjectOutputStream oos = new java.io.ObjectOutputStream(
                new java.io.FileOutputStream("registrations.dat"))) {
            oos.writeObject(this);
            System.out.println("Registration data has been successfully saved to registrations.dat.");
        } catch (Exception e) {
            System.out.println("Failed to save registration data: " + e.getMessage());
        }
    }

    public static StudentController loadRegistrationsFromDatFile() {
        try (java.io.ObjectInputStream ois = new java.io.ObjectInputStream(
                new java.io.FileInputStream("registrations.dat"))) {
            Object obj = ois.readObject();
            if (obj instanceof StudentController) {
                System.out.println("Registration data loaded successfully from registrations.dat.");
                return (StudentController) obj;
            } else {
                System.out.println("File format is incorrect.");
            }
        } catch (Exception e) {
        }
        return new StudentController();
    }
}
