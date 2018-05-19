import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import org.newdawn.slick.SlickException;

/**
 * Enemies class - initialises and updates each of the enemies read from the waves.txt file
 *
 */
public class Enemies {
	/** Index constants used for reading the tokenised lines from the waves.txt file */
	public static final int FIRST_INDEX = 0, X_INDEX = 1, DELAY_INDEX = 2;
	/** Comment token used in the waves.txt file */
	public static final char COMMENT = '#';
	private static ArrayList<BasicEnemy> enemies = new ArrayList<BasicEnemy>();
	
	/** Reads waves.txt and initialises the enemies accordingly into an ArrayList
	 * @throws SlickException
	 */
	public static void init() throws SlickException {
		// Initialise ArrayList of basicEnemies
	    String fileName = "res/waves.txt";
	    String line = null;
	    try {
	        FileReader fileReader = new FileReader(fileName);
	        BufferedReader bufferedReader = new BufferedReader(fileReader);
	        String[] splitLine;
	        while((line = bufferedReader.readLine()) != null) {
	            if (line.charAt(FIRST_INDEX) != COMMENT) {
	            	splitLine = line.split(",");
	            	float x = Float.parseFloat(splitLine[X_INDEX]);
	            	float delay = Float.parseFloat(splitLine[DELAY_INDEX]);
	            	if (splitLine[FIRST_INDEX].equals("BasicEnemy")) {
	            		BasicEnemy basicEnemy = new BasicEnemy(x, delay);
	            		enemies.add(basicEnemy);
	            		Score.getScoreByEnemy().put(basicEnemy, basicEnemy.isDead());
	            		Score.getKillsByEnemyCollision().put(basicEnemy, basicEnemy.getHasCollided());
	            	} else if (splitLine[FIRST_INDEX].equals("SineEnemy")) {
	            		SineEnemy sineEnemy = new SineEnemy(x, delay);
	            		enemies.add(sineEnemy);
	            		Score.getScoreByEnemy().put(sineEnemy, sineEnemy.isDead());
	            		Score.getKillsByEnemyCollision().put(sineEnemy, sineEnemy.getHasCollided());
	            	} else if (splitLine[FIRST_INDEX].equals("BasicShooter")) {
	            		BasicShooter basicShooter = new BasicShooter(x, delay);
	            		enemies.add(basicShooter);
	            		Score.getScoreByEnemy().put(basicShooter, basicShooter.isDead());
	            		Score.getKillsByEnemyCollision().put(basicShooter, basicShooter.getHasCollided());
	            	} else if (splitLine[FIRST_INDEX].equals("Boss")) {
	            		Boss boss = new Boss(x, delay);
	            		enemies.add(boss);
	            		Score.getScoreByEnemy().put(boss, boss.isDead());
	            		Score.getKillsByEnemyCollision().put(boss, boss.getHasCollided());
	            	}
	            }
	        }
	        bufferedReader.close();         
	    }
	    catch(IOException ioe) {
	        ioe.printStackTrace();
	    }
	}
	/** Returns the generateed ArrayList of enemies
	 * @return
	 */
	public static ArrayList<BasicEnemy> getEnemies() {
		return enemies;
	}
	/**
	 * @param elapsedTime Time elapsed since game commenced (milliseconds)
	 * @param delta delta Time passed since last frame (milliseconds)
	 * @throws SlickException
	 */
	public void update(int elapsedTime, int delta) throws SlickException {
		// If an enemy has been shot, remove it from the ArrayList and adjust its size accordingly
		for (int i = 0; i < enemies.size(); i++) {
			BasicEnemy enemy = enemies.get(i);
			enemy.update(elapsedTime, delta);
            if (enemy.isDead()) {
            	int lastEnemy = enemies.size()-1;
            	enemies.set(i, enemies.get(lastEnemy));
            	enemies.remove(lastEnemy);
            	Score.getScoreByEnemy().put(enemy, enemy.isDead());
            }
        }
		Score.update();
	}
}
