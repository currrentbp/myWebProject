package com.currentbp.controller;

import com.currentbp.common.entity.Student;
import com.currentbp.util.all.Assert;
import org.apache.commons.io.IOUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.async.WebAsyncTask;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.util.concurrent.Callable;

/**
 * @author baopan
 * @createTime 20180607
 */
@RestController
@RequestMapping(value = "/pg")
public class PostGetParamsTestController {
    private final long timeOut = 5000;

    @RequestMapping(value = "/student", method = RequestMethod.POST)
    public Student student(@RequestBody Student student) {
        System.out.println("=========================" + student.toString());
        if(student != null){
            throw new RuntimeException("student is not null");
        }
        return student;
    }

    @RequestMapping(value = "/student2/{id}", method = RequestMethod.POST)
    public Student student2(@PathVariable Integer id, @RequestBody Student student) {
        System.out.println("=========================" + id + student.toString());
        if(student != null){
            throw new RuntimeException("student is not null");
        }
        return student;
    }
}
