package com.alejandro.inventory_service.utils;

import com.alejandro.inventory_service.domain.InventoryStatus;

import java.util.Random;

public class InventoryStatusUtil {

    public static String getRandomInventoryStatus() {
        Random random = new Random();
        InventoryStatus[] inventoryStatuses = InventoryStatus.values();
        return inventoryStatuses[random.nextInt(inventoryStatuses.length)].getName();
    }
}
