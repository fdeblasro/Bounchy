import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class UI {
	
	private Game game;
	
	public UI(Game game){
		this.game=game;
	}
	
	public void render(Graphics g){
		g.setColor(Color.RED);
		g.drawRect(512-220,512+7,200,50);
		for (int i=0;i<this.game.player.getHP();++i){
			g.fillRect(297+(40*i), 524, 30, 40);
		}
		
		g.fillRect(30, 542, 200, 2);
		g.fillPolygon(new int[] {200, 210, 220}, new int[] {572-5, 542-5,572-5}, 3);
	}
	
	
}
