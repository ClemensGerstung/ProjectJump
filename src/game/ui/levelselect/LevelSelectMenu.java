package game.ui.levelselect;

import game.GameState;
import game.World;
import game.ui.Button;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.newdawn.slick.*;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.util.ResourceLoader;


import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class LevelSelectMenu implements MouseListener {
    private List<LevelMenuElement> elements = new ArrayList<>();
    private Rectangle slider = new Rectangle(0, 0, 0, 20);
    private boolean pressed = false;
    private Button mainMenu = new Button("Hauptmenu".toUpperCase(), 25.f, 380.f);

    public LevelSelectMenu() {
        mainMenu.setClickPerformed(() -> GameState.getInstance().setState(GameState.State.MENU));
    }

    public void render(GameContainer gc, Graphics g) throws SlickException {
        Color c = g.getColor();

        for (LevelMenuElement element : elements) {
            element.render(gc, g);
        }

        g.setColor(new Color(40, 40, 40, 190));
        g.fillRect(0, gc.getHeight() - 20, gc.getWidth(), 20);
        g.setColor(Color.decode("#A32700"));
        slider.setY(gc.getHeight() - 20);
        slider.setWidth(LevelMenuElement.WIDTH * elements.size() < gc.getWidth() ? gc.getWidth() : gc.getWidth() /
                (LevelMenuElement.WIDTH * elements.size()) * gc.getWidth());
        g.fill(slider);
        g.setColor(c);
        mainMenu.render(gc, g);
    }

    public void update(GameContainer gc) throws SlickException {
        for (LevelMenuElement element : elements) {
            element.update(gc);
        }

        if (Keyboard.isKeyDown(Keyboard.KEY_LEFT)) {
            for (LevelMenuElement element : elements) {
                element.setPosition(-0.2f, 0);
            }
        }

        mainMenu.update(gc);
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
                                levelObj.addMapTile(j * Level.maptile.getWidth(), i);
                                break;
                        }
                    }
                }

                LevelMenuElement element = new LevelMenuElement(levelObj);
                element.setX(LevelMenuElement.WIDTH * elements.size());
                elements.add(element);
            }
        }
    }


    @Override
    public void mouseWheelMoved(int change) {

    }

    @Override
    public void mouseClicked(int button, int x, int y, int clickCount) {

    }

    @Override
    public void mousePressed(int button, int x, int y) {
        if (button == 0) {
            if (slider.intersects(new Rectangle(x, y, 1, 1))) {
                pressed = true;
            }
        }
    }

    @Override
    public void mouseReleased(int button, int x, int y) {
        pressed = false;
    }

    @Override
    public void mouseMoved(int oldx, int oldy, int newx, int newy) {

    }

    @Override
    public void mouseDragged(int oldx, int oldy, int newx, int newy) {

        if (pressed) {
            if (slider.getX() >= 0 && slider.getX() + slider.getWidth() <= 800) {
                slider.setX(slider.getX() + newx - oldx);
                if (elements.size() * LevelMenuElement.WIDTH >= elements.get(elements.size() - 1).getX() +
                        LevelMenuElement.WIDTH && 800 - elements.size() * LevelMenuElement.WIDTH <= elements.get(0).getX()) {
                    for (LevelMenuElement element : elements) {
                        element.setPosition(oldx - newx, 0);
                    }
                }
            }
            if (slider.getX() + slider.getWidth() > 800) {
                slider.setX(800 - slider.getWidth());
            }
            if (slider.getX() < 0) {
                slider.setX(0);
            }
            if (elements.get(0).getX() < 800 - elements.size() * LevelMenuElement.WIDTH) {
                for (int i = 0; i < elements.size(); i++) {
                    elements.get(i).setX(800 - elements.size() * LevelMenuElement.WIDTH + i * LevelMenuElement.WIDTH);
                }
            }
            if (elements.get(elements.size() - 1).getX() + LevelMenuElement.WIDTH > elements.size() * LevelMenuElement.WIDTH) {
                for (int i = 0; i < elements.size(); i++) {
                    elements.get(i).setX(i * LevelMenuElement.WIDTH);
                }
            }
        }
    }

    @Override
    public void setInput(Input input) {

    }

    @Override
    public boolean isAcceptingInput() {
        return true;
    }

    @Override
    public void inputEnded() {

    }

    @Override
    public void inputStarted() {

    }
}
