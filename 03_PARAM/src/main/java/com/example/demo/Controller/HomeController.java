package com.example.demo.Controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Slf4j
// 사용자의 요청을 받는 HomeController라는 이름의 bean이 하나 만들어 진다
public class HomeController {
    // 이런 경로는 뭔가 http://localhost:8095/
    @GetMapping("/")
    public String home(){
//        System.out.println("GET /");
        log.info("GET /");
        return "index";
        // index 페이지를 반환할겁니다.
        // 결과 : WEB-INF/views/index.jsp
    }

}
