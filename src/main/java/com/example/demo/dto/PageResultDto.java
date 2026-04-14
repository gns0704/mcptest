package com.example.demo.dto;

import com.example.demo.entity.Board;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@NoArgsConstructor
@AllArgsConstructor
@Data
public class PageResultDto {
    private List<BoardDto> list;
    private int startPage;
    private int endPage;
    private int totalPageCount;
    private int page;
    public PageResultDto(List<BoardDto> list,int page,int totalPageCount,int blockLimit){
        this.list=list;
        this.page=page+1;
        this.totalPageCount=totalPageCount;
        if(totalPageCount==0){
            startPage=0;
            endPage=0;
            return;
        }
        this.startPage=((this.page-1)/blockLimit)*blockLimit+1;
        this.endPage=Math.min(startPage+blockLimit-1,totalPageCount);
    }
}
