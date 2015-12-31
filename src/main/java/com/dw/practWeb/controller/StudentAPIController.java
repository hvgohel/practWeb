package com.dw.practWeb.controller;

import java.util.List;

import javax.inject.Inject;

import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.dw.practWeb.config.WebConfig;
import com.dw.practWeb.model.Student;
import com.dw.practWeb.service.StudentService;

@RestController
@Transactional(propagation = Propagation.REQUIRES_NEW)
public class StudentAPIController
{
    @Inject
    private StudentService studentService;

    @RequestMapping(value = WebConfig.CREATE_STUDENT, method = RequestMethod.POST,
                    produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Student add(@RequestBody Student student)
    {
        return studentService.add(student);
    }

    @RequestMapping(value = WebConfig.CREATE_STUDENTS, method = RequestMethod.POST,
                    produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<Student> add(@RequestBody List<Student> students)
    {
        return studentService.add(students);
    }

    @RequestMapping(value = WebConfig.GET_STUDENTS, method = RequestMethod.GET,
                    produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<Student> get(@RequestParam(value = "id") List<Long> ids)
    {
        return studentService.get(ids);
    }

    @RequestMapping(value = WebConfig.GET_STUDENTS_BY_CITY, method = RequestMethod.GET,
                    produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<Student> get(@PathVariable(value = "city") String city)
    {
        return studentService.get(city);
    }

    @RequestMapping(value = WebConfig.GET_STUDENTS_BY_CITY_AND_NAME, method = RequestMethod.GET,
                    produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<Student> get(@RequestParam(value = "city") String city, @RequestParam(value = "name") String name)
    {
        return studentService.get(city, name);
    }
}
