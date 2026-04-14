package com.example.demo.dto;

import com.example.demo.entity.Board;
import com.example.demo.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class BoardDto {
    private Long num;
    private String id;//Member id
    private String title;
    private String content;
    private LocalDateTime regdate;

    public BoardDto(Board board){
        num= board.getNum();
        id=board.getMember().getId();
        title=board.getTitle();
        content=board.getContent();
        regdate=board.getRegdate();
    }
    public Board toEntity(Member member){
        return Board.builder()
                .num(this.num)
                .member(member)
                .title(this.title)
                .content(this.content)
                .regdate(this.regdate)
                .build();

    }
}
