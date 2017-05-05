import java.util.ArrayList;

public class Child {
	public static boolean vanilla;
	public static boolean ASLForEveryone;
	
	public String name;

	private Unit staticParent;
	private boolean dominantInGrowths;
	private boolean dominantInEqHB;

	private UnitClass charClass;
	boolean promoted;

	private Unit otherParent;

	private ArrayList<HolyBlood> hb = new ArrayList<HolyBlood>();

	public int[] growths = new int[8];

	private ArrayList<Integer> nativeSkills = new ArrayList<Integer>();
	private ArrayList<Integer> foreignSkills = new ArrayList<Integer>();
	private ArrayList<Integer> promotedClassSkills = new ArrayList<Integer>();

	private int[] weaponRanks;
	public int[] promotedWeaponRanks;
	
	private int[] sharedRanks; //ranks shared between child and parent
		//parent=2 & child=3 -> this=2; parent=3 & child=0 -> this=0
	
	private ArrayList <Weapon> defaultInventory = new ArrayList <Weapon> ();
	
	public ArrayList<Weapon> getInventory() {
		return defaultInventory;
	}

	private String sibling;
	
	
	char[] tsRanks = new char[] {' ', 'C', 'B', 'A', '*'};
	String[] tsCrusaders = new String[] {"Baldur", "Odo", "Hezul", "Nova", "Dain", "Neir",
			"Ulir", "Blaggi", "Fala", "Tordo", "Forseti", "Naga", "Loptyr"};
	String[] tsWepTypes = new String[] {"Sword", "Lance", "Axe", "Bow", "Staff", "Fire",
			"Thunder", "Wind", "Light", "Dark"};
	String[] tsSkills = new String[] {"Pursuit", "Critical", "Charge", "Nihil", "Ambush", "Continue", "Prayer", "Elite", "Sol", "Bargain", "Astra", "Luna", "Charisma", "Wrath", "Big Shield", "Return", "Recover", "Canto", "Steal", "Dark Sword", "Light Sword", "Life", "None", "Dance"};
	String[] tsStats = new String[] {"HP", "Str", "Mag", "Skl", "Spd", "Lck", "Def", "Res"};
	
	
	public Child(String name, Unit parent, boolean dom) {
		this.name = name;
		staticParent = parent;
		dominantInGrowths = dom;
		dominantInEqHB=dom;
		if (name.equals("Leif") //from Ethlyn
			|| name.equals("Faval")) { //from Briggid
			dominantInGrowths=false;
			dominantInEqHB=true;
		}
		if (name.equals("Altena") //from Ethlyn
			|| name.equals("Patty")) { //from Briggid
			dominantInGrowths=true;
			dominantInEqHB=false;
		}

		// native skills will no longer change except to add class skills
		nativeSkills.addAll(staticParent.personalSkills);

		setStatsByStaticParent();
	}
	
	public String getFParent() {
		return staticParent.name;
	}

	public void setSibling(String str) {
		sibling = str;
	}
	
	public String getSibling() {
		return sibling;
	}
	
	public String getUnitClass() {
		return charClass.getName(this.promoted);
	}
	
	public String getOtherParent() {
		if (otherParent != null)
			return otherParent.name;
		else
			return null;
	}
	
	public void setClass(UnitClass theirClass, boolean promoted) {
		// the stuff in here will never change after the method is called the
		// first time :)
		this.promoted = promoted;
		charClass = theirClass;

		for (Integer x : charClass.getClassSkills(promoted)) {
			if (!nativeSkills.contains(x))
				nativeSkills.add(x);
		}
		promotedClassSkills = charClass.getClassSkills(true);

		// this stuff will!
		weaponRanks = charClass.getRankDuplicates(promoted);
		promotedWeaponRanks = charClass.getRankDuplicates(true);
	}
	
	public void setInventory(ArrayList <Weapon> inv) {
		for (Weapon x : inv) {
			defaultInventory.add(x);
		}
	}

	public void setOtherParent(Unit other) {
		setStatsByStaticParent();
		while(foreignSkills.size()>0)
			foreignSkills.remove(0);
		otherParent = other;
		for (int i = 0; i < 8; i++) {
			if (dominantInGrowths)
				growths[i] += otherParent.baseGrowths[i] / 2;
			else
				growths[i] += otherParent.baseGrowths[i];
		}
		for (Integer x : otherParent.personalSkills) {
			if (!nativeSkills.contains(x))
				foreignSkills.add(x);
		}
		setBloodByOtherParent();
		updateRanks();
		updateGrowths();
	}

	private void resetWeaponRanks() {
		weaponRanks = charClass.getRankDuplicates(promoted);
		promotedWeaponRanks = charClass.getRankDuplicates(true);
		//sharedRanks will reset itself
	}

	private void setStatsByStaticParent() {
		setBloodByStaticParent();

		for (int i = 0; i < 8; i++) {
			if (dominantInGrowths)
				growths[i] = staticParent.baseGrowths[i];
			else
				growths[i] = staticParent.baseGrowths[i] / 2;
		}
	}

	private void setBloodByStaticParent() {
		hb.removeAll(hb);
		for (HolyBlood x : staticParent.hb) {
			if (doesNotContain(x)) {
					if (!x.isMajor()) // minor blood
						hb.add(x);
					else if (dominantInEqHB) // major blood on dominant parent
						hb.add(x);
					else // major blood on recessive parent
						hb.add(new HolyBlood(x.getCrusader(), x.getWepType(), x.getCertainGrowths(false), false));
			}
		}
	}

	private void setBloodByOtherParent() {
		for (HolyBlood x : otherParent.hb) {
			boolean match = false;
			for (HolyBlood y : staticParent.hb) {
				if (x.typeMatch(y)) {
					hb.set(hb.indexOf(y),
							new HolyBlood(x.getCrusader(), x.getWepType(), x.getCertainGrowths(true), true));
					match = true;
				}
			}
			if (!match) {
				if (x.isMajor() && !dominantInEqHB)
					hb.add(x);
				else
					hb.add(new HolyBlood(x.getCrusader(), x.getWepType(), x.getCertainGrowths(false), false));
			}
		}
		if (vanilla) {
			for (int i=0; i<hb.size(); i++) {
				if (hb.get(i).getCrusader()==12) {
					hb.remove(i);
					break;
				}
					
			}
		}
	}

	private boolean doesNotContain(HolyBlood h) {
		for (HolyBlood x : hb) {
			if (x.equals(h))
				return false;
		}
		return true;
	}

	private void updateRanks() {
		resetWeaponRanks();
		for (HolyBlood h : hb) {
			int typeIndex = h.getWepType();
			// 0 = None; 1 = C; 2 = B; 3 = A; 4+ = *
			if (weaponRanks[typeIndex] > 0) { // if you've got a rank
				if (h.isMajor()) // if major, give 4 for a * rank
					weaponRanks[typeIndex] = 4;
				else if (weaponRanks[typeIndex] < 3) // if you've got a C or B
					weaponRanks[typeIndex]++;
			}
			if (promotedWeaponRanks[typeIndex] > 0) { //C-A
				if (h.isMajor())
					promotedWeaponRanks[typeIndex] = 4;
				else if (promotedWeaponRanks[typeIndex] == 1)
					promotedWeaponRanks[typeIndex] = 2;
				else if (promotedWeaponRanks[typeIndex] == 2)
					promotedWeaponRanks[typeIndex] = 3;
			}
		}
		writeSharedRanks();
	}

	private void writeSharedRanks() {
		sharedRanks = new int[10];
		for (int i=0; i<weaponRanks.length; i++)
			sharedRanks[i] = weaponRanks[i];
		if (dominantInEqHB) {
			for (int i=0; i<sharedRanks.length; i++) {
				if (weaponRanks[i] <= staticParent.usableRanks[i])
					sharedRanks[i] = weaponRanks[i];
				else
					sharedRanks[i] = staticParent.usableRanks[i];
			}
		}
		else {
			for (int i=0; i<sharedRanks.length; i++) {
				if (weaponRanks[i] <= otherParent.usableRanks[i])
					sharedRanks[i] = weaponRanks[i];
				else
					sharedRanks[i] = otherParent.usableRanks[i];
			}
		}
	}
	
	private void updateGrowths() {
		for (HolyBlood x : hb) {
			int[] bloodBonuses = x.getCertainGrowths(x.isMajor());
			for (int i=0; i < bloodBonuses.length; i++) {
				growths[i] += bloodBonuses[i];
			}
		}
	}
	
	public String toString() {
		
		String x = name + "\nClass: " + charClass.getName(promoted) + "\nFixed Parent: " + staticParent.name
				+ "\tOther Parent: " + otherParent.name + "\nGrowths: ";

		
		if(this.otherParent==null) {
			this.setOtherParent(Driver.allParents.get(24));
		}
		
		for (int i = 0; i < 8; i++)
			x += growths[i] + "(" + tsStats[i] + ") ";

		x += "\nNative skills: ";
		for (Integer y : nativeSkills) {
			if (y != 22) { //not None
				if (y==8||y==10||y==11) { //Sol, Astra, or Luna
					if (ASLForEveryone || charClass.canInheritASL())
						x += tsSkills[y] + " "; //add the skill
					else
						; //don't add
				}
				else //not a sword skill, so add
					x += tsSkills[y] + " ";
			}
		}

		x += "\tForeign skills: ";
		for (Integer y : foreignSkills) {
			if (y != 22) { //not None
				if (y==8||y==10||y==11) { //Sol, Astra, or Luna
					if (ASLForEveryone || charClass.canInheritASL())
						x += tsSkills[y] + " "; //add the skill
					else
						; //don't add
				}
				else //not a sword skill, so add
					x += tsSkills[y] + " ";
			}
		}

		x += "\nHoly blood: ";
		for (HolyBlood y : hb) {
			if (y.isMajor())
				x += "Major ";
			else
				x += "Minor ";
			x += tsCrusaders[y.getCrusader()] + "(" + tsWepTypes[y.getWepType()] + ")";
			if(hb.indexOf(y)<hb.size()-1)
				x+=", ";
		}
		
		x += "\nDefault inventory: ";
		for (int i=0; i<defaultInventory.size(); i++) {
			x += defaultInventory.get(i).getName();
			if ( (i+1) < defaultInventory.size()) 
				x += ", ";
		}

		x += "\nWeapon ranks:";
		if (weaponRanks[0] > 0)
			x += " Swd-" + tsRanks[weaponRanks[0]];
		if (weaponRanks[1] > 0)
			x += " Lnc-" + tsRanks[weaponRanks[1]];
		if (weaponRanks[2] > 0)
			x += " Axe-" + tsRanks[weaponRanks[2]];
		if (weaponRanks[3] > 0)
			x += " Bow-" + tsRanks[weaponRanks[3]];
		if (weaponRanks[4] > 0)
			x += " Stf-" + tsRanks[weaponRanks[4]];
		if (weaponRanks[5] > 0)
			x += " Fr-" + tsRanks[weaponRanks[5]];
		if (weaponRanks[6] > 0)
			x += " Thn-" + tsRanks[weaponRanks[6]];
		if (weaponRanks[7] > 0)
			x += " Wnd-" + tsRanks[weaponRanks[7]];
		if (weaponRanks[8] > 0)
			x += " Lgt-" + tsRanks[weaponRanks[8]];
		if (weaponRanks[9] > 0)
			x += " Drk-" + tsRanks[weaponRanks[9]];
		// 0sword, 1lance, 2axe, 3bow, 4staff, 5fire, 6thunder, 7wind, 8light,
		// 9dark
		// 0 = None; 1 = C; 2 = B; 3 = A; 4+ = *

		if (dominantInEqHB)
			x += "\nRanks shared with " + staticParent.name + ":";
		else
			x += "\nRanks shared with " + otherParent.name + ":";
		if (sharedRanks[0] > 0)
			x += " Swd-" + tsRanks[sharedRanks[0]];
		if (sharedRanks[1] > 0)
			x += " Lnc-" + tsRanks[sharedRanks[1]];
		if (sharedRanks[2] > 0)
			x += " Axe-" + tsRanks[sharedRanks[2]];
		if (sharedRanks[3] > 0)
			x += " Bow-" + tsRanks[sharedRanks[3]];
		if (sharedRanks[4] > 0)
			x += " Stf-" + tsRanks[sharedRanks[4]];
		if (sharedRanks[5] > 0)
			x += " Fr-" + tsRanks[sharedRanks[5]];
		if (sharedRanks[6] > 0)
			x += " Thn-" + tsRanks[sharedRanks[6]];
		if (sharedRanks[7] > 0)
			x += " Wnd-" + tsRanks[sharedRanks[7]];
		if (sharedRanks[8] > 0)
			x += " Lgt-" + tsRanks[sharedRanks[8]];
		if (sharedRanks[9] > 0)
			x += " Drk-" + tsRanks[sharedRanks[9]];
		
		if (!promoted) {
			x += "\nOn promotion:\n\tClass: " + charClass.getName(true);
			boolean extraSkills = false;
			String z = "\n\tSkills: ";
			for (Integer y : promotedClassSkills) {
				if (!nativeSkills.contains(y) && y != 22) {
					extraSkills=true;
					z += tsSkills[y] + " ";
				}
			}
			if(extraSkills)
				x += z;
			
			x += "\n\tRanks:";
				if (promotedWeaponRanks[0] > 0)
					x += " Swd-" + tsRanks[promotedWeaponRanks[0]];
				if (promotedWeaponRanks[1] > 0)
					x += " Lnc-" + tsRanks[promotedWeaponRanks[1]];
				if (promotedWeaponRanks[2] > 0)
					x += " Axe-" + tsRanks[promotedWeaponRanks[2]];
				if (promotedWeaponRanks[3] > 0)
					x += " Bow-" + tsRanks[promotedWeaponRanks[3]];
				if (promotedWeaponRanks[4] > 0)
					x += " Stf-" + tsRanks[promotedWeaponRanks[4]];
				if (promotedWeaponRanks[5] > 0)
					x += " Fr-" + tsRanks[promotedWeaponRanks[5]];
				if (promotedWeaponRanks[6] > 0)
					x += " Thn-" + tsRanks[promotedWeaponRanks[6]];
				if (promotedWeaponRanks[7] > 0)
					x += " Wnd-" + tsRanks[promotedWeaponRanks[7]];
				if (promotedWeaponRanks[8] > 0)
					x += " Lgt-" + tsRanks[promotedWeaponRanks[8]];
				if (promotedWeaponRanks[9] > 0)
					x += " Drk-" + tsRanks[promotedWeaponRanks[9]];
		}
		return x + "\n";
	}
	
	public String getSkills() {
		String x="";
		for (Integer y : nativeSkills) {
			if (y != 22) { //not None
				if (y==8||y==10||y==11) { //Sol, Astra, or Luna
					if (ASLForEveryone || charClass.canInheritASL()) {
						x += tsSkills[y] + " "; //add the skill
						if(nativeSkills.indexOf(y) + 1 < nativeSkills.size() &&
								nativeSkills.indexOf(y+1) != 22)
							//x += ", ";
							;
					}
					else
						; //don't add
				}
				else //not a sword skill, so add
					{
					x += tsSkills[y] + " "; //add the skill
					if(nativeSkills.indexOf(y) + 1 < nativeSkills.size() &&
							nativeSkills.indexOf(y+1) != 22)
						//x += ", ";
						;
				}
			}
		}
		for (Integer y : foreignSkills) {
			if (y != 22) { //not None
				if (y==8||y==10||y==11) { //Sol, Astra, or Luna
					if (ASLForEveryone || charClass.canInheritASL())
						x += tsSkills[y] + " "; //add the skill
					else
						; //don't add
				}
				else //not a sword skill, so add
					x += tsSkills[y] + " ";
			}
		}

		
		return x;
	}

	public int[] getWpnRanks() {
		return weaponRanks;
	}
	
	public int[] getSharedRanks() {
		return sharedRanks;
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
	
	public String getEqHBParent() {
		if (dominantInEqHB)
			return staticParent.name;
		else
			return otherParent.name;
	}
}
