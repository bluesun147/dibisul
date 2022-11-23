package com.umc.demo.Branch;

import com.umc.demo.Account.Account;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/branch")
public class BranchController {

    @Autowired
    BranchRepository branchRepository;

    // 모든 브랜치 조회
    @GetMapping("/all")
    public List<Object> getAllAccounts() {
        return branchRepository.getAllBranchs();
    }

    /*// 모든 브랜치 넘버 조회
    @GetMapping("/alli")
    public List<Integer> getAllAccounts3() {
        return branchRepository.getAllBranchs();
    }

    // 모든 브랜치 조회
    @GetMapping("/alla")
    public List<String> getAllAccounts2() {
        return branchRepository.getAllBranchs2();
    }*/
}