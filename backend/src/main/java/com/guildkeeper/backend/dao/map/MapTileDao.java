package com.guildkeeper.backend.dao.map;

import com.guildkeeper.backend.model.map.MapTile;

import java.util.List;

public interface MapTileDao {
    List<MapTile> getMapsByCampaign(int campaignId);
    void revealTile (int tileId);
    void hideTile(int tileId);
    void updateTile(MapTile tile);
}
