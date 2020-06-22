package corrida;

import javax.swing.JFrame;

public class corridaMain extends JFrame{
	    public static void main(String[] args){
	        JFrame app = new JFrame();
	        Gamework game = new Gamework();
	        app.add(game);
	        app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        app.setSize(500, 720);
	        app.setVisible(true);
	    }
	}

