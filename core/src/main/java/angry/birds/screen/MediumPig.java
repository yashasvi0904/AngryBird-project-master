package angry.birds.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class MediumPig extends Pig {
    public MediumPig(float x, float y) {
        super(x, y, 75, 75); // Set size of the medium pig
    }

    @Override
    protected void loadTexture() {
        pigTexture = new Texture(Gdx.files.internal("medium_pig.png"));
    }
}
