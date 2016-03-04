package com.dw.practWeb.email.configuration.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.util.Set;

/**
 * This Dto is used for Message.
 */
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class Message {

  private String from;
  private String to;
  private String subject;
  private String html;
  private String text;
  private String replyTo;
  private Set<Attachment> attachments;

  public String getFrom() {
    return from;
  }

  public void setFrom(String from) {
    this.from = from;
  }

  public String getTo() {
    return to;
  }

  public void setTo(String to) {
    this.to = to;
  }

  public String getSubject() {
    return subject;
  }

  public void setSubject(String subject) {
    this.subject = subject;
  }

  public String getHtml() {
    return html;
  }

  public void setHtml(String html) {
    this.html = html;
  }

  public String getText() {
    return text;
  }

  public void setText(String text) {
    this.text = text;
  }

  public String getReplyTo() {
    return replyTo;
  }

  public void setReplyTo(String replyTo) {
    this.replyTo = replyTo;
  }

  public Set<Attachment> getAttachments() {
    return attachments;
  }

  public void setAttachments(Set<Attachment> attachments) {
    this.attachments = attachments;
  }
}
