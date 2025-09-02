package com.guildkeeper.backend.model;

import java.time.LocalDateTime;

public class QuestUpdate {
    private int update_id;
    private int quest_id;
    private int user_id;
    private String content;
    private LocalDateTime updatedAt;

    public QuestUpdate() {
    }

    public QuestUpdate(int update_id, int quest_id, int user_id, String content, LocalDateTime updatedAt) {
        this.update_id = update_id;
        this.quest_id = quest_id;
        this.user_id = user_id;
        this.content = content;
        this.updatedAt = updatedAt;
    }
    //getters and setters

    public int getUpdate_id() {
        return update_id;
    }

    public void setUpdate_id(int update_id) {
        this.update_id = update_id;
    }

    public int getQuest_id() {
        return quest_id;
    }

    public void setQuest_id(int quest_id) {
        this.quest_id = quest_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}
