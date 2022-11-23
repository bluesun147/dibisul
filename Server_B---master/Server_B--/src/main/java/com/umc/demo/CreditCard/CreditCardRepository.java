package com.umc.demo.CreditCard;


import com.umc.demo.post.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface CreditCardRepository extends JpaRepository<Post, Integer>{
    // 특정 사용자 카드 조회
    @Query(value = "select * from CreditCard", nativeQuery = true)
    public List<Object> getAllCards();
}