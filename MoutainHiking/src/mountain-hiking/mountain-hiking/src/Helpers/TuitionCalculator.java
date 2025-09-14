/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Helpers;

/**
 *
 * @author hoade
 */
public class TuitionCalculator {
    private static final double DEFAULT_TUITION = 6000000;

    private static final String[] VIETTEL_PREFIXES = 
        {"032","033","034","035","036","037","038","039","086","096","097","098"};
    
    private static final String[] VINAPHONE_PREFIXES = 
        {"081","082","083","084","085","088","091","094"};

    public static double calculateTuition(String phoneNumber) {
        String firstThreeDigits = phoneNumber.substring(0, 3);
        if (isViettel(firstThreeDigits) || isVinaphone(firstThreeDigits)) {
            return DEFAULT_TUITION * 0.65;
        }
        return DEFAULT_TUITION;
    }

    private static boolean isViettel(String firstThreeDigits) {
        for (String p : VIETTEL_PREFIXES) {
            if (p.equals(firstThreeDigits)) return true;
        }
        return false;
    }

    private static boolean isVinaphone(String firstThreeDigits) {
        for (String p : VINAPHONE_PREFIXES) {
            if (p.equals(firstThreeDigits)) return true;
        }
        return false;
    }
}
