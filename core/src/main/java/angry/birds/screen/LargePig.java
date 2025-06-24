package angry.birds.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class LargePig extends Pig {
    public LargePig(float x, float y) {
        super(x, y, 90, 90); // Set size of the large pig
    }

    @Override
    protected void loadTexture() {
        pigTexture = new Texture(Gdx.files.internal("large_pig.png"));
    }
}
