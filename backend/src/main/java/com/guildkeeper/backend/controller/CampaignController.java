package com.guildkeeper.backend.controller;

import com.guildkeeper.backend.model.campaign.Campaign;
import com.guildkeeper.backend.service.CampaignService;
import com.guildkeeper.backend.util.auth.AuthUtil;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/campaign")
@PreAuthorize("isAuthenticated")
@CrossOrigin
public class CampaignController {
    private final CampaignService campaignService;
    private final AuthUtil authUtil;

    public CampaignController(CampaignService campaignService, AuthUtil authUtil) {
        this.campaignService = campaignService;
        this.authUtil = authUtil;
    }

}
