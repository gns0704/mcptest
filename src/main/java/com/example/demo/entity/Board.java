package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString(exclude = "member") //toString메소드 오버라이딩할때는 연관객체는 제외한다.
@Builder
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long num;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="id")
    private Member member;
    private String title;
    @Lob
    private String content;
    @CreationTimestamp
    private LocalDateTime regdate;
}
