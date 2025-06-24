package angry.birds.screen;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.Gdx;

public class Catapult {
    private Texture cata;
    private float x, y;
    private float width, height;

    public Catapult(float x, float y, float width, float height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;

        // Load the catapult image
        cata = new Texture(Gdx.files.internal("catapult.png"));
    }

    // Render the catapult
    public void render(SpriteBatch batch) {
        batch.draw(cata, x, y, width, height);
    }

    // Dispose of the texture when no longer needed
    public void dispose() {
        cata.dispose();
    }
}
