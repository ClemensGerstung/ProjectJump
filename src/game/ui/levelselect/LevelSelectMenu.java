package game.ui.levelselect;

import game.World;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.util.ResourceLoader;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class LevelSelectMenu {
    private List<LevelMenuElement> elements = new ArrayList<>();

    public LevelSelectMenu() {
    }

    public void render(GameContainer gc, Graphics g) throws SlickException {
        Color c = g.getColor();

        for (LevelMenuElement element : elements) {
            element.render(gc, g);
        }

        g.setColor(new Color(40, 40, 40, 190));
        g.fillRect(0, gc.getHeight() - 20, gc.getWidth(), 20);

        g.setColor(c);
    }

    public void update(GameContainer gc) throws SlickException {
        for (LevelMenuElement element : elements) {
            element.update(gc);
        }
    }

    public void init() throws Exception {
        File[] files = new File(ResourceLoader.getResource("lvl").getFile()).listFiles();
        for (File level : files) {
            if (level.getName().endsWith(".config")) {
                BufferedReader reader = new BufferedReader(new FileReader(level));
                String line = reader.readLine();
                String name = line.substring(line.indexOf(": ") + 2);
                reader.readLine();
                reader.readLine();
                line = reader.readLine();
                String lvl = line.substring(line.indexOf(": ") + 2);

                reader = new BufferedReader(new FileReader(new File(ResourceLoader.getResource("lvl").getFile() + File.separator + lvl)));
                String[] world_tiles = new String[9];
                for (int i = 0; i < 9; i++) {
                    world_tiles[i] = reader.readLine();
                }

                Level levelObj = new Level(level.getName(), name);

                for (int i = 0; i < world_tiles.length; i++) {
                    for (int j = 0; j < world_tiles.length; j++) {
                        switch (world_tiles[i].charAt(j)) {
                            case '#':
                                levelObj.addMapTile(j * World.maptile.getWidth(), i);
                                break;
                        }
                    }
                }

                elements.add(new LevelMenuElement(levelObj));
            }
        }

        System.out.println(elements.size());
    }


}
