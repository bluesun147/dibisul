package com.umc.demo.Customer;

import com.umc.demo.Account.Account;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    CustomerRepository customerRepository;

    // 고객 등록
    @PostMapping("/signin")
    public void createCustomer(
            @RequestParam("socialNumber") String socialNumber,
            @RequestParam("name") String name,
            @RequestParam("address") String address,
            @RequestParam("birthdate") String birthdate,
            @RequestParam("email") String email,
            @RequestParam("phoneNumber") String phoneNumber,
            @RequestParam("job") String job) {
        Customer c = new Customer();
        c.setSocialnumber(socialNumber);
        c.setName(name);
        c.setAddress(address);
        c.setBirthdate(LocalDate.parse(birthdate));
        c.setEmail(email);
        c.setPhonenumber(phoneNumber);
        c.setJob(job);
        customerRepository.save(c);
    }

    // 전체 고객 조회
    @GetMapping("/all")
    public List<Customer> getPosts() { // list 타입 Customer로 안하고 그냥 Object해도 결고 나옴.
        return customerRepository.getAllCustomers();
    }

    // 이름으로 고객 정보 조회
    @GetMapping("/name")
    public List<Customer> getInfoByName(@RequestParam("name") String name) {
        return customerRepository.getInfoByName(name);
    }

    // 전체 고객 생일 조회
    @GetMapping("/all/birthday")
    public List<Date> getPosts2() { // list 타입 Customer로 안하고 그냥 Object해도 결고 나옴.
        return customerRepository.getAllCustomersBirthday();
    }

    // 테스트 쿼리1
    @GetMapping("/test")
    public List<Object> test() { // list 타입 Customer로 안하고 그냥 Object해도 결고 나옴.
        return customerRepository.test();
    }
}