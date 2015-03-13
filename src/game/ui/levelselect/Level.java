package game.ui.levelselect;

import game.World;
import game.WorldItem;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import java.util.ArrayList;
import java.util.List;

public class Level {
    private String file;
    private String name;
    private List<WorldItem> items = new ArrayList<>();

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

    public List<WorldItem> getItems() {
        return items;
    }

    public void setItems(List<WorldItem> items) {
        this.items = items;
    }

    public void addMapTile(float x, int line) {
        try {
            WorldItem item = new WorldItem(x, World.maptile.getHeight() * line + 36.f,
                    World.maptile);
            item.setX(item.getX()+100.f);
            items.add(item);
        } catch (Exception ignored) {
        }
    }

    public void render(GameContainer gc, Graphics g) throws SlickException {
        g.scale(0.5f, 0.5f);
        for(WorldItem item : items){
            item.render(gc, g);
        }
        g.scale(2, 2);
    }

    public void update(GameContainer gc) throws SlickException {

    }
}
