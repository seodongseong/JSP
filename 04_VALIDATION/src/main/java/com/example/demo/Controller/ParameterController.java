package com.example.demo.Controller;


import com.example.demo.Dto.PersonDto;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Map;

@Controller
@Slf4j
@RequestMapping("/param")

public class ParameterController {
    @RequestMapping(value="/p01", method= RequestMethod.GET)
    public void paramHandler_1(@RequestParam(name="username",required = false) String name){
        log.info("GET /param/p01..."+name);

    }
    @GetMapping("/p02")
    public void paramHandler_2(@RequestParam(name="username",required = true) String name){
        log.info("GET /param/p02..."+name);

    }

    @PostMapping("/p03")
    public void paramHandler_3(@RequestParam(name="username",required = true) String name){
        log.info("POST /param/p03..."+name);

    }

    //@RequestParam : 동기요청 파라미터 처리(나머지)  /   html form 기반 전달되는 파라미터 처리(JS의 form-data도 받기 가능, JOSN Type 받기 불가)
    //@RequestBody  : 비동기요청 파라미터 처리(파일, json) /   json,filedata등 전달되는 파라미터 처리
    @PostMapping("/p04")
    public void paramHandler_4(@RequestBody String name){
        log.info("POST /param/p04..."+name);

    }

    //디폴트
    @PostMapping("/p05")
    public void paramHandler_5(@RequestParam(name="username",required = true, defaultValue = "웅앵웅") String name){
        log.info("POST /param/p05..."+name);

    }

    //파라미터 다수 생성
    @GetMapping("/p06")
    public void paramHandler_6(
            @RequestParam String name,
            @RequestParam int age,
            @RequestParam String addr

    ){
        log.info("GET /param/p06..."+name+" "+age+" "+addr);
    // String으로 전달되지만 형변환 되서 들어옴
    }

    @GetMapping("/p07")
    public void paramHandler_7(PersonDto dto){
        log.info("GET /param/p07..."+dto);
    }

    @GetMapping("/p08/{name}/{age}/{addr}")
    public void paramHandler_8(
            @PathVariable(value="name") String username,
            @PathVariable int age,
            @PathVariable String addr
            // @PathVariable : 경로기반의 파라미터 전달 방식

    ){
        log.info("GET /param/p08..."+username+" "+age+" "+addr);
        // String으로 전달되지만 형변환 되서 들어옴
    }

    //PersonDto로 받기
    @GetMapping("/p09/{name}/{age}/{addr}")
    public void paramHandler_9(PersonDto dto){
        log.info("GET /param/p09..."+dto);
    }
    //http://loclhost:8095/param/page1
    @GetMapping("/page1")
    public void page1(PersonDto dto, Model model){           //파라미터를 외부로 부터 받음
        log.info("GET /param/page1..."+dto);
        //01 파라미터 받기
        //02 유효성 검증
        //03 서비스 실행

        model.addAttribute("dto",dto);          //뷰로 키를 통해 value를 받을 수 있다
        model.addAttribute("isLogin",true);          //뷰로 키를 통해 value를 받을 수 있다
        //04 뷰(자동)



    }

    @GetMapping("/page2/{name}/{age}/{addr}")
    public ModelAndView page2_handler(PersonDto dto){
        log.info("GET /param/page2..."+dto);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("dto",dto);
        modelAndView.setViewName("param/page2");
        return modelAndView;

    }

    @GetMapping("/page3")
    public void page3_handler(HttpServletRequest request){

        String name = request.getParameter("name");
        int age = Integer.parseInt(request.getParameter("age"));        //Integer.parseInt :
        String addr = request.getParameter("addr");
        log.info("GET /param/page3..."+name+" "+age+" "+addr);

        PersonDto dto = new PersonDto(name,age,addr);
        request.setAttribute("dto", dto);


//        HttpSession session = request.getSession();
//        session.setAttribute("dto",dto);

//        Cookie cookie = new Cookie("c1","v1");
//        response.addCookie(cookie);

    }

    //--------------------------------------------------------------------------------------------
    //--------------------------------------------------------------------------------------------
    //--------------------------------------------------------------------------------------------

    @GetMapping("/page4")
    public void page4_handler(@RequestParam Map<String, Object> params){        // 중요하다 했었죠
        log.info("GET /param/page4..."+params);
    }

    // ---------------------
    // FORWARD : 요청하면 forwarding 된 페이지로 넘겨줌
    // ---------------------
    @GetMapping("/forward/init")
    public String forward_init_handler(Model model){
        log.info("GET /param/forward/init...");
        model.addAttribute("init","init_value");
        return "forward:/param/forward/step1";
    }

    @GetMapping("/forward/step1")
    public String forward_step1_handler(Model model){
        model.addAttribute("step1","step1_value");
        log.info("GET /param/forward/step1...");

        return "forward:/param/forward/step2";
    }

    @GetMapping("/forward/step2")
    public void forward_step2_handler(){
        log.info("GET /param/forward/step2...");
    }



    // ---------------------
    // REDIRECT
    // ---------------------
    @GetMapping("/redirect/init")
    public String redirect_init_handler(Model model, RedirectAttributes redirectAttributes){
        log.info("GET /param/redirect/init...");
        model.addAttribute("init","init_value");
        redirectAttributes.addAttribute("r_init","r_init_value");               //쿼리스트링
        redirectAttributes.addFlashAttribute("r_init2","r_init2_value");        //session 속성 추가
        return "redirect:/param/redirect/step1";
    }

    @GetMapping("/redirect/step1")
    public void redirect_step1_handler(Model model, @RequestParam  String r_init){
        log.info("GET /param/redirect/step1..."+r_init);
        model.addAttribute("step1","step1_value");
    }

    @GetMapping("/redirect/step2")
    public void redirect_step2_handler(){
        log.info("GET /param/redirect/step2...");
    }
//    @RequestMapping("")
//    public void paramHandler_1(){
//        log.info("");
//    }

}
