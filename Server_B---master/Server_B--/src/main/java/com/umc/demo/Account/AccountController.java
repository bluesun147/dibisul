package com.umc.demo.Account;

import com.umc.demo.Customer.Customer;
import com.umc.demo.Transaction.Transaction;
import com.umc.demo.Transaction.TransactionRepository;
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
    @Autowired
    TransactionRepository transactionRepository;

    // --------> 이건 그냥 jpa 쓰면 안됨??
    // 계좌 개설 --> 로그인 해야 할 수 있도록
    @PostMapping("/signin")
    public List<Customer> createAccount(
            @RequestParam("socialNumber") String socialNumber,
            @RequestParam("branchnumber") int branchnumber,
            @RequestParam("type") String type,
            @RequestParam("balance") double balance,
            @RequestParam("cardappstatus") boolean cardappstatus) {
        // 로그인 상태면 정보 모두 있으므로 파람으로 안받고 자동으로 넣어주면 됨.
        return accountRepository.createAccount(socialNumber, branchnumber, type, balance, cardappstatus);
    }

    // 모든 계좌 조회
    @GetMapping("/all")
    public List<Account> getAllAccounts() {
        return accountRepository.getAllAccounts();
    }

    // 특정 사용자 계좌 조회
    @GetMapping("/user")
    public List<Account> getAccounts(@RequestParam("socialNumber") String socialNumber) {
        return accountRepository.getAccount(socialNumber);
    }

    // 특정 계좌 잔고 조회
    @GetMapping("/balance")
    public Double getAccountBalance(@RequestParam("accountNumber") int accountNumber) {
        return accountRepository.getAccountBalance(accountNumber);
    }

    // 입출금시 거래내역 테이블에도 데이터 들어가야 함.



    // 입금
    @GetMapping("/deposit")
    public double deposit(
            @RequestParam("deposit") double deposit,
            @RequestParam("accountNumber") int accountNumber) {

        double accountBalance = accountRepository.getAccountBalance(accountNumber); // 원금
        double result = accountBalance + deposit; // 원금 + 입금액
        accountRepository.deposit(result, accountNumber); // 입금

        // 거래내역 테이블에 데이터 입력
//        transactionRepository.putTrans(accountNumber, deposit, "2022-11-21 22:20:47");
        // transactionRepository.putTrans(accountNumber, deposit);
        Transaction tr = new Transaction();
        tr.setAccountnumber(accountNumber);
        tr.setTransactionamount(deposit);
        transactionRepository.save(tr);

        return accountRepository.getAccountBalance(accountNumber); // 입금 후 잔액 반환
    }

    // 출금
    @GetMapping("/withdraw")
    public double withdraw(
            @RequestParam("withdraw") double withdraw,
            @RequestParam("accountNumber") int accountNumber) {

        double accountBalance = accountRepository.getAccountBalance(accountNumber); // 원금
        double result = accountBalance - withdraw; // 원금 - 출금액
        accountRepository.withdraw(result, accountNumber); // 출금

        // 거래내역 테이블에 데이터 입력
        //transactionRepository.putTrans(accountNumber, (-1*withdraw));
        Transaction tr = new Transaction();
        tr.setAccountnumber(accountNumber);
        tr.setTransactionamount((-1)*withdraw);
        transactionRepository.save(tr);

        return accountRepository.getAccountBalance(accountNumber); // 출금 후 잔액 반환
    }

    // 계좌 이체
    @GetMapping("/transfer")
    public void transfer(
            @RequestParam("money") double money,
            @RequestParam("accountNumber1") int accountNumber1,
            @RequestParam("accountNumber2") int accountNumber2) {
         this.withdraw(money, accountNumber1); // 계좌1에서 출금
         this.deposit(money, accountNumber2); // 계좌2에 입금
    }

    // 계좌에 연결된 카드 조회
    @GetMapping("/card")
    public List<Object> getCards(@RequestParam("accountNumber") int accountNumber) {
        return accountRepository.getCards(accountNumber);
    }

    // 특정 브랜치에서(branchNumber 사용) 개설된 계좌 조회
    @GetMapping("/branch")
    public List<Account> getBranchAccounts(@RequestParam("branchNumber") int branchNumber) {
        return accountRepository.getBranchAccounts(branchNumber);
    }
}