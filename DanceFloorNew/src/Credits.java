import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Credits {
	
	private int y;
	
	public Credits(){
		this.y=Game.ALTO+30;
	}
	
	public void render(Graphics g){
		if (this.y>30) this.y-=1;
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, Game.ANCHO+64,Game.ALTO+64);
		g.setColor(Color.WHITE);
		g.setFont(new Font("Verdana", Font.BOLD, 20));
		g.drawString("Coding and Graphics:", 120, this.y);
		g.drawString("Ferran de Blas", 120, this.y+40);
		g.drawString("Graphics Skeleton: ", 120, this.y+100);
		g.drawString("dogchicken", 120, this.y+140);
		g.drawString("From OpenGameArt.org", 120, this.y+180);
		g.drawString("Press SPACE to go to MENU", 120, this.y+320);
	}
	
	public void setY(int n){
		this.y=n;
	}
}
