
package io.github.group_project.gameplay.entities;

import io.github.group_project.engine.entity.Entity;
import io.github.group_project.engine.Engine;

public class MazeSpeedBoost extends Entity {

    public MazeSpeedBoost() {
        super();
        name = "SpeedBoost";
        SetSprite("star_on.png");
    }

    @Override
    public void Update() {
        if (IsCollidingWithEntityType(hitbox, "Player")) {
            int playerID = Engine.getFirstEntityIDOfType("Player");

            if (playerID >= 0) {
                MazePlayer player = (MazePlayer) Engine.entities.get(playerID);
                player.activateSpeedBoost();
                destroyed = true;
            }
        }

        super.Update();
    }
}
