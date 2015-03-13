package game.ui.levelselect;

import game.ui.MenuElement;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;


public class LevelMenuElement extends MenuElement {
    private float x = 0;
    private float y = 300;
    public static float WIDTH = 300.f;

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
        level.setX((WIDTH - level.getWidth()) / 2.f);
        level.render(gc, g);
        g.drawString(level.getName().toUpperCase(), x + (WIDTH - gc.getDefaultFont().getWidth(level.getName().toUpperCase())) / 2.f, y);
        //g.drawRect(x, 0, x + WIDTH, gc.getHeight());
    }

    @Override
    public void update(GameContainer gc) throws SlickException {
        level.update(gc);
    }

    public void setPosition(float x_offset, float y_offset) {
        level.setPosition(x_offset, y_offset);
        x += x_offset / 2.f;
        y += y_offset / 2.f;
    }


}
