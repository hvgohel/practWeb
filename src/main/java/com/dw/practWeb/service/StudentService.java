package com.dw.practWeb.service;

import com.dw.practWeb.model.Student;
import com.dw.practWeb.paging.PagedResult;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface StudentService {
  Student add(Student student);

  List<Student> add(List<Student> students);

  List<Student> getByIds(List<Long> ids);

  List<Student> getByCity(String city);

  List<Student> getByCityAndName(String city, String name);

  @PreAuthorize("hasAnyRole('ROLE_USER')")
  PagedResult<Student> getAll();

  Student getById(Long id);

  Student update(Long id, Student student);

  void delete(Long id);
}
