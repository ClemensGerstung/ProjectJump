package game;

import game.ui.Button;
import game.ui.Menu;
import game.ui.TextArea;
import game.ui.levelselect.LevelSelectMenu;
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
    private Menu mainMenu = new Menu();
    private Menu pauseMenu = new Menu();
    private Menu info = new Menu();
    private Menu win = new Menu();
    private Menu gameOver = new Menu();
    private LevelSelectMenu levelSelectMenu = new LevelSelectMenu();
    private TrueTypeFont font;

    @Override
    public void init(GameContainer gc) throws SlickException {
        gc.getInput().addMouseListener(levelSelectMenu);

        this.background = new Background(2.5f, "gfx/background.jpg");
        this.font = new TrueTypeFont(loadFont("fonts/font.ttf"), false);
        gc.setDefaultFont(font);

        initMenu(gc);
        initPauseMenu(gc);
        initInfo(gc);
        initWin(gc);
        initGameOver(gc);

        try {
            levelSelectMenu.init();
        } catch (Exception e) {
            e.printStackTrace();
        }

//        try {
//            world = World.loadFromFile("lvl/level_1.config");
//            System.out.println(world);
//        } catch (Exception ignored) {
//        }

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
                gameOver.render(gc, g);
                break;
            case WIN:
                world.render(gc, g);
                win.render(gc, g);
                break;
            case INFO:
                info.render(gc, g);
                break;
            case SELECTLEVEL:
                levelSelectMenu.render(gc, g);
                break;
        }
    }

    @Override
    public void update(GameContainer gc, int delta) throws SlickException {
        background.update(gc, delta);
        world = GameState.getInstance().getWorld();
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
                gameOver.update(gc);
                break;
            case WIN:
                win.update(gc);
                break;
            case INFO:
                info.update(gc);
                break;
            case SELECTLEVEL:
                levelSelectMenu.update(gc);
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
        info.setClickPerformed(() -> GameState.getInstance().setState(GameState.State.INFO));
        mainMenu.addChild(info);
        Button loadlvl = new Button("LEVELAUSWAHL");
        loadlvl.setClickPerformed(() -> GameState.getInstance().setState(GameState.State.SELECTLEVEL));
        mainMenu.addChild(loadlvl);
        Button close = new Button("SCHLIESSEN");
        close.setClickPerformed(gc::exit);
        mainMenu.addChild(close);
        mainMenu.centerAndSpread(gc, font);
    }

    private void initPauseMenu(GameContainer gc) {
        game.ui.TextArea textArea = new TextArea("PAUSE");
        pauseMenu.addChild(textArea);
        Button play = new Button("WEITERSPIELEN");
        play.setClickPerformed(() -> GameState.getInstance().setState(GameState.State.PLAYING));
        pauseMenu.addChild(play);
        Button restart = new Button("NEUSTARTEN");
        restart.setClickPerformed(() -> {
            try {
                world = World.loadFromFile("lvl/level_1.config");
            } catch (Exception ignored) {
            }
            GameState.getInstance().setState(GameState.State.PLAYING);
        });
        pauseMenu.addChild(restart);
        Button menu = new Button("ZUM HAUPTMENU");
        menu.setClickPerformed(() -> GameState.getInstance().setState(GameState.State.MENU));
        pauseMenu.addChild(menu);

        pauseMenu.centerAndSpread(gc, font);
    }

    private void initInfo(GameContainer gc) {
        game.ui.TextArea textArea = new TextArea("INFO");
        info.addChild(textArea);
        game.ui.TextArea text = new TextArea("Diese Spiel wurde von Gott".toUpperCase());
        info.addChild(text);
        text = new TextArea("geschrieben!".toUpperCase());
        info.addChild(text);

        Button menu = new Button("ZUM HAUPTMENU");
        menu.setClickPerformed(() -> GameState.getInstance().setState(GameState.State.MENU));
        info.addChild(menu);

        info.centerAndAlignTop(gc, font, 15.f, 40.f);
    }

    private void initWin(GameContainer gc) {
        game.ui.TextArea textArea = new TextArea("GESCHAFFT");
        win.addChild(textArea);
        Button loadlvl = new Button("LEVELAUSWAHL");
        loadlvl.setClickPerformed(() -> GameState.getInstance().setState(GameState.State.SELECTLEVEL));
        win.addChild(loadlvl);
        Button menu = new Button("ZUM HAUPTMENU");
        menu.setClickPerformed(() -> GameState.getInstance().setState(GameState.State.MENU));
        win.addChild(menu);
        win.centerAndSpread(gc, font);
    }

    private void initGameOver(GameContainer gc) {
        game.ui.TextArea textArea = new TextArea("NICHT GESCHAFFT");
        gameOver.addChild(textArea);
        Button restart = new Button("NEUSTARTEN");
        restart.setClickPerformed(() -> {
            try {
                world = World.loadFromFile("lvl/level_1.config");
            } catch (Exception ignored) {
            }
            GameState.getInstance().setState(GameState.State.PLAYING);
        });
        gameOver.addChild(restart);
        Button menu = new Button("ZUM HAUPTMENU");
        menu.setClickPerformed(() -> GameState.getInstance().setState(GameState.State.MENU));
        gameOver.addChild(menu);

        gameOver.centerAndSpread(gc, font);
    }

}