package com.dw.practWeb.email.configuration.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.io.File;

/**
 * This Dto is used for Attachment.
 */
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class Attachment {

  private String name;
  private String contentType;
  private File file;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getContentType() {
    return contentType;
  }

  public void setContentType(String contentType) {
    this.contentType = contentType;
  }

  public File getFile() {
    return file;
  }

  public void setFile(File file) {
    this.file = file;
  }
}
