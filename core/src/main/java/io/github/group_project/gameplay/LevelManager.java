package io.github.group_project.gameplay;

import io.github.group_project.engine.Engine;
import io.github.group_project.gameplay.entities.Victory;

public class LevelManager {
    static public int level = 0;
    static public String[] levelList = {"level1.bin","level2.bin","level3.bin","level4.bin","level5.bin"};

    public static void startLevel(int level)
    {
        LevelManager.level = level;
        Engine.destroyAllEntities();
        MazeGenerator.loadMaze(levelList[level]);
    }

    public static void advanceLevel()
    {
        if (level < levelList.length-1)
        {
            startLevel(level+1);
        }
        else
        {
            Engine.spawnEntity(Victory.class, 200, 200);
        }
    }
}
