package com.dw.practWeb.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.dw.practWeb.model.MyObserver;
import com.dw.practWeb.model.MyRxJavaSchedulersHook;
import com.dw.practWeb.model.Student;
import com.dw.practWeb.repository.StudentRepository;
import com.dw.practWeb.service.StudentService;
import com.dw.practWeb.utils.BeanMapper;

import rx.Observable;
import rx.Observable.OnSubscribe;
import rx.plugins.RxJavaPlugins;
import rx.schedulers.Schedulers;

@Service
@Transactional(propagation = Propagation.MANDATORY)
public class StudentServiceImpl implements StudentService
{

    @Inject
    private StudentRepository studentRepository;

    @Inject
    private BeanMapper beanMapper;

    private Logger logger = LoggerFactory.getLogger(StudentService.class);

    @Override
    public Student add(Student student)
    {
        logger.debug("add() :: start");
        try
        {
            Thread.sleep(5000l);
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
        student = studentRepository.save(student);
        return beanMapper.map(student, Student.class, "student-1");
    }

    @Override
    public List<Student> add(List<Student> students)
    {

        MyRxJavaSchedulersHook hook = new MyRxJavaSchedulersHook();
        RxJavaPlugins.getInstance().registerSchedulersHook(hook);

        List<Observable<Student>> observables = new ArrayList<Observable<Student>>();

        final List<Student> studs = new ArrayList<Student>();
        logger.debug("add() :: more than one student creation start");
        for (final Student s : students)
        {
            Observable<Student> o = Observable.fromCallable(new Callable<Student>()
            {
                @Override
                public Student call() throws Exception
                {
                    Student stud = add(s);
                    studs.add(stud);
                    return stud;

                }
            }).subscribeOn(Schedulers.computation());

            observables.add(o);
        }

        Observable<Student> o4 = Observable.merge(observables);
        o4.subscribe(new MyObserver());

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
