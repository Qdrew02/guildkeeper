package com.guildkeeper.backend.dao.character;

import com.guildkeeper.backend.model.character.PlayerStats;
import com.guildkeeper.backend.util.exception.DaoException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import java.util.ArrayList;
import java.util.List;

public class JdbcPlayerStatsDao implements PlayerStatsDao{
    private final JdbcTemplate jdbcTemplate;
    private final String BASE_SQL = "SELECT character_stat_id, character_id, stat_id, current_value, max_value " +
            "FROM player_stats ";
    public JdbcPlayerStatsDao (JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<PlayerStats> getVisibleStats(int characterId) {
        List<PlayerStats> stats = new ArrayList<>();
        String sql = "SELECT ps.character_stat_id, ps.character_id, ps.stat_id, ps.current_value, ps.max_value, " +
                "sd.name, sd.type, sd.category, sd.sort_order " +
                "FROM player_stats ps " +
                "JOIN stat_definition sd ON ps.stat_id = sd.stat_id " +
                "WHERE ps.character_id = ? AND sd.is_private = FALSE " +
                "ORDER BY sd.sort_order, sd.name ";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, characterId);
            while (results.next()) {
                stats.add(mapRowSetToPlayerStats(results));
            }
        }catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Could not connect to Database");
        }
        return stats;
    }

    @Override
    public List<PlayerStats> getStatsForCharacter(int characterId) {
        String sql = "SELECT ps.character_stat_id, ps.character_id, ps.stat_id, ps.current_value, ps.max_value, " +
                "sd.name, sd.type, sd.category, sd.sort_order, sd.is_private " +
                "FROM player_stats ps " +
                "JOIN stat_definition sd ON ps.stat_id = sd.stat_id " +
                "WHERE ps.character_id = ? " +
                "ORDER BY sd.sort_order, sd.name";
        List<PlayerStats> stats = new ArrayList<>();

        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, characterId);
            while (results.next()) {
                stats.add(mapRowSetToPlayerStats(results));
            }
        }catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Could not connect to Database");
        }
        return stats;
    }

    @Override
    public void updateStatValue(int statId, int currentValue, int maxValue) {
        String sql = "UPDATE player_stats SET current_value = ?, max_value = ? WHERE stat_id = ?";
        int rowsUpdated = jdbcTemplate.update(sql, currentValue, maxValue, statId);

        if (rowsUpdated == 0) {
            throw new DaoException("No stat found with id " + statId);
        }
    }


    private PlayerStats mapRowSetToPlayerStats(SqlRowSet results) {
        PlayerStats stats = new PlayerStats();
        stats.setCharacterId(results.getInt("character_id"));
        stats.setCharacterStatId(results.getInt("character_stat_id"));
        stats.setStatId(results.getInt("stat_id"));
        stats.setMaxValue(results.getInt("max_value"));
        stats.setCurrentValue(results.getInt("current_value"));

        return stats;
    }
}
