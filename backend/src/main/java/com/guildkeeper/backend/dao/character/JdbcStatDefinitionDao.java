package com.guildkeeper.backend.dao.character;

import com.guildkeeper.backend.model.character.StatDefinition;
import com.guildkeeper.backend.util.exception.DaoException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import java.util.ArrayList;
import java.util.List;

public class JdbcStatDefinitionDao implements StatDefinitionDao{

    private final JdbcTemplate jdbcTemplate;

    public JdbcStatDefinitionDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public StatDefinition createStatDefinition(StatDefinition statDef) {
        String sql = "INSERT INTO stat_definition " +
                "(campaign_id, name, type, max_value, sort_order, is_private, category) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?) " +
                "RETURNING stat_id";

        try {
            int newId = jdbcTemplate.queryForObject(sql, int.class, statDef.getCampaign_id(), statDef.getName(), statDef.getType(), statDef.getMaxValue(),
                    statDef.getSortOrder(), statDef.isPrivate(), statDef.getCategory());
            return getStatDefinitionById(newId);
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Error connecting to the database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data Integrity Violation", e);
        }
    }

    @Override
    public List<StatDefinition> getStatsByCampaign(int campaignId) {
        List<StatDefinition> statDef = new ArrayList<>();
        String sql = "SELECT stat_id, campaign_id, name, type, max_value, sort_order, is_private, category " +
                "FROM stat_definition " +
                "WHERE campaign_id = ? " +
                "ORDER BY sort_order, name";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, campaignId);
            while(results.next()) {
                statDef.add(mapRowSetToDefinition(results));
            }
        }catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Could not connect to Database");
        }
        return statDef;
    }

    @Override
    public StatDefinition getStatDefinitionById(int statId) {
        return null;
    }

    @Override
    public void updateStatDefinition(StatDefinition statDef) {

    }

    @Override
    public void deleteStatDefinition(int statId) {

    }

    private StatDefinition mapRowSetToDefinition(SqlRowSet results) {
        StatDefinition statDef = new StatDefinition();
        statDef.setCampaign_id(results.getInt("campaign_id"));
        statDef.setStat_id(results.getInt("stat_id"));
        statDef.setName(results.getString("name"));
        statDef.setType(results.getString("type"));
        statDef.setCategory(results.getNString("category"));
        statDef.setMaxValue(results.getInt("max_value"));
        statDef.setPrivate(results.getBoolean("is_private"));
        statDef.setSortOrder(results.getInt("sort_order"));
        return statDef;
    }

}
