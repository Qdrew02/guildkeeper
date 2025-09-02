package com.guildkeeper.backend.dao.character;

import com.guildkeeper.backend.model.character.PlayerStats;

public interface PlayerStatsDao {
    PlayerStats getStatsForCharacter(int characterId);
    void updateStat(int characterId, int statId, int value);
    void updateStatVisibility(int statId, boolean isPrivate);
}
