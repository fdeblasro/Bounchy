import java.util.Random;

public class RandomGenerator {
	
	private Random random;
	
	public RandomGenerator(){
		this.random=new Random();
		this.random.setSeed(System.currentTimeMillis());
	}
	
	public String getRandomColor(){
		int n = random.nextInt(5);
		switch(n){
			case 0: return "azul";
			case 1: return "verde";
			case 2: return "amarillo";
			case 3: return "rojo";
			case 4: return "morado";
			default: return "nada";
		}
	}
	
	public int randomInt(int max){
		return this.random.nextInt(max);
	}
}
