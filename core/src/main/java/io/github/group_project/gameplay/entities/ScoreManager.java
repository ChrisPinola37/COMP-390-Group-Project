package io.github.group_project.gameplay.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

import io.github.group_project.engine.Engine;
import io.github.group_project.engine.entity.Entity;
import io.github.group_project.gameplay.LeaderboardManager;

public class ScoreManager extends Entity{
    private boolean visible = false;
    private int playerID = 0;
    private String playerName = "";
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
        if (Gdx.input.isKeyJustPressed(Input.Keys.L)) {
            visible = !visible;
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.U)) {
            LeaderboardManager.saveScore(playerName, playerID);
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

            java.util.ArrayList<String> scores = LeaderboardManager.getScores();

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
}
