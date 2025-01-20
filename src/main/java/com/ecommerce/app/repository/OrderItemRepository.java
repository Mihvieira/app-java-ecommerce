package com.ecommerce.app.repository;

import com.ecommerce.app.entities.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

import com.ecommerce.app.entities.pk.OrderItemPK;

public interface OrderItemRepository extends JpaRepository<OrderItem, OrderItemPK> {
}
