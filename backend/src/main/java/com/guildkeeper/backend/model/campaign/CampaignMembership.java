package com.guildkeeper.backend.model.campaign;

public class CampaignMembership {
    private int membershipId;
    private int userId;
    private int campaignId;
    private String role;

    public CampaignMembership() {
    }

    public CampaignMembership(int membershipId, int userId, int campaignId, String role) {
        this.membershipId = membershipId;
        this.userId = userId;
        this.campaignId = campaignId;
        this.role = role;
    }

    //Getters and Setters

    public int getMembershipId() {
        return membershipId;
    }

    public void setMembershipId(int membershipId) {
        this.membershipId = membershipId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getCampaignId() {
        return campaignId;
    }

    public void setCampaignId(int campaignId) {
        this.campaignId = campaignId;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
