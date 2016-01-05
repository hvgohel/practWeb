package com.dw.practWeb.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.dw.practWeb.model.Student;

@Service
public interface StudentService
{
    Student add(Student student);

    List<Student> add(List<Student> students);

    List<Student> get(List<Long> ids);

    List<Student> get(String city);

    List<Student> get(String city, String name);

    List<Student> get();
}
