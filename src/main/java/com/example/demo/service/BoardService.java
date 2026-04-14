package com.example.demo.service;

import com.example.demo.dto.BoardDto;
import com.example.demo.dto.PageResultDto;
import com.example.demo.entity.Board;
import com.example.demo.entity.Member;
import com.example.demo.repository.BoardRepository;
import com.example.demo.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.apache.el.stream.Stream;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository br;
    private final MemberRepository mr;

    public BoardDto insert(BoardDto dto){
        Optional<Member> optionalMember=mr.findById(dto.getId());
        Member member=optionalMember.orElseThrow(()->new RuntimeException("회원아이디가 존재하지 않습니다."));
        Board board=dto.toEntity(member);
        Board savedBoard= br.save(board);
        return new BoardDto(savedBoard);
    }
    public PageResultDto list(Pageable pageable){
        Page<Board> pageList= br.findAll(pageable);
//        List<BoardDto> list=pageList.stream().map(board->{
//            return new BoardDto(board);
//        }).toList();
 //       List<BoardDto> list=pageList.stream().map(board->new BoardDto(board)).toList();
        List<BoardDto> list=pageList.stream().map(BoardDto::new).toList();

        PageResultDto pageResultDto=new PageResultDto(list,
                pageList.getNumber(),pageList.getTotalPages(),5);
        return pageResultDto;
    }
    // 수정기능
    public BoardDto update(BoardDto dto) {
        Board board=br.findById(dto.getNum())
                .orElseThrow(()->new RuntimeException("회원아이디가 존재하지 않습니다."));
        board.setTitle(dto.getTitle());
        board.setContent(dto.getContent());
        //br.save(board);
        return new BoardDto(board);
    }
    // 삭제기능
    public void delete(Long num) {
        br.deleteById(num);
    }
    // 글번호로 조회기능
    public BoardDto select(Long num) {
        return new BoardDto(br.findById(num)
                .orElseThrow(()->new RuntimeException("회원아이디가 존재하지 않습니다.")));
    }
}
