package com.example.demo.controller;

import com.example.demo.dto.PageResultDto;
import com.example.demo.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class BoardListController {
    private final BoardService boardService;

    @GetMapping("/board/list")
    public String list(@RequestParam(value="page",defaultValue = "1") int page, Model model){
        PageRequest pageable=PageRequest.of(page-1,
                5,
                Sort.by("num").descending());
        PageResultDto result=boardService.list(pageable);
        model.addAttribute("list",result.getList());
        model.addAttribute("startPage",result.getStartPage());
        model.addAttribute("endPage",result.getEndPage());
        model.addAttribute("pageCount",result.getTotalPageCount());
        model.addAttribute("page",result.getPage());
        return "board/list";
    }

    @GetMapping("/board/list1")
    public String list1(@PageableDefault(size=5,sort="num",direction = Sort.Direction.DESC)
                            Pageable pageable,Model model){
        System.out.println("page==>" + pageable.getPageNumber());//page번호
        PageResultDto result=boardService.list(pageable);
        model.addAttribute("list",result.getList());
        model.addAttribute("startPage",result.getStartPage());
        model.addAttribute("endPage",result.getEndPage());
        model.addAttribute("pageCount",result.getTotalPageCount());
        model.addAttribute("page",result.getPage());
        return "board/list";
    }
}
