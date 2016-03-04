package com.dw.practWeb.email.configuration;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.velocity.tools.generic.DateTool;
import org.apache.velocity.tools.generic.EscapeTool;
import org.apache.velocity.tools.generic.LinkTool;
import org.apache.velocity.tools.generic.NumberTool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dw.practWeb.utils.AppConfig;

/**
 * Utility functions used for template (Velocity engine) features.
 * 
 */
@Component
public class TemplateUtil {

  private static Logger logger = LoggerFactory.getLogger(TemplateUtil.class);

  @Autowired
  private AppConfig appConfig;

  /**
   * Returns context-data as Map which are common to all the templates.
   * 
   * @return Map which consists common template data.
   */
  public Map<String, Object> getCommonTemplateData() {
    logger.debug("getCommonTemplateData() :: Getting common template data.");

    Map<String, Object> data = new HashMap<String, Object>();

    data.put("baseUri", appConfig.getBaseUri());

    SimpleDateFormat copyWriteDate = new SimpleDateFormat("yyyy");
    data.put("copyrightDate", copyWriteDate.format(new Date()));

    data.put("date", new DateTool());
    data.put("linkTool", new LinkTool());
    data.put("number", new NumberTool());
    data.put("StringUtils", StringUtils.class);
    data.put("escapeTool", new EscapeTool());

    return data;
  }

  /**
   * Returns the context data as a Map by merging common template data. <br>
   * Notes: <br>
   * 1. Returned map will always be the new reference of the Map. <br>
   * 2. If passed data contains a key which is also present in the common template data, then the
   * data passed in the argument will be finally visible.
   * 
   * @param data common template data.
   * 
   * @return merged template data
   */
  public Map<String, Object> mergeCommonTemplateData(Map<String, Object> data) {
    logger.debug("mergeCommonTemplateData() :: merging common template data.");

    Map<String, Object> mergedData = new HashMap<String, Object>();
    mergedData.putAll(data);
    mergedData.putAll(getCommonTemplateData());
    return mergedData;
  }
}
