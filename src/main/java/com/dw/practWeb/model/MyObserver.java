package com.dw.practWeb.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import rx.Observer;

public class MyObserver implements Observer<Student> {
  private static Logger logger = LoggerFactory.getLogger(MyObserver.class);

  @Override
  public void onCompleted() {
    logger.debug("onCompleted() :: all student created successfully");
  }

  @Override
  public void onError(Throwable e) {
    logger.debug("onError() :: {} ", e);
  }

  @Override
  public void onNext(Student student) {
    // BeanUtils.only().getStudentService().add(student);

    logger.debug("onNext() :: student {} created successfully", student.getId());
  }

}
