package com.dw.practWeb.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.MimeType;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.dw.practWeb.config.WebConfig;
import com.dw.practWeb.model.Sample;
import com.dw.practWeb.model.Student;
import com.dw.practWeb.paging.PagedResult;
import com.dw.practWeb.security.SecurityServiceHelper;
import com.dw.practWeb.service.SecurityRegisteredUserManager;
import com.dw.practWeb.service.StudentService;
import com.dw.practWeb.service.impl.SampleServieImpl;
import com.dw.practWeb.utils.AppConfig;

@RestController
@Transactional(propagation = Propagation.REQUIRES_NEW)
public class StudentAPIController
{
    private Logger                        logger = LoggerFactory.getLogger(StudentAPIController.class);

    @Inject
    private StudentService                studentService;

    @Inject
    private SecurityRegisteredUserManager securityRegisteredUserManager;

    @Inject
    private SecurityServiceHelper         securityServiceHelper;

    @Inject
    private SampleServieImpl              sampleServieImpl;

    @Inject
    private AppConfig                     appConfig;

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

    @RequestMapping(value = WebConfig.UPDATE_STUDENT, method = RequestMethod.PATCH,
                    produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Student update(@PathVariable(value = "id") Long id, @RequestBody Student student)
    {
        return studentService.update(id, student);
    }

    @RequestMapping(value = WebConfig.DELETE_STUDENT, method = RequestMethod.DELETE,
                    produces = MediaType.APPLICATION_JSON_VALUE)
    public void update(@PathVariable(value = "id") Long id)
    {
        studentService.delete(id);
    }

    @RequestMapping(value = WebConfig.GET_STUDENT_BY_IDS, method = RequestMethod.GET,
                    produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<Student> get(@RequestParam(value = "id") List<Long> ids)
    {
        return studentService.getByIds(ids);
    }

    @RequestMapping(value = WebConfig.GET_STUDENT, method = RequestMethod.GET,
                    produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public PagedResult<Student> get()
    {
        return studentService.getAll();
    }

    @RequestMapping(value = WebConfig.GET_STUDENT_BY_ID, method = RequestMethod.GET,
                    produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Student getById(@PathVariable(value = "id") String id)
    {
        return studentService.getById(Long.valueOf(id.toString()));
    }

    @RequestMapping(value = WebConfig.GET_STUDENTS_BY_CITY, method = RequestMethod.GET,
                    produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<Student> getByCity(@PathVariable(value = "city") String city)
    {
        return studentService.getByCity(city);
    }

    @RequestMapping(value = WebConfig.GET_STUDENTS_BY_CITY_AND_NAME, method = RequestMethod.GET,
                    produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<Student> getByCityAndName(@RequestParam(value = "city") String city,
                                          @RequestParam(value = "name") String name)
    {
        return studentService.getByCityAndName(city, name);
    }

    @RequestMapping(value = "/test/getuser", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Long getUser()
    {
        Long regId = securityRegisteredUserManager.getCurrentRegisteredUserId();
        return regId;
    }

    @RequestMapping(value = "/login/withUserName", method = RequestMethod.GET,
                    produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void loginWithUser(@RequestParam(value = "username") String userName)
    {
        securityServiceHelper.loginAsUser(userName);
        System.out.println("user has been successfully login");
    }

    @RequestMapping(value = "/login/withSystem", method = RequestMethod.GET,
                    produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void loginWithSystem()
    {
        securityServiceHelper.loginAsSystem();
        System.out.println("system has been successfully login");
    }

    @RequestMapping(value = "/sample", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Sample createSample(@RequestBody Sample sample)
    {
        return sampleServieImpl.add(sample);
    }

    @RequestMapping(value = "/test/file", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void fileExample(@RequestParam(value = "file") MultipartFile[] multipartFiles) throws IllegalStateException,
                                                                                          IOException
    {
        logger.debug("{}", appConfig.getTest());

        List<File> files = new ArrayList<File>();
        for (MultipartFile m : multipartFiles)
        {
            files.add(getFile(m));
        }
    }

    private File getFile(MultipartFile multipartFile) throws IllegalStateException, IOException
    {
        String type[] = StringUtils.split(multipartFile.getOriginalFilename(), ".");
        String fileName = UUID.randomUUID().toString() + "." + type[1];
        File file = new File(System.getProperty("java.io.tmpdir") + "/" + fileName);
        multipartFile.transferTo(file);
        return file;
    }

}
