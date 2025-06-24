package angry.birds.screen;

import angry.birds.Main;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.ScreenUtils;

public class Level_screen implements Screen {
    Main game;
    Texture levelView;
    Texture level1, level2;
    Texture level1_on, level2_on;
    Texture back_on, back_off;

    private float buttonWidth, buttonHeight;
    private float level1X, level2X, buttonY;
    private float backButtonWidth, backButtonHeight;
    private float backButtonX, backButtonY;

    public Level_screen(Main game) {
        this.game = game;
        levelView = new Texture("level_view.jpg");
        level1 = new Texture("level_1.png");
        level1_on = new Texture("level_1_on.png");
        level2 = new Texture("level_2.png");
        level2_on = new Texture("level_2_on.png");
        back_on = new Texture("back_on.png");
        back_off = new Texture("back_off.png");
    }

    @Override
    public void show() {
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0.15f, 0.15f, 0.2f, 1f);
        game.batch.begin();

        game.batch.draw(levelView, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        int screenWidth = Gdx.graphics.getWidth();
        int screenHeight = Gdx.graphics.getHeight();
        buttonY = screenHeight * 0.45f;

        if (Gdx.input.getX() < level1X + buttonWidth && Gdx.input.getX() > level1X &&
            screenHeight - Gdx.input.getY() < buttonY + buttonHeight &&
            screenHeight - Gdx.input.getY() > buttonY) {
            game.batch.draw(level1_on, level1X, buttonY, buttonWidth, buttonHeight);
            if (Gdx.input.isTouched()) {
                game.setScreen(new Gameplay(game));
            }
        } else {
            game.batch.draw(level1, level1X, buttonY, buttonWidth, buttonHeight);
        }

        if (Gdx.input.getX() < level2X + buttonWidth && Gdx.input.getX() > level2X &&
            screenHeight - Gdx.input.getY() < buttonY + buttonHeight &&
            screenHeight - Gdx.input.getY() > buttonY) {
            game.batch.draw(level2_on, level2X, buttonY, buttonWidth, buttonHeight);
            if (Gdx.input.isTouched()) {
                game.setScreen(new Gameplay2(game));
            }
        } else {
            game.batch.draw(level2, level2X, buttonY, buttonWidth, buttonHeight);
        }

        if (Gdx.input.getX() < backButtonX + backButtonWidth && Gdx.input.getX() > backButtonX &&
            screenHeight - Gdx.input.getY() < backButtonY + backButtonHeight &&
            screenHeight - Gdx.input.getY() > backButtonY) {
            game.batch.draw(back_on, backButtonX, backButtonY, backButtonWidth, backButtonHeight);
            if (Gdx.input.isTouched()) {
                game.setScreen(new menu(game));
            }
        } else {
            game.batch.draw(back_off, backButtonX, backButtonY, backButtonWidth, backButtonHeight);
        }

        game.batch.end();
    }

    @Override
    public void resize(int width, int height) {
        buttonWidth = width * 0.15f;
        buttonHeight = height * 0.25f;

        level1X = width * 0.275f;
        level2X = width * 0.575f;

        backButtonWidth = width * 0.075f;
        backButtonHeight = height * 0.1f;
        backButtonX = width * 0.05f;
        backButtonY = height * 0.05f;
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
        levelView.dispose();
        level1.dispose();
        level1_on.dispose();
        level2.dispose();
        level2_on.dispose();
        back_on.dispose();
        back_off.dispose();
    }
}
