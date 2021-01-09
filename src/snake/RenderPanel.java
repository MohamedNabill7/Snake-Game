/*The code below shows the RenderPanel class. renderPanel class extends JPanel. It has a default constructor which initialises the render panel.
The action events on the screen move the snake based on the current location of the snake and apple. If the collision happens,the number of snake parts increase. 
If the snake hits the walls, the game is over. */
package snake;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

import javax.swing.JPanel;

@SuppressWarnings("serial")
/*
  @author Mohamed Nabil
 */
public class RenderPanel extends JPanel
{

	public static final Color GREEN = new Color(1666073);

	@Override
        // Used to paint our components to the screen
	protected void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		
		Snake snake = Snake.snake;
                // draw the screen
		g.setColor(GREEN);
		
		g.fillRect(0, 0, 800, 700);
                
		g.setColor(Color.WHITE);

		for (Point point : snake.snakeParts)
		{
                         // Snake's head   
			g.fillRect(point.x * Snake.SCALE, point.y * Snake.SCALE, Snake.SCALE, Snake.SCALE);
		}
		// Body of snake
		g.fillOval(snake.body.x * Snake.SCALE, snake.body.y * Snake.SCALE, Snake.SCALE, Snake.SCALE);
		// draw the food
		g.setColor(Color.RED);
		
		g.fillRect(snake.Apple.x * Snake.SCALE, snake.Apple.y * Snake.SCALE, Snake.SCALE, Snake.SCALE);
		
		String string =  "Score: " + snake.score + " , Length: " + snake.tailLength ;
		// draw the snake
		g.setColor(Color.white);
		
		g.drawString(string, (int) (getWidth() / 2 - string.length() * 2.5f), 10);

		string = "Game Over!";

		if (snake.over)
		{
			g.drawString(string, (int) (getWidth() / 2 - string.length() * 2.5f), (int) snake.dim.getHeight() / 4);
		}

		string = "Paused!";

		if (snake.paused && !snake.over)
		{
			g.drawString(string, (int) (getWidth() / 2 - string.length() * 2.5f), (int) snake.dim.getHeight() / 4);
		}
	}
}