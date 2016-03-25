package com.dw.practWeb.service;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import com.dw.practWeb.model.Student;
import com.dw.practWeb.paging.PagedResult;

@Service
public interface StudentService {
  @PreAuthorize("hasRole('ROLE_USER')")
  Student add(Student student);

  List<Student> add(List<Student> students);

  List<Student> getByIds(List<Long> ids);

  List<Student> getByCity(String city);

  List<Student> getByCityAndName(String city, String name);

  @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
  PagedResult<Student> getAll();

  Student getById(Long id);

  Student update(Long id, Student student);

  void delete(Long id);
}
