package com.guildkeeper.backend.model;

import java.time.LocalDateTime;

public class Quest {
    private int questId;
    private int campaignId;
    private int creatorId;
    private String questGiver;
    private String title;
    private String description;
    private String status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Quest() {
    }

    public Quest(int questId, int campaignId, int creatorId, String questGiver, String title, String description, String status, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.questId = questId;
        this.campaignId = campaignId;
        this.creatorId = creatorId;
        this.questGiver = questGiver;
        this.title = title;
        this.description = description;
        this.status = status;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    //getters and setters

    public int getQuestId() {
        return questId;
    }

    public void setQuestId(int questId) {
        this.questId = questId;
    }

    public int getCampaignId() {
        return campaignId;
    }

    public void setCampaignId(int campaignId) {
        this.campaignId = campaignId;
    }

    public int getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(int creatorId) {
        this.creatorId = creatorId;
    }

    public String getQuestGiver() {
        return questGiver;
    }

    public void setQuestGiver(String questGiver) {
        this.questGiver = questGiver;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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
