import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class ParticulasTeleport {
	
	private int x;
	private int y;
	private BufferedImage[] animacion = new BufferedImage[4];
	private Imagenes imagenes;
	private int frame;
	private Game game;
	
	public ParticulasTeleport(int x,int y, Imagenes imagenes, Game game){
		this.x=x;
		this.y=y;
		this.frame=0;
		this.imagenes= imagenes;
		this.cargarImagenes();
		this.game=game;
		this.game.addParticulasTeleport(this);
	}
	
	private void cargarImagenes(){
		this.animacion=this.imagenes.getParticulasTeleport();
	}
	
	public void render(Graphics g){
		g.drawImage(this.animacion[frame],this.x,this.y,null);
	}
	
	public void frameUp(){
		this.frame++;
		if (this.frame==4) this.game.removeParticulasTeleport(this);
	}
	
	public boolean clean(){
		if (this.frame>=4){
			this.game.removeParticulasTeleport(this);
			return true;
		}
		return false;
	}
}
