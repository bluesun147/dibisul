package com.umc.demo.Account;

import com.umc.demo.Customer.Customer;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/account")
public class AccountController {

    @Autowired
    AccountRepository accountRepository;

    // 계좌 개설 --> 로그인을 해야 할 수 있도록
    // 이따가 하기
    @PostMapping()
    public List<Customer> saveCustomer(
            @RequestParam("socialNumber") String socialNumber,
            @RequestParam("name") String name,
            @RequestParam("birthdate") LocalDate birthdate,
            @RequestParam("email") String email,
            @RequestParam("phoneNumber") String phoneNumber,
            @RequestParam("job") String job) {
        // 로그인 상태면 정보 모두 있으므로 파람으로 안받고 자동으로 넣어주면 됨.
        return accountRepository.saveCustomer(socialNumber, name, birthdate, email, phoneNumber, job);
    }

    // 모든 계좌 조회
    @GetMapping("/all")
    public List<Account> getAllAccounts() {
        return accountRepository.getAllAccounts();
    }

    // 특정 사용자 계좌 조회
    @GetMapping("/user/{socialNumber}")
    public List<Account> getAccounts(@PathVariable("socialNumber") String socialNumber) {
        return accountRepository.getAccount(socialNumber);
    }

    // 특정 계좌 잔고 조회
    @GetMapping("/balance/{accountNumber}")
    public Double getAccountBalance(@PathVariable("accountNumber") int accountNumber) {
        return accountRepository.getAccountBalance(accountNumber);
    }

    // 입출금시 거래 테이블에도 데이터 들어가야 함.



    // 입금
    @GetMapping("/deposit")
    public double deposit(
            @RequestParam("deposit") double deposit,
            @RequestParam("accountNumber") int accountNumber) {

        double accountBalance = accountRepository.getAccountBalance(accountNumber);
        double result = accountBalance + deposit;
        accountRepository.deposit(result, accountNumber);

        return accountRepository.getAccountBalance(accountNumber);
    }

    // 출금
    @GetMapping("/withdraw")
    public double withdraw(
            @RequestParam("withdraw") double withdraw,
            @RequestParam("accountNumber") int accountNumber) {

        double accountBalance = accountRepository.getAccountBalance(accountNumber);
        double result = accountBalance - withdraw;
        accountRepository.withdraw(result, accountNumber);

        return accountRepository.getAccountBalance(accountNumber);
    }

    // 계좌 이체
    @GetMapping("/transfer")
    public void transfer(
            @RequestParam("money") double money,
            @RequestParam("accountNumber1") int accountNumber1,
            @RequestParam("accountNumber2") int accountNumber2) {
         this.withdraw(money, accountNumber1);
         this.deposit(money, accountNumber2);
    }
}