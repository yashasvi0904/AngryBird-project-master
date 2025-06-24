package angry.birds.screen;

import angry.birds.Main;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.ScreenUtils;

public class menu implements Screen {

    Main game;
    Texture background;
    Texture play_on;
    Texture play_off;
    Texture exit_on;
    Texture exit_off;
    Texture levels_on;
    Texture levels_off;
    Texture textImage;
    private float drawWidth, drawHeight;
    private float xOffset, yOffset;

    private float buttonWidth, buttonHeight;
    private float playButtonY, exitButtonY, levelsButtonY;

    public menu(Main game) {
        this.game = game;
        background = new Texture("background.jpg");
        play_on = new Texture("play_on.png");
        play_off = new Texture("play_off.png");
        exit_on = new Texture("exit_on.png");
        exit_off = new Texture("exit_off.png");
        levels_on = new Texture("levels_on.png");
        levels_off = new Texture("levels_off.png");
        textImage = new Texture("text.png");
    }

    @Override
    public void show() {
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0.15f, 0.15f, 0.2f, 1f);
        game.batch.begin();

        game.batch.draw(background, xOffset, yOffset, drawWidth, drawHeight);


        float textImageWidth = drawWidth * 0.55f;
        float textImageHeight = drawHeight * 0.40f;

        game.batch.draw(textImage, xOffset + (drawWidth - textImageWidth) / 2,
            yOffset + drawHeight * 0.65f, textImageWidth, textImageHeight);

        game.batch.setColor(1, 1, 1, 1);
        int screenWidth = Gdx.graphics.getWidth();
        int screenHeight = Gdx.graphics.getHeight();
        int x_center = (screenWidth - (int) buttonWidth) / 2;

        if (Gdx.input.getX() < x_center + buttonWidth && Gdx.input.getX() > x_center &&
            screenHeight - Gdx.input.getY() < playButtonY + buttonHeight &&
            screenHeight - Gdx.input.getY() > playButtonY) {
            game.batch.draw(play_on, x_center, playButtonY, buttonWidth, buttonHeight);
            if (Gdx.input.isTouched()) {
                game.setScreen(new Gameplay(game));
            }
        } else {
            game.batch.draw(play_off, x_center, playButtonY, buttonWidth, buttonHeight);
        }

        if (Gdx.input.getX() < x_center + buttonWidth && Gdx.input.getX() > x_center &&
            screenHeight - Gdx.input.getY() < levelsButtonY + buttonHeight &&
            screenHeight - Gdx.input.getY() > levelsButtonY) {
            game.batch.draw(levels_on, x_center, levelsButtonY, buttonWidth, buttonHeight);
            if (Gdx.input.isTouched()) {
                game.setScreen(new Level_screen(game));
            }
        } else {
            game.batch.draw(levels_off, x_center, levelsButtonY, buttonWidth, buttonHeight);
        }

        if (Gdx.input.getX() < x_center + buttonWidth && Gdx.input.getX() > x_center &&
            screenHeight - Gdx.input.getY() < exitButtonY + buttonHeight &&
            screenHeight - Gdx.input.getY() > exitButtonY) {
            game.batch.draw(exit_on, x_center, exitButtonY, buttonWidth, buttonHeight);
            if (Gdx.input.isTouched()) {
                Gdx.app.exit();
            }
        } else {
            game.batch.draw(exit_off, x_center, exitButtonY, buttonWidth, buttonHeight);
        }

        game.batch.end();
    }

    @Override
    public void resize(int width, int height) {
        float aspectRatio = (float) background.getWidth() / (float) background.getHeight();
        if (width / (float) height > aspectRatio) {
            drawWidth = width;
            drawHeight = width / aspectRatio;
        } else {
            drawHeight = height;
            drawWidth = height * aspectRatio;
        }
        xOffset = (width - drawWidth) / 2;
        yOffset = (height - drawHeight) / 2;

        buttonWidth = width * 0.20f;
        buttonHeight = height * 0.11f;

        playButtonY = height * 0.60f;
        levelsButtonY = height * 0.45f;
        exitButtonY = height * 0.1f;
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void hide() {
    }

    @Override
    public void dispose() {
        background.dispose();
        play_on.dispose();
        play_off.dispose();
        exit_on.dispose();
        exit_off.dispose();
        levels_on.dispose();
        levels_off.dispose();
        textImage.dispose();
    }
}
