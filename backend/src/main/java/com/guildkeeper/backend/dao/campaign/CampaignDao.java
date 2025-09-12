package com.guildkeeper.backend.dao.campaign;

import com.guildkeeper.backend.model.campaign.Campaign;

import java.util.List;

public interface CampaignDao {
    Campaign createCampaign(Campaign campaign, int userId);

    Campaign getCampaignById(int campaignId);
    List<Campaign> getCampaignsByUser(int userId);
    void deleteCampaign(int campaignId);
}
