package com.umc.demo.Customer;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    CustomerRepository customerRepository;

/*    // 고객 등록
    @PostMapping()
    public List<Object> saveCustomer(String socialNumber, String name, String address, Date birthdate, String email, String phoneNumber, String job) {
        return customerRepository.saveCustomer(socialNumber, name, address, birthdate, email, phoneNumber, job);
    }*/

    // 전체 고객 조회
    @GetMapping("/all")
    public List<Object> getPosts() { // list 타입 Customer로 안하고 그냥 Object해도 결고 나옴.
        return customerRepository.getAllCustomers();
    }

    // 전체 고객 생일 조회
    @GetMapping("/all/b")
    public List<Date> getPosts2() { // list 타입 Customer로 안하고 그냥 Object해도 결고 나옴.
        return customerRepository.getAllCustomers2();
    }
}