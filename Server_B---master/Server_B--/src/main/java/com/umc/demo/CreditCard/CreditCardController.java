package com.umc.demo.CreditCard;

import com.umc.demo.Customer.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/card")
public class CreditCardController {

    @Autowired
    CreditCardRepository creditCardRepository;

    // 모든 카드 조회
    @GetMapping("/all")
    public List<Object> getAllCards() {
        return creditCardRepository.getAllCards();
    }
}