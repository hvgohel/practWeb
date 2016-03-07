package com.dw.practWeb.email.configuration;


import java.io.IOException;
import java.util.Properties;
import java.util.Set;

import javax.mail.internet.MimeMessage;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.mail.MailParseException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import com.dw.practWeb.email.configuration.dto.Attachment;
import com.dw.practWeb.email.configuration.dto.Message;

@Component
public class EmailSender {

  private static Logger logger = LoggerFactory.getLogger(EmailSender.class);

  @Autowired
  private ResourceLoader resourceLoader;

  /**
   * This method used to send mail.
   * 
   * @param formSubmission instance of {@link FormSubmission}
   * @throws IOException 
   */
  @Async
  public void sendEmail() throws IOException {
    logger.debug("sendEmail() :: Sending E-mail.");

    Resource resource = resourceLoader.getResource("classpath:smtpConfig.properties");

    Properties smtpProperties = new Properties();

    smtpProperties.load(resource.getInputStream());

    JavaMailSender javaMailSender = JavaMailSenderFactory.get(smtpProperties);

    Message message = createAdminMessage();
    send(message, javaMailSender);

    logger.debug("sendEmail() :: AutoResponse email is not sent, as it is disabled.");
  }


  /**
   * Separating `to` using `,`. <br/>
   * `to` must be comma separated string.
   * 
   * @param to comma separated string
   * @return String[]
   * @throws IllegalArgumentException if `to` is {@literal null} or blank
   */
  private String[] separateTo(String to) {
    logger.debug("getTo() :: Separating to '{}'.", to);
    return StringUtils.split(to, ",");
  }

  /**
   * Prepares admin {@link Message} instance.
   * 
   * @param formSubmission instance of {@link FormSubmission}
   * @return {@link Message} instance
   * @throws RuntimeException if error occurred while getting template data
   */
  private Message createAdminMessage() {
    logger.debug("createAdminMessage() :: Creating message for admin email.");
    
    Message message = new Message();
    message.setFrom("jaydeep@gmail.com");
    message.setTo("jaydeepkumbhani.dream@gmail.com");
    message.setReplyTo("jaydeepkumbhani.dream@gmail.com");
    
    message.setSubject("Testing Email");
    message.setHtml("This is testing mail");
    message.setText("This is testing mail");

//    String subject = templateEngine.mergeTemplate(emailConfig.getSubject(), formSubmission.getFormSubmissionIntoMap(),
//        "adminEmailSubject");
//    message.setSubject(subject);
//
//    try {
//      InputStream inputStream = new FileInputStream(form.getAdminEmailHtml());
//      String html =
//          templateEngine.mergeTemplate(inputStream, formSubmission.getFormSubmissionIntoMap(), "adminEmailHtml");
//      message.setHtml(html);
//
//      inputStream = new FileInputStream(form.getAdminEmailText());
//      String text =
//          templateEngine.mergeTemplate(inputStream, formSubmission.getFormSubmissionIntoMap(), "adminEmailText");
//      message.setText(text);
//    } catch (Exception exception) {
//      throw new RuntimeException("Error occurred while parsing template for adminEmail's html and text", exception);
//    }
//
//    List<Attachment> attachment = formSubmission.getAttachmentList();
//    if (CollectionUtils.isNotEmpty(attachment)) {
//      message.setAttachments(new HashSet<>(formSubmission.getAttachmentList()));
//    }
    return message;
  }

  /**
   * Sends email using received message data.
   * 
   * @param message instance of {@link Message}
   * @param mailSender instance of {@link JavaMailSender}
   * @throws MailParseException if error occurred during sending mail
   */
  private void send(Message message, JavaMailSender mailSender) {
    logger.debug("send() :: Sending email to '{}'", message.getTo());

    MimeMessage mimeMessage = mailSender.createMimeMessage();

    // create mime message
    try {
      MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);

      helper.setFrom(message.getFrom());
      helper.setSubject(message.getSubject());
      helper.setText(message.getText(), message.getHtml());
      helper.setReplyTo(message.getReplyTo());

      String[] to = separateTo(message.getTo());
      helper.setTo(to);

//      addAttachments(helper, message.getAttachments());
    } catch (Exception exception) {
      throw new MailParseException("Problem occured while sending email : \n", exception);
    }

    // send message
    mailSender.send(mimeMessage);
    logger.debug("send() :: Email has been sent successfully.");
  }

  /**
   * used to attach received attachments with email being sent.
   * 
   * @param helper MimeMessageHelper received message helper instance
   * @param attachments received attachments to be attached with email
   */
  private void addAttachments(MimeMessageHelper helper, final Set<Attachment> attachments) {

    if (CollectionUtils.isEmpty(attachments)) {
      logger.debug("addAttachments() :: No attachments found." + "So, skipping adding attachments.");
      return;
    }

    int count = 0;
    for (Attachment attachment : attachments) {
      try {
        helper.addAttachment(attachment.getName(), attachment.getFile());
        count++;
      } catch (Exception exception) {
        logger.error("addAttachments() :: Problem occured while adding attachment for file: {}", attachment.getName(),
            exception);
      }
    }

    logger.debug("addAttachments() :: {} attachments has been attached.", count);
  }
}
