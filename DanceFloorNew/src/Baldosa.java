import java.awt.Graphics;

public class Baldosa {
	private int x;
	private int y;
	private String color;
	private Imagenes imagen;
	private RandomGenerator random;
	private Game game;
	
	public Baldosa(int x,int y,RandomGenerator random, Imagenes imagen,Game game){
		this.imagen=imagen;
		this.x=x;
		this.y=y;
		this.random=random;
		this.color=this.random.getRandomColor();
		this.game=game;
	}
	
	public Baldosa(int x,int y,RandomGenerator random, Imagenes imagen, Game game,String color){
		this.imagen=imagen;
		this.x=x;
		this.y=y;
		this.random=random;
		this.color=color;
		this.game=game;
	}
	
	public void change(){
		if(!this.getColor().equals("marron")){
			this.setColor(this.random.getRandomColor());
		}
		this.game.getUIRectangles().add(new UIRectangle(this.game));
	}
	
	public void effect(){
		if (this.getColor().equals("azul")){
			int count=0;
			for (Baldosa b : this.game.getMap()){
				if (b.getColor().equals("azul")){
					++count;
				}
			}
			int count2 = this.random.randomInt(count)+1;
			for (Baldosa b : this.game.getMap()){
				if (b.getColor().equals("azul")){
					++count2;
					if (count==count2){
						if (this.equals(b)) this.effect();
						else{
							b.setColor("gris");
							new ParticulasTeleport(this.getX(),this.getY()-20,imagen,this.game);
							new ParticulasTeleport(b.getX(),b.getY()-20,imagen,this.game);
							this.game.teleport(b.getX(),b.getY());
						}
					}
				}
			}
		}
		if (this.getColor().equals("verde")){
			this.game.heal();
			new ParticulasHeal(this.getX(),this.getY()-20,imagen,this.game);
		}
		if (this.getColor().equals("rojo")){
			this.game.damage();
		}
		if (this.getColor().equals("amarillo")){
			this.game.toSkull();
		}
		if (this.getColor().equals("morado")){
			this.game.moveRandomly(this.random.randomInt(4));
		}
		if (!this.getColor().equals("marron")) this.setColor("gris");
	}
	
	public int getX(){
		return this.x;
	}
	
	public int getY(){
		return this.y;
	}
	
	public void setColor(String color){
		this.color=color;
	}
	
	public String getColor(){
		return this.color;
	}
	
	public void render(Graphics g){
		if (this.color.equals("azul")){
			g.drawImage(this.imagen.getAzul(), this.getX(), this.getY(), this.game);
		}
		else if (this.color.equals("verde")){
			g.drawImage(this.imagen.getVerde(), this.getX(), this.getY(), this.game);
		}
		else if (this.color.equals("amarillo")){
			g.drawImage(this.imagen.getAmarillo(), this.getX(), this.getY(), this.game);
		}
		else if (this.color.equals("rojo")){
			g.drawImage(this.imagen.getRojo(), this.getX(), this.getY(), this.game);
		}
		else if (this.color.equals("morado")){
			g.drawImage(this.imagen.getMorado(), this.getX(), this.getY(), this.game);
		}
		else if (this.color.equals("marron")){
			g.drawImage(this.imagen.getMarron(), this.getX(), this.getY(), this.game);
		}
		else if (this.color.equals("gris")){
			g.drawImage(this.imagen.getGris(), this.getX(), this.getY(), this.game);
		}
	}
	
}
