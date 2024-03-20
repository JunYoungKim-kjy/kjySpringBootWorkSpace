package entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@Table(name="major_tbl")
@ToString(exclude = "students")//항상 연관관계가 있는 필드는 toString에서 제외를 시켜줘야한다.
public class Major {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long majorId;
    private String name;
    private String category;
    // 관계형 데이터베이스 MYSQL 에서는 생성이 안된다
    @OneToMany(mappedBy = "major") //연관관계 주인은 student table의 major 프로퍼티라는 의미
    private List<Student> students = new ArrayList<>(); //읽기전용


    public Major(String name, String category) {
        this.name = name;
        this.category = category;
    }
}
