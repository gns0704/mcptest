package com.example.demo.repository;

import com.example.demo.entity.Board;
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
public class BoardRepositoryTest {
    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private BoardRepository boardRepository;
    //  Board테이블에 글 2개 등록해 보세요.
    @Test
    public void insertMember(){
        Member m=Member.builder()
                .id("hello")
                .pwd("1234")
                .email("hello@")
                .age(19)
                .build();
        memberRepository.save(m);
    }
    @Test
    public void insertBoard(){
        Member m=
                memberRepository.findById("hello")
                        .orElseThrow(()->new RuntimeException("회원이 존재하지 않아요"));
        for(int i=1;i<=15;i++) {
            Board b = Board.builder()
                    .member(m)
                    .title("테스트.." + i)
                    .content("jpa테스트" + i)
                    .build();
            boardRepository.save(b);
        }
    }
    // 0번째 페이지의 글 5개를 꺼내와 출력해 보세요.
    @Test
    public void list1(){
        PageRequest pageable=PageRequest.of(0,5);
        Page<Board> pageList=boardRepository.findAll(pageable);
        System.out.println("현재페이지번호==>" + pageList.getNumber());
        System.out.println("------------------------------------");
        List<Board> list= pageList.getContent();
        list.forEach(b->{
            System.out.println("글번호:" + b.getNum());
            System.out.println("작성자:" + b.getMember().getId());
            System.out.println("제목:" + b.getTitle());
            System.out.println("내용:" + b.getContent());
            System.out.println("작성일:" + b.getRegdate());
            System.out.println("------------------------------");
        });
    }
    @Test
    public void list2(){
        PageRequest pageable=
                PageRequest.of(0,10, Sort.by("num").descending());
        Page<Board> pageList= boardRepository.findAll(pageable);
      //  pageList.getContent().forEach(b->System.out.println(b));
        pageList.getContent().forEach(System.out::println);
    }
}











