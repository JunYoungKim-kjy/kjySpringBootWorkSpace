package kr.boot.basic.repository;

import kr.boot.basic.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
//인터페이스가 인터페이스를 상속받을 땐 extends 사용
public interface SpringJpaMemberRepository extends JpaRepository<Member, Long>, MemberRepository {
    // JPQL select m from Member m where m.name = ?
    @Override
    Optional<Member> findByName(String name);
}
