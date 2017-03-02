import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dialog;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class Yard extends JFrame implements KeyListener{

	private static final long serialVersionUID = 1L;
	private Snake snake;
	private MyCanvas can;
	private int score = 0;
	private JPanel jp_score;
	private JLabel jl_score;
	static final int BLOCK_SIZE = 15;
	static final int COL = 20;
	static final int ROW = 23;
	static Color BgColor;
	
	private Egg egg = null;
	
	public static Yard yard = null;
	
	public static Yard getInstance()
	{
		return new Yard();
	}
	
	private Yard()
	{
		setVisible(true);
		setSize(300 + 8, 400 + 5);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);
		setTitle("Snake");
		jl_score = new JLabel("得分："+ score);
		jp_score = new JPanel();
		jp_score.add(jl_score);
		add(jp_score,BorderLayout.NORTH);
		can = new MyCanvas();
		snake = new Snake();
		egg = new Egg(snake);
		add(can,BorderLayout.CENTER);
		addKeyListener(this);
		can.run();	
	}
	
	@Override
	public void keyPressed(KeyEvent ke) {
		Dir d = snake.head.dir;
		switch (ke.getKeyCode()) {
		case KeyEvent.VK_UP:
			if(d!=Dir.D) snake.changeDir(Dir.U);
			break;
		case KeyEvent.VK_DOWN:
			if(d!=Dir.U) snake.changeDir(Dir.D);
			break;
		case KeyEvent.VK_LEFT:
			if(d!=Dir.R) snake.changeDir(Dir.L);
			break;
		case KeyEvent.VK_RIGHT:
			if(d!=Dir.L) snake.changeDir(Dir.R);
			break;
		default:
			break;
		}
		
	}

	@Override
	public void keyReleased(KeyEvent arg0) {}

	@Override
	public void keyTyped(KeyEvent arg0) {}
	
	private class MyCanvas extends Canvas implements Runnable{
		private static final long serialVersionUID = 1L;

		@Override
		public void update(Graphics g) {
			Color c = g.getColor();
			egg.drawEgg(g, Color.red);
			snake.drawSnake(g);
			g.setColor(c);
		}

		@Override
		public void paint(Graphics g) {
			BgColor = can.getBackground();
			g.setColor(Color.gray);
			for (int i = 0; i <= COL; i++) {
				g.drawLine(i*BLOCK_SIZE, 0, i*BLOCK_SIZE, ROW * BLOCK_SIZE);
			}
			for (int i = 0; i <= ROW; i++) {
				g.drawLine(0, i*BLOCK_SIZE, COL * BLOCK_SIZE, i*BLOCK_SIZE);
			}
			snake.drawSnake(g);
			egg.drawEgg(g, Color.red);
			g.setColor(BgColor);
		}

		@Override
		public void run() {
			while(snake.alive){
				int x = snake.head.col, y = snake.head.row;
				if(x<0 || y<0 || x>Yard.COL-1 || y>Yard.ROW-1) snake.alive = false;
				for(Node n = snake.head.next; n != null; n = n.next){
					if(x==n.col && y==n.row) snake.alive = false;
				}
				if(!snake.alive) break;
				if(x == egg.col && y == egg.row){
					snake.addToTail();
					egg = new Egg(snake);
					score += 20;
					jl_score.setText("得分："+score);
				}
				
				snake.updateSnake();
				x = snake.head.col;
				y = snake.head.row;
				if(x<0 || y<0 || x>Yard.COL-1 || y>Yard.ROW-1) break;
				this.repaint();
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}	
			}
			new Dialog(new GameOverDialog());
		}
	}
}


