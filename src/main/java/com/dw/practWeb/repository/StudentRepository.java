package com.dw.practWeb.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.dw.practWeb.model.Student;

public interface StudentRepository extends JpaRepository<Student, Long>, JpaSpecificationExecutor<Student>
{
    List<Student> findByCity(String city);

    List<Student> findByCityAndNameLike(String city, String name);
}
