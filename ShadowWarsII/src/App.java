/**
 * Project 2B for SWEN20003: Object Oriented Software Development 2018 (Semester 1)
 * by Daniel Benjamin Masters (583334), University of Melbourne
 * 
 * Shadow Wars game (version 2)
 * 
 * N.B. Extension granted by Eleanor McMurtry on 14/5/18 via email. 
 * Due to family circumstances beyond my control, I was unable to devote as much time as I wanted to this project; therefore, some features are not fully implemented.
 */

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Input;

/**
 * Main class for the game
 * Handles initialisation, input and rendering
 */
public class App extends BasicGame {
	/** Screen width, in pixels */
    public static final int SCREEN_WIDTH = 1024;
    /** Screen height, in pixels */
    public static final int SCREEN_HEIGHT = 768;
    /** X- and y-coordinates for score text position */
    public static final int SCORE_X = 20, SCORE_Y = 738;
    /** Screen boundary value */
	public static final int SCREEN_BOUNDARY = 0;
    
    private World world;

    public App() {    	
        super("Shadow Wars");
    }

    @Override
    public void init(GameContainer gc)
    		throws SlickException {
    	world = new World();
    }

    /** Update the game state for a frame.
     * @param gc The Slick game container object
     * @param delta Time passed since last frame (milliseconds)
     */
    @Override
    public void update(GameContainer gc, int delta)
    		throws SlickException {
        // Get data about the current input (keyboard state).
        Input input = gc.getInput();
        world.update(input, delta);
    }

    /** Render the entire screen, so it reflects the current game state.
     * @param gc The Slick game container object
     * @param g The Slick graphics object, used for drawing
     */
    public void render(GameContainer gc, Graphics g)
    		throws SlickException {
    	world.render();
    	g.drawString("Score: " + world.getScore(), SCORE_X, SCORE_Y);
    }

    /** Start-up method. Creates the game and runs it
     * @param args Command-line arguments (ignored)
     */
    public static void main(String[] args)
    		throws SlickException {
        AppGameContainer app = new AppGameContainer(new App());
        app.setShowFPS(true);
        app.setDisplayMode(SCREEN_WIDTH, SCREEN_HEIGHT, false);
		app.start();
    }
}