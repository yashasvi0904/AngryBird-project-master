package angry.birds.screen;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;

public class WoodBlock extends Block {
    public WoodBlock(float x, float y,float width, float height) {
        super(x, y, width, height);
        texture = new Texture("wood_block.png"); // Path to your wood block image
    }

    @Override
    public void render(Batch batch) {
        batch.draw(texture, x, y, width, height);
    }
}
