package game.ui.levelselect;

import game.ui.MenuElement;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;


public class LevelMenuElement extends MenuElement {
    private Level level;

    public LevelMenuElement(Level level) {
        super("");
        this.level = level;
    }

    public LevelMenuElement(String content) {
        super(content);
    }

    public LevelMenuElement(String content, float x, float y) {
        super(content, x, y);
    }

    @Override
    public void render(GameContainer gc, Graphics g) throws SlickException {
        level.render(gc, g);
    }

    @Override
    public void update(GameContainer gc) throws SlickException {
        level.update(gc);
    }
}
