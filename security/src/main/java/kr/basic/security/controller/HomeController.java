package kr.basic.security.controller;

import kr.basic.security.config.auth.PrincipalDetails;
import kr.basic.security.entity.Users;
import kr.basic.security.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Slf4j
@Controller
@RequiredArgsConstructor
public class HomeController {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder encodePw;
    @GetMapping({"","/"})
    public String home(){
        return "index";
    }

    @GetMapping("/loginForm")
    public String login(){
        return "loginForm";
    }

    @GetMapping("/joinForm")
    public String joinForm(){
        return "joinForm";
    }
    @PostMapping("/join")
    public String join(@ModelAttribute Users user){
        log.trace("user = {}",user);
        String initPassword = user.getPassword();
        String enPassword = encodePw.encode(initPassword);

        user.setPassword(enPassword);
        Users adduser = userRepository.save(user);
        log.trace("adduser = {}",adduser);
        return "redirect:/loginForm";
    }

    @GetMapping("/admin")
    @ResponseBody
    public String admin(){
        return "admin";
    } @GetMapping("/manager")
    @ResponseBody
    public String manager(){
        return "admin";
    } @GetMapping("/user")
    @ResponseBody
    public String user(){
        return "admin";
    }
    @GetMapping("/test")
    @ResponseBody
    public PrincipalDetails test(@AuthenticationPrincipal PrincipalDetails principalDetails){
        if(principalDetails == null){
            return null;
        }
        log.trace("principal = {}",principalDetails.toString());

        return principalDetails;
    }

    @GetMapping("/auth/login")
    public @ResponseBody String login(String error,String exception){
        log.error("error = {} , exception = {}",error,exception);
        return exception.toString();
    }

    @GetMapping("/test/oauth")
    public @ResponseBody OAuth2User testLogin(Authentication authentication, @AuthenticationPrincipal OAuth2User oAuth2User){
        OAuth2User oAuth2User2 = (OAuth2User)authentication.getPrincipal();
        log.error("oAuth2User = {}",oAuth2User2);
        return oAuth2User2;
    }

}
