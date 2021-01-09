package snake;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;


import javax.swing.JFrame;
import javax.swing.Timer;

/**
 * @author Mohamed Nabil
 */
public class Snake implements ActionListener, KeyListener
{
           
	public static Snake snake;

	public JFrame jframe;

	public RenderPanel renderPanel;

	public Timer timer = new Timer(20, this);
        // list of objects which i will define as point
	public ArrayList<Point> snakeParts = new ArrayList<Point>();

	public static final int UP = 0, DOWN = 1, LEFT = 2, RIGHT = 3, SCALE = 10;

	public int mark = 0, direction = DOWN,score ,tailLength = 10;

	public Point body, Apple;

	public Random random;

	public boolean over = false, paused;

	public Dimension dim;

	public Snake()
	{
                // define dimension for screen
                dim = Toolkit.getDefaultToolkit().getScreenSize();
		// set title for jframe
                jframe = new JFrame("Snake Game");
                // for jframe is visible
		jframe.setVisible(true);
                // set dimension for screen
		jframe.setSize(805, 700);
		jframe.setResizable(false);
                // set location for screen
		jframe.setLocation(dim.width / 2 - jframe.getWidth() / 2, dim.height / 2 - jframe.getHeight() / 2);
		jframe.add(renderPanel = new RenderPanel());
                // for exit the operation
		jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jframe.addKeyListener(this);
		startGame();
	}
	public void startGame()
	{
                // Game features at the start
		over = false;
		paused = false;
		tailLength = 5;
		mark = 0;
		direction = DOWN;
                score = 0;
		body = new Point(0, -1);
		random = new Random();
		snakeParts.clear();
		// set apple random in screen
                Apple = new Point(random.nextInt(79), random.nextInt(66));
		timer.start();
	}

	@Override
	public void actionPerformed(ActionEvent arg0)
	{
                //call rnderpanal.repaint that every tick 
		renderPanel.repaint();
		mark++;
                
		if (mark % 2 == 0 && body != null && !over && !paused)
		{
                        
			snakeParts.add(new Point(body.x, body.y));
                        // to determine the snake direction
			if (direction == UP)
			{
				if (body.y - 1 >= 0 && noTailAt(body.x, body.y - 1))
				{
					body = new Point(body.x, body.y - 1);
				}
				else
				{
					over = true;

				}
			}

			if (direction == DOWN)
			{
				if (body.y + 1 < 67 && noTailAt(body.x, body.y + 1))
				{
					body = new Point(body.x, body.y + 1);
				}
				else
				{
					over = true;
				}
			}

			if (direction == LEFT)
			{
				if (body.x - 1 >= 0 && noTailAt(body.x - 1, body.y))
				{
					body = new Point(body.x - 1, body.y);
				}
				else
				{
					over = true;
				}
			}

			if (direction == RIGHT)
			{
				if (body.x + 1 < 80 && noTailAt(body.x + 1, body.y))
				{
					body = new Point(body.x + 1, body.y);
				}
				else
				{
					over = true;
				}
			}
                        
			if (snakeParts.size() > tailLength)
			{
				snakeParts.remove(0);

			}
                        // Eat the snake food and the length will increase by 1 for each apple,score will increase by 5 for each apple   
			if (Apple != null)
			{
				if (body.equals(Apple))
				{
					tailLength++;
                                        score += 5;
                                        Apple.setLocation(random.nextInt(15), random.nextInt(8));                    
				}
			}
		}
	}
        
	public boolean noTailAt(int x, int y)
	{
		for (Point point : snakeParts)
		{
			if (point.equals(new Point(x, y)))
			{
				return false;
			}
		}
		return true;
	}
        // this is main class     
	public static void main(String[] args)
	{
		snake = new Snake();
	}


        @Override
         // To control the snake arrows 
	public void keyPressed(KeyEvent e)
	{
		int key = e.getKeyCode();

		if ((key == KeyEvent.VK_A || key == KeyEvent.VK_LEFT) && direction != RIGHT)
		{
			direction = LEFT;
		}

		if ((key == KeyEvent.VK_D || key == KeyEvent.VK_RIGHT) && direction != LEFT)
		{
			direction = RIGHT;
		}

		if ((key == KeyEvent.VK_W || key == KeyEvent.VK_UP) && direction != DOWN)
		{
			direction = UP;
		}

		if ((key == KeyEvent.VK_S || key == KeyEvent.VK_DOWN) && direction != UP)
		{
			direction = DOWN;
		}
		if (key == KeyEvent.VK_SPACE)
		{
			if (over)
			{
				startGame();
			}
			else
			{
				paused = !paused;
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent e)
	{
	}

	@Override
	public void keyTyped(KeyEvent e)
	{
	}

}