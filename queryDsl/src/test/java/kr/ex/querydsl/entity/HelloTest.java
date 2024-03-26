package kr.ex.querydsl.entity;

import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest //통합테스트 : 스프링부트 시작 처럼 똑같이 web root-context : db 객체
@Transactional  // 트랜잭셔널 열어야지만 db에 값 전달 가능  //entity manager 가 실행되는 곳 transactional.commit 후에 rollback()
class HelloTest {
    @Autowired
    EntityManager em;

    @Test
    @Commit
    void aaaaa(){
        Hello hello = new Hello();
        Hello hello2 = new Hello();
        Hello hello3 = new Hello();
        Hello hello4 = new Hello();
        em.persist(hello);
        em.persist(hello2);
        em.persist(hello3);
        em.persist(hello4);

        List<Hello> list = em.createQuery("SELECT h from Hello h").getResultList();

        list.forEach(h -> {
            System.out.println("h = " + h);
        });
    }

    @Test
    @Commit
    void bb(){
        Hello hello1 = new Hello();
        Hello hello2 = new Hello();
        Hello hello3 = new Hello();
        Hello hello4 = new Hello();
        em.persist(hello1);
        em.persist(hello2);
        em.persist(hello3);
        em.persist(hello4);

        System.out.println("================================");
        JPAQueryFactory queryFactory = new JPAQueryFactory(em);
        QHello h = new QHello("hello");

        List<Hello> list = queryFactory.select(h.hello).from(h.hello).fetch();
        //QHello 클래스 안에 미리 static final Hello 객체를 만들어놨다
        Hello findHello = queryFactory.selectFrom(QHello.hello).fetchFirst();

        System.out.println("findHello = "+ findHello);

        //Assertions.assertEquals(findHello,hello2);
        assertThat(findHello).isEqualTo(hello2);

        list.forEach(h2 -> System.out.println("h2 = "+ h2));

    }

}