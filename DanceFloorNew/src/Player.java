import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

public class Player {
	
	private int x;
	private int y;
	private Imagenes imagen;
	private Game game;
	private BufferedImage[] idle = new BufferedImage[4];
	private BufferedImage[] walk = new BufferedImage[4];
	private BufferedImage[] toSkull = new BufferedImage[5];
	private BufferedImage[] toSkeleton = new BufferedImage[5];
	private BufferedImage[] fly = new BufferedImage[2];
	private BufferedImage[] death = new BufferedImage[7];
	private BufferedImage[] damage = new BufferedImage[5];
	private BufferedImage sombra;
	private String estado;
	private boolean performingAction;
	private int frame;
	private int xDestination;
	private int yDestination;
	private int HP;
	private int flyCountdown;
	private Sound sonido;
	
	public Player(int x, int y, Imagenes imagen, Game game){
		this.x=x;
		this.y=y;
		this.imagen=imagen;
		this.game=game;
		this.cargarAnimaciones();
		this.estado="idle";
		this.frame=0;
		this.performingAction=false;
		this.HP=5;
		this.sombra=imagen.getSombra();
		this.flyCountdown=100;
		this.sonido = new Sound();
	}
	
	private void cargarAnimaciones(){
		this.idle = this.imagen.getIdle();
		this.walk = this.imagen.getWalk();
		this.toSkull = this.imagen.getToSkull();
		this.toSkeleton = this.imagen.getToSkeleton();
		this.fly = this.imagen.getFly();
		this.death = this.imagen.getDeath();
		this.damage = this.imagen.getDamage();
	}
	
	public void render(Graphics g){
		g.drawImage(this.sombra, this.x+21, this.y+50, this.game);
		if (!this.isPerformingAction()) this.estado="idle";
		if (this.estado.equals("idle")){
			if (this.frame>=4){
				this.frame=0;
			}
			g.drawImage(this.idle[this.frame], this.x, this.y, this.game);
		}
		else if (this.estado.equals("walkUp") || this.estado.equals("walkDown") || this.estado.equals("walkRight") || this.estado.equals("walkLeft")){
			if (this.frame>=4){
				this.frame=0;
			}
			g.drawImage(this.walk[this.frame], this.x, this.y, this.game);
		}
		else if (this.estado.equals("toSkull")){
			if (this.frame>=5){
				this.frame=0;
				this.performingAction=false;
				this.fly();
			}
			g.drawImage(this.toSkull[this.frame], this.x, this.y, this.game);
		}
		else if (this.estado.equals("toSkeleton")){
			if (this.frame>=5){
				this.frame=0;
				this.performingAction=false;
				this.reposition();
			}
			g.drawImage(this.toSkeleton[this.frame], this.x, this.y, this.game);
		}
		else if (this.estado.equals("fly")){
			if (this.frame>=2){
				this.frame=0;
			}
			g.drawImage(this.fly[this.frame], this.x, this.y, this.game);
		}
		else if (this.estado.equals("death")){
			if (this.frame>=7){
				this.game.gameOver();
			}
			g.drawImage(this.death[this.frame], this.x, this.y, this.game);
		}
		else if (this.estado.equals("damage")){
			if (this.frame>=5){
				this.frame=0;
				if (this.getHP()<=0) this.game.gameOver();
				this.performingAction=false;
			}
			g.drawImage(this.damage[this.frame], this.x, this.y, this.game);
		}
	}
	
	public void setEstado(String estado){
		this.frame=0;
		if (estado.equals("toSkull")) this.sonido.toSkull();
		this.estado=estado;
	}
	
	public void frameUp(){
		this.frame++;
	}
	
	public int getX(){
		return this.x;
	}
	
	public int getY(){
		return this.y;
	}
	
	public void setX(int n){
		this.x=n;
	}
	
	public void setY(int n){
		this.y=n;
	}
	
	public boolean isPerformingAction(){
		return this.performingAction;
	}
	
	public void moveUp(){
		this.performingAction=true;
		this.setEstado("walkUp");
	}
	
	public void moveDown(){
		this.performingAction=true;
		this.setEstado("walkDown");
	}
	
	public void moveLeft(){
		this.performingAction=true;
		this.setEstado("walkLeft");
	}
	
	public void moveRight(){
		this.performingAction=true;
		this.setEstado("walkRight");
	}
	
	public void moveUpFly(){
		this.y-=5;
	}
	
	public void moveDownFly(){
		this.y+=5;
	}
	
	public void moveRightFly(){
		this.x+=5;
	}
	
	public void moveLeftFly(){
		this.x-=5;
	}
	
	public void toSkull(){
		this.performingAction=true;
		this.setEstado("toSkull");
	}
	
	public void toSkeleton(){
		this.performingAction=true;
		this.setEstado("toSkeleton");
	}
	
	public void fly(){
		this.performingAction=true;
		this.flyCountdown=2;
		this.setEstado("fly");
	}
	
	public String getEstado(){
		return this.estado;
	}
	
	public void tick(){
		if (this.estado.equals("idle")){
			this.game.getEfecto(this.getX(),this.getY());
		}
		if (this.estado.equals("walkUp")){
			if (this.getX()!=this.xDestination || this.getY()!=this.yDestination){
				this.y-=4;
			}
			else{
				if (!this.estado.equals("damage") && (!this.estado.equals("toSkull")))	this.performingAction=false;
				this.game.getEfecto(this.getX(),this.getY());
			}
		}
		if (this.estado.equals("walkDown")){
			if (this.getX()!=this.xDestination || this.getY()!=this.yDestination){
				this.y+=4;
			}
			else{
				if (!this.estado.equals("damage") && (!this.estado.equals("toSkull")))	this.performingAction=false;
				this.game.getEfecto(this.getX(),this.getY());
			}
		}
		if (this.estado.equals("walkRight")){
			if (this.getX()!=this.xDestination || this.getY()!=this.yDestination){
				this.x+=4;
			}
			else{
				if (!this.estado.equals("damage") && (!this.estado.equals("toSkull")))	this.performingAction=false;
				this.game.getEfecto(this.getX(),this.getY());
			}
		}
		if (this.estado.equals("walkLeft")){
			if (this.getX()!=this.xDestination || this.getY()!=this.yDestination){
				this.x-=4;
			}
			else{
				if (!this.estado.equals("damage") && (!this.estado.equals("toSkull")))	this.performingAction=false;
				this.game.getEfecto(this.getX(),this.getY());
			}
		}
	}
	
	public void setDestination(String dest){
		if (dest.equals("left")){
			this.xDestination=this.getX()-64;
			this.yDestination=this.getY();
		}
		if (dest.equals("right")){
			this.xDestination=this.getX()+64;
			this.yDestination=this.getY();
		}
		if (dest.equals("up")){
			this.xDestination=this.getX();
			this.yDestination=this.getY()-64;
		}
		if (dest.equals("down")){
			this.xDestination=this.getX();
			this.yDestination=this.getY()+64;
		}
	}
	
	public boolean canMove(String direction){
		if (direction.equals("left")){
			if (this.getX()==64) return false;
			return true;
		}
		else if (direction.equals("right")){
			if (this.getX()==384) return false;
			return true;
		}
		else if (direction.equals("up")){
			if (this.getY()==64-20) return false;
			return true;
		}
		else if (direction.equals("down")){
			if (this.getY()==384-20) return false;
			return true;
		}
		return false;
	}
	
	public void damage(){
		this.HP--;
		System.out.println(this.HP);
		this.performingAction=true;
		this.sonido.hit();
		if (this.HP<=0) this.setEstado("death");
		else this.setEstado("damage");
	}
	
	public void heal(){
		if (this.HP<5) this.HP++;
		System.out.println(this.HP);
	}
	
	public void flyCountdownTick(){
		if (this.estado.equals("fly")){
			this.flyCountdown--;
			if (this.flyCountdown<=0){
				this.toSkeleton();
				this.flyCountdown=2;
			}
		}
	}
	
	public void reposition(){
		int x,y;
		x=this.x+21+10;
		y=this.y+50+10;
		x=x/64;
		y=y/64;
		this.x=x*64;
		this.y=(y*64)-20;
	}
	
	public int getHP(){
		return this.HP;
	}
	
}
