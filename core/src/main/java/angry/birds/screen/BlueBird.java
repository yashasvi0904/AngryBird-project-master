package angry.birds.screen;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.World;

public class BlueBird extends Bird {

    public BlueBird(float x, float y, float width, float height, World world) {
        super(x, y, width, height,world);
        birdTexture = new Texture("bluebird.png");
    }

    @Override
    public void draw(SpriteBatch batch) {
        batch.draw(birdTexture, x, y, width, height);
    }
}
