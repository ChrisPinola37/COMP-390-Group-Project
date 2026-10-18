package io.github.group_project.gameplay.entities;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector2;

import io.github.group_project.engine.Engine;
import io.github.group_project.engine.entity.Entity;

public class TestBlock extends Entity {

    public TestBlock()
    {
        super();
        name = "Block";
        SetSprite("test_block.png");
    }

    private List<Entity> colliding = new ArrayList<Entity>();

    @Override 
    public void Update()
    { 
         colliding.clear();
        if (Gdx.input.isKeyJustPressed(Input.Keys.B))
        {
            position.add(0, 0);
        }
        super.Update();
    }

        public Boolean IsCollidingWithEntityType(String entityName) //Loops through all entities to find valid collision
    {
        Boolean collided = false;
        for (int i = 0; i < Engine.entities.size(); i++)
        {
            Entity queryEntity = Engine.entities.get(i);
            if (queryEntity == this)
            {
                continue;
            }
            if (doEntitiesCollide(queryEntity))
            {
                colliding.add(queryEntity);
                collided = true;
            }
        }
        
        return collided;
    }

    private Boolean doEntitiesCollide(Entity entity)
    { return
        (this.position.x <= entity.position.x + entity.sprite.getWidth() && this.position.x + this.sprite.getWidth() >= entity.position.x) &&
        (this.position.y <= entity.position.y + entity.sprite.getHeight() && this.position.y + this.sprite.getHeight() >= entity.position.y);
    }
}
