import entity.Customer;
import entity.Locker;
import entity.Major;
import entity.Student;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;

public class JpaMain {

    public static void init(EntityManager em){

        Student stu1 = new Student("김","1학년");
        Student stu2 = new Student("이","2학년");
        Student stu3 = new Student("박","3학년");

        Major m1 = new Major("컴싸","소프트엔지니어링");
        em.persist(m1);

//        stu1.setMajorId(m1.getMajorId());
//        stu2.setMajorId(m1.getMajorId());
//        stu3.setMajorId(m1.getMajorId());

        stu1.setMajor(m1);
        stu2.setMajor(m1);
        stu3.setMajor(m1);

        em.persist(stu1);
        em.persist(stu2);
        em.persist(stu3);

    }
    public static void main(String[] args){

        // sessionFactory 객체
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("customer-ex");
        // session 객체
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        tx.begin(); //start transaction;
        try{
//            init(em);
            Student stu = em.find(Student.class,1L);
            Locker locker = em.find(Locker.class, 1L);
//            Locker locker = new Locker(100);
//            Locker locker2 = new Locker(200);
//            em.persist(locker);
//            em.persist(locker2);
            stu.setLocker(locker);

            tx.commit();
        }catch(Exception e){
            tx.rollback();
        }finally{
            em.close();
            emf.close();
        }
    }
}
