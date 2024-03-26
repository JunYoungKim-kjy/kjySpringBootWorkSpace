package kr.basic.jpa1.domain;

import jakarta.persistence.*;
import lombok.*;

@ToString
@Data
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)  //jpa 만 내 객체를 생성할 수 있다
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="member_id")
    private Long id; //bigint
    private String loginId;
    private String password;
    private String name;
    @Enumerated(EnumType.STRING)
    private Role role;

    public void setId(Long id){
        this.id = id;
    }

    @Builder
    public Member(String loginId, String password, String name) {
        this.loginId = loginId;
        this.password = password;
        this.name = name;
        this.role = Role.STUDENT;
    }
}
