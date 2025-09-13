package com.guildkeeper.backend.dao.character;

import com.guildkeeper.backend.model.character.PlayerStats;

import java.util.List;

public interface PlayerStatsDao {
    List<PlayerStats> getVisibleStats(int characterId);
    List<PlayerStats> getStatsForCharacter(int characterId);

    void updateStatValue(int characterId, int statId, int newValue);
}

