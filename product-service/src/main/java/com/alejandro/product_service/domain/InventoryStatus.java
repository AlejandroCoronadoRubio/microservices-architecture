package com.alejandro.product_service.domain;

public enum InventoryStatus {

    IN_STOCK("In stock"),
    LOW_STOCK("Low stock"),
    OUT_OF_STOCK("Out of stock");

    private final String name;

    InventoryStatus(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
    @Override
    public String toString() {
        return name;
    }
}