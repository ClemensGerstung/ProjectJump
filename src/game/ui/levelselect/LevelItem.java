package game.ui.levelselect;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class LevelItem {
    private float x;
    private float y;
    private Image image;

    public LevelItem(float x, float y, Image image) {
        this.x = x;
        this.y = y;
        this.image = image;
    }

    public void render(GameContainer gc, Graphics g) throws SlickException {
        g.drawImage(image, x, y);
    }

    public void update(GameContainer gc) throws SlickException {

    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }
}
