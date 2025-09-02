package com.guildkeeper.backend.dao.quest;

import com.guildkeeper.backend.model.quest.Quest;

import java.util.List;

public interface QuestDao {
    Quest createQuest (Quest quest);
    Quest getQuestById(int questId);
    List<Quest> getQuestsByCampaign(int campaignId);
    void updateQuestStatus(int questId, String status);
    void deleteQuest(int questId);
}
