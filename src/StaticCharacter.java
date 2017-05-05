import java.util.ArrayList;

public class StaticCharacter extends Unit{
	/**
		public String name;
		
		public int[] baseGrowths;
		public ArrayList <Integer> personalSkills;
		public ArrayList <HolyBlood> hb;
		
		public int[] usableRanks = new int[10]; //this is PROMOTED and WITH HB
		public UnitClass charClass;
		public boolean promoted;
	 */
		
		public ArrayList <Integer> allSkills = new ArrayList <Integer> ();
		public int[] baseRanks = new int[10];
		public int[] promotedRanks = new int[10];
		
		public String[] substituteData= new String[4];
		
		private ArrayList <Weapon> defaultInventory = new ArrayList <Weapon> ();
		
		public ArrayList <Weapon> getInventory() {
			return defaultInventory;
		}
		
	public StaticCharacter(String name, int[] theirGrowths, ArrayList <Integer> theirPSkills, 
			ArrayList <HolyBlood> theirHB, UnitClass theirClass, boolean isPromoted, 
			String partner, String replaces, String partnerReplaces, String motherExcluded) {
		super(name, theirGrowths, theirPSkills, theirHB, theirClass, isPromoted);
		updateGrowths();
		updateUsableRanks(true);
		updateRanks();
		updateSkillsList();
		
		substituteData[0] = partner; //Roddlevan
		substituteData[1] = replaces; //Larcei
		substituteData[2] = partnerReplaces; //Ulster
		substituteData[3] = motherExcluded; //Ayra
	}
	
	public String getHolyBlood() {
		String x = "";
		for (HolyBlood y : hb) {
			if (y.isMajor())
				x += "major ";
			else
				x += "minor ";
			x += tsCrusaders[y.getCrusader()] + " (" + tsWepTypes[y.getWepType()] + ")";
			if(hb.indexOf(y)<hb.size()-1)
				x+=", ";
		}
		return x;
	}
	
	private void updateGrowths() {
		for (HolyBlood x : hb) {
			int[] bloodBonuses = x.getCertainGrowths(x.isMajor());
			for (int i=0; i < bloodBonuses.length; i++) {
				baseGrowths[i] += bloodBonuses[i];
			}
		}
	}
	
	private void updateSkillsList() {
		for (Integer x : personalSkills)
			allSkills.add(x);
		for (Integer x : charClass.getClassSkills(promoted))
			allSkills.add(x);
		removeDuplicateSkills();
	}
	
	private void removeDuplicateSkills() {
		for (int i=0; i<allSkills.size(); i++) {
			for (int j=i+1; j<allSkills.size(); j++) {
				if (allSkills.get(i)==allSkills.get(j)) {
					allSkills.remove(j);
					j--;
				}
					
			}
		}
	}
	
	private void updateRanks() {
		for (int i=0; i<10; i++) {
			baseRanks[i] = charClass.getRanks(false)[i];
			promotedRanks[i] = charClass.getRanks(true)[i];
		}
		for (HolyBlood h : hb) {
			int typeIndex = h.getWepType();
			// 0 = None; 1 = C; 2 = B; 3 = A; 4+ = *
			if (baseRanks[typeIndex] > 0) { // if you've got a rank
				if (h.isMajor()) // if major, give 4 for a * rank
					baseRanks[typeIndex] = 4;
				else if (baseRanks[typeIndex] < 3) // if you've got a C or B
					baseRanks[typeIndex]++;
			}
			if (promotedRanks[typeIndex] > 0) { // if you've got a rank
				if (h.isMajor()) // if major, give 4 for a * rank
					promotedRanks[typeIndex] = 4;
				else if (promotedRanks[typeIndex] < 3) // if you've got a C or B
					promotedRanks[typeIndex]++;
			}
		}
	}
	
	public void setInventory(ArrayList <Weapon> inv) {
		for (Weapon x : inv) {
			defaultInventory.add(x);
		}
	}
	
	public String toString() {
		char[] tsRanks = new char[] {' ', 'C', 'B', 'A', '*'};
		String[] tsCrusaders = new String[] {"Baldur", "Odo", "Hezul", "Nova", "Dain", "Neir",
				"Ulir", "Blaggi", "Fala", "Tordo", "Forseti", "Naga", "Loptyr"};
		String[] tsWepTypes = new String[] {"Sword", "Lance", "Axe", "Bow", "Staff", "Fire",
				"Thunder", "Wind", "Light", "Dark"};
		String[] tsSkills = new String[] {"Pursuit", "Critical", "Charge", "Nihil", "Ambush", "Continue", "Prayer", "Elite", "Sol", "Bargain", "Astra", "Luna", "Charisma", "Wrath", "Big Shield", "Return", "Recover", "Canto", "Steal", "Dark Sword", "Light Sword", "Life", "None", "Dance"};
		String[] tsStats = new String[] {"HP", "Str", "Mag", "Skl", "Spd", "Lck", "Def", "Res"};
		
		String x = name;
		
		x+="\nClass: " + charClass.getName(promoted);
		
		if (!substituteData[0].equals("None")) { //if they ARE a sub
			x += "\n" + name + " and " + substituteData[0] + " replace " + substituteData[1] + " and " + substituteData[2] + ", " + substituteData[3] + "'s kids.";
		}
		
		x += "\nGrowths: ";
		for (int i = 0; i < 8; i++)
			x += baseGrowths[i] + "(" + tsStats[i] + ") ";
		
		x += "\nSkills: ";
		for (Integer y : allSkills) {
			if (y != 22) { //not None
					x += tsSkills[y] + " ";
			}
		}
		
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
		
		x += "\nDefault inventory: ";
		for (int i=0; i<defaultInventory.size(); i++) {
			x += defaultInventory.get(i).getName();
			if ( (i+1) < defaultInventory.size()) 
				x += ", ";
		}
		
		if (!promoted) {
			x += "\nWeapon ranks:";
			if (baseRanks[0] > 0)
				x += " Swd-" + tsRanks[baseRanks[0]];
			if (baseRanks[1] > 0)
				x += " Lnc-" + tsRanks[baseRanks[1]];
			if (baseRanks[2] > 0)
				x += " Axe-" + tsRanks[baseRanks[2]];
			if (baseRanks[3] > 0)
				x += " Bow-" + tsRanks[baseRanks[3]];
			if (baseRanks[4] > 0)
				x += " Stf-" + tsRanks[baseRanks[4]];
			if (baseRanks[5] > 0)
				x += " Fr-" + tsRanks[baseRanks[5]];
			if (baseRanks[6] > 0)
				x += " Thn-" + tsRanks[baseRanks[6]];
			if (baseRanks[7] > 0)
				x += " Wnd-" + tsRanks[baseRanks[7]];
			if (baseRanks[8] > 0)
				x += " Lgt-" + tsRanks[baseRanks[8]];
			if (baseRanks[9] > 0)
				x += " Drk-" + tsRanks[baseRanks[9]];
			
				x += "\nOn promotion:\n\tClass: " + charClass.getName(true);
				boolean extraSkills = false;
				String z = "\n\tSkills: ";
				for (Integer y : charClass.getClassSkills(true)) {
					if (!allSkills.contains(y) && y != 22) {
						extraSkills=true;
						z += tsSkills[y] + " ";
					}
				}
				if(extraSkills)
					x += z;
				
				x += "\n\tRanks:";
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
				
		}
		else {
			x += "\nRanks:";
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
		}
		
		return x + "\n";
	}
	
	public String getUnitClass() {
		return charClass.getName(this.promoted);
	}
	
	public String getSkills() {
		String x="";
		for (Integer y : allSkills) {
			if (y != 22) { //not None
				x += tsSkills[y] + " "; //add the skill
				if(allSkills.indexOf(y) + 1 < allSkills.size() &&
					allSkills.indexOf(y+1) != 22)
					;
			}
		}
		
		return x;
	}
	
	public int[] getWpnRanks() {
		if(promoted)
			return promotedRanks;
		else
			return baseRanks;
	}
}
