package kr.basic.jpa1.form;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter @Builder//생성할때만 초기값 넣을수 있음
@ToString
public class StudyForm {
    private Long memberId;
    private LocalDate studyDay; //미래 시간 선택 x , 과거 시간 선택 o
    private LocalTime startTime; //오후 6:10
    private int studyMins;  // 40분
    private String contents;
}
