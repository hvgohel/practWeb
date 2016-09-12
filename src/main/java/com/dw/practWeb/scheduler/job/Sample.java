package com.dw.practWeb.scheduler.job;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Sample implements Job {

  private static final Logger logger = LoggerFactory.getLogger(Sample.class);
  private Long id;

  public void setId(Long id) {
    this.id = id;
  }

  @Override
  public void execute(JobExecutionContext context) throws JobExecutionException {
    logger.debug("execute() :: student created " + id);
  }
}
