package com.dw.practWeb.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.jpa.domain.Specifications;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.dw.practWeb.model.Student;
import com.dw.practWeb.repository.StudentRepository;
import com.dw.practWeb.service.StudentService;
import com.dw.practWeb.specification.StudentSpecification;
import com.dw.practWeb.utils.BeanMapper;

@Service
@Transactional(propagation = Propagation.MANDATORY)
public class StudentServiceImpl implements StudentService
{

    @Inject
    private StudentRepository studentRepository;

    @Inject
    private BeanMapper        beanMapper;

    private Logger            logger = LoggerFactory.getLogger(StudentService.class);

    @Override
    public Student add(Student student)
    {
        logger.debug("add() :: start");
        student = studentRepository.save(student);
        return beanMapper.map(student, Student.class, "student-1");
    }

    @Override
    public List<Student> add(List<Student> students)
    {
        List<Student> studs = new ArrayList<Student>();
        logger.debug("add() :: more than one student creation start");
        for (Student s : students)
        {
            studs.add(add(s));
        }
        logger.debug("add() :: more than one student creation end");

        return beanMapper.mapCollection(studs, Student.class);
    }

    @Override
    public List<Student> get(List<Long> ids)
    {
        List<Student> students = studentRepository.findAll(ids);
        return beanMapper.mapCollection(students, Student.class, "student-1");
    }

    @Override
    public List<Student> get(String city)
    {
        return studentRepository.findByCity(city);
        // return studentRepository.findAll(StudentSpecification.getStudentByName(city));
    }

    @Override
    public List<Student> get(String city, String name)
    {

        return studentRepository.findByCityAndNameLike(city, "%" + name + "%");
        // return studentRepository.findAll(Specifications.where(StudentSpecification.getByCity(city))
        // .and(StudentSpecification.getByName(name)));
    }
}
