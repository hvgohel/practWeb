package com.dw.practWeb.email.configuration;


import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.util.Map;

import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class VelocityTemplateEngine implements TemplateEngine {

  private static Logger logger = LoggerFactory.getLogger(VelocityTemplateEngine.class);

  @Autowired
  private VelocityEngine velocityEngine;

  @Autowired
  private TemplateUtil templateUtil;

  @Override
  public String mergeTemplate(String templateText, Map<String, Object> data, String tag) {
    logger.debug("mergeTemplate() :: Merging template using text '{}'.", templateText);

    VelocityContext context = new VelocityContext();

    // merge TemplateUtil's common helper objects in to context
    for (Map.Entry<String, Object> entry : templateUtil.getCommonTemplateData().entrySet()) {
      context.put(entry.getKey(), entry.getValue());
    }

    // add received data into context
    if (data != null) {
      for (Map.Entry<String, Object> entry : data.entrySet()) {
        context.put(entry.getKey(), entry.getValue());
      }
    }

    StringWriter writer = new StringWriter();
    velocityEngine.evaluate(context, writer, tag, templateText);

    return writer.toString();
  }

  @Override
  public String mergeTemplate(InputStream templateStream, Map<String, Object> data, String tag) {
    logger.debug("mergeTemplate() :: Merging template using text");

    VelocityContext context = new VelocityContext();

    // merge TemplateUtil's common helper objects in to context
    for (Map.Entry<String, Object> entry : templateUtil.getCommonTemplateData().entrySet()) {
      context.put(entry.getKey(), entry.getValue());
    }

    // add received data into context
    if (data != null) {
      for (Map.Entry<String, Object> entry : data.entrySet()) {
        context.put(entry.getKey(), entry.getValue());
      }
    }

    StringWriter writer = new StringWriter();
    Reader inputStreamReader;
    try {
      inputStreamReader = new InputStreamReader(templateStream, "UTF-8");
    } catch (UnsupportedEncodingException exception) {
      throw new IllegalArgumentException("Error occured during creating reader.", exception);
    }

    velocityEngine.evaluate(context, writer, tag, inputStreamReader);

    return writer.toString();
  }
}
