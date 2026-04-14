package com.example.demo.repository;

import com.example.demo.entity.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SpringBootTest
@Transactional
@Commit
public class MemberRepositoryTest {
    @Autowired
    MemberRepository mr;

    @Test
    public void insert(){
        for(int i=1;i<=15;i++) {
            Member m=Member.builder()
                    .id(i+"")
                    .pwd("1234")
                    .email(i +"@test.com")
                    .age(i+10)
                    .build();
            mr.save(m);
        }
    }
    @Test
    public void list1(){
        //첫번째 페이지에서 10개 얻어오기(페이지번호는 0번부터 시작됨)
        PageRequest pageable= PageRequest.of(0,10);
       // PageRequest pageable= PageRequest.of(1,10);//두번째 페이지에서 10개 얻어오기
        Page<Member> pageList= mr.findAll(pageable);
        System.out.println("-----------------------------");
        System.out.println("전체페이지 수==>" + pageList.getTotalPages());
        List<Member> list=pageList.getContent();
        for(Member m:list){
            System.out.println(m.getId() +"," + m.getEmail());
        }
    }
    @Test
    public void list2(){
//        PageRequest pageable= PageRequest.of(0,5, Sort.by("id"));
        PageRequest pageable=
                PageRequest.of(1,5, Sort.by("age").descending());//내림차순
        Page<Member> pagelist= mr.findAll(pageable);
        List<Member> list=pagelist.getContent();
        System.out.println("페이지번호:" + pagelist.getNumber());
        for(Member m:list){
            System.out.println(m.getId()+"," + m.getEmail() +"," + m.getAge());
        }
    }
    @Test
    public void list3(){
       //List<Member> list=mr.pageList(0,10); //첫번째 페이지에서 10개 얻어오기
        List<Member> list=mr.pageList(10,10); // 두번째 페이지에서 10개 얻어오기(인덱스10부터 10개)
        for(Member m:list){
            System.out.println(m.getId()+"," + m.getEmail() +"," + m.getAge());
        }
    }
    @Test
    public void updateAge(){
       int n= mr.update("1");
        System.out.println("n==>" + n);
    }
}
