package angry.birds.screen;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;

public class GlassBlock extends Block {
    public GlassBlock(float x, float y, float width, float height) {
        super(x, y, width, height);
        texture = new Texture("glass_block.png"); // Path to your glass block image
    }

    @Override
    public void render(Batch batch) {
        batch.draw(texture, x, y, width, height);
    }
}
