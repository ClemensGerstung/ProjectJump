package game;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;

public class WorldItem implements Comparable<WorldItem> {
	private float x;
	private final float x_origin;
	private float y;
	private Image spread;

	public WorldItem(float x, float y, Image img) {
		this.x = x;
		this.x_origin = x;
		this.y = y;
		this.spread = img;
	}

	public void render(GameContainer gc, Graphics g) throws SlickException {

		if ((x + spread.getWidth()) >= 0 && x <= gc.getWidth()) {
			g.drawImage(spread, x, y);
		}
	}

	public void update(float distance) throws SlickException {
		if ((x + spread.getWidth()) >= 0) {
			x = x_origin - distance;
		}
	}

	public Rectangle getRectangle() {
		return new Rectangle(x, y, spread.getWidth(), spread.getHeight());
	}

	public float getWidth() {
		return spread.getWidth();
	}

    public float getY() {
        return y;
    }

    public float getX() {
        return x;
    }

    @Override
	public int compareTo(WorldItem o) {
		return o.x < this.x ? 1 : 0;
	}
}
