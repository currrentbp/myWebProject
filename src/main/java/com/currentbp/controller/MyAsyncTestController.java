package com.currentbp.controller;

import com.currentbp.util.all.Assert;
import org.apache.commons.io.IOUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.async.WebAsyncTask;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.util.concurrent.Callable;

/**
 * @author baopan
 * @createTime 20180530
 */
@RestController
@RequestMapping(value = "/bp")
public class MyAsyncTestController {
    private final long timeOut = 5000;

    @RequestMapping(value = "/c", method = RequestMethod.GET)
    @ResponseBody
    public String g1() {
        System.out.println("=========================");
        return "ssss";
    }

    @RequestMapping(value = "/c1", method = RequestMethod.POST)
    public WebAsyncTask<String> wtpCallBack(final HttpServletRequest request) {
        WebAsyncTask<String> webAsyncTask = new WebAsyncTask<>(timeOut, new Callable<String>() {
            @Override
            public String call() {
                String requestBody = null;
                try (BufferedReader reader = request.getReader()){
                    requestBody = IOUtils.toString(reader);
                    Assert.hasText(requestBody,"获取的内容为空");
                    System.out.println("===>requsetBody:" + requestBody);
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                    requestBody = "error";
                }
                return requestBody;
            }
        });
        webAsyncTask.onTimeout(new Callable<String>() {
            @Override
            public String call() {
                return "time out error";
            }
        });
        return webAsyncTask;
    }
}
