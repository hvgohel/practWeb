package com.dw.practWeb.utils;

import com.dw.practWeb.repository.StudentRepository;
import com.dw.practWeb.service.StudentService;

import javax.inject.Inject;
import javax.inject.Named;

@Named
public class BeanUtils {
  private static BeanUtils bOnly;

  @Inject
  private StudentRepository studentRepository;

  @Inject
  private StudentService studentService;

  public BeanUtils() {
    bOnly = this;
  }

  public static BeanUtils only() {
    return bOnly;
  }

  public StudentRepository getStudentRepository() {
    return studentRepository;
  }

  public StudentService getStudentService() {
    return studentService;
  }
}
