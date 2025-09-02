package com.guildkeeper.backend.model.character;

public class StatDefinition {
    private int stat_id;
    private int campaign_id;
    private String name;
    private String type;
    private int sortOrder;
    private int maxValue;
    private boolean isPrivate;
    private String category;

    public StatDefinition() {
    }

    public StatDefinition(int stat_id, int campaign_id, String name, String type, int sortOrder, int maxValue, boolean isPrivate, String category) {
        this.stat_id = stat_id;
        this.campaign_id = campaign_id;
        this.name = name;
        this.type = type;
        this.sortOrder = sortOrder;
        this.maxValue = maxValue;
        this.isPrivate = isPrivate;
        this.category = category;
    }
//Getters and Setters
    public int getStat_id() {
        return stat_id;
    }

    public void setStat_id(int stat_id) {
        this.stat_id = stat_id;
    }

    public int getCampaign_id() {
        return campaign_id;
    }

    public void setCampaign_id(int campaign_id) {
        this.campaign_id = campaign_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(int sortOrder) {
        this.sortOrder = sortOrder;
    }

    public int getMaxValue() {
        return maxValue;
    }

    public void setMaxValue(int maxValue) {
        this.maxValue = maxValue;
    }

    public boolean isPrivate() {
        return isPrivate;
    }

    public void setPrivate(boolean aPrivate) {
        isPrivate = aPrivate;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
