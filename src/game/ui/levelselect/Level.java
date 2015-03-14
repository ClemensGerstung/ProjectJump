package game.ui.levelselect;

import org.newdawn.slick.*;
import org.newdawn.slick.geom.Rectangle;

import java.util.ArrayList;
import java.util.List;

public class Level {
    private String file;
    private String name;
    private List<LevelItem> items = new ArrayList<>();

    public static Image maptile;

    static {
        try {
            maptile = new Image("gfx/maptile_small.png");
        } catch (SlickException ignored) {
        }
    }

    private Rectangle rectangle = new Rectangle(0, 0, maptile.getWidth() * 9, maptile.getHeight() * 9);

    public Level(String file, String name) {
        this.file = file;
        this.name = name;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<LevelItem> getItems() {
        return items;
    }

    public void addMapTile(float x, int line) {
        try {
            LevelItem item = new LevelItem(x, maptile.getHeight() * line, maptile);
            items.add(item);
        } catch (Exception ignored) {
        }
    }

    public void render(GameContainer gc, Graphics g) throws SlickException {
        for (LevelItem item : items) {
            item.render(gc, g);
        }
        Color c = g.getColor();
        g.setColor(Color.gray);
        g.draw(rectangle);
        g.setColor(c);
    }

    public void update(GameContainer gc) throws SlickException {

    }

    public void setPosition(float x_offset, float y_offset) {
        for (LevelItem item : items) {
            item.setX(item.getX() + x_offset);
            item.setY(item.getY() + y_offset);
        }
        rectangle.setX(rectangle.getX() + x_offset);
        rectangle.setY(rectangle.getY() + y_offset);
    }

    public void setX(float x) {
        float offset = rectangle.getX() - x;
        for (LevelItem item : items) {
            item.setX(item.getX() - offset);
        }
        rectangle.setX(rectangle.getX() - offset);
    }

    public float getX() {
        return rectangle.getX();
    }

    public void setY(float y) {
        float offset = y > rectangle.getY() ? y - rectangle.getY() : rectangle.getY() - y;
        for (LevelItem item : items) {
            item.setY(item.getY() + offset);
        }
        rectangle.setY(rectangle.getY() + offset);
    }

    public float getWidth() {
        return rectangle.getWidth();
    }
}
