package com.guildkeeper.backend.dao.character;

import com.guildkeeper.backend.model.character.PlayerCharacter;
import com.guildkeeper.backend.util.exception.DaoException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.security.core.parameters.P;

import java.util.ArrayList;
import java.util.List;

public class JdbcPlayerCharacterDao implements PlayerCharacterDao {
    private final JdbcTemplate jdbcTemplate;
    private final String BASE_SQL = "SELECT character_id, campaign_id, user_id, role, name, level FROM player_character";
    public JdbcPlayerCharacterDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public PlayerCharacter createCharacter(PlayerCharacter character) {
        String sql = "INSERT INTO player_character (campaign_id, user_id, role, name, level) " +
                "VALUES (?, ?, ?, ?, ?) RETURNING character_id";
        try {
            int newId = jdbcTemplate.queryForObject(sql, int.class, character.getCampaignId(), character.getUserId(), character.getRole(), character.getName(), character.getLevel());
            return getCharacterById(newId);
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Error connecting to the database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data Integrity Violation", e);
        }
    }

    @Override
    public PlayerCharacter getCharacterById(int characterId) {
        PlayerCharacter character = null;
        String sql = BASE_SQL + "WHERE character_id = ?";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(BASE_SQL, characterId);
            if (results.next()) {
                character = mapRowSetToCharacter(results);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Could not connect to Database");
        }

        return character;
    }

    @Override
    public List<PlayerCharacter> getCharactersByCampaign(int campaignId) {
        List<PlayerCharacter> characters = new ArrayList<>();
        String sql = BASE_SQL + "WHERE campaign_id = ?";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(BASE_SQL, campaignId);
            while (results.next()) {
                characters.add(mapRowSetToCharacter(results));
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Could not connect to Database");
        }

        return characters;
    }

    @Override
    public List<PlayerCharacter> getCharactersByUser(int userId) {
        List<PlayerCharacter> characters = new ArrayList<>();
        String sql = BASE_SQL + "WHERE user_id = ?";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(BASE_SQL, userId);
            while (results.next()) {
                characters.add(mapRowSetToCharacter(results));
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Could not connect to Database");
        }

        return characters;
    }

    @Override
    public void updateCharacter(PlayerCharacter character) {

    }

    @Override
    public void deleteCharacter(int characterId) {

    }

    private PlayerCharacter mapRowSetToCharacter(SqlRowSet results) {
        PlayerCharacter character = new PlayerCharacter();
        character.setCharacterId(results.getInt("character_id"));
        character.setCampaignId(results.getInt("campaign_id"));
        character.setName(results.getString("name"));
        character.setLevel(results.getInt("level"));
        character.setUserId(results.getInt("user_id"));
        return character;
    }
}
