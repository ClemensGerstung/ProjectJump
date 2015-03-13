package game.ui;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import sun.font.TrueTypeFont;

public class Button {

	private String content;
	private IClickPerformed iClickPerformed;
	private float x;
	private float y;

	public Button(String content) {
        this.content = content;
	}
	
	public Button(float x, float y, String content) {
		this(content);
		this.x = x;
		this.y = y;
	}

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public void setClickPerformed(IClickPerformed iClickPerformed) {
		this.iClickPerformed = iClickPerformed;
	}
	
	public void render(GameContainer gc, Graphics g) throws SlickException {

		g.drawString(content, x, y);
	}

	public void update(GameContainer gc, int delta) throws SlickException {
//        g.drawString(Float.toString(Mouse.getX()) + " : " + Float.toString(gc.getHeight() - Mouse.getY()), 0, 100);

    }
	
	public static interface IClickPerformed {
		public abstract void click();
	}
}
