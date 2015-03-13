package game;

import game.ui.Button;
import game.ui.Menu;
import org.lwjgl.input.Keyboard;
import org.newdawn.slick.*;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.util.ResourceLoader;

import java.awt.*;
import java.awt.Font;
import java.io.IOException;
import java.io.InputStream;

public class GameApp extends BasicGame {
    public GameApp(String title) {
        super(title);
    }

    private World world;
    private Background background;
    private Menu mainMenu;
    private Menu pauseMenu;
    private TrueTypeFont font;

    @Override
    public void init(GameContainer gc) throws SlickException {
        this.background = new Background(2.5f, "gfx/background.jpg");
        this.font = new TrueTypeFont(loadFont("fonts/font.ttf"), false);
        gc.setDefaultFont(font);

        this.mainMenu = new Menu();
        initMenu(gc);

        this.pauseMenu = new Menu();
        initPauseMenu(gc);

        try {
            world = World.loadFromFile("lvl/level_1.config");
        } catch (Exception e) {
        }

    }

    @Override
    public void render(GameContainer gc, Graphics g) throws SlickException {
        background.render(gc, g);
        g.setFont(font);

        switch (GameState.getInstance().getState()) {
            case MENU:
                mainMenu.render(gc, g);
                break;
            case PLAYING:
                world.render(gc, g);
                break;
            case PAUSE:
                world.render(gc, g);
                pauseMenu.render(gc, g);
                break;
            case GAMEOVER:
                world.render(gc, g);
                break;
            case WIN:
                world.render(gc, g);
                break;
        }
    }

    @Override
    public void update(GameContainer gc, int delta) throws SlickException {
        background.update(gc, delta);

        switch (GameState.getInstance().getState()) {
            case MENU:
                mainMenu.update(gc);
                break;
            case PLAYING:
                world.update(gc, delta);
                break;
            case PAUSE:
                pauseMenu.update(gc);
                break;
            case GAMEOVER:

                break;
            case WIN:
                break;
        }

        if (Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)) {
            GameState.getInstance().setState(GameState.State.PAUSE);
        }
    }

    private Font loadFont(String font) {
        InputStream inputStream = ResourceLoader.getResourceAsStream(font);
        try {
            return Font.createFont(Font.TRUETYPE_FONT, inputStream).deriveFont(36f);
        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private void initMenu(GameContainer gc) {
        Button play = new Button("SPIELEN");
        play.setClickPerformed(() -> GameState.getInstance().setState(GameState.State.PLAYING));
        mainMenu.addChild(play);
        Button info = new Button("INFO");
        info.setClickPerformed(() -> {
        });
        mainMenu.addChild(info);
        Button loadlvl = new Button("LEVELAUSWAHL");
        loadlvl.setClickPerformed(() -> {
        });
        mainMenu.addChild(loadlvl);
        Button close = new Button("SCHLIESSEN");
        close.setClickPerformed(() -> gc.exit());
        mainMenu.addChild(close);
        mainMenu.centerAndSpread(gc, font);
    }

    private void initPauseMenu(GameContainer gc) {
        Button play = new Button("WEITER SPIELEN");
        play.setClickPerformed(() -> GameState.getInstance().setState(GameState.State.PLAYING));
        pauseMenu.addChild(play);
        Button menu = new Button("ZUM HAUPTMENU");
        menu.setClickPerformed(() -> GameState.getInstance().setState(GameState.State.MENU));
        pauseMenu.addChild(menu);

        pauseMenu.centerAndSpread(gc, font);
    }
}
