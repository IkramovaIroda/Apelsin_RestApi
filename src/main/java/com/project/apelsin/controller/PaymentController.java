package com.project.apelsin.controller;

import com.project.apelsin.dto.ApiResponse;
import com.project.apelsin.dto.PaymentDto;
import com.project.apelsin.entity.Invoice;
import com.project.apelsin.entity.Payment;
import com.project.apelsin.repository.DetailRepository;
import com.project.apelsin.repository.InvoiceRepository;
import com.project.apelsin.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalTime;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/payment")
public class PaymentController {
    final PaymentRepository paymentRepository;
    final InvoiceRepository invoiceRepository;
    final DetailRepository detailRepository;

    @PostMapping
    public HttpEntity<?> add(@RequestBody PaymentDto paymentDto){
        Optional<Invoice> optionalInvoice = invoiceRepository.findById(paymentDto.getInvoice_id());
        if(optionalInvoice.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        Payment payment=new Payment();
        payment.setTime(LocalTime.now());
        payment.setInv(optionalInvoice.get());
        payment.setAmount(optionalInvoice.get().getAmount());
        Payment save = paymentRepository.save(payment);
        return ResponseEntity.ok().body(new ApiResponse("Paid!", true ,save));
    }

    @GetMapping("/details")
    public HttpEntity<?> getDetail(@RequestParam("id") Long id){
        Optional<Payment> optionalPayment = paymentRepository.findById(id);
        if(optionalPayment.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(optionalPayment.get());
    }
}
