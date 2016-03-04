package com.dw.practWeb.email.configuration;


import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

public class JavaMailSenderFactory {

  private static Logger logger = LoggerFactory.getLogger(JavaMailSenderFactory.class);

  /**
   * using this method sent email given stmp configuration.
   * 
   * @param config {@link SmtpConfig} instance
   */
  public static JavaMailSender get(Properties smtpProperties) {
    logger.debug("get() :: Getting JavaMailSender instance.");


    JavaMailSenderImpl mailSender = new JavaMailSenderImpl();

    mailSender.setHost(smtpProperties.getProperty("smtp.mailgun.org"));
    mailSender.setPort(Integer.valueOf(smtpProperties.getProperty("smtp.port")));
    mailSender.setUsername(smtpProperties.getProperty("smtp.userName"));
    mailSender.setPassword(smtpProperties.getProperty("smtp.password"));

    Properties javaMailProperties = new Properties();
    javaMailProperties.put("mail.smtp.auth", smtpProperties.getProperty("smtp.authentication"));
    javaMailProperties.put("mail.smtp.starttls.enable", smtpProperties.getProperty("smtp.startTlsEnable"));

    mailSender.setJavaMailProperties(javaMailProperties);


    return mailSender;
  }
}
