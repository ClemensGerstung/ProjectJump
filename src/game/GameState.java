package game;

public class GameState {

    public static enum State {
        MENU,
        PLAYING,
        PAUSE,
        GAMEOVER
    }

    private State state;

    private static GameState ourInstance = new GameState();

    public static GameState getInstance() {
        return ourInstance;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    private GameState() {
        state = State.MENU;
    }
}
