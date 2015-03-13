package game.ui;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

public class TextArea extends MenuElement {
    public TextArea(String content) {
        super(content);
        current = Color.yellow;
    }

    public TextArea(String content, float x, float y) {
        super(content, x, y);
        current = Color.yellow;
    }

    @Override
    public void render(GameContainer gc, Graphics g) throws SlickException {
        Color c = g.getColor();
        g.setColor(current);
        g.drawString(content, x, y);
        g.setColor(c);
    }

    @Override
    public void update(GameContainer gc) throws SlickException {

    }
}
