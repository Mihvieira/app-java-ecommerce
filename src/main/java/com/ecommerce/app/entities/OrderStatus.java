package com.ecommerce.app.entities;

public enum OrderStatus {

    WAITING_PAYMENT(1),
    PAID(2),
    SHIPPED(3),
    DELIVERED(4),
    CANCELED(5);

    private Integer status;

    OrderStatus(Integer status) {
        this.status = status;
    }

    OrderStatus() {
    }

    public Integer getStatus() {
        return status;
    }

    public void setOrderStatus(Integer status){
        this.status = status;
    }

    public static OrderStatus valueOf(Integer status){
        for (OrderStatus value: OrderStatus.values()){
            if(value.getStatus() == status){
                return value;
            }
        }
        throw new IllegalArgumentException("Invalid OrderStatus");
    }
}
