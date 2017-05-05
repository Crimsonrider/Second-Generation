
public class Weapon {
	private String name;
	
	private int wepType;
		//0sword, 1lance, 2axe, 3bow, 4staff, 5fire, 6thunder, 7wind, 8light, 9dark
	
	private int rank;
		//0 = None; 1 = C; 2 = B; 3 = A; 4+ = *
	
	public Weapon (String name, int wep, int rnk) {
		this.name = name;
		wepType = wep;
		rank = rnk;
	}
	
	public String getName() {
		return name;
	}
	
	public int getWepType() {
		return wepType;
	}
	
	public int getRank() {
		return rank;
	}
}
