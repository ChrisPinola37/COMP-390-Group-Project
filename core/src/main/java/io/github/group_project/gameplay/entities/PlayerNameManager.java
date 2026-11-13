package io.github.group_project.gameplay.entities;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import io.github.group_project.engine.Engine;
import io.github.group_project.engine.entity.Entity;
import io.github.group_project.gameplay.LeaderboardManager;

public class PlayerNameManager extends Entity {
    public PlayerNameManager()
    {
        super();
        Engine.enableTyping();
    }

    @Override 
    public void Update()
    {
        super.Update();
        if (Engine.typingEnded)
        {
            LeaderboardManager.setPlayerName(Engine.textEntry);

            String playerName = LeaderboardManager.getPlayerName();

            int playerID = 0;

            String sql = "SELECT EXISTS(SELECT 1 FROM players WHERE player_name = ?)";
            Boolean playerExists = false;

            try (Connection c = Engine.db.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
                ps.setString(1, playerName);
                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        playerExists = rs.getBoolean(1);
                    }
                }
            }
            catch (SQLException s)
            {

            }
            
            if (playerExists == true)
            {
                Engine.enableTyping();
                return;
            }

            sql = "INSERT INTO players(player_id,player_name,password) VALUES (?,?,?)";
            try (Connection c = Engine.db.getConnection(); PreparedStatement ps = c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                ps.setString(2, playerName); ps.setString(3, playerName);
                ps.executeUpdate();
                playerID = ps.getGeneratedKeys().getInt(0);
            } 
            catch (SQLException s)
            {
                System.err.println("database error");
            };

            destroyed = true;

            LeaderboardManager.setPlayerID(playerID);
            Engine.spawnEntity(ScoreManager.class, 0, 0);
        }
    }

    @Override 
    public void DrawEnd()
    {
        if (Engine.typingEnabled == true) {
            Engine.leaderboardFont.draw(
                Engine.spriteBatch,
                "Enter Your Name: " + Engine.textEntry,
                250,
                300
            );

            Engine.leaderboardFont.draw(
                Engine.spriteBatch,
                "Press ENTER to start",
                250,
                270
            );
        }
    }
}
