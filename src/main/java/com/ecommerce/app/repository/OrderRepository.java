package com.ecommerce.app.repository;

import com.ecommerce.app.dto.OrderDTO;
import com.ecommerce.app.dto.OrderUserDTO;
import com.ecommerce.app.entities.Order;

import jakarta.transaction.Transactional;

import java.time.Instant;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface OrderRepository extends JpaRepository<Order, Long> {

    @Query(nativeQuery = true, value = """
            SELECT u.name AS userName, o.id AS orderId, o.moment AS orderMoment, o.ORDER_STATUS AS ostatus
            FROM tb_order o 
            INNER JOIN tb_user u ON o.client_id = u.id 
            WHERE o.client_id = :client_id
            ORDER BY o.moment""")
    List<OrderUserDTO> findOrdersByClient(@Param("client_id") Long client_id);
    
    @Modifying(flushAutomatically = true)
    @Query(nativeQuery = true, value = """
                        INSERT INTO tb_order (moment, order_status, client_id) VALUES (:moment, :order_status, :client_id)""")
        void saveOrder(Instant moment, int order_status, Long client_id);

    @Query(nativeQuery = true, value = """
            SELECT o.id AS orderId, o.moment AS orderMoment, o.ORDER_STATUS AS ostatus
            FROM tb_order o 
            WHERE o.client_id = :client_id AND o.moment = :moment AND o.ORDER_STATUS = :order_status
            ORDER BY o.moment""")
    OrderDTO findOrdersByArgs(Instant moment, int order_status, Long client_id);
}
