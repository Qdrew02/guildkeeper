package com.guildkeeper.backend.dao.character;

import com.guildkeeper.backend.model.character.PlayerCharacter;

import java.util.List;

public interface PlayerCharacterDao {
    PlayerCharacter createCharacter(PlayerCharacter character);
    PlayerCharacter getCharacterById(int characterId);
    List<PlayerCharacter> getCharactersByCampaign(int campaignId);
    List<PlayerCharacter> getCharactersByUser(int userId);
    void updateCharacter(PlayerCharacter character);
    void deleteCharacter(int characterId);
}
