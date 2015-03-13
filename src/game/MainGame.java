package game;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.SlickException;


public class MainGame {

	public static void main(String[] args) throws SlickException {
		AppGameContainer jumper = new AppGameContainer(new GameApp("Jump And Run beta 0.7.5"));
        jumper.setDisplayMode(800, 450, false);
        jumper.setAlwaysRender(true);
        jumper.setShowFPS(false); // TODO: set to false
        jumper.start();
	}

}
