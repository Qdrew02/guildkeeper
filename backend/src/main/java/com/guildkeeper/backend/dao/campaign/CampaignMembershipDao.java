package com.guildkeeper.backend.dao.campaign;

import com.guildkeeper.backend.model.campaign.CampaignMembership;
import com.guildkeeper.backend.model.user.User;

import java.util.List;

public interface CampaignMembershipDao {
    CampaignMembership addMember(int campaignId, int userId, String role);
    void removeMember(int campaignId, int userId);
    List<User> getMembers(int campaignId);
    String getUserRole(int campaignId, int userId);
    CampaignMembership getMemberById(int id, int campaign_id);
}
