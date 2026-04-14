package com.example.demo.service;

import com.example.demo.dto.BoardDto;
import com.example.demo.dto.PageResultDto;
import jdk.swing.interop.SwingInterOpUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SpringBootTest
@Transactional
@Commit
public class BoardServiceTest {
    @Autowired
    private BoardService boardService;
    @Test
    public void insert(){
        BoardDto dto=BoardDto.builder()
                        .id("hello")
                        .title("좋아좋아")
                        .content("good!!!!")
                .build();
        BoardDto dto1=boardService.insert(dto);
        System.out.println("등록된 글==>" + dto1);
    }
    @Test
    public void list(){
        PageRequest pageable=PageRequest.of(1,5);
        PageResultDto pageinfo= boardService.list(pageable);
      //  System.out.println(pageinfo);
        System.out.println("현재 페이지:" + pageinfo.getPage());
        System.out.println("시작페이지번호:" + pageinfo.getStartPage());
        System.out.println("마지막페이지번호:" + pageinfo.getEndPage());
        System.out.println("전체 페이지 갯수:" + pageinfo.getTotalPageCount());
        System.out.println("===현재 페이지 글목록===");
        List<BoardDto> list=pageinfo.getList();
        list.forEach(System.out::println);
    }
}









