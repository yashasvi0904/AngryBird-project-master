package angry.birds.screen;

import angry.birds.Main;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.ScreenUtils;
import java.util.ArrayList;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class Level1Game implements Screen {

    protected static World world;
    protected Box2DDebugRenderer debugRenderer;
    protected OrthographicCamera camera;
    Texture image;
    Texture pauseButton, pauseButtonOn, pauseMenu, resumeButton, resumeButtonOn;
    Texture mainMenuButton, mainMenuButtonOn, textImage;
    Texture restartButton, restartButtonOn;

    Texture winButton, winButtonOn;
    Texture loseButton, loseButtonOn;
    Texture winMenu, loseMenu;
    Texture nextButton, nextButtonOn;

    Catapult catapult;
    ArrayList<Block> blocks;
    ArrayList<Bird> birds;
    ArrayList<Pig> pigs;

    Main game;
    boolean isPaused = false;
    boolean hoverPauseButton = false;
    boolean hoverResumeButton = false;
    boolean hoverMainMenuButton = false;
    boolean hoverRestartButton = false;
    boolean hoverWinButton = false;
    boolean hoverLoseButton = false;
    boolean showWinScreen = false;
    boolean showLoseScreen = false;

    public Level1Game(Main game) {
        this.game = game;
        blocks = new ArrayList<>();
        birds = new ArrayList<>();
        pigs = new ArrayList<>();
        world =new World ( new Vector2(0,-9.81f),true);
        debugRenderer = new Box2DDebugRenderer();

        camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
    }

    @Override
    public void show() {
        world = new World(new Vector2(0, -9.8f), true);
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
        winButton = new Texture("win.png");
        winButtonOn = new Texture("win_on.png");
        loseButton = new Texture("lose.png");
        loseButtonOn = new Texture("lose_on.png");
        winMenu = new Texture("win_screen.jpg");
        loseMenu = new Texture("lose_screen.jpeg");
        nextButton = new Texture("next.png");
        nextButtonOn = new Texture("next_on.png");

        birds.add(new BlackBird(100, 140, 75, 75, world));
        birds.add(new BlueBird(200, 150, 75, 75, world));
        birds.add(new RedBird(300, 150, 75, 75, world));
        birds.add(new RedBird(400, 200, 75, 75, world));

        catapult = new Catapult(350, 150, 150, 100);

        pigs.add(new SmallPig(1440, 150));
        pigs.add(new SmallPig(1380, 300));
        pigs.add(new SmallPig(1440, 300));
        pigs.add(new SmallPig(1500, 300));

        pigs.add(new MediumPig(1700, 500));
        pigs.add(new MediumPig(1600, 500));
        pigs.add(new LargePig(1650, 350));

        blocks.add(new SteelBlock(1600, 150, 50, 50));
        blocks.add(new SteelBlock(1600, 200, 50, 50));
        blocks.add(new SteelBlock(1600, 250, 50, 50));
        blocks.add(new SteelBlock(1600, 300, 50, 50));
        blocks.add(new SteelBlock(1650, 150, 50, 50));
        blocks.add(new SteelBlock(1650, 200, 50, 50));
        blocks.add(new SteelBlock(1650, 250, 50, 50));
        blocks.add(new SteelBlock(1650, 300, 50, 50));
        blocks.add(new SteelBlock(1700, 150, 50, 50));
        blocks.add(new SteelBlock(1700, 200, 50, 50));
        blocks.add(new SteelBlock(1700, 250, 50, 50));
        blocks.add(new SteelBlock(1700, 300, 50, 50));
        blocks.add(new SteelBlock(1750, 150, 50, 50));
        blocks.add(new SteelBlock(1750, 200, 50, 50));
        blocks.add(new SteelBlock(1750, 250, 50, 50));
        blocks.add(new SteelBlock(1750, 300, 50, 50));

        blocks.add(new GlassBlock(1600, 350, 50, 50));
        blocks.add(new GlassBlock(1600, 400, 50, 50));
        blocks.add(new GlassBlock(1600, 450, 50, 50));
        blocks.add(new GlassBlock(1650, 450, 50, 50));
        blocks.add(new GlassBlock(1700, 450, 50, 50));
        blocks.add(new GlassBlock(1750, 350, 50, 50));
        blocks.add(new GlassBlock(1750, 400, 50, 50));
        blocks.add(new GlassBlock(1750, 450, 50, 50));

        blocks.add(new WoodBlock(1500, 150, 60, 50));
        blocks.add(new WoodBlock(1500, 200, 60, 50));
        blocks.add(new WoodBlock(1500, 250, 60, 50));
        blocks.add(new WoodBlock(1440, 250, 60, 50));
        blocks.add(new WoodBlock(1380, 250, 60, 50));
        blocks.add(new WoodBlock(1380, 200, 60, 50));
        blocks.add(new WoodBlock(1380, 150, 60, 50));



        blocks.add(new GlassBlock(1280, 150, 50, 50));
        blocks.add(new GlassBlock(1280, 200, 50, 50));
        blocks.add(new GlassBlock(1280, 250, 50, 50));
        blocks.add(new GlassBlock(1280, 300, 50, 50));
        blocks.add(new GlassBlock(1280, 350, 50, 50));
        blocks.add(new GlassBlock(1280, 400, 50, 50));
        blocks.add(new GlassBlock(1280, 450, 50, 50));
        blocks.add(new GlassBlock(1280, 500, 50, 50));



    }

    @Override
    public void render(float delta) {
        world.step(1 / 60f, 6, 2);

        // Optionally, render debug data
        debugRenderer.render(world, camera.combined);
        ScreenUtils.clear(0.15f, 0.15f, 0.2f, 1f);

        game.batch.begin();

        int screenWidth = Gdx.graphics.getWidth();
        int screenHeight = Gdx.graphics.getHeight();

        game.batch.draw(image, 0, 0, screenWidth, screenHeight);

        if (!isPaused && !showWinScreen && !showLoseScreen) {
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

            // Win button logic
            int winButtonWidth = 100;
            int winButtonHeight = 100;
            int winButtonX = screenWidth - winButtonWidth - 20;
            int winButtonY = screenHeight - winButtonHeight - 20;

            if (mouseX >= winButtonX && mouseX <= winButtonX + winButtonWidth &&
                mouseY >= winButtonY && mouseY <= winButtonY + winButtonHeight) {
                game.batch.draw(winButtonOn, winButtonX, winButtonY, winButtonWidth, winButtonHeight);
                hoverWinButton = true;
            } else {
                game.batch.draw(winButton, winButtonX, winButtonY, winButtonWidth, winButtonHeight);
                hoverWinButton = false;
            }

            // Lose button logic
            int loseButtonWidth = 100;
            int loseButtonHeight = 100;
            int loseButtonX = screenWidth - loseButtonWidth - 130;
            int loseButtonY = screenHeight - loseButtonHeight - 20;

            if (mouseX >= loseButtonX && mouseX <= loseButtonX + loseButtonWidth &&
                mouseY >= loseButtonY && mouseY <= loseButtonY + loseButtonHeight) {
                game.batch.draw(loseButtonOn, loseButtonX, loseButtonY, loseButtonWidth, loseButtonHeight);
                hoverLoseButton = true;
            } else {
                game.batch.draw(loseButton, loseButtonX, loseButtonY, loseButtonWidth, loseButtonHeight);
                hoverLoseButton = false;
            }

            if (Gdx.input.isTouched()) {
                if (hoverWinButton) {
                    showWinScreen = true;
                } else if (hoverLoseButton) {
                    showLoseScreen = true;
                }
            }
        }
        else if (showWinScreen) {
            renderWinScreen(screenWidth, screenHeight);
        }
        else if (showLoseScreen) {
            renderLoseScreen(screenWidth, screenHeight);
        }

        else {
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

        for(Bird bird : birds) {
            bird.draw(game.batch);
        }

        for(Pig pig : pigs) {
            pig.render(game.batch);
        }

        catapult.render(game.batch);

        for (Block block : blocks) {
            block.render(game.batch);
        }

        game.batch.end();
    }

    private void renderWinScreen(int screenWidth, int screenHeight) {
        game.batch.draw(winMenu, (screenWidth - 550) / 2, (screenHeight - 800) / 2, 550, 800);
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
            game.batch.draw(restartButtonOn, restartButtonX, restartButtonY, restartButtonWidth, restartButtonHeight);
            if (Gdx.input.isTouched()) {
                game.setScreen(new Level1Game(game)); // Restart the current level
            }
        } else {
            game.batch.draw(restartButton, restartButtonX, restartButtonY, restartButtonWidth, restartButtonHeight);
        }

        if (mouseX >= nextButtonX && mouseX <= nextButtonX + nextButtonWidth &&
            mouseY >= nextButtonY && mouseY <= nextButtonY + nextButtonHeight) {
            game.batch.draw(nextButtonOn, nextButtonX, nextButtonY, nextButtonWidth, nextButtonHeight);
            if (Gdx.input.isTouched()) {
                game.setScreen(new Level2Game(game)); // Move to the next level
            }
        } else {
            game.batch.draw(nextButton, nextButtonX, nextButtonY, nextButtonWidth, nextButtonHeight);
        }
    }

    private void renderLoseScreen(int screenWidth, int screenHeight) {
        game.batch.draw(loseMenu, (screenWidth - 550) / 2, (screenHeight - 800) / 2, 575, 800);

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
            game.batch.draw(restartButtonOn, restartButtonX, restartButtonY, restartButtonWidth, restartButtonHeight);
            if (Gdx.input.isTouched()) {
                game.setScreen(new Level1Game(game)); // Restart the current level
            }
        } else {
            game.batch.draw(restartButton, restartButtonX, restartButtonY, restartButtonWidth, restartButtonHeight);
        }

        if (mouseX >= mainMenuButtonX && mouseX <= mainMenuButtonX + mainMenuButtonWidth &&
            mouseY >= mainMenuButtonY && mouseY <= mainMenuButtonY + mainMenuButtonHeight) {
            game.batch.draw(mainMenuButtonOn, mainMenuButtonX, mainMenuButtonY, mainMenuButtonWidth, mainMenuButtonHeight);
            if (Gdx.input.isTouched()) {
                game.setScreen(new menu(game)); // Navigate to the main menu
            }
        } else {
            game.batch.draw(mainMenuButton, mainMenuButtonX, mainMenuButtonY, mainMenuButtonWidth, mainMenuButtonHeight);
        }
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
        winButton.dispose();
        winButtonOn.dispose();
        loseButton.dispose();
        loseButtonOn.dispose();

        for(Bird bird:birds){
            bird.dispose();
        }
        catapult.dispose();
        for(Pig pig:pigs){
            pig.dispose();
        }

        for (Block block : blocks) {
            block.dispose();
        }
    }
}
