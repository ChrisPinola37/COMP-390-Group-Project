
package io.github.group_project.gameplay.entities;

import com.badlogic.gdx.math.Rectangle;
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
        // Set the collectible's collision area
        if (hitbox == null) {
            hitbox = new Rectangle(position.x, position.y, 64, 64);
        } else {
            hitbox.setPosition(position.x, position.y);
            hitbox.setSize(64, 64);
        }

        if (IsCollidingWithEntityType(hitbox, "Player")) {
            int playerID = Engine.getFirstEntityIDOfType("Player");

            if (playerID >= 0) {
                MazePlayer player = (MazePlayer) Engine.entities.get(playerID);
                System.out.println("SPEED BOOST ACTIVATED!"); 
                player.activateSpeedBoost();
                destroyed = true;
            }
        }

        super.Update();
    }
}