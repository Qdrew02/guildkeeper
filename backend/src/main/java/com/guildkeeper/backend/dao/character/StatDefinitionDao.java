package com.guildkeeper.backend.dao.character;

import com.guildkeeper.backend.model.character.StatDefinition;

import java.util.List;

public interface StatDefinitionDao {
    StatDefinition createStatDefinition(StatDefinition statDef);
    List<StatDefinition> getStatsByCampaign(int campaignId);
    void updateStatDefinition(StatDefinition statDef);
    void deleteStatDefinition(int statId);
}
