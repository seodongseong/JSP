package com.example.demo;


import org.junit.jupiter.api.Test;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
@RequestMapping(/"memo")


publci class DemoApplicationTests {
    @GetMapping("/add")
    public void add_memo_get(){
        log.info("GET /memo/add...")
    }
}
