package com.umc.demo.Branch;

import com.umc.demo.Account.Account;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/branch")
public class BranchController {

    @Autowired
    BranchRepository branchRepository;

    // 전체 브랜치 조회
    @GetMapping("/all")
    public List<Branch> getAllAccounts() {
        return branchRepository.getAllBranchs();
    }
}