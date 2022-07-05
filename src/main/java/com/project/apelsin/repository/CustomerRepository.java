package com.project.apelsin.repository;

import com.project.apelsin.entity.Customer;
import com.project.apelsin.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

    @Query(nativeQuery = true, value = "select * from customer where id not in (select customer_id from orders)")
    List<Customer> findAllByNotOrder();
}