package com.dw.practWeb.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Callable;

import javax.inject.Inject;

import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerFactory;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.dw.practWeb.model.MyObserver;
import com.dw.practWeb.model.MyRxJavaSchedulersHook;
import com.dw.practWeb.model.Student;
import com.dw.practWeb.repository.StudentRepository;
import com.dw.practWeb.scheduler.job.Sample;
import com.dw.practWeb.service.StudentService;
import com.dw.practWeb.utils.BeanMapper;

import rx.Observable;
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
        
        schedulerExample(student.getId());
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
    public List<Student> getByIds(List<Long> ids)
    {
        List<Student> students = studentRepository.findAll(ids);
        return beanMapper.mapCollection(students, Student.class, "student-1");
    }

    @Override
    public List<Student> getByCity(String city)
    {
        return studentRepository.findByCity(city);
        // return studentRepository.findAll(StudentSpecification.getStudentByName(city));
    }

    @Override
    public List<Student> getByCityAndName(String city, String name)
    {

        return studentRepository.findByCityAndNameLike(city, "%" + name + "%");
        // return studentRepository.findAll(Specifications.where(StudentSpecification.getByCity(city))
        // .and(StudentSpecification.getByName(name)));
    }

    @Override
    public List<Student> getAll()
    {
        List<Student> students = studentRepository.findAll();

        //Map<String, List<Student>> map = students.stream().collect(Collectors.groupingBy(s -> s.getCity()));

        //System.out.println(map);
        
        return beanMapper.mapCollection(students, Student.class);
    }
    
    @Override
    public Student getById(Long id)
    {
        Student student = studentRepository.getOne(id);
        return beanMapper.map(student, Student.class);
    }
    
    @Override
    public Student update(Long id, Student student)
    {
        Student attachStudent = studentRepository.getOne(id);
        beanMapper.map(student, attachStudent, "student-2");
        studentRepository.save(attachStudent);
        return attachStudent;
    }
    
    @Override
    public void delete(Long id)
    {
        studentRepository.delete(id);
    }

    private void schedulerExample(Long id)
    {
        logger.debug("schedulerExample() :: execute");

        Date timeToExecute = new Date(System.currentTimeMillis() + 60 * 1000);

        JobDetail jobDetail = JobBuilder.newJob(Sample.class).usingJobData("id", id)
                .withIdentity(Sample.class.getName()).build();

        // Trigger trigger = TriggerBuilder.newTrigger().withIdentity(Sample.class.getName())
        // .withSchedule(CronScheduleBuilder.cronSchedule("0 0/1 * 1/1 * ? *")).build();

        Trigger trigger = TriggerBuilder.newTrigger().withIdentity(Sample.class.getName()).startAt(timeToExecute).build();
        
        try
        {
            SchedulerFactory schedulerFactory = new StdSchedulerFactory();
            Scheduler scheduler = schedulerFactory.getScheduler();
            scheduler.start();
            scheduler.scheduleJob(jobDetail, trigger);
            logger.debug("schedulerExample() ::  schedule");
        }
        catch (Exception exception)
        {
            logger.debug("schedulerExample() ::  error - ", exception);
        }
    }
    
}
