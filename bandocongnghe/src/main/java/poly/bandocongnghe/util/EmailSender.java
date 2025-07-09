/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package poly.bandocongnghe.util;
import jakarta.mail.*;
import jakarta.mail.internet.*;
import java.util.Properties;

public class EmailSender {
    public static void sendEmail(String EMAIL,String tieude, String noidung) {
        final String senderEmail = "lamkodeptrai2@gmail.com";
        final String senderPassword = "fnintmtlvveeqasw"; // Sử dụng App Password từ Google
//        final String senderPassword = "xyvn ktls nnnp zgxx"; // Sử dụng App Password từ Google



        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(senderEmail, senderPassword);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(senderEmail));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(EMAIL));
            message.setSubject(tieude);
            message.setText(noidung);

            Transport.send(message);
            System.out.println("Gửi email thành công!");

        } catch (AuthenticationFailedException e) {
    System.err.println("Xác thực thất bại! Kiểm tra email hoặc app password.");
} catch (MessagingException e) {
    e.printStackTrace();
}

    }

    public static void main(String[] args) {
        sendEmail("ln678090@gmail.com","hihi", "123456");
    }
}