package com.project.apelsin.repository;

import com.project.apelsin.dto.CountryProductDto;
import com.project.apelsin.dto.OrderWithoutInvoiceDto;
import com.project.apelsin.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    @Query(value = "select * from orders where id not in (select ord_id from detail)", nativeQuery = true)
    List<Order> getNotDetail();

    @Query(nativeQuery = true, value = "select distinct on (o.customer_id) customer_id, o.id as id, o.date from orders o" +
            " join customer c on c.id = o.customer_id" +
            " where o.date in (select max(date) from orders where orders.customer_id=c.id)" +
            " group by o.customer_id, o.date, o.id")
    List<Order> getLastOrders();
    @Query(nativeQuery = true, value = "select c.country as country, count(orders.id) as total from orders" +
            " join customer c on c.id = orders.customer_id" +
            " group by c.country")
    List<CountryProductDto> getCountryProduct();
    @Query(nativeQuery = true, value = "select o.id as id, o.date as date, sum(d.quantity*p.price) as total from orders o" +
            "    join detail d on o.id = d.ord_id" +
            "    join product p on p.id = d.pr_id " +
            "where o.id not in (select ord_id from invoice) " +
            "group by o.id")
    List<OrderWithoutInvoiceDto> getOrdersWithoutInvoice();

}