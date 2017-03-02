import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

public class Egg {

    int row = 0;
	int col = 0;
	public Egg(int x,int y)
	{
		row = y;
		col = x;
	}
	
	public Egg(Snake s) {
		while(! rand(s));
	}
	
	private boolean rand(Snake s)
	{
		col = new Random().nextInt();
		if(col<0)col = -col;
		col%=Yard.COL;
		
		row = new Random().nextInt();
		if(row<0)row = -row;
		row %= Yard.ROW;
		for(Node n = s.head;n!=null;n=n.next){
			if(col==n.col && row==n.row)return false;
		}
		return true;
	}

	public void drawEgg(Graphics g, Color color)
	{
		Color c = g.getColor();
		g.setColor(color);
		g.fillRect(col * Yard.BLOCK_SIZE, row * Yard.BLOCK_SIZE, Yard.BLOCK_SIZE, Yard.BLOCK_SIZE);
		g.setColor(c);
	}
}

