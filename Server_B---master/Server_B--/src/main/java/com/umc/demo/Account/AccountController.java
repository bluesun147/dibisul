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
    public List<Double> getAccounts2(@PathVariable("accountNumber") int accountNumber) {
        return accountRepository.getAccountBalance(accountNumber);
    }

/*    // 계좌 이체
    @GetMapping("/transfer/{accountNumber1}/{accountNumber2}")
    public void transfer(@PathVariable("accountNumber1") int accountNumber1, @PathVariable("accountNumber2") int accountNumber2) {
        // accountRepository.plus(accountNumber1, accountNumber2);
    }

    // 테스트
    @GetMapping("/xx")
    public Object test(@PathVariable("accountNumber") int accountNumber) {
        return accountRepository.getAccountBalance(accountNumber);
    }*/
}