package Controller;

import Model.Moutain;
import Model.Student;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class StudentList extends ArrayList<Student> {

    MoutainList ml = new MoutainList();

    public void addStudent() {
        ml.readFile("MountainList.csv");
        Scanner sc = new Scanner(System.in);
        String studentId = null, name = null, phoneNumber = null, email;
        int mountainCode;

        System.out.println("Enter student ID: ");

        while (true) {
            studentId = sc.nextLine();

            if (!studentId.matches("(SE|HE|DE|QE|CE)\\d{6}")) {
                System.out.println("Wrong student ID format. Try again!");
            } else if (!checkStudentId(studentId)) {
                System.out.println("Student ID already exists. Enter another!");
            } else {
                break;
            }
        }

        System.out.println("Enter student name: ");

        do {
            name = sc.nextLine();

            if (name.length() < 2 || name.length() > 20) {
                System.out.println("Invalid name");
            }

        } while (name.length() < 2 || name.length() > 20);

        System.out.println("Enter phone number: ");

        do {
            phoneNumber = sc.nextLine();

            if (!phoneNumber.matches("\\d{10}")) {
                System.out.println("Invalid phone number!");
            }
        } while (!phoneNumber.matches("\\d{10}"));

        System.out.println("Enter Email: ");

        do {
            email = sc.nextLine();

            if (!email.matches("^[A-Za-z0-9]+@[a-z]+\\.com$")) {
                System.out.println("Invalid email!");
            } else {
                break;
            }

        } while (true);

        System.out.println("Enter moutain code: ");

        while (true) {
            try {

                mountainCode = sc.nextInt();
                boolean found = false;

                for (Moutain m : ml) {
                    if (m.getCode() == mountainCode) {
                        found = true;
                        break;
                    }
                }

                if (found) {
                    break;
                } else {
                    System.out.println("Invalid moutain code! try again");
                }

            } catch (Exception e) {
                System.out.println("Moutain code must be a number");
            }
        }

        double tuition = calculateTuitionFee(phoneNumber);

        System.out.println("Student added successfully!");

        this.add(new Student(studentId, name, phoneNumber, email, mountainCode, tuition));

    }

    public boolean checkStudentId(String studentId) {
        for (Student s : this) {
            if (!s.getStudentId().matches("(SE|HE|DE|QE|CE)\\d{6}")) {
                return false;
            }
        }

        for (Student s : this) {
            if (s.getStudentId().equalsIgnoreCase(studentId)) {
                return true;
            }
        }

        return true;
    }

    public double calculateTuitionFee(String phoneNumber) {
        double defaultTuition = 6000000;
        double discountRate = 0.35;

        String[] viettelNumber = {"086", "096", "097", "098", "032", "033", "034", "035", "036", "037", "038", "039"};
        String[] vnptNumber = {"091", "094", "083", "084", "085", "081", "082"};

        String prefix = phoneNumber.substring(0, 3);
        boolean discount = false;

        for (String p : viettelNumber) {
            if (prefix.equalsIgnoreCase(p)) {
                discount = true;
                break;
            }
        }

        if (discount == false) {
            for (String p : vnptNumber) {
                if (prefix.equalsIgnoreCase(p)) {
                    discount = true;
                    break;
                }
            }
        }

        if (discount) {
            return defaultTuition * (1 - discountRate);
        }
        return defaultTuition;
    }

    public void updateStudent(String studentId) {
        Scanner sc = new Scanner(System.in);
        if (studentId.length() != 8) {
            System.out.println("Student Id must be 8 characters");
            return;
        }

        Student found = null;

        for (Student s : this) {
            if (s.getStudentId().equalsIgnoreCase(studentId)) {
                found = s;
            }
        }

        if (found == null) {
            System.out.println("This student has not register yet");
        } else {
            System.out.println("Enter student name: ");
            String name;

            do {
                name = sc.nextLine();

                if (name.length() < 2 || name.length() > 20) {
                    System.out.println("Invalid name");
                }

            } while (name.length() < 2 || name.length() > 20);

            found.setName(name);

            System.out.println("Enter phone number: ");
            String phoneNumber;

            do {
                phoneNumber = sc.nextLine();

                if (!phoneNumber.matches("\\d{10}")) {
                    System.out.println("Invalid phone number!");
                }
            } while (!phoneNumber.matches("\\d{10}"));

            found.setPhoneNumber(phoneNumber);

            System.out.println("Enter Email: ");
            String email;

            do {
                email = sc.nextLine();

                if (!email.matches("^[A-Za-z0-9]+@[a-z]+\\.com$")) {
                    System.out.println("Invalid email!");
                } else {
                    break;
                }

            } while (true);

            found.setEmail(email);

            System.out.println("Enter moutain code: ");
            int mountainCode;

            while (true) {
                try {

                    mountainCode = sc.nextInt();
                    boolean founded = false;

                    for (Moutain m : ml) {
                        if (m.getCode() == mountainCode) {
                            founded = true;
                            break;
                        }
                    }

                    if (founded) {
                        break;
                    } else {
                        System.out.println("Invalid moutain code! try again");
                    }

                } catch (Exception e) {
                    System.out.println("Moutain code must be a number");
                }
            }
            found.setMountianCode(mountainCode);

            System.out.println("Student updated successfully!");
        }
    }

    public void displayStudent() {
        if (this.isEmpty()) {
            System.out.println("No students have registerd yet");
            return;
        }

        System.out.println("Registered Students:");
        System.out.println("-------------------------------------------------------------");
        System.out.printf("%-10s | %-15s | %-10s | %-8s | %-10s%n",
                "Student ID", "Name", "Phone", "Peak Code", "Fee");
        System.out.println("-------------------------------------------------------------");

        for (Student s : this) {
            System.out.printf("%-10s | %-15s | %-10s | %-8s | %,10.2f%n",
                    s.getStudentId(),
                    s.getName(),
                    s.getPhoneNumber(),
                    "MT" + String.format("%02d", s.getMountianCode()),
                    s.getTuitionFee());
        }
    }

    public void deleteRegister(String studentId) {
        Scanner sc = new Scanner(System.in);

        for (int i = 0; i < this.size(); i++) {
            Student s = this.get(i);

            if (s.getStudentId().equalsIgnoreCase(studentId)) {

                System.out.println("Student Details:");
                System.out.println("--------------------------------------------------");
                System.out.printf("Student ID : %s%n", s.getStudentId());
                System.out.printf("Name       : %s%n", s.getName());
                System.out.printf("Phone      : %s%n", s.getPhoneNumber());
                System.out.printf("Mountain   : MT%02d%n", s.getMountianCode());
                System.out.printf("Fee        : %,d%n", (long) s.getTuitionFee());
                System.out.println("--------------------------------------------------");

                System.out.println("Are you sure you want to delete this registration? (Y/N):");
                String choice = sc.nextLine().toLowerCase();
                if (choice.equals("y")) {
                    this.remove(i);
                    System.out.println("The registration has been successfully deleted.");
                    return;
                } else if (choice.equals("n")) {
                    return;
                }
            }
            System.out.println("This student has not registered yet");
            return;
        }
    }

    public void searchByName(String studentName) {

        ArrayList<Student> storeStudent = new ArrayList<>();

        for (Student s : this) {
            if (s.getName().equalsIgnoreCase(studentName) || s.getName().contains(studentName)) {
                storeStudent.add(s);
            }
        }

        if (storeStudent.isEmpty()) {
            System.out.println("No one matches the search criteria!");
        } else {
            System.out.println("Registered Students:");
            System.out.println("-------------------------------------------------------------");
            System.out.printf("%-10s | %-15s | %-10s | %-8s | %-10s%n",
                    "Student ID", "Name", "Phone", "Peak Code", "Fee");
            System.out.println("-------------------------------------------------------------");

            for (Student s : storeStudent) {
                System.out.printf("%-10s | %-15s | %-10s | %-8s | %,10f%n",
                        s.getStudentId(),
                        s.getName(),
                        s.getPhoneNumber(),
                        "MT" + String.format("%02d", s.getMountianCode()),
                        s.getTuitionFee());
            }
        }

    }

    public void filterDataByCampus() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter Campus Code (CE, DE, HE, SE, QE): ");
        String campusCode = sc.nextLine().toUpperCase().trim();

        ArrayList<Student> storeData = new ArrayList<>();
        String campusName = null;

        for (Student s : this) {
            if (s.getStudentId().toUpperCase().startsWith(campusCode)) {
                storeData.add(s);
            }
        }

        if (storeData.isEmpty()) {
            System.out.println("No students have registered under this campus");
            return;
        } else {

            switch (campusCode) {
                case "CE":
                    campusName = "Can Tho";
                    break;
                case "DE":
                    campusName = "Da Nang";
                    break;
                case "HE":
                    campusName = "Ha Noi";
                    break;
                case "SE":
                    campusName = "Ho Chi Minh";
                    break;
                case "QE":
                    campusName = "Quy Nhon";
                    break;

                default:
                    campusName = "unknown";
            }
        }

        System.out.printf("Registered Students Under %s Campus(%s): \n", campusName, campusCode);
        System.out.println("-------------------------------------------------------------");
        System.out.printf("%-10s | %-15s | %-10s | %-8s | %-10s%n",
                "Student ID", "Name", "Phone", "Mountain", "Fee");
        System.out.println("-------------------------------------------------------------");

        for (Student s : storeData) {
            System.out.printf("%-10s | %-15s | %-10s | %-8s | %,10d%n",
                    s.getStudentId(),
                    s.getName(),
                    s.getPhoneNumber(),
                    "MT" + String.format("%02d", s.getMountianCode()),
                    (long) s.getTuitionFee());
        }
        System.out.println("-------------------------------------------------------------");

    }

    public void statisticByLocation() {
        Map<Integer, int[]> stat = new HashMap<>();

        for (Student s : this) {
            int moutainCode = s.getMountianCode();

            int data[] = stat.getOrDefault(moutainCode, new int[2]);
            data[0]++;
            data[1] += s.getTuitionFee();
            stat.put(moutainCode, data);
        }

        if (stat.isEmpty()) {
            System.out.println("No registrations found.");
            return;
        }

        System.out.println("Statistics of Registration by Mountain Peak:");
        System.out.println("-------------------------------------------------------------");
        System.out.printf("%-10s | %-22s | %-15s%n",
                "Peak Name", "Number of Participants", "Total Cost");
        System.out.println("-------------------------------------------------------------");

        ArrayList<Integer> result = new ArrayList<>(stat.keySet());
        Collections.sort(result);

        for (Integer i : result) {
            int[] data = stat.get(i);
            String peakName = "MT" + i;
            int participants = data[0];
            int totalFee = data[1];

            System.out.printf("%-10s | %22d | %,15d%n",
                    peakName, participants, totalFee);
        }
        System.out.println("-------------------------------------------------------------");
    }

    public void saveFile() {
        String fileName = "registrations.dat";
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {

            oos.writeObject(new ArrayList<>(this));
            System.out.println("Registration data has been successfully saved to " + fileName);
        } catch (IOException e) {
            System.out.println("Error saving file: " + e.getMessage());
        }
    }
}
