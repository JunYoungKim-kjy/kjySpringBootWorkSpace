package kr.basic.jpa1.repository;

import kr.basic.jpa1.domain.Member;
import kr.basic.jpa1.domain.StudyRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface StudyRepository extends JpaRepository<StudyRecord,Long> {
    @Query(value = "SELECT m.member_id,r.study_id, r.start_time, r.study_day, r.study_mins, r.contents  FROM member m,study_record r WHERE m.member_id = r.member_id" , nativeQuery = true)
    List<StudyRecord> selectAll();

    @Query(value="SELECT * FROM study_record WHERE member_id = :memberId" , nativeQuery = true)
    List<StudyRecord> searchStudyRecordByMemberId(@Param(value="memberId") Long memberId);

    //delete study_record WHERE member_id = ?
    void deleteByMember(Member mebmer);
}
