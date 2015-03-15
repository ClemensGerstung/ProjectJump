package game;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.SlickException;


public class MainGame {

	public static void main(String[] args) throws SlickException {
		AppGameContainer jumper = new AppGameContainer(new GameApp("ProjectJump 1.0.0"));
        jumper.setDisplayMode(800, 450, false);
        jumper.setAlwaysRender(true);
        jumper.setShowFPS(false); // TODO: set to false
        jumper.start();
	}
}
