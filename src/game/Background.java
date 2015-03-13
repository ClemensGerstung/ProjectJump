package game;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Background {
	
	private Image[] background = new Image[2];
	private float[] offset = new float[2];
	private float speed = 1.f;
	
	public Background(float speed, String src) throws SlickException {
		background[0] = new Image(src);
		background[1] = background[0];
		offset[0] = 0.f;
		offset[1] = offset[0] + background[0].getWidth();
		this.speed = speed;
	}
	
	public void render(GameContainer gc, Graphics g) throws SlickException {
		for (int i = 0; i < 2; i++) {
			g.drawImage(background[i], offset[i], 0);
		}
	}

	public void update(GameContainer gc, int delta) throws SlickException {
		for (int i = 0; i < offset.length; i++) {
			offset[i] -= (float) delta * speed / 50.f;
			if (offset[i] + background[i].getWidth() <= 0) {
				offset[i] = i == offset.length - 1 ? offset[0]
						+ background[0].getWidth() : offset[i + 1]
						+ background[i + 1].getWidth();
			}
		}

	}
}
