package edu.iu.c322.orderservice.repository;

import edu.iu.c322.orderservice.model.entity.OrderItems;
import edu.iu.c322.orderservice.model.entity.Payment;
import edu.iu.c322.orderservice.model.entity.Return;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReturnRepository extends JpaRepository<Return, Integer> {
    @Query("SELECT oi FROM Return oi WHERE oi.order.orderId = :orderId")
    Return findByOrderId(@Param("orderId") Integer orderId);

}
