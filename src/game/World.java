package game;

import java.io.*;
import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class World {

    private Player player = null;

    private float speed = 1.f;
    private float endspeed = 1.f;
    private float distance = 0.f;
    private String name = "";
    private float start_offset = 0.f;
    private float end_offset = 0.f;
    private final float gravity = 0.05f;
    private List<WorldItem> maptiles = new ArrayList<WorldItem>();
    private static Image maptile;
    private final float acceleration;
    private float length;


    static {
        try {
            maptile = new Image("gfx/maptile.png");
        } catch (SlickException ignored) {
        }
    }

    private World(float speed, float endspeed, int length, String name) throws SlickException {
        this.endspeed = endspeed;
        this.name = name;
        this.length = length;
        this.speed = speed;
        this.acceleration = (float) ((endspeed - speed) / length) / 100.f;
    }

    public void render(GameContainer gc, Graphics g) throws SlickException {
        player.render(gc, g);
        for (WorldItem item : maptiles) {
            item.render(gc, g);
        }
    }

    public void update(GameContainer gc, int delta) throws SlickException {
        distance += (float) delta * speed / 50.f;

        for (WorldItem item : maptiles) {
            item.update(distance);
        }

        player.update(gc, this, delta);

        speed += acceleration;


    }

    public void addMapTile(float x, int line) {
        try {
            maptiles.add(new WorldItem(x, maptile.getHeight() * line + 36.f,
                    maptile));
        } catch (Exception ignored) {
        }
    }

    public float getDistance() {
        return distance;
    }

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public float getStart_offset() {
        return start_offset;
    }

    private void setStart_offset(float start_offset) {
        this.start_offset = start_offset;
    }

    public float getEnd_offset() {
        return end_offset;
    }

    private void setEnd_offset(float end_offset) {
        this.end_offset = end_offset;
    }

    public float getGravity() {
        return gravity;
    }

    public List<WorldItem> getMaptiles() {
        return maptiles;
    }

    protected void initPlayer(float x, float y) throws SlickException {
        player = new Player(x, y, 1.5f, "gfx/char.png");
    }

    @Override
    public String toString() {
        return "World [speed=" + speed + ", endspeed=" + endspeed
                + ", name=" + name
                + ", start_offset=" + start_offset + ", end_offset="
                + end_offset + "]";
    }

    public static World loadFromFile(String src) throws Exception {
        if (!src.endsWith(".config")) {
            throw new InvalidParameterException(
                    "Falsches Dateiformat! Bitte lade die *.config um ein Level zu laden.");
        }
        BufferedReader reader = new BufferedReader(new InputStreamReader(
                World.class.getClassLoader().getResourceAsStream(src)));
        String line = reader.readLine();
        String name = line.substring(line.indexOf(": ") + 2);
        line = reader.readLine();
        float speed = Float.parseFloat(line.substring(line.indexOf(": ") + 2));
        line = reader.readLine();
        float endspeed = Float
                .parseFloat(line.substring(line.indexOf(": ") + 2));
        line = reader.readLine();

        String lvl = line.substring(line.indexOf(": ") + 2);
        reader = new BufferedReader(new InputStreamReader(World.class
                .getClassLoader().getResourceAsStream("lvl/" + lvl)));

        String[] world_tiles = new String[9];
        for (int i = 0; i < 9; i++) {
            world_tiles[i] = reader.readLine();
        }

        World world = new World(speed, endspeed, world_tiles[0].length(), name);

        for (int i = 0; i < world_tiles.length; i++) {
            for (int j = 0; j < world_tiles[i].length(); j++) {
                switch (world_tiles[i].charAt(j)) {
                    case '#':
                        world.addMapTile(j * maptile.getWidth(), i);
                        break;
                    case 'S':
                        world.setStart_offset(j * maptile.getWidth());
                        world.initPlayer(j * maptile.getWidth(), i * maptile.getHeight());
                        break;
                    case 'E':
                        world.setEnd_offset(j * maptile.getWidth());
                        break;
                }
            }
        }

        return world;
    }
}
