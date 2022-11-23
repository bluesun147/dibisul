package com.umc.demo.post;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Integer> {
    List<Post> findByUserId(int user_id);

    // <Post> findByText(String text, Pageable pageable);

    // JPA 레포지토리에 Paging 함수 선언
    Page<Post> findAll(Pageable pageable);

    //// 디비설 쿼리
    // 일반 SQL쿼리
    @Query(value = "select * from post where text = ?1", nativeQuery = true)
    public List<Post> selectAllSQL(String text);
}