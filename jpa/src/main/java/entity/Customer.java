package entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Entity
@NoArgsConstructor // 기본생성자 반드시 필수.
@Table(name="Customer_tb")
public class Customer {
    @Id
    @Column(name="customer_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String name;
    LocalDate regDate;
    public Customer( String name) {
        this.name = name;
        this.regDate = LocalDate.now();
        System.out.println(LocalDate.now());
        System.out.println(this.regDate);
    }
}
