package com.umc.demo.Customer;


import com.umc.demo.post.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public interface CustomerRepository extends JpaRepository<Post, Integer>{
 // https://kitty-geno.tistory.com/118
    // 고객 등록
    @Query(value = "insert into Customer (socialNumber, name, address, birthdate, email, phoneNumber, job) values (:socialNumber, :name, :address, :birthdate, :email, :phoneNumber, :job)", nativeQuery = true)
    public List<Object> saveCustomer(@Param("socialNumber") String socialNumber, String name, String address, Date birthdate, String email, String phoneNumber, String job);

    // 전체 고객 조회
    @Query(value = "select * from Customer", nativeQuery = true)
    public List<Object> getAllCustomers(); // 왜 customer로 했을 때 안되는거야 date 때문?

    // 전체 고객 생일 조회
    @Query(value = "select birthDate from Customer", nativeQuery = true)
    public List<Date> getAllCustomers2(); // 타입 틀리면 (Integer로 했을 시)No converter found capable of converting from type [java.sql.Date] to type [@org.springframework.data.jpa.repository.Query java.lang.Integer]


   // 테스트 쿼리1
   @Query(value = "select name, type, balance, openDate\n" +
           "from Account as a\n" +
           "inner join Customer as c\n" +
           "    on a.socialNumber = c.socialNumber\n" +
           "    where c.name = '선민우';", nativeQuery = true)
   public List<Object> test();
}