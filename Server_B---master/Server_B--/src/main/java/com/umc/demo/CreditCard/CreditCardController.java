package com.umc.demo.CreditCard;

import com.umc.demo.Account.Account;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/card")
public class CreditCardController {

    @Autowired
    CreditCardRepository creditCardRepository;

    // 카드 등록
    @PostMapping("/signin")
    public void createCard(
            @RequestParam("socialNumber") String socialNumber,
            @RequestParam("accountnumber") int accountnumber,
            @RequestParam("type") String type,
            @RequestParam("cardlimit") double cardlimit,
            @RequestParam("applicationdate") String applicationdate) {
        CreditCard cd = new CreditCard();
        cd.setSocialnumber(socialNumber);
        cd.setAccountnumber(accountnumber);
        cd.setType(type);
        cd.setCardlimit(cardlimit);
        cd.setApplicationdate(LocalDate.parse(applicationdate));
        creditCardRepository.save(cd);
    }

    // 고객의 모든 카드 조회 (주민번호로)
    @GetMapping("/user")
    public List<CreditCard> getAllCards(@RequestParam("socialNumber") String socialNumber) {
        return creditCardRepository.getOnesAllCards(socialNumber);
    }

    // 카드 번호로 연결 계좌 잔고 조회
    @GetMapping("/account_balance")
    public double getAccountBalance(@RequestParam("cardNumber") int cardNumber) {
        return creditCardRepository.getAccountBalance(cardNumber);
    }

    // 카드 한도 조회
    @GetMapping("/limit")
    public double getLimit(@RequestParam("cardNumber") int cardNumber) {
        return creditCardRepository.getLimit(cardNumber);
    }
}