package angry.birds.screen;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;

public abstract class Block {
    protected Texture texture;
    protected float x;
    protected float y;
    protected float width;   // Width of the block
    protected float height;  // Height of the block

    public Block(float x, float y, float width, float height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public abstract void render(Batch batch);

    public void dispose() {
        if (texture != null) {
            texture.dispose();
        }
    }
}
