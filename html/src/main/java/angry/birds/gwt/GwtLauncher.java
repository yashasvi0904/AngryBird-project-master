package angry.birds.gwt;

import angry.birds.Main;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.backends.gwt.GwtApplication;
import com.badlogic.gdx.backends.gwt.GwtApplicationConfiguration;

/** Launches the browser (GWT) version of the game. */
public class GwtLauncher extends GwtApplication {

    @Override
    public GwtApplicationConfiguration getConfig() {
        // The gameplay cameras use Gdx.graphics.getWidth()/getHeight(), and the
        // game was designed and tested at the desktop window size of 1920x1080
        // (16:9). Render at that resolution so the whole play area is visible;
        // index.html/styles.css then scale this canvas to fit the browser window.
        GwtApplicationConfiguration config = new GwtApplicationConfiguration(1920, 1080);
        return config;
    }

    @Override
    public ApplicationListener createApplicationListener() {
        return new Main();
    }
}
