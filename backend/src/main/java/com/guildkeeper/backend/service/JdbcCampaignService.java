package com.guildkeeper.backend.service;

import com.guildkeeper.backend.model.campaign.Campaign;
import org.springframework.stereotype.Service;

@Service
public class JdbcCampaignService implements CampaignService {
    @Override
    public Campaign createCampaign(Campaign campaign, int creatorUserInt) {
        return null;
    }

    @Override
    public void deleteCampaign(int campaignId, int requestingUserId) {

    }

    @Override
    public Campaign getCampaignById(int campaignId, int requestingUserId) {
        return null;
    }
}
