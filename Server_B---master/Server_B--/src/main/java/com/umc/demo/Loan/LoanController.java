package com.umc.demo.Loan;

import com.umc.demo.CreditCard.CreditCardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/loan")
public class LoanController {

    @Autowired
    LoanRepository loanRepository;

    // 고객(주민번호로)의 대출 정보 조회 ---> 내역 조회 말고 그냥 정보 한번에 조회. 대출 상환(이자x) 하면 그냥 amount 줄어드는 식
    @GetMapping("/user")
    public List<Loan> getUsersLoan(@RequestParam("socialNumber") String socialNumber) {
        return loanRepository.getUsersLoan(socialNumber);
    }

    // 특정 대출의 남은 amount 조회
    @GetMapping("/amount")
    public double getLoanAmount(@RequestParam("loanNumber") int loanNumber) {
        return loanRepository.getLoanAmount(loanNumber);
    }

        // 특정 대출의 대출 상환
    @GetMapping("/repayment")
    public double repayment(
            @RequestParam("loanNumber") int loanNumber,
            @RequestParam("money") double money) {
        double repaymentamount = loanRepository.getLoanAmount(loanNumber);
        double result = repaymentamount - money;

        loanRepository.repayment(result, loanNumber);
        return loanRepository.getLoanAmount(loanNumber);
    }
}