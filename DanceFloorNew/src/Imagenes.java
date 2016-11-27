import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Imagenes {
	private BufferedImage ssMenu;
	private BufferedImage splash;
	private BufferedImage ssSkeleton;
	private BufferedImage ssBaldosas;
	private BufferedImage ssParticulasTeleport;
	private BufferedImage ssParticulasHeal;
	private BufferedImage sombra;
	private BufferedImage baldosaAzul;
	private BufferedImage baldosaVerde;
	private BufferedImage baldosaAmarillo;
	private BufferedImage baldosaRojo;
	private BufferedImage baldosaMorado;
	private BufferedImage baldosaGris;
	private BufferedImage baldosaMarron;
	
	public Imagenes(){
		try{
			this.ssMenu = ImageIO.read(Imagenes.class.getResourceAsStream("ssMenu.png"));
			this.splash = ImageIO.read(Imagenes.class.getResourceAsStream("PoloGames.png"));
			this.ssBaldosas = ImageIO.read(Imagenes.class.getResourceAsStream("ssBaldosas.png"));
			this.ssSkeleton = ImageIO.read(Imagenes.class.getResourceAsStream("ssSkeleton.png"));
			this.ssParticulasTeleport = ImageIO.read(Imagenes.class.getResourceAsStream("ssParticulasTeleport.png"));
			this.sombra = ImageIO.read(Imagenes.class.getResourceAsStream("Sombra.png"));
			this.ssParticulasHeal = ImageIO.read(Imagenes.class.getResourceAsStream("ssParticulasHeal.png"));
		}
		catch (IOException e){
			e.printStackTrace();
		}
		this.cargarImagenesBaldosas();
	}
	
	private void cargarImagenesBaldosas(){
		this.baldosaAzul = this.ssBaldosas.getSubimage(0,0,64, 64);
		this.baldosaVerde = this.ssBaldosas.getSubimage(64,0,64, 64);
		this.baldosaAmarillo = this.ssBaldosas.getSubimage(128,0,64, 64);
		this.baldosaRojo = this.ssBaldosas.getSubimage(192,0,64, 64);
		this.baldosaMorado = this.ssBaldosas.getSubimage(256,0,64, 64);
		this.baldosaGris = this.ssBaldosas.getSubimage(320,0,64, 64);
		this.baldosaMarron = this.ssBaldosas.getSubimage(384,0,64, 64);
	}
	
	public BufferedImage getSplash(){
		return this.splash;
	}
	
	public BufferedImage getAzul(){
		return this.baldosaAzul;
	}
	
	public BufferedImage getVerde(){
		return this.baldosaVerde;
	}
	
	public BufferedImage getAmarillo(){
		return this.baldosaAmarillo;
	}
	
	public BufferedImage getRojo(){
		return this.baldosaRojo;
	}
	
	public BufferedImage getMorado(){
		return this.baldosaMorado;
	}
	
	public BufferedImage getGris(){
		return this.baldosaGris;
	}
	
	public BufferedImage getMarron(){
		return this.baldosaMarron;
	}
	
	public BufferedImage[] getIdle(){
		BufferedImage[] animacion = new BufferedImage[4];
		animacion[0] = this.ssSkeleton.getSubimage(0,0,64, 64);
		animacion[1] = this.ssSkeleton.getSubimage(64,0,64, 64);
		animacion[2] = this.ssSkeleton.getSubimage(128,0,64, 64);
		animacion[3] = this.ssSkeleton.getSubimage(192,0,64, 64);
		return animacion;
	}
	
	public BufferedImage[] getWalk(){
		BufferedImage[] animacion = new BufferedImage[4];
		animacion[0] = this.ssSkeleton.getSubimage(0,64,64, 64);
		animacion[1] = this.ssSkeleton.getSubimage(64,64,64, 64);
		animacion[2] = this.ssSkeleton.getSubimage(128,64,64, 64);
		animacion[3] = this.ssSkeleton.getSubimage(192,64,64, 64);
		return animacion;
	}
	
	public BufferedImage[] getDeath(){
		BufferedImage[] animacion = new BufferedImage[7];
		animacion[0] = this.ssSkeleton.getSubimage(0,192,64, 64);
		animacion[1] = this.ssSkeleton.getSubimage(64,192,64, 64);
		animacion[2] = this.ssSkeleton.getSubimage(128,192,64, 64);
		animacion[3] = this.ssSkeleton.getSubimage(192,192,64, 64);
		animacion[4] = this.ssSkeleton.getSubimage(256,192,64, 64);
		animacion[5] = this.ssSkeleton.getSubimage(320,192,64, 64);
		animacion[6] = this.ssSkeleton.getSubimage(384,192,64, 64);
		return animacion;
	}
	
	public BufferedImage[] getDamage(){
		BufferedImage[] animacion = new BufferedImage[5];
		animacion[0] = this.ssSkeleton.getSubimage(0,192,64, 64);
		animacion[1] = this.ssSkeleton.getSubimage(64,192,64, 64);
		animacion[2] = this.ssSkeleton.getSubimage(128,192,64, 64);
		animacion[3] = this.ssSkeleton.getSubimage(64,192,64, 64);
		animacion[4] = this.ssSkeleton.getSubimage(0,192,64, 64);
		return animacion;
	}
	
	public BufferedImage[] getToSkull(){
		BufferedImage[] animacion = new BufferedImage[5];
		animacion[0] = this.ssSkeleton.getSubimage(0,320,64, 64);
		animacion[1] = this.ssSkeleton.getSubimage(64,320,64, 64);
		animacion[2] = this.ssSkeleton.getSubimage(128,320,64, 64);
		animacion[3] = this.ssSkeleton.getSubimage(192,320,64, 64);
		animacion[4] = this.ssSkeleton.getSubimage(256,320,64, 64);
		return animacion;
	}
	
	public BufferedImage[] getToSkeleton(){
		BufferedImage[] animacion = new BufferedImage[5];
		animacion[0] = this.ssSkeleton.getSubimage(256,320,64, 64);
		animacion[1] = this.ssSkeleton.getSubimage(192,320,64, 64);
		animacion[2] = this.ssSkeleton.getSubimage(128,320,64, 64);
		animacion[3] = this.ssSkeleton.getSubimage(64,320,64, 64);
		animacion[4] = this.ssSkeleton.getSubimage(0,320,64, 64);
		return animacion;
	}
	
	public BufferedImage[] getFly(){
		BufferedImage[] animacion = new BufferedImage[2];
		animacion[0] = this.ssSkeleton.getSubimage(320,320,64, 64);
		animacion[1] = this.ssSkeleton.getSubimage(384,320,64, 64);
		return animacion;
	}
	
	public BufferedImage[] getParticulasTeleport(){
		BufferedImage[] animacion = new BufferedImage[4];
		animacion[0] = this.ssParticulasTeleport.getSubimage(0,0,64, 64);
		animacion[1] = this.ssParticulasTeleport.getSubimage(64,0,64, 64);
		animacion[2] = this.ssParticulasTeleport.getSubimage(128,0,64, 64);
		animacion[3] = this.ssParticulasTeleport.getSubimage(192,0,64, 64);
		return animacion;
	}
	
	public BufferedImage[] getParticulasHeal(){
		BufferedImage[] animacion = new BufferedImage[4];
		animacion[0] = this.ssParticulasHeal.getSubimage(0,0,64, 64);
		animacion[1] = this.ssParticulasHeal.getSubimage(64,0,64, 64);
		animacion[2] = this.ssParticulasHeal.getSubimage(128,0,64, 64);
		animacion[3] = this.ssParticulasHeal.getSubimage(192,0,64, 64);
		return animacion;
	}
	
	public BufferedImage getSombra(){
		return this.sombra;
	}
	
	public BufferedImage getBotonPlay(){
		return this.ssMenu.getSubimage(0,0,128, 64);
	}
	
	public BufferedImage getBotonPlayPulsado(){
		return this.ssMenu.getSubimage(0,64,128, 64);
	}
	
	public BufferedImage getBotonCreditsPulsado(){
		return this.ssMenu.getSubimage(0,192,128, 64);
	}
	
	public BufferedImage getBotonCredits(){
		return this.ssMenu.getSubimage(0,128,128, 64);
	}
	
	public BufferedImage getBotonSalir(){
		return this.ssMenu.getSubimage(0,256,128, 64);
	}
	
	public BufferedImage getBotonSalirPulsado(){
		return this.ssMenu.getSubimage(0,320,128, 64);
	}
	
	public BufferedImage getSSBaldosas(){
		return this.ssBaldosas;
	}
		
	
}
