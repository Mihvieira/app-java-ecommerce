package com.ecommerce.app.repository;

import com.ecommerce.app.dto.OrderUserDTO;
import com.ecommerce.app.entities.OrderItem;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecommerce.app.entities.pk.OrderItemPK;

public interface OrderItemRepository extends JpaRepository<OrderItem, OrderItemPK> {

    Optional<OrderItem> findByIdProduct(Long id);

    Optional<OrderItem> findByIdOrder(Long id);

    void deleteByOrder(Long id);

    OrderItem getReferenceByOrderId(Long id_order);

    List<OrderUserDTO> findAllOrderItems(Long order_id);
}
