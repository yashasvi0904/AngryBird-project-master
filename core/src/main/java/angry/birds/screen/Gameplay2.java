package angry.birds.screen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.Timer;

import java.util.ArrayList;
import java.util.List;

public class Gameplay2 implements Screen {
    private World world;
    private Box2DDebugRenderer debugRenderer;
    private OrthographicCamera camera;
    private final float TIMESTEP = 1 / 60f;
    private final int VELOCITYITERATIONS = 8;
    private final int POSITIONITERATION = 3;
    private Body BlackBird;
    private Body BlueBird;
    private Body catapult;
    private Body redBird;
    private SpriteBatch batch;
    private Array<Body> tmpBodies = new Array<>();
    private Texture blackBirdTexture;
    private Texture backgroundTexture;
    private boolean isDragging = false; // Tracks whether the bird is being dragged
    private Vector2 initialPosition = new Vector2(3.7f, 2.6f);
    private Array<Body> birds = new Array<>();
    private List<Body> panchi = new ArrayList<>();
    private List<Body> pigs = new ArrayList<>(); // List to hold all pigs
    private int currentBirdIndex = 0; // To keep track of which bird is being launched
    private boolean birdLaunched = false;
    private List<Body> pigsToDestroy = new ArrayList<>();
    private Game game;
    private boolean logic = false;

    public Gameplay2(Game game) {
        this.game = game;
    }

    @Override
    public void show() {
        world = new World(new Vector2(0, -9.81f), true);
        debugRenderer = new Box2DDebugRenderer();
        batch = new SpriteBatch();
        camera = new OrthographicCamera(Gdx.graphics.getWidth() / 100f, Gdx.graphics.getHeight() / 100f);
        backgroundTexture = new Texture("gameplay_back.jpg");
        blackBirdTexture = new Texture("blackbird.png");

        world.setContactListener(new ContactListener() {
            public void beginContact(Contact contact) {
                Fixture fixtureA = contact.getFixtureA();
                Fixture fixtureB = contact.getFixtureB();
                Body bodyA = fixtureA.getBody();
                Body bodyB = fixtureB.getBody();
                if (isPigAndGroundCollision(bodyA, bodyB)) {
                    Body pigBody = (isPig(bodyA)) ? bodyA : bodyB;
                    pigsToDestroy.add(pigBody);
                }
            }

            @Override
            public void endContact(Contact contact) {
            }

            @Override
            public void preSolve(Contact contact, Manifold oldManifold) {
            }

            @Override
            public void postSolve(Contact contact, ContactImpulse impulse) {
            }
        });

        // Create the Black Bird body
        BodyDef BlackBirdDef = new BodyDef();
        BlackBirdDef.type = BodyDef.BodyType.DynamicBody;
        BlackBirdDef.position.set(1, 8.7f); // Scaled to Box2D units

        CircleShape shape = new CircleShape();
        shape.setRadius(0.2f); // Set radius in Box2D units

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.density = 2.5f;
        fixtureDef.friction = 0.25f;
        fixtureDef.restitution = 0.4f;

        BlackBird = world.createBody(BlackBirdDef);
        BlackBird.createFixture(fixtureDef);

        // Attach the sprite to the body
        Sprite BlackBirdSprite = new Sprite(blackBirdTexture);
        BlackBirdSprite.setSize(0.6f, 0.6f); // Match Box2D units (2 * radius)
        BlackBirdSprite.setOrigin(BlackBirdSprite.getWidth() / 2, BlackBirdSprite.getHeight() / 2);
        BlackBird.setUserData(BlackBirdSprite);

        shape.dispose();
        // Define the Blue Bird body
        BodyDef BlueBirdDef = new BodyDef();
        BlueBirdDef.type = BodyDef.BodyType.DynamicBody;
        BlueBirdDef.position.set(2, 1.7f); // Adjust position as needed

        CircleShape blueBirdShape = new CircleShape();
        blueBirdShape.setRadius(0.2f); // Set radius in Box2D units

        FixtureDef blueBirdFixtureDef = new FixtureDef();
        blueBirdFixtureDef.shape = blueBirdShape;
        blueBirdFixtureDef.density = 2.5f;
        blueBirdFixtureDef.friction = 0.25f;
        blueBirdFixtureDef.restitution = 0.3f;

        BlueBird = world.createBody(BlueBirdDef);
        BlueBird.createFixture(blueBirdFixtureDef);

// Attach the sprite to the body
        Texture blueBirdTexture = new Texture("bluebird.png");
        Sprite BlueBirdSprite = new Sprite(blueBirdTexture);
        BlueBirdSprite.setSize(0.4f, 0.4f); // Match Box2D units (2 * radius)
        BlueBirdSprite.setOrigin(BlueBirdSprite.getWidth() / 2, BlueBirdSprite.getHeight() / 2);
        BlueBird.setUserData(BlueBirdSprite);

        blueBirdShape.dispose();

        //Define the Catapult body
        BodyDef catapultDef = new BodyDef();
        catapultDef.type = BodyDef.BodyType.StaticBody; // Static since the catapult doesn't move
        catapultDef.position.set(3.7f, 2.6f); // Set x position right after the red bird (adjust y if needed)

// Define the catapult shape
        PolygonShape catapultShape = new PolygonShape();
        catapultShape.setAsBox(.3f, 1.1f); // Width = 2 Box2D units, Height = 0.4 Box2D units

        FixtureDef catapultFixtureDef = new FixtureDef();
        catapultFixtureDef.shape = catapultShape;
        catapultFixtureDef.friction = 0.5f;
        catapultFixtureDef.restitution = 0.1f; // Minimal bounce
        catapultFixtureDef.density = 5.0f;

        catapult = world.createBody(catapultDef);
        catapult.createFixture(catapultFixtureDef);

// Attach the sprite to the body
        Texture catapultTexture = new Texture("catapult.png"); // Ensure this texture exists
        Sprite catapultSprite = new Sprite(catapultTexture);
        catapultSprite.setSize(1f, 2.5f); // Match the Box2D dimensions (Width = 2, Height = 0.4)
        catapultSprite.setOrigin(catapultSprite.getWidth() / 2, catapultSprite.getHeight() / 2);
        catapult.setUserData(catapultSprite);

// Adjust the sprite position to align with the body
        catapultSprite.setPosition(
            catapultDef.position.x - catapultSprite.getWidth() / 2,
            catapultDef.position.y - catapultSprite.getHeight() / 2
        );


        // Create the Red Bird body
        BodyDef redBirdDef = new BodyDef();
        redBirdDef.type = BodyDef.BodyType.DynamicBody;

        // Set the position of the red bird to be right after the blue bird
        redBirdDef.position.set(2.6f, 1.7f); // Slightly to the right of the blue bird (adjust as needed)

        // Define the shape for the red bird
        CircleShape redBirdShape = new CircleShape();
        redBirdShape.setRadius(0.2f); // Radius in Box2D units

        FixtureDef redBirdFixtureDef = new FixtureDef();
        redBirdFixtureDef.shape = redBirdShape;
        redBirdFixtureDef.density = 2.5f;
        redBirdFixtureDef.friction = 0.25f;
        redBirdFixtureDef.restitution = 0.3f;

        redBird = world.createBody(redBirdDef);
        redBird.createFixture(redBirdFixtureDef);

        // Attach the sprite to the body
        Texture redBirdTexture = new Texture("redbird.png"); // Ensure this texture exists
        Sprite redBirdSprite = new Sprite(redBirdTexture);
        redBirdSprite.setSize(0.4f, 0.4f); // Match Box2D units (2 * radius)
        redBirdSprite.setOrigin(redBirdSprite.getWidth() / 2, redBirdSprite.getHeight() / 2);
        redBird.setUserData(redBirdSprite);

        redBirdShape.dispose();


        // Create the ground
        ChainShape groundShape = new ChainShape();
        groundShape.createChain(new Vector2[]{new Vector2(-500, 0), new Vector2(500, 0)});
        BodyDef groundDef = new BodyDef();
        groundDef.type = BodyDef.BodyType.StaticBody;
        groundDef.position.set(0, 1.5f);

        fixtureDef.shape = groundShape;
        fixtureDef.friction = .5f;
        fixtureDef.restitution = 0;
        world.createBody(groundDef).createFixture(fixtureDef);
        groundShape.dispose();


        birds.add(BlackBird);
        birds.add(redBird);
        birds.add(BlueBird);
        panchi.add(BlackBird);
        panchi.add(redBird);
        panchi.add(BlueBird);

        addBlock("wood_block.png", new Vector2(12, 2), 0.5f, 0.5f, 2.0f);
        addBlock("wood_block.png", new Vector2(12, 3), 0.5f, 0.5f, 2.0f);
        addBlock("wood_block.png", new Vector2(11.5f, 2), 0.5f, 0.5f, 2.0f);
        addBlock("wood_block.png", new Vector2(11, 2), 0.5f, 0.5f, 2.0f);
        addBlock("wood_block.png", new Vector2(11, 3), 0.5f, 0.5f, 2.0f);


        addBlock("glass_block.png", new Vector2(14, 2), 0.5f, 0.5f, 1.0f);
        addBlock("glass_block.png", new Vector2(14, 2.5f), 0.5f, 0.5f, 1.0f);
        addBlock("glass_block.png", new Vector2(14, 3), 0.5f, 0.5f, 1.0f);
        addBlock("glass_block.png", new Vector2(13.5f, 2), 0.5f, 0.5f, 1.0f);
        addBlock("glass_block.png", new Vector2(14.5f, 2), 0.5f, 0.5f, 1.0f);


        addBlock("steel_block.png", new Vector2(15.5f, 2), 0.5f, 0.5f, 5.0f);
        addBlock("steel_block.png", new Vector2(15.5f, 3), 0.5f, 0.5f, 5.0f);
        addBlock("steel_block.png", new Vector2(16.5f, 2), 0.5f, 0.5f, 5.0f);
        addBlock("steel_block.png", new Vector2(16.5f, 3), 0.5f, 0.5f, 5.0f);

        addBlock("steel_block.png", new Vector2(16, 2), 0.5f, 0.5f, 5.0f);
        addBlock("steel_block.png", new Vector2(16, 3), 0.5f, 0.5f, 5.0f);
        addBlock("steel_block.png", new Vector2(16, 4), 0.5f, 0.5f, 5.0f);
        addBlock("steel_block.png", new Vector2(16, 5), 0.5f, 0.5f, 5.0f);

        addPig("small_pig.png", new Vector2(11.5f, 3.1f), 0.15f, 1.0f);
        addPig("medium_pig.png", new Vector2(14f, 3.5f), 0.2f, 1.5f);
        addPig("large_pig.png", new Vector2(16f, 5.5f), 0.3f, 2.0f);

        // Place the first bird on the catapult
        resetBirdPosition();
    }

    private void addPig(String texturePath, Vector2 position, float radius, float density) {
        // Load texture
        Texture pigTexture = new Texture(texturePath);
        Sprite pigSprite = new Sprite(pigTexture);
        pigSprite.setSize(radius * 2, radius * 2); // Diameter = 2 * radius
        pigSprite.setOrigin(pigSprite.getWidth() / 2, pigSprite.getHeight() / 2);

        // Define body
        BodyDef pigDef = new BodyDef();
        pigDef.type = BodyDef.BodyType.DynamicBody;
        pigDef.position.set(position);

        CircleShape pigShape = new CircleShape();
        pigShape.setRadius(radius);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = pigShape;
        fixtureDef.density = density;
        fixtureDef.friction = 0.5f;
        fixtureDef.restitution = 0.3f;

        // Create the pig in the world
        Body pigBody = world.createBody(pigDef);
        pigBody.createFixture(fixtureDef);

        // Attach the sprite to the body
        pigBody.setUserData(pigSprite);
        pigs.add(pigBody);
        // Dispose of shape after creation
        pigShape.dispose();
    }

    private void addBlock(String texturePath, Vector2 position, float width, float height, float density) {
        // Load texture
        Texture blockTexture = new Texture(texturePath);
        Sprite blockSprite = new Sprite(blockTexture);
        blockSprite.setSize(width, height);
        blockSprite.setOrigin(blockSprite.getWidth() / 2, blockSprite.getHeight() / 2);

        // Define body
        BodyDef blockDef = new BodyDef();
        blockDef.type = BodyDef.BodyType.DynamicBody;
        blockDef.position.set(position);

        PolygonShape blockShape = new PolygonShape();
        blockShape.setAsBox(width / 2, height / 2);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = blockShape;
        fixtureDef.density = density;
        fixtureDef.friction = 0.25f;
        fixtureDef.restitution = 0.1f;

        // Create the block in the world
        Body blockBody = world.createBody(blockDef);
        blockBody.createFixture(fixtureDef);

        // Attach the sprite to the body
        blockBody.setUserData(blockSprite);

        // Dispose of shape after creation
        blockShape.dispose();
    }

    private void resetBirdPosition() {
        if (currentBirdIndex < birds.size) {
            Body bird = birds.get(currentBirdIndex);
            bird.setTransform(3.7f, 3.6f, 0);
            bird.setLinearVelocity(0, 0);
            bird.setAngularVelocity(0);
            initialPosition.set(bird.getPosition());
            birdLaunched = false; // Reset launch status
        }
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        for (Body pigBody : pigsToDestroy) {
            destroyPig(pigBody);
            pigs.remove(pigBody);
        }
        pigsToDestroy.clear();

        world.step(TIMESTEP, VELOCITYITERATIONS, POSITIONITERATION);

        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        batch.draw(backgroundTexture, 0, 0, Gdx.graphics.getWidth() / 100f, Gdx.graphics.getHeight() / 100f);
        batch.end();

        // Draw sprites
        batch.begin();
        world.getBodies(tmpBodies);
        for (Body body : tmpBodies) {
            if (body.getUserData() instanceof Sprite) {
                Sprite sprite = (Sprite) body.getUserData();
                sprite.setPosition(body.getPosition().x - sprite.getWidth() / 2, body.getPosition().y - sprite.getHeight() / 2);
                sprite.setRotation(body.getAngle() * MathUtils.radiansToDegrees);
                sprite.draw(batch);
            }
        }

        batch.end();

        // Handle drag-and-launch for the current bird
        if (currentBirdIndex < birds.size) {
            Body currentBird = birds.get(currentBirdIndex);

            if (Gdx.input.isButtonPressed(Input.Buttons.LEFT)) {
                Vector3 mousePosition3D = camera.unproject(new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0));
                Vector2 mousePosition = new Vector2(mousePosition3D.x, mousePosition3D.y);

                if (!isDragging) {
                    // Check if the mouse is close enough to the bird to start dragging
                    if (currentBird.getPosition().dst(mousePosition) < 1f) { // Adjust radius as needed
                        isDragging = true;
                    }
                }

                if (isDragging) {
                    // Visualize the direction line but do not move the bird
                    batch.begin();
                    ShapeRenderer shapeRenderer = new ShapeRenderer();
                    shapeRenderer.setProjectionMatrix(camera.combined);
                    shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
                    shapeRenderer.setColor(1, 0, 0, 1); // Red color for direction line
                    shapeRenderer.line(initialPosition.x, initialPosition.y, mousePosition.x, mousePosition.y);
                    shapeRenderer.end();
                    shapeRenderer.dispose();
                    batch.end();
                }
            } else if (isDragging) {
                // Launch the bird on mouse release
                Vector3 releasePosition3D = camera.unproject(new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0));
                Vector2 releasePosition = new Vector2(releasePosition3D.x, releasePosition3D.y);
                Vector2 impulse = initialPosition.sub(releasePosition).scl(3f); // Scale to control force
                currentBird.applyLinearImpulse(impulse, currentBird.getWorldCenter(), true);
                isDragging = false;
                birdLaunched = true;
            }
        }


        // Move to the next bird if the current one is launched and has slowed down
        if (birdLaunched && currentBirdIndex < birds.size) {
            Body currentBird = birds.get(currentBirdIndex);
            if (currentBird.getLinearVelocity().len() < 20f) {
                currentBirdIndex++;
                if (currentBirdIndex < birds.size) {
                    resetBirdPosition();
                }
            }
            panchi.removeFirst();
        }
        debugRenderer.render(world, camera.combined);

        if (logic){
            return;
        }

        if (pigs.isEmpty()) {
            logic = true;
            Timer.schedule(new Timer.Task() {
                @Override
                public void run() {
                    game.setScreen(new WinScreen(game));
                }
            }, 1);
        } else if (!pigs.isEmpty() && panchi.isEmpty()) {
            logic = true;
            Timer.schedule(new Timer.Task() {
                @Override
                public void run() {
                    game.setScreen(new LoseScreen(game));
                }
            }, 3);
        }

    }

    private boolean isPigAndGroundCollision(Body bodyA, Body bodyB) {
        return (isPig(bodyA) && isGround(bodyB)) || (isPig(bodyB) && isGround(bodyA));
    }

    private boolean isPig(Body body) {
        // Make sure user data is set and we have a valid reference
        Object userData = body.getUserData();
        if (userData instanceof Sprite) {
            Sprite sprite = (Sprite) userData;
            // Use texture name or other identifier to check for pig
            return sprite.getTexture().toString().contains("pig");
        }
        return false;
    }

    private boolean isGround(Body body) {
        // Assuming ground is a static body
        return body.getType() == BodyDef.BodyType.StaticBody;
    }



    private void destroyPig(Body pigBody) {
        if (pigBody != null && pigBody.getUserData() != null) {
            // Remove the pig's body from the world
            world.destroyBody(pigBody);

            // Optionally, remove the sprite from rendering (if applicable)
            Sprite pigSprite = (Sprite) pigBody.getUserData();
            if (pigSprite != null) {
                pigSprite.setTexture(null); // Clear the texture
            }
        }
    }



    @Override
    public void resize(int width, int height) {
        camera.setToOrtho(false, width / 100f, height / 100f);
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void hide() {
    }

    @Override
    public void dispose() {
        world.dispose();
        debugRenderer.dispose();
        batch.dispose();
        blackBirdTexture.dispose();
        backgroundTexture.dispose();

    }
}
