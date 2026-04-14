package com.example.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Date;
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Member {
    @Id
    private String id;
    private String pwd;
    private String email;
    private int age;
    @CreationTimestamp
    private Date regdate;
}
