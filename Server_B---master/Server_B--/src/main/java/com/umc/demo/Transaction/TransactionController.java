package com.umc.demo.Transaction;

import com.umc.demo.Loan.Loan;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/transaction")
public class TransactionController {

    @Autowired
    TransactionRepository transactionRepository;

    // 특정 계좌 거래 내역 조회 (누구한테 보낸지는 말고 입금, 출금만 기록)
    @GetMapping("/account") // <>안의 타입과 리포지토리 extends 타입과 맞아야 함
    public List<Transaction> getAccountTrans(@RequestParam("accountNumber") int accountNumber) {
        return transactionRepository.getAccountTrans(accountNumber);
    }

    // 거래 내역 작성 // 쓰이는곳 없음. accountController에서 trRepository.save 쓰고있음
    @PostMapping("/")
    public void putTrans(
            @RequestParam("accountNumber") int accountNumber,
            @RequestParam("money") double money) {
        transactionRepository.putTrans(accountNumber, money);
    }
}