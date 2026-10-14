package io.github.group_project.engine.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

import io.github.group_project.engine.Engine;

//Parent class for every entity in the game
public class Entity {
    public String name = ""; //String used to identify a subclass

    public Texture sprite; //Its current sprite

    public Vector2 position = new Vector2(); //A vector containing the entity's x and y position

    private List<Entity> colliding = new ArrayList<Entity>(); //If a collision check is done, all colliding entities will be stored here

    public Entity()
    {
       SetSprite("libgdx.png");
    }
    public void Update() //Runs every frame
    {
        colliding.clear();
    }

    public void Draw() //Draw entity's sprite to screen
    {
        Engine.spriteBatch.draw(sprite, position.x, position.y);
    }

    public void SetSprite(String spriteName) //Changes the sprite based on the string provided
    {
        sprite = new Texture(spriteName); //I don't think this is correct but it works.
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
