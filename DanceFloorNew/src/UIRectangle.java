import java.awt.Graphics;

public class UIRectangle {
	
	private int x;
	private int y;
	private Game game;
	
	public UIRectangle(Game game){
		this.x=30;
		this.y=522;
		this.game=game;
	}
	
	public void tick(){
		this.x+=3;
	}
	
	public void render(Graphics g){
		if (!this.checkRemove()) g.fillRect(this.x, this.y, 1, 40);
	}
	
	public int getX(){
		return this.x;
	}
	
	public void setY(int n){
		this.y-=n;
	}
	
	public boolean checkRemove(){
		if (this.x>=230){
			return true;
		}
		return false;
	}
}
