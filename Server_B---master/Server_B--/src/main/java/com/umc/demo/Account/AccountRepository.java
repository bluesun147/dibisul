package com.umc.demo.Account;

import com.umc.demo.Customer.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

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
    public List<Account> getAccount(@Param("socialNumber") String socialNumber);

    // 특정 계좌 잔고 조회
    @Query(value = "select balance from Account where accountNumber = :accountNumber", nativeQuery = true)
    public Double getAccountBalance(@Param("accountNumber") int accountNumber);// 특정 사용자 계좌 조회();


    // 입금 https://kitty-geno.tistory.com/118
    @Transactional
    @Modifying // 이거 붙여야 함.
    @Query(value = "update Account set balance = :result where accountNumber = :accountNumber", nativeQuery = true)
    public void deposit(@Param("result") double result, // void로 해도됨. List<Object>하면 오류
                                 @Param("accountNumber") int accountNumber);

    // 출금
    @Transactional
    @Modifying
    @Query(value = "update Account set balance = :result where accountNumber = :accountNumber", nativeQuery = true)
    public void withdraw(@Param("result") double result,
                       @Param("accountNumber") int accountNumber);

    //@Query(value = "select balance from Account where accountNumber = :accountNumber", nativeQuery = true)
    //public void transfer(@Param("accountNumber1") int accountNumber1, @Param("accountNumber2") int accountNumber2);// 특정 사용자 계좌 조회();
}