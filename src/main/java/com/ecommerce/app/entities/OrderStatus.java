package com.ecommerce.app.entities;

public enum OrderStatus {

    WAITING_PAYMENT(1),
    PAID(2),
    SHIPPED(3),
    DELIVERED(4),
    CANCELED(5);

    private final int status;

    OrderStatus(int status) {
        this.status = status;
    }

    public int getStatus() {
        return status;
    }

    public static OrderStatus valueOf(int status){
        for (OrderStatus value: OrderStatus.values()){
            if(value.getStatus() == status){
                return value;
            }
        }
        throw new IllegalArgumentException("Invalid OrderStatus");
    }
}
