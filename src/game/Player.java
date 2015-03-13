package game;

import org.lwjgl.input.Keyboard;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Ellipse;
import org.newdawn.slick.geom.Shape;

public class Player {
    private float x;
    private float x_origin;
    private float y;
    private Image image;
    private float upspeed;
    private final float maxupspeed;
    private boolean jumping = false;
    private float time = 0;

    public Player(float x, float y, float maxupspeed, String src)
            throws SlickException {
        super();
        this.x = x;
        this.x_origin = x;
        this.y = y;
        this.image = new Image(src);
        this.maxupspeed = maxupspeed;
        this.upspeed = 0.f;
    }

    public void jump() {
        if (!jumping) {
            upspeed = maxupspeed;
            jumping = true;
        }
    }

    private void applyGravity(float gravity, int delta) {
        y += upspeed * -1 + gravity * time;
        time += delta / 10.f;
    }

    public void render(GameContainer gc, Graphics g) throws SlickException {
        g.drawImage(image, x, y);
    }

    public void update(GameContainer gc, World world, int delta)
            throws SlickException {

        applyGravity(world.getGravity(), delta);
        Ellipse e = getEllipse();
        for (WorldItem item : world.getMaptiles()) {
            if (e.intersects(item.getRectangle())) {
                if (jumping) {
                    y = item.getY() - image.getHeight() - 0.5f;
                } else {
                    if(e.getY() >= item.getY() && e.getY() + e.getHeight() <= item.getY() + item.getRectangle().getHeight() + 5.f){
                        System.out.println("move backwards");
                        x -= (float) delta * world.getSpeed() / 50.f;
                    }
                }
                time = 0;
                upspeed = 0;
                jumping = false;
            }
        }

        if (jumping) {
            if (x >= x_origin - 1.f && x <= x_origin + 100.f) {
                x += delta * 0.1f;
            }
        } else {
            if (x > x_origin + 1.f) {
                x -= delta * 0.05f;
            }
        }

        if (Keyboard.isKeyDown(Keyboard.KEY_SPACE)) {
            jump();
        }
    }

    public Ellipse getEllipse() {
        return new Ellipse(x + image.getWidth() / 2.f, y + image.getHeight() / 2.f, image.getWidth() / 2.f - image.getWidth() * 0.2f, image.getHeight() / 2.f);
    }
}
