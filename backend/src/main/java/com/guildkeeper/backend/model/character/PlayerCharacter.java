package com.guildkeeper.backend.model.character;

public class PlayerCharacter {

    private int characterId;
    private int campaignId;
    private int userId;
    private String role;
    private String name;
    private int level;

    public PlayerCharacter() {
    }

    public PlayerCharacter(int characterId, int campaignId, int userId, String role, String name, int level) {
        this.characterId = characterId;
        this.campaignId = campaignId;
        this.userId = userId;
        this.role = role;
        this.name = name;
        this.level = level;
    }

    //getters and setters

    public int getCharacterId() {
        return characterId;
    }

    public void setCharacterId(int characterId) {
        this.characterId = characterId;
    }

    public int getCampaignId() {
        return campaignId;
    }

    public void setCampaignId(int campaignId) {
        this.campaignId = campaignId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }
}
