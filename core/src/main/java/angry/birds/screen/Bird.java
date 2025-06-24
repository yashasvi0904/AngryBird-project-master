package angry.birds.screen;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;

public abstract class Bird {
    protected Texture birdTexture;
    protected float x, y;
    protected float width, height;
    protected World world;


    public Bird(float x, float y, float width, float height,World world) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.world = world;

    }
    private void createbird(World world){
        BodyDef birdDef = new BodyDef();
        birdDef.type= BodyDef.BodyType.DynamicBody;
        birdDef.position.set(x,y);
        CircleShape shape = new CircleShape();

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape= null;
        fixtureDef.density= 2.5f;
        fixtureDef.friction=.25f;
        fixtureDef.restitution= .75f;

        world.createBody(birdDef).createFixture(fixtureDef);
    }

    public abstract void draw(SpriteBatch batch);

    public void dispose() {
        birdTexture.dispose();
    }
}
