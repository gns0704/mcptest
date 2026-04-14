package com.example.demo.controller;

import com.example.demo.dto.MemberDto;
import com.example.demo.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class MemberController {
    private final MemberService ms;

    @GetMapping("/member/join")
    public String joinForm(){
        return "members/join";
    }
    @PostMapping("/member/join")
    public String join(MemberDto dto, Model model){
        MemberDto saveMember= ms.insert(dto);
        model.addAttribute("member",saveMember);
        return "result";
    }
}
