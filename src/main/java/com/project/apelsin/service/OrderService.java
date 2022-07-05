package com.project.apelsin.service;

import com.project.apelsin.dto.*;
import com.project.apelsin.entity.*;
import com.project.apelsin.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {
    final OrderRepository orderRepository;
    final CustomerRepository customerRepository;
    final ProductRepository productRepository;
    final DetailRepository detailRepository;
    final InvoiceRepository invoiceRepository;

    public List<CustomerLastOrderDto> getCustomerLastOrderDtos(){
        List<Order> lastOrders = orderRepository.getLastOrders();
        return lastOrders.stream().map(this::parseCustomerToDto).collect(Collectors.toList());
    }

    public CustomerLastOrderDto parseCustomerToDto(Order order){
        return new CustomerLastOrderDto(
                order.getCustomer().getId(), order.getCustomer().getName(), order.getDate()
        );
    }

    public ApiResponse add(OrderDto orderDto) {
        Optional<Customer> optionalCustomer = customerRepository.findById(orderDto.getCustomer_id());
        if (optionalCustomer.isEmpty()) {
            return new ApiResponse("Customer not found", false);
        }
        Order order=new Order();
        order.setDate(LocalDate.now());
        order.setCustomer(optionalCustomer.get());
        Order save = orderRepository.save(order);
        double total=0D;
        for (DetailDto product : orderDto.getProducts()) {
            Detail detail=new Detail();
            Optional<Product> optionalProduct = productRepository.findById(product.getProduct_id());
            if (optionalProduct.isEmpty()) {
                continue;
            }
            detail.setOrd(save);
            detail.setQuantity(product.getQuantity());
            detail.setPr(optionalProduct.get());
            detailRepository.save(detail);
            total+=(optionalProduct.get().getPrice()*product.getQuantity());
        }
        Invoice invoice=new Invoice();
        invoice.setOrder(save);
        invoice.setAmount(total);
        invoice.setIssued(LocalDate.now());
        invoice.setDue(LocalDate.now().plusDays(3));
        Invoice savedInvoice = invoiceRepository.save(invoice);
        Map<String, Long> response=new LinkedHashMap<>();
        response.put("invoice_number", savedInvoice.getId());
        return new ApiResponse("Saved", true, response);
    }

    public OrderDetails getDetails(Long id) {
        Optional<Order> byId = orderRepository.findById(id);
        if(byId.isEmpty()){
            return null;
        }
        List<DetailsDto> detailsDtos = detailRepository.findAllByOrd_id(id).stream()
                .map(detail -> new DetailsDto(detail.getPr().getName(), detail.getQuantity())).toList();
        return new OrderDetails(byId.get(), detailsDtos);
    }

}
