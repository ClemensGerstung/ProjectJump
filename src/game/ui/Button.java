package game.ui;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

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
	
	public void setClickPerformed(IClickPerformed iClickPerformed) {
		this.iClickPerformed = iClickPerformed;
	}
	
	public void render(GameContainer gc, Graphics g) throws SlickException {
		g.drawString(content, x, y);
	}
	
	public void update(GameContainer gc, int delta) throws SlickException {
		
	}
	
	public static interface IClickPerformed {
		public abstract void click();
	}
}
