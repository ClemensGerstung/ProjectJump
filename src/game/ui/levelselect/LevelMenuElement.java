package game.ui.levelselect;

import game.ui.MenuElement;
import org.lwjgl.input.Mouse;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;


public class LevelMenuElement extends MenuElement {
    private float x = 0;
    private float y = 300;
    public static float WIDTH = 300.f;

    private Color normal = new Color(200, 100, 10, 0);
    private Color hover = new Color(200, 100, 10, 100);
    private Color current = normal;


    private Level level;

    public LevelMenuElement(Level level) {
        super("");
        this.level = level;
        level.setY(46.5f);
    }

    public LevelMenuElement(String content) {
        super(content);
    }

    public LevelMenuElement(String content, float x, float y) {
        super(content, x, y);
    }

    @Override
    public void render(GameContainer gc, Graphics g) throws SlickException {
        Color c = g.getColor();
        g.setColor(current);
        g.fillRect(x, 0, x + WIDTH, gc.getHeight() - 20.f);
        g.setColor(c);

        level.setX((WIDTH - level.getWidth()) / 2.f);
        level.render(gc, g);
        g.drawString(level.getName().toUpperCase(), x + (WIDTH - gc.getDefaultFont().getWidth(level.getName().toUpperCase())) / 2.f, y);
    }

    @Override
    public void update(GameContainer gc) throws SlickException {
        level.update(gc);
        if (Mouse.getX() > x
                && Mouse.getX() < x + WIDTH
                && (gc.getHeight() - Mouse.getY()) > 0
                && (gc.getHeight() - Mouse.getY()) < gc.getHeight() - 20.f) {
            current = hover;
        } else {
            current = normal;
        }
    }

    public void setPosition(float x_offset, float y_offset) {
        level.setPosition(x_offset, y_offset);
        x += x_offset / 2.f;
        y += y_offset / 2.f;
    }


}
