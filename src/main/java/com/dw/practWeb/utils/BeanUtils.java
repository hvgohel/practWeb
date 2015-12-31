package com.dw.practWeb.utils;

import javax.inject.Inject;
import javax.inject.Named;

import com.dw.practWeb.repository.StudentRepository;

@Named
public class BeanUtils
{
    private static BeanUtils  bOnly;

    @Inject
    private StudentRepository studentRepository;

    public BeanUtils()
    {
        bOnly = this;
    }

    public static BeanUtils only()
    {
        return bOnly;
    }

    public StudentRepository getStudentRepository()
    {
        return studentRepository;
    }
}
