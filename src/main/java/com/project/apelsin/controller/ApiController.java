
package com.project.apelsin.controller;

import com.project.apelsin.repository.*;
import com.project.apelsin.service.InvoiceService;
import com.project.apelsin.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/")
public class ApiController {
    final CustomerRepository customerRepository;
    final OrderRepository orderRepository;
    final CategoryRepository categoryRepository;
    final DetailRepository detailRepository;
    final PaymentRepository paymentRepository;
    final ProductRepository productRepository;
    final InvoiceRepository invoiceRepository;

    final InvoiceService invoiceService;
    final OrderService orderService;

    @GetMapping("/expired_invoice")
    public HttpEntity<?> get1(){
        return ResponseEntity.ok().body(invoiceRepository.findAllByIssuedAfterDue());
    }
    @GetMapping("/wrong_date_invoices")
    public HttpEntity<?> get2(){
        return ResponseEntity.ok().body(invoiceService.getWrongDateDtos());
    }
    @GetMapping("/orders_without_details")
    public HttpEntity<?> get3(){
        return ResponseEntity.ok().body(orderRepository.getNotDetail());
    }
    @GetMapping("/customers_without_orders")
    public HttpEntity<?> get4(){
        return ResponseEntity.ok().body(customerRepository.findAllByNotOrder());
    }
    @GetMapping("/customers_last_orders")
    public HttpEntity<?> get5(){
        return ResponseEntity.ok().body(orderService.getCustomerLastOrderDtos());
    }
    @GetMapping("/overpaid_invoices")
    public HttpEntity<?> get6(){
        return ResponseEntity.ok().body(invoiceService.getOverPaid());
    }
    @GetMapping("/high_demand_products")
    public HttpEntity<?> get7(){
        return ResponseEntity.ok().body(detailRepository.getMostSoldProduct());
    }
    @GetMapping("/bulk_products")
    public HttpEntity<?> get8(){
        return ResponseEntity.ok().body(detailRepository.getBulkProducts());
    }
    @GetMapping("/number_of_products_in_year")
    public HttpEntity<?> get9(){
        return ResponseEntity.ok().body(orderRepository.getCountryProduct());
    }
    @GetMapping("/orders_without_invoices")
    public HttpEntity<?> get10(){
        return ResponseEntity.ok().body(orderRepository.getOrdersWithoutInvoice());
    }
}
