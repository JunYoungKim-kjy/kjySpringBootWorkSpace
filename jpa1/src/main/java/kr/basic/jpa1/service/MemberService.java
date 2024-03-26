package kr.basic.jpa1.service;

import kr.basic.jpa1.domain.Member;
import kr.basic.jpa1.repository.MemberJpaRepository;
import kr.basic.jpa1.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
@Transactional(readOnly = true) //읽기전용 트렌젝셔널 -> sql 저장소 빠진것
public class MemberService {

    private final MemberJpaRepository memberJpaRepository;
    public MemberService (MemberJpaRepository memberJpaRepository){
        this.memberJpaRepository = memberJpaRepository;
    }

    @Transactional // 읽기 , 쓰기(삭제,수정)
    public Long join(Member member) throws IllegalArgumentException{
        validateMemberId(member);
        Member m = memberJpaRepository.save(member);
        log.trace("savedMember = {}",m);
        return m.getId();
    }

    public Member getOneMemberByLoginId(String loginId){
        return memberJpaRepository.findByLoginId(loginId);
    }
    public Member getOneMemberById(Long id){
        return memberJpaRepository.findById(id);
    }

    public void validateMemberId(Member member) throws IllegalArgumentException{
        if(memberJpaRepository.findByLoginId(member.getLoginId()) != null){
            throw new IllegalArgumentException(" 이미 존재하는 회원 아이디가 있습니다.");
        }
    }

    public List<Member> getList(){
        return memberJpaRepository.findAll();
    }

    @Transactional
    public void deleteMember (Long id){

        memberJpaRepository.deleteById(id);

    }
}
