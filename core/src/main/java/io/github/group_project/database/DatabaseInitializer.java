package io.github.group_project.database;

import java.sql.*;

public class DatabaseInitializer {
    public static void initialize(Database database) throws SQLException {
        try (Connection conn = database.getConnection(); Statement st = conn.createStatement()) {
            st.execute("CREATE TABLE IF NOT EXISTS players (player_id INTEGER PRIMARY KEY AUTOINCREMENT, player_name TEXT UNIQUE NOT NULL, password TEXT NOT NULL)");
            st.execute("CREATE TABLE IF NOT EXISTS player_score (player_id INTEGER PRIMARY KEY AUTOINCREMENT, score INTEGER, FOREIGN KEY(player_id) REFERENCES players(player_id))");
            st.execute("CREATE TABLE IF NOT EXISTS normal_leaderboard (player_id INTEGER PRIMARY KEY AUTOINCREMENT, player_name TEXT NOT NULL, score INTEGER, FOREIGN KEY(player_id) REFERENCES player_score(player_id), FOREIGN KEY(player_name) REFERENCES players(player_name), FOREIGN KEY(score) REFERENCES player_score(score))");
        }
    }
}