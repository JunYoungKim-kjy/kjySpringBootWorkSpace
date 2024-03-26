package kr.basic.jpa1.form;

import lombok.Data;
import lombok.Getter;

@Data
// 화면(presentation 화면)
public class MemberForm {
    private String id;
    private String pw;
    private String name;
}
