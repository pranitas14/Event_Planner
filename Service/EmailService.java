package com.example.Event.Management.Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

/**
 * Service for sending emails using JavaMailSender.
 */
@Service
public class EmailService {

    private static final Logger logger = LoggerFactory.getLogger(EmailService.class);

    @Autowired
    private JavaMailSender javaMailSender;

    /**
     * Sends an email with the specified recipient, subject, and body.
     * @param to the recipient's email address.
     * @param subject the subject of the email.
     * @param body the body of the email.
     */
    public void sendEmail(String to, String subject, String body) {
        try {
            // Create a MimeMessageHelper to help construct the email message
            MimeMessageHelper messageHelper = new MimeMessageHelper(javaMailSender.createMimeMessage(), true);
            messageHelper.setTo(to); // Set the recipient address
            messageHelper.setSubject(subject); // Set the subject of the email
            messageHelper.setText(body, true); // Set the body of the email

            // Send the email
            javaMailSender.send(messageHelper.getMimeMessage());
            logger.info("Email sent successfully to {}", to);
        } catch (Exception e) {
            // Log the exception if there is an error sending the email
            logger.error("Failed to send email to {}: {}", to, e.getMessage(), e);
        }
    }
}
