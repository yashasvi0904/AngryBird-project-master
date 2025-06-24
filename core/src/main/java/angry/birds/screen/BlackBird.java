package angry.birds.screen;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.World;

public class BlackBird extends Bird {

    public BlackBird(float x, float y, float width, float height, World world) {
        super(x, y, width, height, world);
        birdTexture = new Texture("blackbird.png");

    }

    @Override
    public void draw(SpriteBatch batch) {
        batch.draw(birdTexture, x, y, width, height);
    }
}
