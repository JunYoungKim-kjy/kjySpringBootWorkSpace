package kr.basic.jpa1.controller;

import kr.basic.jpa1.domain.Member;
import kr.basic.jpa1.domain.StudyRecord;
import kr.basic.jpa1.form.StudyForm;
import kr.basic.jpa1.service.MemberService;
import kr.basic.jpa1.service.StudyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Controller
@RequestMapping("/study")
@Slf4j
@RequiredArgsConstructor
public class StudyContorller {

    private final MemberService memberService;
    private final StudyService studyService;
    @GetMapping
    public String addForm(Model model){
        List<Member> members = memberService.getList();
        if(members == null){
            return "redirect:/member";
        }
        LocalDate now = LocalDate.now();
        LocalTime nowTime = LocalTime.now();
        model.addAttribute("members",members);
        model.addAttribute("curdate",now);
        model.addAttribute("curtime",nowTime);

        return "study/addForm";

    }
    @PostMapping
    public String addStudyRecord(@ModelAttribute StudyForm form) {
        Member member = memberService.getOneMemberById(form.getMemberId());

        if(member == null){
            log.error(" 로그인 아이디가 존재하지 않음");
        }
        log.trace("addRecord = " + form);
        studyService.saveRecord(form,member);
//        StudyRecode studyRecode = StudyRecode.builder()
//                .studyDay(form.getStudyDay())
//                .startTime(form.getStartTime())
//                .studyMins(form.getStudyMins())
//                .contents(form.getContents())
//                .member(member)
//                .build();
//        log.trace("studyRecode = {}",studyRecode);
//        studyService.addRecord(studyRecode);

        return "redirect:/study";
    }

    @GetMapping("/records")
    public String recordsList(Model model){
        List<Member> members = memberService.getList();
        if(members == null){
            return "redirect:/";
        }
        List<StudyRecord> list = studyService.getAllRecords();
        if(list == null){
            return "redirect:/";
        }
        for(StudyRecord r : list){
            System.out.println("r = " + r);
        }
        model.addAttribute("list",list);
        return "study/list";
        //return "redirect:/";
    }

    @DeleteMapping("/{id}")
    public @ResponseBody String deleteRecord(@PathVariable Long id){
        studyService.deleteRecord(id);
        return "ok";
    }

    @GetMapping("/{keyId}")
    public String updateFrom(@PathVariable Long keyId,Model model){
        StudyRecord record = studyService.getOneRecordById(keyId);
        log.trace("record = {}",record);
        if(record==null){
            return "redirect:/";
        }
        model.addAttribute("record",record);

        LocalDate now = LocalDate.now();
        //LocalTime nowTime = LocalTime.now();
        model.addAttribute("curdate",now);
        //model.addAttribute("curtime",nowTime);

        return "study/updateForm";
        //return "redirect:/";
    }

    @PostMapping("/update")
    public String updateRecord(@ModelAttribute StudyForm form,@RequestParam Long id){
//        StudyRecord record = studyService.getOneRecordById(id);
//        record.setStudyDay(form.getStudyDay());
//        record.setContents(form.getContents());
//        record.setStudyMins(form.getStudyMins());
//        record.setStartTime(form.getStartTime());
//        studyService.update(record);
        StudyRecord record = studyService.getOneRecordById(id);
        System.out.println("cotroller form ="+ form);
        studyService.updateRecord(form,record);
        return "redirect:/study/records";
    }
}
