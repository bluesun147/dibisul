package com.umc.demo.Account;


import com.umc.demo.Customer.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface AccountRepository extends JpaRepository<Account, Integer>{
 // https://kitty-geno.tistory.com/118
    // 고객 등록
    @Query(value = "insert into Customer (socialNumber, name, birthdate, email, phoneNumber, job) values (:socialNumber, :name, :birthdate, :email, :phoneNumber, :job)", nativeQuery = true)
    public List<Customer> saveCustomer(@Param("socialNumber") String socialNumber, String name, LocalDate birthdate, String email, String phoneNumber, String job);

   // 모든 계좌 조회
   @Query(value = "select * from Account", nativeQuery = true)
   public List<Account> getAllAccounts();// 특정 사용자 계좌 조회();

    // 특정 사용자 계좌 조회
    @Query(value = "select * from Account where socialNumber = :socialNumber", nativeQuery = true)
    public List<Account> getAccount(@Param("socialNumber") String socialNumber);// 특정 사용자 계좌 조회();


    // 특정 계좌 잔고 조회
    @Query(value = "select balance from Account where accountNumber = :accountNumber", nativeQuery = true)
    public List<Double> getAccountBalance(@Param("accountNumber") int accountNumber);// 특정 사용자 계좌 조회();

    //@Query(value = "select balance from Account where accountNumber = :accountNumber", nativeQuery = true)
    //public void transfer(@Param("accountNumber1") int accountNumber1, @Param("accountNumber2") int accountNumber2);// 특정 사용자 계좌 조회();
}