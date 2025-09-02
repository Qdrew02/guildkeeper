package com.guildkeeper.backend.dao.map;

import com.guildkeeper.backend.model.map.Maps;

import java.util.List;

public interface MapDao {
    Maps createMap(Maps map);
    Maps getMapById(int mapId);
    List<Maps> getMapsByCampaign(int campaignId);
}
