package com.project.apelsin.service;

import com.project.apelsin.dto.ApiResponse;
import com.project.apelsin.dto.CustomerDto;
import com.project.apelsin.dto.CustomerLastOrderDto;
import com.project.apelsin.entity.Customer;
import com.project.apelsin.entity.Order;
import com.project.apelsin.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomerService {
    final CustomerRepository customerRepository;

    public HttpEntity<?> add(CustomerDto customerDto){
        try {
            Customer customer=new Customer();
            customer.setAddress(customerDto.getAddress());
            customer.setCountry(customerDto.getCountry());
            customer.setPhone(customerDto.getPhone());
            customer.setName(customerDto.getName());
            Customer save = customerRepository.save(customer);
            return ResponseEntity.ok().body(new ApiResponse("Created", true, save));
        }catch (Exception e){
            return ResponseEntity.badRequest().body(new ApiResponse(e.getMessage(), false));
        }

    }

    public HttpEntity<?> edit(Long id, CustomerDto customerDto){

        try {
            Optional<Customer> byId = customerRepository.findById(id);
            if(byId.isEmpty()){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
            Customer customer=byId.get();
            if(customerDto.getAddress()!=null){
                customer.setAddress(customerDto.getAddress());
            }
            if(customerDto.getCountry()!=null){
                customer.setCountry(customerDto.getCountry());
            }
            if(customerDto.getPhone()!=null){
                customer.setPhone(customerDto.getPhone());
            }
            if(customerDto.getName()!=null){
                customer.setName(customerDto.getName());
            }
            Customer save = customerRepository.save(customer);
            return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("Saved", true, save));
        }catch (Exception e){
            return ResponseEntity.badRequest().body(new ApiResponse(e.getMessage(), false));
        }

    }
    public HttpEntity<?> delete(Long id){
        boolean exists = customerRepository.existsById(id);
        if(!exists){
            return ResponseEntity.notFound().build();
        }
        customerRepository.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("Deleted", true));
    }
}
