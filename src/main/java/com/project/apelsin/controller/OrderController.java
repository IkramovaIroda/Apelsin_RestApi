package com.project.apelsin.controller;

import com.project.apelsin.dto.ApiResponse;
import com.project.apelsin.dto.OrderDto;
import com.project.apelsin.repository.DetailRepository;
import com.project.apelsin.repository.OrderRepository;
import com.project.apelsin.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/order")
public class OrderController {
    final OrderService orderService;
    final OrderRepository orderRepository;
    final DetailRepository detailRepository;

    @PostMapping
    public HttpEntity<?> add(@RequestBody OrderDto orderDto){
        ApiResponse apiResponse=orderService.add(orderDto);
        return ResponseEntity.status(apiResponse.isSuccess()?200:400).body(apiResponse);
    }
    @GetMapping("/details")
    public HttpEntity<?> getOne(@RequestParam("order_id") Long id){
        if (!orderRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(orderService.getDetails(id));
    }
}
