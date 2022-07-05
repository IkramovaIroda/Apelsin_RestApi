package com.project.apelsin.service;

import com.project.apelsin.dto.InvoiceWrongDateDto;
import com.project.apelsin.dto.OverPaidDto;
import com.project.apelsin.entity.Invoice;
import com.project.apelsin.repository.InvoiceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class InvoiceService {
    final InvoiceRepository invoiceRepository;

    public List<InvoiceWrongDateDto> getWrongDateDtos(){
        List<Invoice> allByWrongDate = invoiceRepository.findAllByWrongDate();
        return allByWrongDate.stream().map(this::parseInvoiceToDto).collect(Collectors.toList());
    }

    public InvoiceWrongDateDto parseInvoiceToDto(Invoice invoice){
        return new InvoiceWrongDateDto(
                invoice.getId(), invoice.getOrder().getId(), invoice.getIssued(), invoice.getOrder().getDate()
        );
    }

    public List<OverPaidDto> getOverPaid(){
        List<Invoice> overPaidInvoices = invoiceRepository.getOverPaidInvoices();
        return overPaidInvoices.stream().map(this::parseInvoiceToOverPaid).collect(Collectors.toList());
    }
    public OverPaidDto parseInvoiceToOverPaid(Invoice invoice){
        return new OverPaidDto(
                invoice.getId(), invoice.getAmount()
        );
    }
}
