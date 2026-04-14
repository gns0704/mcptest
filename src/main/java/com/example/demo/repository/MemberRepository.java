package com.example.demo.repository;

import com.example.demo.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MemberRepository extends JpaRepository<Member,String> {
    //select * from member limit 0,10; => member테이블의 0번째 인덱스부터 10개 추출 (MySQL)
    //nativeQuery = true : JPQL이 아닌 sql구문을 사용하고자 할때
    @Query(value="select * from member order by age desc limit :a,:b",nativeQuery = true)
    List<Member> pageList(@Param("a")int a,@Param("b")int b);
    
    @Modifying //DML작업을 할때는 반드시 @Modifying어노테이션을 설정해야 함 
    @Query("update Member m set m.age=m.age+10 where m.id=:id")
    int update(@Param("id")String id);
}
