package Model;

import java.io.Serializable;

public class Student implements Serializable {

    private String studentId;
    private String name;
    private String phoneNumber;
    private String email;
    private int mountianCode;
    private double tuitionFee;

    public Student(String studentId, String name, String phoneNumber, String email, int mountianCode, double tuitionFee) {
        this.studentId = studentId;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.mountianCode = mountianCode;
        this.tuitionFee = tuitionFee;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getMountianCode() {
        return mountianCode;
    }

    public void setMountianCode(int mountianCode) {
        this.mountianCode = mountianCode;
    }

    public double getTuitionFee() {
        return tuitionFee;
    }

    public void setTuitionFee(double tuitionFee) {
        this.tuitionFee = tuitionFee;
    }

    @Override
    public String toString() {
        return studentId + ", " + name + ", " + phoneNumber + ", " + email + ", " + mountianCode + ", " + tuitionFee; 
    }
}
