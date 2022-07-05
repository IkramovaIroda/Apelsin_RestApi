package com.project.apelsin.repository;

import com.project.apelsin.dto.InvoiceWrongDateDto;
import com.project.apelsin.entity.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface InvoiceRepository extends JpaRepository<Invoice, Long> {
    @Query(nativeQuery = true, value = "select * from invoice where issued>invoice.due")
    List<Invoice> findAllByIssuedAfterDue();

    @Query(nativeQuery = true, value = "select i.id, i.amount, i.issued, i.due, i.ord_id from invoice i" +
            " join orders o on o.id = i.ord_id" +
            " where issued<o.date")
    List<Invoice> findAllByWrongDate();

    @Query(nativeQuery = true, value = "select invoice.id as id, (select sum(payment.amount) from payment where inv_id=invoice.id)" +
            "-invoice.amount as amount, invoice.issued, invoice.due, invoice.ord_id from invoice where invoice.amount<(select sum(amount) from payment where inv_id=invoice.id)")
    List<Invoice> getOverPaidInvoices();
}