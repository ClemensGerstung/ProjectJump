package game.ui;

import org.newdawn.slick.*;

import java.util.ArrayList;
import java.util.List;

public class Menu {

    private List<MenuElement> children = new ArrayList<>();

    public Menu() {

    }

    public void addChild(MenuElement element) {
        children.add(element);
    }

    public void update(GameContainer gc) throws SlickException {
        for (MenuElement child : children) {
            child.update(gc);
        }
    }

    public void render(GameContainer gc, Graphics g) throws SlickException {
        Color c = g.getColor();
        g.setColor(new Color(50, 50, 50, 200));
        g.fillRect(0, 0, gc.getWidth(), gc.getHeight());
        g.setColor(c);
        for (MenuElement child : children) {
            child.render(gc, g);
        }
    }

    public void centerAndSpread(GameContainer gc, TrueTypeFont font) {
        float containerHeight = (float) gc.getHeight() / children.size();
        for (int i = 0; i < children.size(); i++) {
            MenuElement b = children.get(i);
            b.setX((gc.getWidth() - font.getWidth(b.getContent())) / 2.f);
            b.setY((containerHeight - font.getHeight(b.getContent())) / 2.f + containerHeight * i);
        }
    }

    public void centerAndAlignTop(GameContainer gc, TrueTypeFont font, float offset){

    }
}
