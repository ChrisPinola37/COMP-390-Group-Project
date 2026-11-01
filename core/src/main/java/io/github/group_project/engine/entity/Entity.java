package io.github.group_project.engine.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import io.github.group_project.engine.Engine;

//Parent class for every entity in the game
public class Entity {
    public String name = ""; //String used to identify a subclass

    public Texture sprite; //Its current sprite

    public Rectangle hitbox;
    
    public boolean destroyed = false; //Whether the entity is to be destroyed on the next cleanup cycle

    public Vector2 position = new Vector2(); //A vector containing the entity's x and y position

    private List<Entity> colliding = new ArrayList<Entity>(); //If a collision check is done, all colliding entities will be stored here

    public String getName() //Used to compare queryEntity with entityName for unique entity collision
    { 
        return this.name;
    }

    public void destroy()
    {
        this.destroyed = true;
    }
    public Entity()
    {
       hitbox = new Rectangle();
       SetSprite("libgdx.png");
    }
    public void Update() //Runs every frame
    {
        colliding.clear();
        hitbox.setPosition(position.x, position.y);
    }

    public void Draw() //Draw entity's sprite to screen
    {
        Engine.spriteBatch.draw(sprite, position.x, position.y);
        if (Engine.debug)
        {
            Engine.shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
            Engine.shapeRenderer.setColor(Color.RED);
            Engine.shapeRenderer.rect(hitbox.x, hitbox.y, hitbox.width, hitbox.height);
            Engine.shapeRenderer.end();
        }
    }

    public void SetSprite(String spriteName) //Changes the sprite based on the string provided
    {
        sprite = new Texture(spriteName); //I don't think this is correct but it works.
        SetCollision(sprite.getWidth(), sprite.getHeight());
    }

    public void SetCollision(float width, float height)
    {
        hitbox.width = width;
        hitbox.height = height;
    }

    public Boolean isCollidingWithEntityTypeAtPoint(float checkX, float checkY,String entityName)
    {
        return IsCollidingWithEntityType(new Rectangle(hitbox.x + checkX, hitbox.y + checkY, hitbox.width, hitbox.height),entityName);
    }

    public Boolean IsCollidingWithEntityType(String entityName)
    {
        return IsCollidingWithEntityType(hitbox,entityName);
    }
    public Boolean IsCollidingWithEntityType(Rectangle hitbox, String entityName) //Loops through all entities to find valid collision
    {
        Boolean collided = false;
        for (int i = 0; i < Engine.entities.size(); i++)
        {
            Entity queryEntity = Engine.entities.get(i);
            if (queryEntity == this)
            {
                continue;
            }
            if (doEntitiesCollide(hitbox, queryEntity))
            {
                if (queryEntity.getName().equals(entityName)) {
                colliding.add(queryEntity);
                collided = true;
                }
            }
        }
        
        return collided;
    }

    private Boolean doEntitiesCollide(Rectangle queryHitbox, Entity entity)
    {
        return queryHitbox.overlaps(entity.hitbox);
    }

    private Boolean doEntitiesCollide(Entity entity)
    { 
        return doEntitiesCollide(hitbox, entity);
    }
}
