package com.dw.practWeb;

import com.dw.practWeb.service.StudentService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PractWebApplication {
  private static Logger logger = LoggerFactory.getLogger(StudentService.class);

  public static void main(String[] args) {
    SpringApplication.run(PractWebApplication.class, args);
    logger.debug("main() :: application start");
  }
}
