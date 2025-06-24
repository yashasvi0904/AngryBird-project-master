package angry.birds.screen;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public abstract class Pig {
    protected Texture pigTexture;
    protected float x, y;
    protected float width, height;

    public Pig(float x, float y, float width, float height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        loadTexture(); // Abstract method to be implemented by subclasses
    }

    // Abstract method to load the texture in each subclass
    protected abstract void loadTexture();

    // Render method to display the pig
    public void render(SpriteBatch batch) {
        batch.draw(pigTexture, x, y, width, height);
    }

    // Dispose method to clean up resources
    public void dispose() {
        if (pigTexture != null) {
            pigTexture.dispose();
        }
    }
}
