package com.umc.demo.Account;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDate;

@Getter
@NoArgsConstructor
@Entity
public class Account {
    @Id
    private int accountnumber; // accountNumber라고 하면 account_number를 찾음
    private String socialnumber;
    private int branchnumber;
    private String type;
    private double balance;
    private boolean cardappstatus;
    private LocalDate opendate;

//    @Builder
//    public Customer(int user_id, String email, String password, String name, String profile_img, String text) {
//        this.user_id = user_id;
//        this.email = email;
//        this.password = password;
//        this.name = name;
//        this.profile_img = profile_img;
//        this.text = text;
//    }
}
