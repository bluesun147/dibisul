package com.umc.demo.Branch;


import com.umc.demo.Account.Account;
import com.umc.demo.post.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BranchRepository extends JpaRepository<Post, Integer>{
    // 전체 브랜치 조회
    @Query(value = "select * from Branch", nativeQuery = true)
    public List<Object> getAllBranchs();

    @Query(value = "select branchCity from Branch", nativeQuery = true)
    public List<String> getAllBranchs2();
}