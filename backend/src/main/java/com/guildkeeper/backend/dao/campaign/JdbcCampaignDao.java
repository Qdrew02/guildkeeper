package com.guildkeeper.backend.dao.campaign;

import com.guildkeeper.backend.model.campaign.Campaign;
import com.guildkeeper.backend.util.exception.DaoException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcCampaignDao implements CampaignDao{

    private final String BASE_SQL =  "SELECT campaign_id, name, description, creator_id, created_at " +
            "FROM campaign";
    private final JdbcTemplate jdbcTemplate;

    public JdbcCampaignDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Campaign createCampaign(Campaign campaign, int userId) {
        String sql = "INSERT INTO campaign ( name, description, creator_id, created_at)" +
                "VALUES ( ?, ?, ?, ?) RETURNING campaign_id";

        try {
            int newId = jdbcTemplate.queryForObject(sql, int.class, campaign.getName(), campaign.getDescription(),
                    campaign.getCreatorId(), campaign.getCreatedAt());
            return getCampaignById(newId);
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Error connecting to the database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data Integrity Violation", e);
        }
    }

    @Override
    public Campaign getCampaignById(int campaignId) {
        Campaign campaign = null;
        String sql = BASE_SQL + "WHERE campaign_id = ?";
        try {
            SqlRowSet results =jdbcTemplate.queryForRowSet(sql, campaignId);
            if (results.next()) {
                campaign = mapRowSetToCampaign(results);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Could not connect to Database");
        }
        return campaign;
    }

    @Override
    public List<Campaign> getCampaignsByUser(int userId) {
        List<Campaign> campaigns = new ArrayList<>();
        String sql = "SELECT c.campaign_id, c.name, c.description, c.creator_id, c.created_at " +
                "FROM campaign c " +
                "JOIN campaign_membership cm ON c.campaign_id = cm.campaign_id " +
                "WHERE cm.user_id = ?";

        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, userId);
            while (results.next()) {
                campaigns.add(mapRowSetToCampaign(results));
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Could not connect to Database");
        }
        return campaigns;
    }
    @Override
    public void deleteCampaign(int campaignId) {
        String sql = "DELETE FROM campaign WHERE campaign_id = ?";
        try {
            int rowsDeleted = jdbcTemplate.update(sql, campaignId);
            if (rowsDeleted == 0) {
                throw new DaoException("No task deleted. ID may not exist.");
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Error connecting to the database", e);
        }
    }


    private Campaign mapRowSetToCampaign(SqlRowSet results) {
        Campaign campaign = new Campaign();
        campaign.setCampaignId(results.getInt("campaign_id"));
        campaign.setCreatedAt(results.getTimestamp("created_at").toLocalDateTime());
        campaign.setCreatorId(results.getInt("creator_id"));
        campaign.setName(results.getString("name"));
        campaign.setDescription(results.getString("description"));

        return campaign;
    }
}


