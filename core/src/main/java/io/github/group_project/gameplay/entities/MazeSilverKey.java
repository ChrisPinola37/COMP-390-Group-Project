package io.github.group_project.gameplay.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.math.Vector2;

import io.github.group_project.engine.Engine;
import io.github.group_project.engine.entity.Entity;
import io.github.group_project.gameplay.AudioManager;
import io.github.group_project.gameplay.entities.MazeGoal;
import io.github.group_project.gameplay.LeaderboardManager;

public class MazeSilverKey extends Entity {

    public MazeSilverKey()
    {
        super();
        name = "Silver Key";
        SetSprite("silver_key.png");
        hitbox.setSize(sprite.getWidth(),sprite.getHeight());
    }

    @Override 
    public void Update()
    {
        if (IsCollidingWithEntityType(hitbox,"Player"))
        {
            LeaderboardManager.addPoints(50);
           
            //Sound pickup = Gdx.audio.newSound(Gdx.files.internal("explosion.mp3"));
            //pickup.play(0.1f);
            AudioManager.instance.playSound("pickup", 0.1f);

        
            int silverID = Engine.getFirstEntityIDOfType("Silver Block");
            while (silverID > -1) {
                MazeSilverBlock silverBlockEntity = (MazeSilverBlock) Engine.entities.get(silverID);
                silverBlockEntity.destroyed = true;
                Engine.clean();
                silverID = Engine.getFirstEntityIDOfType("Silver Block");
            }
            destroyed = true;
            
        }
        super.Update();
    }
}
