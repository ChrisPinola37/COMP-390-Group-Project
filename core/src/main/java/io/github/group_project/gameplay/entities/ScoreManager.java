package io.github.group_project.gameplay.entities;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

import io.github.group_project.engine.Engine;
import io.github.group_project.engine.entity.Entity;
import io.github.group_project.gameplay.LeaderboardManager;

public class ScoreManager extends Entity{
    private boolean visible = false;
    private int playerID = 0;
    private String playerName = "";

    public List<String> scores = new ArrayList<String>();
    public ScoreManager()
    {

    }

    public void setup(int playerID, String playerName)
    {
        this.playerID = playerID;
        this.playerName = playerName;
    }

    @Override 
    public void Update()
    {
        if (Engine.typingEnabled == false)
        {
            if (Gdx.input.isKeyJustPressed(Input.Keys.L)) {
                enableLeaderboard();
            }
            if (Gdx.input.isKeyJustPressed(Input.Keys.U)) {
                LeaderboardManager.addPoints(Engine.random.nextInt(500));
                LeaderboardManager.saveScore();
            }
        }
    }

    @Override 
    public void DrawEnd()
    {
        Engine.leaderboardFont.draw(
            Engine.spriteBatch,
            "Score: " + LeaderboardManager.getCurrentScore(),
            20,
            Gdx.graphics.getHeight() - 20
        );
        if (visible)
        {
             Engine.leaderboardFont.draw(Engine.spriteBatch, "LEADERBOARD", 50, 420);

            if (scores.isEmpty()) {
                Engine.leaderboardFont.draw(Engine.spriteBatch, "No scores saved yet", 50, 390);
            }

            for (int i = 0; i < Math.min(5, scores.size()); i++) {
                Engine.leaderboardFont.draw(
                    Engine.spriteBatch,
                    (i + 1) + ". " + scores.get(i),
                    50,
                    390 - (i * 25)
                );
            }
        }
    }


    public void enableLeaderboard()
    {
        enableLeaderboard(!visible);
    }
    public void enableLeaderboard(Boolean toggle)
    {
        visible = toggle;
        if (toggle == true)
        {
            String sql = "SELECT player_score.score, players.player_name\nFROM players\nJOIN player_score ON players.player_id = player_score.player_id\nORDER BY score DESC";
           // String sql = "SELECT * FROM player_score\nORDER BY score DESC";
            try (Connection c = Engine.db.getConnection(); PreparedStatement ps = c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                ResultSet rs = ps.executeQuery();
                while (rs.next())
                {
                    scores.add(rs.getString("player_name")+ ": " + rs.getInt("score"));
                }
            } 
            catch (SQLException s)
            {
                System.err.println(s.getNextException());
            };
        }
    }
}
