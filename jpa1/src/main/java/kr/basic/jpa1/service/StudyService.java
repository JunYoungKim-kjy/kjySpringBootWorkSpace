package kr.basic.jpa1.service;

import kr.basic.jpa1.domain.Member;
import kr.basic.jpa1.domain.StudyRecord;
import kr.basic.jpa1.form.StudyForm;
import kr.basic.jpa1.repository.StudyRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class StudyService {
    private final StudyRepository studyRepository;
    public void addRecord(StudyRecord recode){
        StudyRecord save = studyRepository.save(recode);
    }

    @Transactional
    public void saveRecord(StudyForm form, Member member){
        StudyRecord record = StudyRecord.createRecode(form,member);
        log.trace("add record = {}", record);
        studyRepository.save(record);
    }

    public StudyRecord getOneRecordById(Long id){
        Optional<StudyRecord> record = studyRepository.findById(id);
        //return studyRepository.getById(id);
        return record.isPresent() ? record.get() : null;
    }

    public List<StudyRecord> getAllRecords(){
        return studyRepository.selectAll();
    }

    public void deleteRecord(Long id){ studyRepository.deleteById(id);}

    public void update(StudyRecord record){
        studyRepository.save(record);
    }
    @Transactional
    public void updateRecord(StudyForm form, StudyRecord record){
        StudyRecord updateRecord = StudyRecord.modifyRecord(form,record);
        System.out.println("service form ="+form);

        System.out.println("updateRecord ="+updateRecord);
        studyRepository.save(updateRecord);

    }

    @Transactional
    public void deleteAllRecord(Member member) {
//        Long memberId = member.getId();
//        List<StudyRecord> list = studyRepository.searchStudyRecordByMemberId(memberId);
//        if(list != null){
//            list.forEach(record -> {
//                log.trace("delete record = {}",record);
//                studyRepository.deleteById(record.getStudyId());
//            });
//        }
        studyRepository.deleteByMember(member);
    }

}

