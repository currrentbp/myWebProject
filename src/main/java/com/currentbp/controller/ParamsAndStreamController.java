package com.currentbp.controller;

import com.currentbp.common.entity.Student;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author baopan
 * @createTime 20180803
 */
@RestController
@RequestMapping(value = "/params")
public class ParamsAndStreamController {

    @RequestMapping(value = "/p", method = RequestMethod.POST)
    public Student student(@RequestBody Student student, HttpServletRequest request) throws IOException {
        System.out.println("=========================" + student.toString());
        ServletInputStream inputStream = request.getInputStream();
        BufferedReader in = new BufferedReader(new InputStreamReader(inputStream));
        String line;
        String temp = "";
        while ((line = in.readLine()) != null) {
            temp += line;
        }
        System.out.println("====>" + temp);
        if (student != null) {
            throw new RuntimeException("student is not null");
        }
        return student;
    }
}
