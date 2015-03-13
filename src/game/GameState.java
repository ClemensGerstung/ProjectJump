package game;

public class GameState {

    public static enum State {
        MENU,
        PLAYING,
        PAUSE,
        GAMEOVER
    }

    private static GameState ourInstance = new GameState();

    public static GameState getInstance() {
        return ourInstance;
    }

    private GameState() {
    }
}
