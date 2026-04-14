package com.example.demo.service;

import com.example.demo.dto.MemberDto;
import com.example.demo.entity.Member;
import com.example.demo.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository mr;
    public MemberDto insert(MemberDto dto){
        //DTO를 엔티티로 바꾸기
        Member member=dto.toEntity();
        Member savedMember= mr.save(member);
        mr.flush();//DB에 즉시 반영해라.
        System.out.println(savedMember);
        MemberDto memberDto=new MemberDto(savedMember);
        return memberDto;
    }
}
