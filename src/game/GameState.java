package game;

public class GameState {

    public static enum State {
        MENU,
        PLAYING,
        PAUSE,
        GAMEOVER,
        WIN,
        INFO,
        SELECTLEVEL
    }

    private State state;
    private World world;
    private String levelfile = "level_1.config";

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
        try {
            world = World.loadFromFile("lvl/level_1.config");
        } catch (Exception ignored) {
        }
    }

    public World getWorld() {
        return world;
    }

    public void setWorld(String level) throws Exception {
        this.levelfile = level;
        world = World.loadFromFile("lvl/" + level);
    }

    public void resetWorld() throws Exception {
        world = World.loadFromFile("lvl/" + levelfile);
    }
}
