package com.guildkeeper.backend.dao.campaign;

import com.guildkeeper.backend.model.campaign.Campaign;

import java.util.List;

public interface CampaignDao {
    Campaign createCampaign(Campaign campaign);
    Campaign getCampaignById(int campaignId);
    List<Campaign> getCampaignsByUser(int userId);
    boolean deleteCampaign(int campaignId);
}
