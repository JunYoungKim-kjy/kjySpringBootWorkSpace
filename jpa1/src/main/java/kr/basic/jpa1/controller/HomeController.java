package kr.basic.jpa1.controller;

import kr.basic.jpa1.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Slf4j
@Controller
@RequiredArgsConstructor
public class HomeController {


    @GetMapping("/")
    public String home(){
//        System.out.println("trace");
//
//        log.trace(" 트레이스 ");
//        log.debug(" 디버그 ");
//        log.info( " 인포 ");
//        log.warn( " 워ㄹ ");
//        log.error(" error ");
        return "home/home";
    }
}
