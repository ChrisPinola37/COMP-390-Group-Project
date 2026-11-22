package io.github.group_project;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.Texture;
import io.github.group_project.gameplay.AudioManager;

public class TitleScreen {
    public static final int NONE = 0;
    public static final int PLAY = 1;
    public static final int QUIT = 2;

    private String title = "KEY MAZE"; 
    private String[] options = {"Play", "Quit"};
    private int selected = 0;

    private BitmapFont titleFont;
    private BitmapFont menuFont;
    private BitmapFont hintFont;
    private BitmapFont audFont;
    private GlyphLayout layout = new GlyphLayout();

    private String subtitle = "A game made by Elijah, Jamilson, Jeremias, and Chris";
    private BitmapFont subtitleFont;

    private Texture player;
    private Texture enemy;

    public TitleScreen() {
        titleFont = new BitmapFont();
        titleFont.getData().setScale(3f);

        menuFont = new BitmapFont();
        menuFont.getData().setScale(1.5f);

        hintFont = new BitmapFont();
        audFont = new BitmapFont();

        subtitleFont = new BitmapFont();
        subtitleFont.getData().setScale(1.25f);
        subtitleFont.setColor(Color.LIGHT_GRAY);

        player = new Texture("player_right_0.png");
        enemy = new Texture("enemy.png");
    }
    //player inputs
    public int update() {
        if (Gdx.input.isKeyJustPressed(Input.Keys.UP)) {
            selected--;
            if (selected < 0) selected = options.length - 1;
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.DOWN)) {
            selected++;
            if (selected >= options.length) selected = 0;
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.ENTER)) {
            if (selected == 0){
                AudioManager.instance.playSound("star", 0.2f);
                return PLAY;
            } 
            if (selected == 1) return QUIT;
        }
        return NONE;
    }
    //draws text and images to the screen
    public void draw(SpriteBatch batch) {
        float width = Gdx.graphics.getWidth();
        float height = Gdx.graphics.getHeight();

        layout.setText(titleFont, title);
        float titleX = (width - layout.width) / 2;
        float titleY = height * 0.7f;
        float titleWidth = layout.width;
        float titleHeight = layout.height;
        titleFont.draw(batch, layout, titleX, titleY);

        float imageSize = 64; 
        float gap = 20;      
        float imageY = titleY - titleHeight / 2 - imageSize / 2; 

        batch.draw(player, titleX - gap - imageSize, imageY, imageSize, imageSize);  
        batch.draw(enemy, titleX + titleWidth + gap, imageY, imageSize, imageSize);   

        float subtitleY = titleY - titleHeight - 20;
        layout.setText(subtitleFont, subtitle);
        subtitleFont.draw(batch, layout, (width - layout.width) / 2, subtitleY);

        //menu options 
        for (int i = 0; i < options.length; i++) {
            String text = options[i];
            if (i == selected) {
                menuFont.setColor(Color.YELLOW);
                text = "> " + text + " <";
            } else {
                menuFont.setColor(Color.WHITE);
            }
            layout.setText(menuFont, text);
            menuFont.draw(batch, layout, (width - layout.width) / 2, height * 0.45f - i * 40);
        }
        layout.setText(audFont, "Use - and + to control volume.");
        audFont.draw(batch, layout, (width - layout.width) / 2, 20);
        //controls and game info at bottom of screen
        layout.setText(hintFont, "Arrow Keys to Move, Hold SHIFT to Sprint. Collect the gold key, and return back to the Star!");
        hintFont.draw(batch, layout, (width - layout.width) / 2, 40);
    }
    
    public void dispose() {
        titleFont.dispose();
        menuFont.dispose();
        hintFont.dispose();
        audFont.dispose();
        subtitleFont.dispose();
        enemy.dispose();
        player.dispose();
    }
}