package com.example.Event.Management.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.Event.Management.Service.EmailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Controller for handling email-related requests.
 */
@RestController
public class EmailController {

    private static final Logger logger = LoggerFactory.getLogger(EmailController.class);

    @Autowired
    private EmailService emailService;

    /**
     * Endpoint for sending an email.
     *
     * @param to the recipient's email address
     * @param subject the subject of the email
     * @param body the body of the email
     * @return a confirmation message
     */
    @GetMapping("/sendEmail")
    public String sendEmail(@RequestParam String to, @RequestParam String subject, @RequestParam String body) {
        logger.info("Sending email to: {}", to);
        
        // Validate email parameters
        if (to == null || to.isEmpty()) {
            logger.warn("Email recipient address is missing.");
            return "Failed to send email: recipient address is missing.";
        }

        if (subject == null || subject.isEmpty()) {
            logger.warn("Email subject is missing.");
            return "Failed to send email: subject is missing.";
        }

        if (body == null || body.isEmpty()) {
            logger.warn("Email body is missing.");
            return "Failed to send email: body is missing.";
        }

        // Attempt to send the email
        try {
            emailService.sendEmail(to, subject, body);
            logger.info("Email sent successfully to: {}", to);
            return "Email sent!";
        } catch (Exception e) {
            logger.error("Failed to send email to: {}. Error: {}", to, e.getMessage());
            return "Failed to send email.";
        }
    }
}
