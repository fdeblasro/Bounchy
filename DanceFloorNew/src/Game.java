
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import javax.swing.JFrame;
import javax.swing.JOptionPane;


@SuppressWarnings("serial")
public class Game extends Canvas implements Runnable {

	public static final int ANCHO = 8*64-8; // so 512 
	public static final int ALTO = 8*64-8+64; // so 512 + (UI)
	public static final int ESCALA = 1;
	public  final String TITULO = "Dance Floor";
	
	private boolean corriendo = false;
	private Thread hilo;
	
	private BufferedImage imagen = new BufferedImage(ANCHO, ALTO, BufferedImage.TYPE_INT_RGB);
	public static boolean AUDIO = true;

	
	public static String ESTADO;
	private ArrayList<Baldosa> map = new ArrayList<Baldosa>();
	private int tickCount = 0;
	private int tickFrame = 0;
	private int tickPlayer = 0;
	private int tickParticulas = 0;
	private int tickFly = 0;
	private int tickRectangles = 0;
	private UI ui = new UI(this);
	private ArrayList<UIRectangle> uiRectangles = new ArrayList<UIRectangle>();
	private static Imagenes imagenes = new Imagenes();
	public Player player = new Player(128,128-20,this.imagenes,this);
	private ArrayList<ParticulasTeleport> particulas = new ArrayList<ParticulasTeleport>();
	private ArrayList<ParticulasHeal> particulasHeal = new ArrayList<ParticulasHeal>();
	private PresentationScreen intro= new PresentationScreen(imagenes);
	private Menu menu = new Menu(imagenes, this);
	public static Credits credits = new Credits();
	public static Sound sonido = new Sound();
	
	public ArrayList<Baldosa> getMap(){
		return this.map;
	}
	
	public void addParticulasTeleport(ParticulasTeleport p){
		this.particulas.add(p);
		sonido.teleport();
	}
	
	public void removeParticulasTeleport(ParticulasTeleport p){
		if (this.particulas.contains(p)) this.particulas.remove(p);
	}
	
	public void addParticulasHeal(ParticulasHeal p){
		this.particulasHeal.add(p);
		sonido.heal();
	}
	
	public void removeParticulasHeal(ParticulasHeal p){
		if (this.particulasHeal.contains(p)) this.particulasHeal.remove(p);
	}
	
	public void damage(){
		this.player.damage();
	}
	
	public void heal(){
		this.player.heal();
	}
	
	public void toSkull(){
		this.player.toSkull();
	}
	
	public void moveRandomly(int n){
		if (n==0){
			if (!player.isPerformingAction()){
				if (player.canMove("left")){
					player.setDestination("left");
					player.moveLeft();
				}
				else moveRandomly(n+1);
			}
		}
		if (n==1){
			if (!player.isPerformingAction()){
				if (player.canMove("right")){
					player.setDestination("right");
					player.moveRight();
				}
				else moveRandomly(n+1);
			}
			
		}
		if (n==2){
			if (!player.isPerformingAction()){
				if (player.canMove("up")){
					player.setDestination("up");
					player.moveUp();
				}
				else moveRandomly(n+1);
			}
		}
		if (n==3){
			if (!player.isPerformingAction()){
				if (player.canMove("down")){
					player.setDestination("down");
					player.moveDown();
				}
				else moveRandomly(0);
			}
		}
	}
	
	public void iniciar(){
		
		requestFocus();
		
		addKeyListener(new Teclado(this));
		this.addMouseListener(new Raton());
		
		//if (AUDIO == true) Sound.intro.loop();
	}
	
	//@Override
	private synchronized void start(){
		if(corriendo)
			return;
		corriendo = true;
		hilo = new Thread(this);
		hilo.start();
		System.out.println("Start");//Solo se vera un instante al principio!!!!
		
	}	
	
	public synchronized void parar() {
		if(!corriendo)
			return;
		
		corriendo = false;
		try {
			hilo.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.exit(1);
		
	}
	
	//@Override
	public void run() {
		
		iniciar();
		long  ultimoInstante = System.nanoTime();
		long  refInstante = System.nanoTime();
		final double cantidadTicks = 60.0;
		double ns = 1000000000 / cantidadTicks;
		double delta = 0;
		int actualizar = 0;
		int frames = 0;
				
		while (corriendo){
			long ahora = System.nanoTime();
			delta += (ahora - ultimoInstante) / ns;
			ultimoInstante = ahora;
			if (delta >=1){
				tick();
				actualizar++;
				delta--;
			}
			render();
			frames++;
			
			if (System.nanoTime() -  refInstante >  1000000000){
				System.out.println(actualizar + " Ticks, Frames por segundo " + frames + " Delta: " + delta);
				actualizar = 0;
				frames = 0;
				refInstante = System.nanoTime();
			}
				
		}
		parar();
	}
	
	
	private void tick(){
		
		//jugador.tick();
		//control.tick();
		//controlexplosion.tick();
		if (ESTADO.equals("intro")){
			this.intro.tick();
		}
		else if(ESTADO.equals("menu")){
			if (!sonido.isPlaying2) sonido.menu();
		}
		else if(ESTADO.equals("credits")){
			
		}
		else{
			if (!sonido.isPlaying1) sonido.mainMusic();
			if (this.tickCount>=120){
				for (Baldosa b : this.getMap()){
					b.change();
				}
				this.tickCount=0;
			}
			else this.tickCount++;
			
			if (this.tickFrame>=8){
				this.player.frameUp();
				this.tickFrame=0;
			}
			else this.tickFrame++;
			
			if (this.tickPlayer>=1){
				this.player.tick();
				this.tickPlayer=0;
			}
			else this.tickPlayer++;
			
			if (this.tickParticulas>=4){
				if (!this.particulas.isEmpty()){
					for(ParticulasTeleport p : this.particulas){
						p.frameUp();
						if (p.clean()){
							break;
						}
					}
					this.tickParticulas=0;
				}
				if (!this.particulasHeal.isEmpty()){
					for(ParticulasHeal p : this.particulasHeal){
						p.frameUp();
						if (p.clean()){
							break;
						}
					}
					this.tickParticulas=0;
				}
			}
			else this.tickParticulas++;
			
			if (this.tickFly>=70){
				this.player.flyCountdownTick();
				this.tickFly=0;
			}
			else this.tickFly++;
			
			if (this.tickRectangles>=1){
				if (!this.uiRectangles.isEmpty()){
					for (UIRectangle p : this.uiRectangles){
						p.tick();
					}
					this.tickRectangles=0;
				}
			}
			else this.tickRectangles++;
			
		}
		
	}
		//realizado con objeto enemigo y métodos para "ver" dentro de la clase enemigo
		
	
	private void render(){
		
		BufferStrategy bs = this.getBufferStrategy();
		
		if (bs == null){
			createBufferStrategy(4);
			return;
		}
		Graphics g = bs.getDrawGraphics();
		
		if (ESTADO.equals("intro")){
			this.intro.render(g);
		}
		else if(ESTADO.equals("menu")){
			this.menu.render(g);
		}
		else if(ESTADO.equals("credits")){
			this.credits.render(g);
		}
		else{
			g.drawImage(imagen, 0, 0, getWidth(), getHeight(), this);
			
			g.setColor(Color.RED);
			for (Baldosa b : this.map){
				b.render(g);
			}
			
			for(ParticulasTeleport p : this.particulas){
				p.render(g);
			}
			
			for(ParticulasHeal p : this.particulasHeal){
				p.render(g);
			}
			
			for(UIRectangle p : this.uiRectangles){
				p.render(g);
			}
			this.player.render(g);
			
			this.ui.render(g);
		}
		
		
			//jugador.render(g);
			//control.render (g);
			//controlexplosion.render(g);
					
		g.dispose();
		bs.show();
		
	}
	
	
	
	public void keyPressed(KeyEvent e){
		int key = e.getKeyCode();
		
		if (key == KeyEvent.VK_SPACE){
			if (this.ESTADO.equals("credits")) this.ESTADO="menu";
		}
		
		if (key == KeyEvent.VK_LEFT){
			if (!player.isPerformingAction()){
				if (player.canMove("left")){
					player.setDestination("left");
					player.moveLeft();
				}
			}
			else if (this.player.getEstado().equals("fly")) this.player.moveLeftFly();
		} 
		
		if (key == KeyEvent.VK_RIGHT){
			if (!player.isPerformingAction()){
				if (player.canMove("right")){
					player.setDestination("right");
					player.moveRight();
				}
			}
			else if (this.player.getEstado().equals("fly")) this.player.moveRightFly();
		} 
		
		if (key == KeyEvent.VK_UP){
			if (!player.isPerformingAction()){
				if (player.canMove("up")){
					player.setDestination("up");
					player.moveUp();
				}
			}
			else if (this.player.getEstado().equals("fly")) this.player.moveUpFly();
		} 
		
		if (key == KeyEvent.VK_DOWN){
			if (!player.isPerformingAction()){
				if (player.canMove("down")){
					player.setDestination("down");
					player.moveDown();
				}
			}
			else if (this.player.getEstado().equals("fly")) this.player.moveDownFly();
		}
	}
	
	public void keyReleased(KeyEvent e){
		int key = e.getKeyCode();
		
		if (key == KeyEvent.VK_RIGHT){
			//jugador.setVelX(0);
			//jugador.setMov(0);
		}
		
	}
	
	public void getEfecto(int x, int y){
		for (Baldosa b : this.getMap()){
			if (b.getX()==x && b.getY()==(y+20)){
				b.effect();
			}
		}
	}
	
	public void teleport(int x, int y){
		player.setX(x);
		player.setY(y-20);
	}
	
	public void gameOver() {
		if (!ESTADO.equals("menu")) JOptionPane.showMessageDialog(this, "YOU LOST!","Game Over", JOptionPane.YES_NO_OPTION);
		System.exit(ABORT);
	}
	
	public ArrayList<UIRectangle> getUIRectangles(){
		return this.uiRectangles;
	}
	
	
	public static void main (String args[]){
		
		Game game = new Game();
		
		game.setPreferredSize(new Dimension(ANCHO * ESCALA, ALTO *ESCALA));
		game.setMaximumSize(new Dimension(ANCHO * ESCALA, ALTO *ESCALA));
		game.setMinimumSize(new Dimension(ANCHO * ESCALA, ALTO *ESCALA));
		
		JFrame frame = new JFrame(game.TITULO);
		frame.add(game);
		frame.pack();
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		
		RandomGenerator random = new RandomGenerator();
		for (int i=0;i<8;++i){
			for (int j=0;j<8;++j){
				if (i==0 || i==7 || j==0 || j==7){
					game.getMap().add(new Baldosa((i*64),(j*64),random,imagenes,game,"marron"));
				}
				else game.getMap().add(new Baldosa((i*64),(j*64),random,imagenes,game));
			}
		}
		game.ESTADO="intro";
		game.start();
		game.getUIRectangles().add(new UIRectangle(game));
		
	}
		

}
