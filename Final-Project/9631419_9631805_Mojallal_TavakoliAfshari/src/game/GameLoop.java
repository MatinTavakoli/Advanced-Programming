package game;

public class GameLoop implements Runnable {

	public static final int FPS = 50;
	
	private GameFrame canvas;
	private GameState state;

    /**
     * constructor for the game loop class
     * @param frame game frame
     */
	public GameLoop(GameFrame frame) {
		canvas = frame;
	}

    /**
     * initializes the game loop
     */
	public void init() {
		state = new GameState(canvas);
		canvas.addKeyListener(state.getKeyListener());
		canvas.addMouseListener(state.getMouseListener());
		canvas.addMouseMotionListener(state.getMouseMotionListener());
	}

    /**
     * runs game loop
     */
	@Override
	public void run() {
		boolean gameOver = false;
		while (!gameOver) {
			try {
				long start = System.currentTimeMillis();
				//
				state.update(canvas);
				canvas.render(state);
				gameOver = state.isGameOver();
				//
				long delay = (1000 / FPS) - (System.currentTimeMillis() - start);
				if (delay > 0)
					Thread.sleep(delay);
			} catch (InterruptedException ex) {
			}
		}
		canvas.render(state);
	}

    /**
     * getter for game state
     * @return game state
     */
    public GameState getState() {
        return state;
    }
}
