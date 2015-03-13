package game.ui;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

public abstract class MenuElement {
    protected String content;
    protected float x;
    protected float y;
    protected Color normal = Color.orange;
    protected Color hover = Color.yellow;
    protected Color active = Color.red;
    protected Color current = normal;

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

    public Color getNormal() {
        return normal;
    }

    public void setNormal(Color normal) {
        this.normal = normal;
    }

    public Color getHover() {
        return hover;
    }

    public void setHover(Color hover) {
        this.hover = hover;
    }

    public Color getActive() {
        return active;
    }

    public void setActive(Color active) {
        this.active = active;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public abstract void render(GameContainer gc, Graphics g) throws SlickException;
    public abstract void update(GameContainer gc) throws SlickException;

    public MenuElement(String content) {
        this.content = content;
    }

    public MenuElement(String content, float x, float y) {
        this.content = content;
        this.x = x;
        this.y = y;
    }
}
