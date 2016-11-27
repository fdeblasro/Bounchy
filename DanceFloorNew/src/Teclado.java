

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Teclado extends KeyAdapter{
	
	Game game;
	
	public Teclado(Game game){
		this.game = game;
		
	}
	public void keyPressed(KeyEvent e){
	
		game.keyPressed(e);
		
	}
	
	public void keyReleased(KeyEvent e){
		
		game.keyReleased(e);
		
		
	}
	
	

}
