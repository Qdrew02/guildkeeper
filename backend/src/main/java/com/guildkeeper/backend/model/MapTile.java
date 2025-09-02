package com.guildkeeper.backend.model;

public class MapTile {
    private int mapTileId;
    private int mapId;
    private int q;
    private int r;
    private boolean revealed;
    private String region;
    private String factionControl;

    public MapTile(int mapTileId, int mapId, int q, int r, boolean revealed, String region, String factionControl) {
        this.mapTileId = mapTileId;
        this.mapId = mapId;
        this.q = q;
        this.r = r;
        this.revealed = revealed;
        this.region = region;
        this.factionControl = factionControl;
    }

    public MapTile() {
    }

    //getters and setters

    public int getMapTileId() {
        return mapTileId;
    }

    public void setMapTileId(int mapTileId) {
        this.mapTileId = mapTileId;
    }

    public int getMapId() {
        return mapId;
    }

    public void setMapId(int mapId) {
        this.mapId = mapId;
    }

    public int getQ() {
        return q;
    }

    public void setQ(int q) {
        this.q = q;
    }

    public int getR() {
        return r;
    }

    public void setR(int r) {
        this.r = r;
    }

    public boolean isRevealed() {
        return revealed;
    }

    public void setRevealed(boolean revealed) {
        this.revealed = revealed;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getFactionControl() {
        return factionControl;
    }

    public void setFactionControl(String factionControl) {
        this.factionControl = factionControl;
    }
}
