package com.ecommerce.app.repository;

import com.ecommerce.app.dto.OrderItemProductDTO;
import com.ecommerce.app.entities.OrderItem;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ecommerce.app.entities.pk.OrderItemPK;

public interface OrderItemRepository extends JpaRepository<OrderItem, OrderItemPK> {

    @Query(nativeQuery = true, value = """
            SELECT *
            FROM tb_order_item i  
            WHERE i.product_id = :id
            """)
    List<OrderItem> findByIdProduct(Long id);

    @Query(nativeQuery = true, value = """
            SELECT *
            FROM tb_order_item i  
            WHERE i.order_id = :id
            """)
    List<OrderItem> findByIdOrder(Long id);

    @Query(nativeQuery = true, value = """
            DELETE
            FROM tb_order_item i  
            WHERE i.order_id = :id
            """)
    void deleteByOrder(Long id);

    @Query(nativeQuery = true, value = """
            SELECT o.id, o.moment, o.order_status, i.quantity, i.price, p.name 
            FROM tb_order_item i
            INNER JOIN tb_order o ON o.id = i.order_id 
            INNER JOIN tb_product p ON p.id = i.product_id
            WHERE i.order_id = :order_id
            ORDER BY o.moment""")
    List<OrderItemProductDTO> findAllOrderItens(Long order_id);
}
