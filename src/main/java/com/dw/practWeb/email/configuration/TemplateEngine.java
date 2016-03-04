package com.dw.practWeb.email.configuration;

import java.io.InputStream;
import java.util.Map;

public interface TemplateEngine {


  /**
   * This method used to merge template with given data.
   * 
   * @param templateText text data of template
   * @param data data to be used in template
   */
  public String mergeTemplate(String templateText, Map<String, Object> data, String tag);


  /**
   * This method used to merge template with given data.
   * 
   * @param templateStream inputstream of temaplate
   * @param data data to be used in template
   */
  public String mergeTemplate(InputStream templateStream, Map<String, Object> data, String tag);
}
