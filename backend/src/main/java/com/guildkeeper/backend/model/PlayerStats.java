package com.guildkeeper.backend.model;

public class PlayerStats {

    private int characterStatId;
    private int characterId;
    private int statId;
    private int currentValue;
    private int maxValue;

    public PlayerStats() {
    }

    public PlayerStats(int characterStatId, int characterId, int statId, int currentValue, int maxValue) {
        this.characterStatId = characterStatId;
        this.characterId = characterId;
        this.statId = statId;
        this.currentValue = currentValue;
        this.maxValue = maxValue;
    }

    //getters and setter

    public int getCharacterStatId() {
        return characterStatId;
    }

    public void setCharacterStatId(int characterStatId) {
        this.characterStatId = characterStatId;
    }

    public int getCharacterId() {
        return characterId;
    }

    public void setCharacterId(int characterId) {
        this.characterId = characterId;
    }

    public int getStatId() {
        return statId;
    }

    public void setStatId(int statId) {
        this.statId = statId;
    }

    public int getCurrentValue() {
        return currentValue;
    }

    public void setCurrentValue(int currentValue) {
        this.currentValue = currentValue;
    }

    public int getMaxValue() {
        return maxValue;
    }

    public void setMaxValue(int maxValue) {
        this.maxValue = maxValue;
    }
}
