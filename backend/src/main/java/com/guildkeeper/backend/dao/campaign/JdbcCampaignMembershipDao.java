package com.guildkeeper.backend.dao.campaign;

import com.guildkeeper.backend.dao.users.UserDao;
import com.guildkeeper.backend.model.campaign.CampaignMembership;
import com.guildkeeper.backend.model.user.User;
import com.guildkeeper.backend.util.exception.DaoException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcCampaignMembershipDao implements CampaignMembershipDao {

    private final JdbcTemplate jdbcTemplate;
    private final UserDao userDao;

    public JdbcCampaignMembershipDao(JdbcTemplate jdbcTemplate, UserDao userDao) {
        this.jdbcTemplate = jdbcTemplate;
        this.userDao = userDao;
    }

    @Override
    public CampaignMembership addMember(int campaignId, int userId, String role) {
        String sql = "INSERT INTO campaign_membership (user_id, campaign_id, role) " +
                "VALUES (?, ?, ?) RETURNING membership_id";
        try {
            int newId = jdbcTemplate.queryForObject(sql, int.class, userId, campaignId, role);

            return getMemberById(newId, campaignId);
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Error connecting to the database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data Integrity Violation", e);
        }
    }

    @Override
    public void removeMember(int campaignId, int userId) {
        String sql = "DELETE FROM campaign_membership WHERE user_id = ? AND campaign_id = ?";
        try {
            int rowsDeleted = jdbcTemplate.update(sql, userId, campaignId);
            if (rowsDeleted == 0) {
                throw new DaoException("No task deleted. ID may not exist.");
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Error connecting to the database", e);
        }
    }

    @Override
    public List<User> getMembers(int campaignId) {
        List<User> users = new ArrayList<>();
        String sql = "SELECT user_id FROM campaign_membership WHERE campaign_id = ?";

        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, campaignId);
            while (results.next()) {
                int userId = results.getInt("user_id");
                users.add(userDao.getUserById(userId));
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Could not connect to Database", e);
        }
        return users;
    }


    @Override
    public String getUserRole(int campaignId, int userId) {
        String role = null;
        String sql = "SELECT role FROM campaign_membership WHERE user_id = ? AND campaign_id = ?";

        try {
            SqlRowSet result = jdbcTemplate.queryForRowSet(sql, userId, campaignId);
            if (result.next()) {
                role = result.getString("role");
            }
        }catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Could not connect to Database");
        }

        return role;
    }

    public CampaignMembership getMemberById(int membershipId, int campaignId) {
        String sql = "SELECT membership_id, user_id, campaign_id, role " +
                "FROM campaign_membership WHERE membership_id = ? AND campaign_id = ?";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, membershipId, campaignId);
            if (results.next()) {
                return mapRowSetToCampaignMembership(results);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Could not connect to Database", e);
        }
        return null;
    }

    private CampaignMembership mapRowSetToCampaignMembership(SqlRowSet results) {
        CampaignMembership membership = new CampaignMembership();
        membership.setCampaignId(results.getInt("campaign_id"));
        membership.setMembershipId(results.getInt("membership_id"));
        membership.setRole(results.getString("role"));
        membership.setUserId(results.getInt("user_id"));

        return membership;
    }
}
