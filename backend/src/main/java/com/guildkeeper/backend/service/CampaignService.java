package com.guildkeeper.backend.service;

import com.guildkeeper.backend.model.campaign.Campaign;
import org.springframework.stereotype.Service;

public interface CampaignService {
    Campaign createCampaign(Campaign campaign, int creatorUserInt);
    void deleteCampaign(int campaignId, int requestingUserId);
    Campaign getCampaignById(int campaignId, int requestingUserId);
}
