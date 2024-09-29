//package com.qa.email;
//
//import com.qa.config.Config;
//
//import javax.mail.*;
//import javax.mail.internet.*;
//import java.util.Properties;
//
//public class EmailService {
//
//    private final String host;
//    private final String user;
//    private final String password;
//
//    public EmailService() {
//        Config config = new Config();
//        this.host = config.getSmtpHost();
//        this.user = config.getSmtpUser();
//        this.password = config.getSmtpPassword();
//    }
//
//    public void sendEmailWithAttachment(String attachmentPath) {
//        Properties props = new Properties();
//        props.put("mail.smtp.auth", "true");
//        props.put("mail.smtp.starttls.enable", "true");
//        props.put("mail.smtp.host", host);
//        props.put("mail.smtp.port", "587");
//
//        Session session = Session.getInstance(props,
//                new javax.mail.Authenticator() {
//                    protected PasswordAuthentication getPasswordAuthentication() {
//                        return new PasswordAuthentication(user, password);
//                    }
//                });
//
//        try {
//            Message message = new MimeMessage(session);
//            message.setFrom(new InternetAddress(user));
//            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(new Config().getEmailRecipient()));
//            message.setSubject(new Config().getEmailSubject());
//
//            MimeBodyPart messageBodyPart = new MimeBodyPart();
//            messageBodyPart.setText(new Config().getEmailBody());
//
//            MimeBodyPart attachmentPart = new MimeBodyPart();
//            attachmentPart.attachFile(attachmentPath);
//
//            Multipart multipart = new MimeMultipart();
//            multipart.addBodyPart(messageBodyPart);
//            multipart.addBodyPart(attachmentPart);
//
//            message.setContent(multipart);
//
//            Transport.send(message);
//            System.out.println("Email sent successfully with attachment.");
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//}
