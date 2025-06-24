package angry.birds.screen;

import angry.birds.Main;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class LoseScreen implements Screen {
    private Game game;
    private Texture loseMenu;
    private Texture restartButton;
    private Texture restartButtonOn;
    private Texture mainMenuButton;
    private Texture mainMenuButtonOn;
    private SpriteBatch batch;

    public LoseScreen(Game game) {
        this.game = game;
    }

    @Override
    public void show() {
        loseMenu = new Texture("lose_screen.jpeg");
        restartButton = new Texture("restart.png");
        restartButtonOn = new Texture("restart_on.png");
        mainMenuButton = new Texture("main_menu.png");
        mainMenuButtonOn = new Texture("main_menu_on.png");
        batch = new SpriteBatch();
    }

    @Override
    public void render(float delta) {
        int screenWidth = Gdx.graphics.getWidth();
        int screenHeight = Gdx.graphics.getHeight();

        batch.begin();
        renderLoseScreen(screenWidth, screenHeight); // Show the win screen buttons
        batch.end();
    }

    private void renderLoseScreen(int screenWidth, int screenHeight) {
        batch.draw(loseMenu, (screenWidth - 550) / 2, (screenHeight - 800) / 2, 575, 800);

        // Restart and Main Menu button logic for the lose screen
        int restartButtonWidth = 100;
        int restartButtonHeight = 100;
        int restartButtonX = (screenWidth - restartButtonWidth) / 2 - 85;
        int restartButtonY = screenHeight / 2 - 300;

        int mainMenuButtonWidth = 155;
        int mainMenuButtonHeight = 80;
        int mainMenuButtonX = (screenWidth - mainMenuButtonWidth) / 2 + 75 ;
        int mainMenuButtonY = screenHeight / 2 - 290;

        int mouseX = Gdx.input.getX();
        int mouseY = Gdx.graphics.getHeight() - Gdx.input.getY();

        if (mouseX >= restartButtonX && mouseX <= restartButtonX + restartButtonWidth &&
            mouseY >= restartButtonY && mouseY <= restartButtonY + restartButtonHeight) {
            batch.draw(restartButtonOn, restartButtonX, restartButtonY, restartButtonWidth, restartButtonHeight);
            if (Gdx.input.isTouched()) {
                game.setScreen(new Gameplay(game));
            }
        } else {
            batch.draw(restartButton, restartButtonX, restartButtonY, restartButtonWidth, restartButtonHeight);
        }

        if (mouseX >= mainMenuButtonX && mouseX <= mainMenuButtonX + mainMenuButtonWidth &&
            mouseY >= mainMenuButtonY && mouseY <= mainMenuButtonY + mainMenuButtonHeight) {
            batch.draw(mainMenuButtonOn, mainMenuButtonX, mainMenuButtonY, mainMenuButtonWidth, mainMenuButtonHeight);
            if (Gdx.input.isTouched()) {
                game.setScreen(new menu((Main) game));
            }
        } else {
            batch.draw(mainMenuButton, mainMenuButtonX, mainMenuButtonY, mainMenuButtonWidth, mainMenuButtonHeight);
        }
    }

    @Override
    public void resize(int width, int height) {
    }

    @Override
    public void hide() {
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void dispose() {
        loseMenu.dispose();
        restartButton.dispose();
        restartButtonOn.dispose();
    }
}
