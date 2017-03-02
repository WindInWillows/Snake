import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;


public class GameOverDialog extends JFrame{
	private JButton jb_ok,jb_esc;
	private JLabel jl;
	public GameOverDialog() {
		jl = new JLabel("重新开始?");
		jb_ok = new JButton("确定");
		jb_esc = new JButton("取消");
		setTitle("游戏结束");
		setSize(100, 75);
		setVisible(true);
		
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		
		add(jl,BorderLayout.NORTH);
		add(jb_ok,BorderLayout.WEST);
		add(jb_esc,BorderLayout.EAST);
		

		
		jb_ok.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				closeDialog();
				restart();
			}
		});
		
		jb_esc.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(Yard.yard != null) Yard.yard = null;
				closeDialog();
			}
		});
	}
	private void closeDialog()
	{
		this.dispose();
		System.gc();
	}
	
	private void restart()
	{
		Yard.yard.dispose();
//		System.gc();
		Yard.yard = Yard.getInstance();
	}
	
}
