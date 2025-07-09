/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package poly.bandocongnghe.util;

import java.security.SecureRandom;

/**
 *
 * @author lam
 */
public class OTPGenerator {
    public static String generateOTP(int length) {
        SecureRandom random = new SecureRandom();
        StringBuilder otp =new StringBuilder();
        
        String digits ="0123456789";
        for (int i = 0; i < length; i++) {
            otp.append(digits.charAt(random.nextInt(digits.length())));
        }
        return otp.toString();
    }
}
