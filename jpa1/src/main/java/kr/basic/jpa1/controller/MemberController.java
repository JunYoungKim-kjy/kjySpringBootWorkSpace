package kr.basic.jpa1.controller;

import jakarta.persistence.Id;
import kr.basic.jpa1.domain.Member;
import kr.basic.jpa1.form.MemberForm;
import kr.basic.jpa1.service.MemberService;
import kr.basic.jpa1.service.StudyService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/member")
@Slf4j
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;
    private final StudyService studyService;

    @GetMapping
    public String joinForm(){
        return "member/joinForm";
    }

    @PostMapping
    public String joinMember(@ModelAttribute MemberForm form){
        //log.debug(" memberForm = {}", form);

        Member member = Member.builder()
                .password(form.getPw())
                .loginId(form.getId())
                .name(form.getName())
                .build();

        log.trace("member = {}",member);

        try{
            memberService.join(member);
        }catch (IllegalArgumentException e){
            log.error("error = {}" ,e.getMessage());
        }

        return "redirect:/";
    }

    @GetMapping("/members")
    public String memberAllList(Model model){
        model.addAttribute("list",memberService.getList());
        return "member/memberList";
    }

    @DeleteMapping("/{id}")
    @ResponseBody public String deleteMember(@PathVariable Long id){
        Member member = memberService.getOneMemberById(id);
        studyService.deleteAllRecord(member);
        memberService.deleteMember(id);
        return "success";
    }

    @GetMapping("/{keyId}")
    public String deleteMemberByKeyId(@PathVariable Long keyId){
        log.error("keyId = {}",keyId);
        Member member = memberService.getOneMemberById(keyId);
        studyService.deleteAllRecord(member);
        memberService.deleteMember(keyId);
        return "redirect:/member/members";
    }



}
