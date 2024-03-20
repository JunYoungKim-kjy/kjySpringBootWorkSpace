package entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
public class Locker {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long lockerId;
    private int lockNo;
    // 관계형 데이터베이스 MYSQL 에서는 생성이 안된다
    @OneToOne(mappedBy = "locker")
    private Student student;

    public Locker(int lockNo, Student student) {
        this.lockNo = lockNo;
        this.student = student;
        student.setLocker(this);
    }
    public Locker(int lockNo) {
        this.lockNo = lockNo;
    }
}
