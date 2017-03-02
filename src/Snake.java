import java.awt.Color;
import java.awt.Graphics;

public class Snake{
	Node head,tail;
	boolean alive = true;
	Node tem;
	public Snake()
	{
		Dir initDir = Dir.D;
		head = new Node(7,7,initDir);
		tail = new Node(7,6,initDir);
		tail.before = head;
		head.next = tail;	
	}
	
	public void changeDir(Dir dir)
	{
		head.dir = dir;
	}
	
	public void drawSnake(Graphics g)
	{
		for(Node n = head;n!=null;n=n.next){
			n.drawNode(g,Color.green);
		}
		if(tem != null) tem.eraseNode(g);
	}
	
	public void addInHead(int col,int row)
	{
		Node n = head;
		head = new Node(col,row,head.dir);
		head.next = n;
		n.before = head;		
	}
	
	public void eraseInTail()
	{
		tem = tail;
		tail = tail.before;
		tail.next = null;
	}
	
	public void updateSnake()
	{
		switch (head.dir) {
		case L:
			addInHead(head.col-1, head.row);
			break;
		case R:
			addInHead(head.col+1, head.row);
			break;
		case U:
			addInHead(head.col, head.row-1);
			break;
		case D:
			addInHead(head.col, head.row+1);
			break;
		default:
			break;
		}
		eraseInTail();
	}
	
	public void addToTail() {
		tail.next = tem;
		tem.before = tail;
		tail = tem;
		tail.next = null;
		updateSnake();
	}
}

class Node{
	public Dir dir;
	public Node next = null,before = null;
	public int col,row;
	
	Node(int col,int row,Dir dir)
	{
		this.col = col;
		this.row = row;
		this.dir = dir;
	}
	void drawNode(Graphics g,Color color)
	{
		Color c = g.getColor();
		g.setColor(color);
		g.fillRect(col*Yard.BLOCK_SIZE, row * Yard.BLOCK_SIZE, Yard.BLOCK_SIZE, Yard.BLOCK_SIZE);
		g.setColor(c);
	}
	void eraseNode(Graphics g)
	{
		drawNode(g, Yard.BgColor);
		Color c = g.getColor();
		int size = Yard.BLOCK_SIZE,x = col*size,y = row*size;
		g.setColor(Color.GRAY);
		g.drawLine(x, y, x+size, y);
		g.drawLine(x, y, x, y+size);
		g.drawLine(x+size, y, x+size, y+size);
		g.drawLine(x, y+size, x+size, y+size);
		g.setColor(c);
	}
}