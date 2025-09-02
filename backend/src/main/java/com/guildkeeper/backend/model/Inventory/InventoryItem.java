package com.guildkeeper.backend.model.Inventory;

import java.time.LocalDateTime;

public class InventoryItem {

    private int itemId;
    private int ownerId;
    private String ownerType;
    private String name;
    private int quantity;
    private double weight;
    private String description;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public InventoryItem() {
    }

    public InventoryItem(int itemId, int ownerId, String ownerType, String name, int quantity, double weight, String description, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.itemId = itemId;
        this.ownerId = ownerId;
        this.ownerType = ownerType;
        this.name = name;
        this.quantity = quantity;
        this.weight = weight;
        this.description = description;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    //getters and setters

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public int getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(int ownerId) {
        this.ownerId = ownerId;
    }

    public String getOwnerType() {
        return ownerType;
    }

    public void setOwnerType(String ownerType) {
        this.ownerType = ownerType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}
