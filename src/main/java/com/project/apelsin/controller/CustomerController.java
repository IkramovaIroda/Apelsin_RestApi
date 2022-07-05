package com.project.apelsin.controller;

import com.project.apelsin.dto.CustomerDto;
import com.project.apelsin.entity.Customer;
import com.project.apelsin.repository.CustomerRepository;
import com.project.apelsin.service.CustomerService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/customer")
public class CustomerController {
    final CustomerService customerService;
    final CustomerRepository customerRepository;

    @GetMapping
    public HttpEntity<?> getAll(){
        return ResponseEntity.ok().body(customerRepository.findAll());
    }

    @GetMapping("/{id}")
    public HttpEntity<?> getOne(@PathVariable Long id){
        Optional<Customer> optionalCustomer = customerRepository.findById(id);
        if(optionalCustomer.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok().body(optionalCustomer.get());
    }

    @DeleteMapping("/{id}")
    public HttpEntity<?> delete(@PathVariable Long id){
        return customerService.delete(id);
    }
    @PutMapping("/{id}")
    public HttpEntity<?> edit(@PathVariable Long id, @RequestBody CustomerDto customerDto){
        return customerService.edit(id, customerDto);
    }

    @PostMapping
    public HttpEntity<?> addCustomer(@RequestBody CustomerDto customerDto){
        return customerService.add(customerDto);
    }

    @GetMapping("/customers_without_orders")
    public HttpEntity<?> noOrderCustomer(){
        return ResponseEntity.ok().body(customerRepository.findAllByNotOrder());
    }
}
