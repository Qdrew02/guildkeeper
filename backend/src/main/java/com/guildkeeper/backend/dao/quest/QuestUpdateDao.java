package com.guildkeeper.backend.dao.quest;

import com.guildkeeper.backend.model.quest.QuestUpdate;

import java.util.List;

public interface QuestUpdateDao {
    List<QuestUpdate> getUpdatesByQuest(int questId);
    QuestUpdate addUpdate (QuestUpdate update);
    void deleteUpdate();

}
