package angry.birds.gwt;

import angry.birds.Main;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.backends.gwt.GwtApplication;
import com.badlogic.gdx.backends.gwt.GwtApplicationConfiguration;

/** Launches the browser (GWT) version of the game. */
public class GwtLauncher extends GwtApplication {

    @Override
    public GwtApplicationConfiguration getConfig() {
        // Fixed logical size matching the desktop game (480x720).
        GwtApplicationConfiguration config = new GwtApplicationConfiguration(Main.WIDTH, Main.HEIGHT);
        // Render into the div with id "embed-html" from index.html.
        return config;
    }

    @Override
    public ApplicationListener createApplicationListener() {
        return new Main();
    }
}
