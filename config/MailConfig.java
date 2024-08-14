package com.example.Event.Management.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

/**
 * Configuration class for setting up JavaMailSender.
 */
@Configuration
public class MailConfig {

    private static final Logger logger = LoggerFactory.getLogger(MailConfig.class);

    /**
     * Configures and returns a JavaMailSender bean.
     * @return a configured JavaMailSender instance.
     */
    @Bean
    public JavaMailSender javaMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        
        try {
            // Set mail server properties
            mailSender.setHost("smtp.example.com"); // Update to your SMTP server
            mailSender.setPort(587); // SMTP port (587 is for TLS, 465 for SSL)
            mailSender.setUsername("your-email@example.com"); // Update to your email
            mailSender.setPassword("your-email-password"); // Update to your email password

            Properties props = mailSender.getJavaMailProperties();
            props.put("mail.transport.protocol", "smtp");
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.debug", "true"); // Enable debugging for troubleshooting

            logger.info("JavaMailSender bean configured successfully");
        } catch (Exception e) {
            logger.warn("Failed to configure JavaMailSender: {}", e.getMessage());
        }

        return mailSender;
    }
}
