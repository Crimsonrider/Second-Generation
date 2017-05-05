import java.util.ArrayList;

public class Unit {
	public String name;
	
	public int[] baseGrowths;
	public ArrayList <Integer> personalSkills;
	public ArrayList <HolyBlood> hb;
	
	public int[] usableRanks = new int[10]; //this is PROMOTED and WITH HB
	public UnitClass charClass;
	public boolean promoted;
	
	public int[] overallGrowths;
	public ArrayList <Integer> classSkillsA, classSkillsB;
	
	char[] tsRanks = new char[] {' ', 'C', 'B', 'A', '*'};
	String[] tsCrusaders = new String[] {"Baldur", "Odo", "Hezul", "Nova", "Dain", "Neir",
			"Ulir", "Blaggi", "Fala", "Tordo", "Forseti", "Naga", "Loptyr"};
	String[] tsWepTypes = new String[] {"Sword", "Lance", "Axe", "Bow", "Staff", "Fire",
			"Thunder", "Wind", "Light", "Dark"};
	String[] tsSkills = new String[] {"Pursuit", "Critical", "Charge", "Nihil", "Ambush", "Continue", "Prayer", "Elite", "Sol", "Bargain", "Astra", "Luna", "Charisma", "Wrath", "Big Shield", "Return", "Recover", "Canto", "Steal", "Dark Sword", "Light Sword", "Life", "None"};
	String[] tsStats = new String[] {"HP", "Str", "Mag", "Skl", "Spd", "Lck", "Def", "Res"};
	
	

	public Unit (String name, int[] theirGrowths, ArrayList <Integer> theirSkills, ArrayList <HolyBlood> theirHB, int[] theirUsableRanks) {
		this.name = name;
		baseGrowths = theirGrowths;
		personalSkills = theirSkills;
		hb = theirHB;
		usableRanks = theirUsableRanks;
	}
	
	public Unit (String name, int[] theirGrowths, ArrayList <Integer> theirSkills, ArrayList <HolyBlood> theirHB, UnitClass theirClass, boolean isPromoted) {
		this.name = name;
		baseGrowths = theirGrowths;
		personalSkills = theirSkills;
		hb = theirHB;
		charClass = theirClass;
		promoted = isPromoted;
		updateUsableRanks(this.promoted);
	}
	
	protected void updateUsableRanks(boolean promoted) {
		for (int i=0; i<usableRanks.length; i++) {
			usableRanks[i] = charClass.getRanks(promoted)[i];
		}
		for (HolyBlood h : hb) {
			int typeIndex = h.getWepType();
			// 0 = None; 1 = C; 2 = B; 3 = A; 4+ = *
			if (usableRanks[typeIndex] > 0) { // if you've got a rank
				if (h.isMajor()) // if major, give 4 for a * rank
					usableRanks[typeIndex] = 4;
				else if (usableRanks[typeIndex] < 3) // if you've got a C or B
					usableRanks[typeIndex]++;
			}
		}
	}
	
	public String toString() {

		
		String x = name;
		if (!promoted) {
			x += "\nBase Class: " + charClass.getName(false);
			x += "\nPromoted Class: " + charClass.getName(true);
		}
		else {
			x += "\nClass: " + charClass.getName(true);
		}
		x += "\nGrowths: ";
		for (int i = 0; i < 8; i++)
			x += baseGrowths[i] + "(" + tsStats[i] + ") ";
		
		x += "\nPersonal Skills: ";
		for (Integer y : personalSkills)
			x += tsSkills[y] + " ";
		if (personalSkills.size()==0)
			x += "None";
		
		x += "\nHoly blood: ";
		for (HolyBlood y : hb){
				if (y.isMajor())
					x += "Major ";
				else
					x += "Minor ";
				x += tsCrusaders[y.getCrusader()] + "(" + tsWepTypes[y.getWepType()] + ")";
				if(hb.indexOf(y)<hb.size()-1)
					x+=", ";
		}
		if (hb.size()==0)
			x += "None";
		
		x += "\nMax Weapon ranks:";
		if (usableRanks[0] > 0)
			x += " Swd-" + tsRanks[usableRanks[0]];
		if (usableRanks[1] > 0)
			x += " Lnc-" + tsRanks[usableRanks[1]];
		if (usableRanks[2] > 0)
			x += " Axe-" + tsRanks[usableRanks[2]];
		if (usableRanks[3] > 0)
			x += " Bow-" + tsRanks[usableRanks[3]];
		if (usableRanks[4] > 0)
			x += " Stf-" + tsRanks[usableRanks[4]];
		if (usableRanks[5] > 0)
			x += " Fr-" + tsRanks[usableRanks[5]];
		if (usableRanks[6] > 0)
			x += " Thn-" + tsRanks[usableRanks[6]];
		if (usableRanks[7] > 0)
			x += " Wnd-" + tsRanks[usableRanks[7]];
		if (usableRanks[8] > 0)
			x += " Lgt-" + tsRanks[usableRanks[8]];
		if (usableRanks[9] > 0)
			x += " Drk-" + tsRanks[usableRanks[9]];
		return x + "\n";
	}
}