import java.util.ArrayList;

public class UnitClass {
	
	private String baseName;
	private String promotedName;
	
	private int[] baseWeaponRanks;
	private int[] promotedWeaponRanks;
		//0sword, 1lance, 2axe, 3bow, 4staff, 5fire, 6thunder, 7wind, 8light, 9dark
		//0 = None; 1 = C; 2 = B; 3 = A; 4+ = *
    
	private ArrayList <Integer> baseClassSkills;
	private ArrayList <Integer> promotedClassSkills;
	
	public UnitClass(String name1, String name2, 
			int[] ranks1, int[] ranks2, 
			ArrayList <Integer> skills1, ArrayList <Integer> skills2) {
		baseName = name1;
		promotedName = name2;
		baseWeaponRanks = ranks1;
		promotedWeaponRanks = ranks2;
		baseClassSkills = skills1;
		promotedClassSkills = skills2;
	}
	
	public String getName(boolean promoted) {
		if (!promoted)
			return baseName;
		else
			return promotedName;
	}
	
	public int[] getRanks(boolean promoted) {
		if (!promoted)
			return baseWeaponRanks;
		else
			return promotedWeaponRanks;
	}
	
	public ArrayList <Integer> getClassSkills(boolean promoted) {
		if (!promoted)
			return baseClassSkills;
		else
			return promotedClassSkills;
	}
	
	public boolean canInheritASL() {
		if (baseName.equals("Junior Lord")
				||baseName.equals("Prince")
				||baseName.equals("Princess")
				||baseName.equals("Swordfighter")
				||baseName.equals("Thief")
				||baseName.equals("Dancer"))
			return true;
		else
			return false;
	}
	
	public String toString() {
		
		String x = "T1: " + baseName + "\tT2: " + promotedName;
		x+="\nT1 ranks: ";
		for (int i=0; i<9; i++) {
			x += baseWeaponRanks[i] + " ";
		}
		x += baseWeaponRanks[9];
		x+="\nT2 ranks: ";
		for (int i=0; i<9; i++) {
			x += promotedWeaponRanks[i] + " ";
		}
		x += promotedWeaponRanks[9] + "\n";
		return x;
	}
	
	public int[] getRankDuplicates(boolean promoted) {
		int[] tempRanks = new int[10];
		if (!promoted) {
			for (int i=0; i<baseWeaponRanks.length; i++) {
				tempRanks[i] = baseWeaponRanks[i];
			}
		}
		else {
			for (int i=0; i<promotedWeaponRanks.length; i++) {
				tempRanks[i] = promotedWeaponRanks[i];
			}
		}
		return tempRanks;
	}
}