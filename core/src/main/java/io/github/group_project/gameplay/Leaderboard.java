
package io.github.group_project.gameplay;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import java.util.ArrayList;
import java.util.Comparator;

public class Leaderboard {

    private static final String FILE_NAME = "maze_leaderboard";
    private static int currentScore = 0;
    private static String playerName = "Player";

public static void setPlayerName(String name) {
    if (name != null && !name.trim().isEmpty()) {
        playerName = name.trim();
    }
}

public static String getPlayerName() {
    return playerName;
}

public static void addPoints(int points) {
    currentScore += points;
}

public static int getCurrentScore() {
    return currentScore;
}

public static void resetScore() {
    currentScore = 0;
}
public static void clearScores() {
    Preferences prefs = Gdx.app.getPreferences(FILE_NAME);
    prefs.clear();
    prefs.flush();
}
    public static void saveScore(String playerName, int score) {
        Preferences prefs = Gdx.app.getPreferences(FILE_NAME);
        int count = prefs.getInteger("count", 0);

        prefs.putString("name_" + count, playerName);
        prefs.putInteger("score_" + count, score);
        prefs.putInteger("count", count + 1);
        prefs.flush();
    }

    public static ArrayList<String> getScores() {
        Preferences prefs = Gdx.app.getPreferences(FILE_NAME);
        ArrayList<String> scores = new ArrayList<>();

        int count = prefs.getInteger("count", 0);

        for (int i = 0; i < count; i++) {
            String name = prefs.getString("name_" + i, "Player");
            int score = prefs.getInteger("score_" + i, 0);
            scores.add(name + " - " + score);
        }

        scores.sort((a, b) -> {
            int scoreA = Integer.parseInt(a.substring(a.lastIndexOf(" - ") + 3));
            int scoreB = Integer.parseInt(b.substring(b.lastIndexOf(" - ") + 3));
            return Integer.compare(scoreB, scoreA);
        });

        return scores;
    }
}
