package com.umc.demo.CreditCard;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/card")
public class CreditCardController {

    @Autowired
    CreditCardRepository creditCardRepository;

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