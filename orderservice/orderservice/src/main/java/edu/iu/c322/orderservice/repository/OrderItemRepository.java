package edu.iu.c322.orderservice.repository;

import edu.iu.c322.orderservice.model.entity.Item;
import edu.iu.c322.orderservice.model.entity.Order;
import edu.iu.c322.orderservice.model.entity.OrderItems;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItems, Integer> {
    @Query("SELECT oi FROM OrderItems oi WHERE oi.order.orderId = :orderId")
    List<OrderItems> findByOrderId(@Param("orderId") Integer orderId);

    @Query("SELECT oi FROM OrderItems oi WHERE oi.order = :order AND oi.item = :item")
    OrderItems findByOrderAndItem(@Param("order") Order order, @Param("item") Item item);

    @Query("SELECT oi FROM OrderItems oi WHERE oi.order.orderId = :orderId")
    List<OrderItems> findAllByOrderId(@Param("orderId") Integer orderId);

}