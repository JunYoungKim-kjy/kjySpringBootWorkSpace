package entity;

import lombok.*;
import org.hibernate.mapping.ToOne;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
@Table(name = "student_tbl")
@ToString(exclude = "major")//항상 연관관계가 있는 필드는 toString에서 제외를 시켜줘야한다.
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long studentId;
    private String name;
    private String grade;
    // 관계 구성
    // FetchType.EAGER(기본값) : 즉시로딩 : 연관되어있는 모든
    // FetchType.LAZY  :지연로딩
    // 연관관계주인은 맨투원이 주인
    @ManyToOne(fetch = FetchType.LAZY)//필요할때만 부름
    @JoinColumn (name="majorId") //테이블 컬럼의 fk 명
    private Major major;

    @OneToOne(fetch = FetchType.LAZY)//필요할때만 부름
    @JoinColumn(unique=true,name="lockerId")//이름을 정해주지 않으면 클래스명_id 로 들어감
    private Locker locker;// 누가 id를 참조하고있으면

    public Student(String name, String grade) {
        this.name = name;
        this.grade = grade;
    }
}
