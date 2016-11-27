import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;


public class PresentationScreen {
	
	private BufferedImage splash;
	private long alpha;
	private long timer;
	private long lastTimer;
	
	public PresentationScreen(Imagenes imagen){
		this.splash = imagen.getSplash();
		this.timer=System.currentTimeMillis();
		this.lastTimer=timer+2550;
		this.alpha=0;
	}
	
	public void tick(){
		this.timer=System.currentTimeMillis();
		this.alpha=(this.lastTimer-this.timer)/20;
		if (this.alpha<=-254) Game.ESTADO="menu";
	}
	
	public void render(Graphics g){
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, Game.ANCHO+30, Game.ALTO+30);
		g.drawImage(this.splash, 0, 0, Game.ANCHO, Game.ALTO, null);
		if (this.alpha>=0) g.setColor(new Color(0, 0, 0, (int)this.alpha));
		else g.setColor(new Color(0, 0, 0, (int)-this.alpha));
		g.fillRect(0, 0, Game.ANCHO+30, Game.ALTO+30);
	}
}
