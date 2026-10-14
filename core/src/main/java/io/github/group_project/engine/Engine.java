package io.github.group_project.engine;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import io.github.group_project.engine.entity.Entity;

//The Engine class containts everything that will need to be saved globally
public class Engine {
    public static List<Entity> entities = new ArrayList<Entity>(); //All active entities
    public static SpriteBatch spriteBatch = new SpriteBatch(); //Rendering thing

    public Engine()
    {
    }

    public static <T> T spawnEntity(Class<T> entity) //Creates an entity and adds them to the list of active entities
    {
        try {
            T newEntity = entity.getDeclaredConstructor().newInstance();
            entities.add((Entity)newEntity);
            return newEntity;
        } catch (NoSuchMethodException e) {
            throw new RuntimeException("No parameterless constructor found for " + entity.getName(), e);
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException("Failed to instantiate " + entity.getName(), e);
        }

    }
}
