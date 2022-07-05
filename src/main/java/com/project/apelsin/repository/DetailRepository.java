package com.project.apelsin.repository;

import com.project.apelsin.dto.BulkProduct;
import com.project.apelsin.dto.MostSoldProduct;
import com.project.apelsin.entity.Detail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DetailRepository extends JpaRepository<Detail, Long> {
    @Query(value = "select pr_id, sum(detail.quantity) as quantity, o.date from detail" +
            "    join orders o on o.id = detail.ord_id" +
            "    group by o.id, pr_id having sum(detail.quantity)>10", nativeQuery = true)
    List<MostSoldProduct> getMostSoldProduct();

    @Query(nativeQuery = true,
            value = "select p.id as pr_id," +
                    " p.price from detail join product p on p.id = detail.pr_id where quantity>=8 group by p.id")
    List<BulkProduct> getBulkProducts();
    List<Detail> findAllByOrd_id(Long ord_id);
}