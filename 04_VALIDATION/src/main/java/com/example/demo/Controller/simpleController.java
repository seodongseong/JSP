package com.example.demo.Controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/simple")
@Slf4j

public class simpleController {

    @RequestMapping(value = "/test1", method = RequestMethod.GET)
    public void test1(){
        log.info("GET /simple/test1...");
    }

    @RequestMapping(value = "/test2", method = RequestMethod.GET)
    public void test2(){
        log.info("GET /simple/test2...");
    }
}
