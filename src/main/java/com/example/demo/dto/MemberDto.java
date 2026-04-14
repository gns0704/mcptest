package com.example.demo.dto;

import com.example.demo.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class MemberDto {
    private String id;
    private String pwd;
    private String email;
    private int age;
    private Date regdate;

    public MemberDto(Member m){
        this.id=m.getId();
        this.pwd=m.getPwd();
        this.email=m.getEmail();
        this.age=m.getAge();
        this.regdate=m.getRegdate();
    }
    public Member toEntity(){
     //   Member m=new Member(id,pwd,email,age,regdate);
       Member m= Member.builder()
                .id(this.id)
                .pwd(this.pwd)
                .email(this.email)
                .age(this.age)
                .regdate(this.regdate)
                .build();

        return m;
    }

}










