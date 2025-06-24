package angry.birds.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class SmallPig extends Pig {
    public SmallPig(float x, float y) {
        super(x, y, 50, 50); // Set size of the small pig
    }

    @Override
    protected void loadTexture() {
        pigTexture = new Texture(Gdx.files.internal("small_pig.png"));
    }
}
