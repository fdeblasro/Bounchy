
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Raton implements MouseListener {
	

	private boolean on = true;

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		

		if (Game.ESTADO.equals("menu")){
			int ratonx = e.getX();
			int ratony = e.getY();
			Rectangle r = new Rectangle(ratonx,ratony,1,1);
			if (r.intersects(Menu.rectanglePlay())){
				Menu.boton1=true;
			}
			else Menu.boton1=false;
			if (r.intersects(Menu.rectangleCredits())){
				Menu.boton2=true;
			}
			else Menu.boton2=false;
			if (r.intersects(Menu.rectangleSalir())){
				Menu.boton3=true;
			}
			else Menu.boton3=false;
		}
		
		//public Rectangle jugarBoton = new Rectangle(Game.ANCHO/2 +120,150,100,50);
		// jugar BOTON
		
									
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}