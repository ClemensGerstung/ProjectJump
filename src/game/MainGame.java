package game;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.SlickException;


public class MainGame {

	public static void main(String[] args) throws SlickException {
		AppGameContainer jumper = new AppGameContainer(new GameApp("Jump And Run alpha 0.0.5"));
        jumper.setDisplayMode(800, 450, false);
        jumper.setAlwaysRender(true);
        jumper.setShowFPS(true); // TODO: set to false
        jumper.start();
	}

}
