package game;

import org.lwjgl.input.Keyboard;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

public class GameApp extends BasicGame {
	public GameApp(String title) {
		super(title);
	}

	private World world;
	private Background background;

    @Override
	public void init(GameContainer gc) throws SlickException {
		this.background = new Background(2.5f, "gfx/background.jpg");
		try {
			world = World.loadFromFile("lvl/level_1.config");
		} catch (Exception e) {	}
		
	}

	@Override
	public void render(GameContainer gc, Graphics g) throws SlickException {
		background.render(gc, g);
		if(world != null) world.render(gc, g);
	}

	@Override
	public void update(GameContainer gc, int delta) throws SlickException {
		background.update(gc, delta);
		if(world != null) world.update(gc, delta);

        if(Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)) {
            gc.exit();
        }
	}

}
