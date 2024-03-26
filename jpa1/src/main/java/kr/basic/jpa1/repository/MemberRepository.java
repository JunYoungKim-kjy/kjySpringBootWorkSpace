package kr.basic.jpa1.repository;

import kr.basic.jpa1.domain.Member;

import java.util.List;

public interface MemberRepository {

    Member save(Member member);
    List<Member> findAll();
    Member findById(Long id);
    Member findByLoginId(String loginId);
    void deleteById(Long id);



}
