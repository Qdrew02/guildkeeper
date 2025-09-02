package com.guildkeeper.backend.dao.inventory;

import com.guildkeeper.backend.model.Inventory.InventoryItem;

import java.util.List;

public interface InventoryItemDao {
    InventoryItem createItem(InventoryItem item);
    List<InventoryItem> getItemsByOwner(int ownerId, String ownerType);
    void updateItem(InventoryItem item);
    void deleteItem(int itemId);
}
