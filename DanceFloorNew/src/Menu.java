import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Menu {
	
	private Game game;
	private BufferedImage ssBaldosas;
	private BufferedImage botonPlay;
	private BufferedImage botonPlayPulsado;
	private BufferedImage botonCredits;
	private BufferedImage botonCreditsPulsado;
	private BufferedImage botonSalir;
	private BufferedImage botonSalirPulsado;
	private RandomGenerator random;
	public static boolean boton1 = false;
	public static boolean boton2 = false;
	public static boolean boton3 = false;

	public Menu(Imagenes imagen, Game game){
		this.game=game;
		this.ssBaldosas=imagen.getSSBaldosas();
		this.botonPlay = imagen.getBotonPlay();
		this.botonPlayPulsado = imagen.getBotonPlayPulsado();
		this.botonCredits = imagen.getBotonCredits();
		this.botonCreditsPulsado = imagen.getBotonCreditsPulsado();
		this.botonSalir = imagen.getBotonSalir();
		this.botonSalirPulsado = imagen.getBotonSalirPulsado();
		this.random = new RandomGenerator();
	}
	
	public void render(Graphics g){
		for (int i=0;i<9;++i){
			for (int j=0;j<8;++j){
				if ((i>=2 && i<=5) && (j>=2 && j<=5)){
					g.drawImage(this.ssBaldosas.getSubimage(384,0,64, 64), j*64, i*64, null);
				}
				int n = random.randomInt(5);
				g.drawImage(this.ssBaldosas.getSubimage(64*n, 0, 64, 64), j*64, i*64, null);
			}
		}
		if (boton1) this.game.ESTADO="jugar";
		else g.drawImage(this.botonPlay, 64*3, 64*3, null);
		if (boton2)  {
			this.game.ESTADO="credits";
			Game.credits.setY(Game.ALTO);
			boton2=false;
		}
		else g.drawImage(this.botonCredits, 64*4, 64*4, null);
		if (boton3)  this.game.gameOver();
		else g.drawImage(this.botonSalir, 64*5, 64*5, null);
	}
	
	public static Rectangle rectanglePlay(){
		return new Rectangle(64*3,64*3,128,64);
	}
	
	public static Rectangle rectangleCredits(){
		return new Rectangle(64*4,64*4,128,64);
	}
	
	public static Rectangle rectangleSalir(){
		return new Rectangle(64*5,64*5,128,64);
	}
}
