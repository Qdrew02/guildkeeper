package com.guildkeeper.backend.model.campaign;

import java.time.LocalDateTime;

public class Campaign {

    private int campaignId;
    private String name;
    private String description;
    private int creatorId;
    private LocalDateTime createdAt;

    public Campaign() {}

    public Campaign(int campaignId, String name, String description, int creatorId, LocalDateTime createdAt) {
        this.campaignId = campaignId;
        this.name = name;
        this.description = description;
        this.creatorId = creatorId;
        this.createdAt = createdAt;
    }

    // Getters and Setters
    public int getCampaignId() { return campaignId; }
    public void setCampaignId(int campaignId) { this.campaignId = campaignId; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public int getCreatorId() { return creatorId; }
    public void setCreatorId(int creatorId) { this.creatorId = creatorId; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}
