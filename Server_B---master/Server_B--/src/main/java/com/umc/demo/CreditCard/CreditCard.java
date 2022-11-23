package com.umc.demo.CreditCard;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDateTime;
import java.util.Date;

@Getter
@NoArgsConstructor
@Entity
public class CreditCard {
    @Id
    private int cardnumber;
    private String socialNumber;
    private int accountnumber;
    private String type;
    private double cardLimit;
    private LocalDateTime birthdate;
}
