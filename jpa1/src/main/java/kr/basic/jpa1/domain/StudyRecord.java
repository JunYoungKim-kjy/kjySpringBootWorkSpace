package kr.basic.jpa1.domain;

import jakarta.persistence.*;
import kr.basic.jpa1.form.StudyForm;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@Entity
@Data
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED) //jpa만 내 객체를 생성할 수 있음
public class StudyRecord {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long studyId;
    private LocalDate studyDay;
    private LocalTime startTime;
    private int studyMins;
    @Lob
    private String contents;
    //fk 값을 가진 테이블(연관관계 주인) = many to one
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="memberId")
    private Member member; //fk => member_id (외래키를 가지고있으면 매니투원)

    //도메인 영향을 미치는 비지니스 로직, 생성자 로직
    //생성자 메서드
    public  static StudyRecord createRecode(StudyForm form, Member member){
        StudyRecord recode = new StudyRecord();
        recode.member = member;
        recode.studyDay = form.getStudyDay();
        recode.startTime= form.getStartTime();
        recode.studyMins= form.getStudyMins();
        recode.contents = form.getContents();

        //LocalDateTime
        return recode;
    }
    public  static StudyRecord modifyRecord(StudyForm form,StudyRecord record){

        record.studyDay = form.getStudyDay();
        record.startTime= form.getStartTime();
        record.studyMins= form.getStudyMins();
        record.contents = form.getContents();
        return record;
    }

    public String getEndStudyDay(){
        String pattern = "yyyy/MM/dd/ HH:mm";
        LocalDateTime endStudyTime = LocalDateTime.of(this.studyDay, this.startTime); //날짜.시간
        endStudyTime = endStudyTime.plusMinutes(this.studyMins);                      // + 공부시간
        String data = endStudyTime.format(DateTimeFormatter.ofPattern(pattern));      // 패턴설정

        return data;
    }

    @Builder
    public StudyRecord(LocalDate studyDay, LocalTime startTime, int studyMins, String contents, Member member) {
        this.studyDay = studyDay;
        this.startTime = startTime;
        this.studyMins = studyMins;
        this.contents = contents;
        this.member = member;
    }
}
