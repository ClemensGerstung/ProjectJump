package game.ui;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

public class Button extends MenuElement {

    private IClickPerformed iClickPerformed;

    public Button(String content) {
        super(content);
    }

    public Button(String content, float x, float y) {
        super(content, x, y);
    }

    public void setClickPerformed(IClickPerformed iClickPerformed) {
        this.iClickPerformed = iClickPerformed;
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
        if (Mouse.getX() > x
                && Mouse.getX() < x + gc.getDefaultFont().getWidth(content)
                && (gc.getHeight() - Mouse.getY()) > y
                && (gc.getHeight() - Mouse.getY()) < y + gc.getDefaultFont().getHeight(content)) {
            current = hover;
            if (Mouse.isButtonDown(0)) {
                current = active;
                if(iClickPerformed != null){
                    iClickPerformed.click();
                }
            }

        } else {
            current = normal;
        }

    }

    public static interface IClickPerformed {
        public abstract void click();
    }
}
