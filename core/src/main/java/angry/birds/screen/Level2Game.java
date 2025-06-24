package angry.birds.screen;

import angry.birds.Main;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.ScreenUtils;

public class Level2Game implements Screen {

    Texture image;
    Texture pauseButton;
    Texture pauseButtonOn;
    Texture pauseMenu;
    Texture resumeButton;
    Texture resumeButtonOn;
    Texture mainMenuButton;
    Texture mainMenuButtonOn;
    Texture restartButton;
    Texture restartButtonOn;
    Texture textImage;

    Main game;
    boolean isPaused = false;
    boolean hoverPauseButton = false;
    boolean hoverResumeButton = false;
    boolean hoverMainMenuButton = false;
    boolean hoverRestartButton = false;

    public Level2Game(Main game) {
        this.game = game;
    }

    @Override
    public void show() {
        image = new Texture("gameplay_back.jpg");
        pauseButton = new Texture("pause.png");
        pauseButtonOn = new Texture("pause_on.png");
        pauseMenu = new Texture("pause_menu.jpg");
        resumeButton = new Texture("resume.png");
        resumeButtonOn = new Texture("resume_on.png");
        mainMenuButton = new Texture("main_menu.png");
        mainMenuButtonOn = new Texture("main_menu_on.png");
        textImage = new Texture("text.png");
        restartButton = new Texture("restart.png");
        restartButtonOn = new Texture("restart_on.png");
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0.15f, 0.15f, 0.2f, 1f);

        game.batch.begin();

        int screenWidth = Gdx.graphics.getWidth();
        int screenHeight = Gdx.graphics.getHeight();

        game.batch.draw(image, 0, 0, screenWidth, screenHeight);

        if (!isPaused) {
            int pauseButtonWidth = 100;
            int pauseButtonHeight = 100;

            int mouseX = Gdx.input.getX();
            int mouseY = Gdx.graphics.getHeight() - Gdx.input.getY();

            if (mouseX < pauseButtonWidth && mouseY > screenHeight - pauseButtonHeight) {
                game.batch.draw(pauseButtonOn, 10, screenHeight - pauseButtonHeight-10, pauseButtonWidth, pauseButtonHeight);
                hoverPauseButton = true;
            } else {
                game.batch.draw(pauseButton, 10, screenHeight - pauseButtonHeight-10, pauseButtonWidth, pauseButtonHeight);
                hoverPauseButton = false;
            }

            if (Gdx.input.isTouched() && hoverPauseButton) {
                isPaused = true;
            }

        } else {
            int pauseMenuWidth = 550;
            int pauseMenuHeight = 800;
            int pauseMenuX = (screenWidth - pauseMenuWidth) / 2;
            int pauseMenuY = (screenHeight - pauseMenuHeight) / 2 - 15 ;

            game.batch.draw(pauseMenu, pauseMenuX, pauseMenuY, pauseMenuWidth, pauseMenuHeight);
            game.batch.draw(textImage, pauseMenuX+30, pauseMenuY+600, 500, 250);

            // Resume button
            int resumeButtonWidth = 250;
            int resumeButtonHeight = 75;
            int resumeButtonX = pauseMenuX + (pauseMenuWidth - resumeButtonWidth) / 2;
            int resumeButtonY = pauseMenuY + 525;

            int adjustedMouseY = screenHeight - Gdx.input.getY();

            if (Gdx.input.getX() >= resumeButtonX && Gdx.input.getX() <= resumeButtonX + resumeButtonWidth &&
                adjustedMouseY >= resumeButtonY && adjustedMouseY <= resumeButtonY + resumeButtonHeight) {
                game.batch.draw(resumeButtonOn, resumeButtonX, resumeButtonY, resumeButtonWidth, resumeButtonHeight);
                hoverResumeButton = true;
            } else {
                game.batch.draw(resumeButton, resumeButtonX, resumeButtonY, resumeButtonWidth, resumeButtonHeight);
                hoverResumeButton = false;
            }

            // Restart button
            int restartButtonWidth = 125;
            int restartButtonHeight = 90;
            int restartButtonX = pauseMenuX + (pauseMenuWidth - restartButtonWidth) / 2;
            int restartButtonY = pauseMenuY + 390;

            if (Gdx.input.getX() >= restartButtonX && Gdx.input.getX() <= restartButtonX + restartButtonWidth &&
                adjustedMouseY >= restartButtonY && adjustedMouseY <= restartButtonY + restartButtonHeight) {
                game.batch.draw(restartButtonOn, restartButtonX, restartButtonY, restartButtonWidth, restartButtonHeight);
                hoverRestartButton = true;
            } else {
                game.batch.draw(restartButton, restartButtonX, restartButtonY, restartButtonWidth, restartButtonHeight);
                hoverRestartButton = false;
            }

            // Main menu button
            int mainMenuButtonWidth = 250;
            int mainMenuButtonHeight = 75;
            int mainMenuButtonX = pauseMenuX + (pauseMenuWidth - mainMenuButtonWidth) / 2;
            int mainMenuButtonY = pauseMenuY + 275;

            if (Gdx.input.getX() >= mainMenuButtonX && Gdx.input.getX() <= mainMenuButtonX + mainMenuButtonWidth &&
                adjustedMouseY >= mainMenuButtonY && adjustedMouseY <= mainMenuButtonY + mainMenuButtonHeight) {
                game.batch.draw(mainMenuButtonOn, mainMenuButtonX, mainMenuButtonY, mainMenuButtonWidth, mainMenuButtonHeight);
                hoverMainMenuButton = true;
            } else {
                game.batch.draw(mainMenuButton, mainMenuButtonX, mainMenuButtonY, mainMenuButtonWidth, mainMenuButtonHeight);
                hoverMainMenuButton = false;
            }

            if (Gdx.input.isTouched()) {
                if (hoverResumeButton) {
                    isPaused = false;
                } else if (hoverRestartButton) {
                    isPaused = false;
                } else if (hoverMainMenuButton) {
                    game.setScreen(new menu(game));
                }
            }
        }


        game.batch.end();
    }


    @Override
    public void resize(int width, int height) {}

    @Override
    public void pause() {}

    @Override
    public void resume() {}

    @Override
    public void hide() {}

    @Override
    public void dispose() {
        image.dispose();
        pauseButton.dispose();
        pauseButtonOn.dispose();
        pauseMenu.dispose();
        resumeButton.dispose();
        resumeButtonOn.dispose();
        mainMenuButton.dispose();
        mainMenuButtonOn.dispose();
    }
}
