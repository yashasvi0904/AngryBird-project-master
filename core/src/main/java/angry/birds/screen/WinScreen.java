package angry.birds.screen;

import angry.birds.Main;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class WinScreen implements Screen {
    private Game game;
    private Texture winMenu;
    private Texture restartButton;
    private Texture nextButton;
    private Texture restartButtonOn;
    private Texture nextButtonOn;
    private SpriteBatch batch;

    public WinScreen(Game game) {
        this.game = game;
    }

    @Override
    public void show() {
        winMenu = new Texture("win_screen.jpg");
        restartButton = new Texture("restart.png");
        nextButton = new Texture("next.png");
        restartButtonOn = new Texture("restart_on.png");
        nextButtonOn = new Texture("next_on.png");
        batch = new SpriteBatch();
    }

    @Override
    public void render(float delta) {
        int screenWidth = Gdx.graphics.getWidth();
        int screenHeight = Gdx.graphics.getHeight();

        batch.begin();
        renderWinScreen(screenWidth, screenHeight); // Show the win screen buttons
        batch.end();
    }

    private void renderWinScreen(int screenWidth, int screenHeight) {
        batch.draw(winMenu, (screenWidth - 550) / 2, (screenHeight - 800) / 2, 550, 800);
        int restartButtonWidth = 100;
        int restartButtonHeight = 100;
        int restartButtonX = (screenWidth - restartButtonWidth) / 2 - 75;
        int restartButtonY = screenHeight / 2 - 310;

        int nextButtonWidth = 90;
        int nextButtonHeight = 90;
        int nextButtonX = (screenWidth - nextButtonWidth) / 2 + 75;
        int nextButtonY = screenHeight / 2 - 305;

        int mouseX = Gdx.input.getX();
        int mouseY = Gdx.graphics.getHeight() - Gdx.input.getY();

        if (mouseX >= restartButtonX && mouseX <= restartButtonX + restartButtonWidth &&
            mouseY >= restartButtonY && mouseY <= restartButtonY + restartButtonHeight) {
            batch.draw(restartButtonOn, restartButtonX, restartButtonY, restartButtonWidth, restartButtonHeight);
            if (Gdx.input.isTouched()) {
                game.setScreen(new Gameplay(game)); // Restart the current level
            }
        } else {
            batch.draw(restartButton, restartButtonX, restartButtonY, restartButtonWidth, restartButtonHeight);
        }

        if (mouseX >= nextButtonX && mouseX <= nextButtonX + nextButtonWidth &&
            mouseY >= nextButtonY && mouseY <= nextButtonY + nextButtonHeight) {
            batch.draw(nextButtonOn, nextButtonX, nextButtonY, nextButtonWidth, nextButtonHeight);
            if (Gdx.input.isTouched()) {
                game.setScreen(new Gameplay2(game)); // Move to the next level
            }
        } else {
            batch.draw(nextButton, nextButtonX, nextButtonY, nextButtonWidth, nextButtonHeight);
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
        winMenu.dispose();
        restartButton.dispose();
        nextButton.dispose();
        restartButtonOn.dispose();
        nextButtonOn.dispose();
    }
}
