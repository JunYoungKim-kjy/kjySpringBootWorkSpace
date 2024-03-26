package kr.basic.jpa1.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import kr.basic.jpa1.domain.Member;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Slf4j
public class MemberJpaRepository implements MemberRepository{

    private final EntityManager em;

    public MemberJpaRepository(EntityManager em){          //==> @RequiredArgsConstructor 와 동일
        this.em = em;
    }
    @Override
    public Member save(Member member) {
        em.persist(member);
        return member;
    }

    @Override
    public List<Member> findAll() {
        return em.createQuery("SELECT m FROM Member m order by m.loginId desc ",Member.class).getResultList();
    }

    @Override
    public Member findById(Long id) {
        return em.createQuery("SELECT m FROM Member m WHERE m.id = :id",Member.class).setParameter("id",id).getSingleResult();
    }

    @Override
    public Member findByLoginId(String loginId) {
        List<Member> m = em.createQuery("select m from Member m where m.loginId = :loginId",Member.class)
                .setParameter("loginId",loginId)
                .getResultList();
        log.trace("member = {}",m);
//        Member mm= null;
//        mm = em.createQuery("SELECT m FROM Member m WHERE m.loginId = :loginId",Member.class)
//                .setParameter("loginId",loginId)
//                .getSingleResult();

        //return m == null ? null : m.get(0);
        return m.stream().findAny().orElse(null);
        //return mm;
    }

    @Override
    public void deleteById(Long id) {
        //em.remove(id);
        // 삭제한 row 갯수 = int
        int delCnt = em.createQuery("DELETE FROM Member m WHERE m.id = :id")
                .setParameter("id",id)
                .executeUpdate();
        if(delCnt == 0){
            log.error("msg = {}","삭제 실패");
        }
    }
}
