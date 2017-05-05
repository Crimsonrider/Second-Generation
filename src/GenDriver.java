import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.io.File;
import java.io.IOException;
import java.awt.*;
import javax.swing.*;

public class GenDriver extends JFrame {
	public static HolyBlood[][] allHolyBlood = new HolyBlood [13][2];
	public static UnitClass[] allMyClasses = new UnitClass [39];
	public static ArrayList <Unit> allParents = new ArrayList <Unit> ();
	public static ArrayList <Child> allKids = new ArrayList <Child> ();
	public static ArrayList <StaticCharacter> allStatics = new ArrayList <StaticCharacter> ();
	public static Scanner parentReader, kidReader, staticReader, sc;
	public static boolean printStuff = false, vanilla = true, UIBuilding=false;
	public static ArrayList <Weapon> allWeapons = new ArrayList <Weapon> ();
	public static Child activeChild;
	public static StaticCharacter activeStatic;
	

	public static void main(String[] args) {
		
		
	
		 EventQueue.invokeLater(() -> {
		 
            Build ex;
			ex = new Build();
			ex.setVisible(true);
            
        });
        
        
		if (!UIBuilding) {
				loadFiles();
			//initialize the stuff
				initialiseHB();
				initialiseClasses();
				initialiseWeapons();
			//read parents from a file :)
				for (int i=0; i<25; i++) {
					allParents.add(initialiseParent());
					if (printStuff)
						System.out.println(allParents.get(i).toString());
				}
				

			//intialise static bros
				for (int i=0; i<22; i++) {
					allStatics.add(initialiseStatics());
					staticSetInventory(allStatics.get(i));
					if (printStuff)
						System.out.println(allStatics.get(i).toString());
				}
				//System.out.print(allStatics.get(allStatics.size()-1).toString());
		}		
		
		initialiseKids();
		//get kid's other parents from user
			sc = new Scanner(System.in);
			
			for (Child kid : allKids) {
				if (printStuff) {
					System.out.println(kid.name + "'s fixed parent is " + kid.getFParent() + ". Who is their other parent?");
					String in = sc.next();
					if (in.equals("Q")) break;
						if (in.equals("S")) continue;
						kid.setOtherParent(findParent(in));
					System.out.println(kid.toString());
				}
			}
			String listString = "";
			
			allKids.get(5).setOtherParent(allParents.get(0));
			int[] z = allKids.get(5).getSharedRanks();
			
			for (Integer n : z)
			{
			    listString += n + "\t";
			}

			System.out.println(listString);
			

			allKids.get(10).setOtherParent(allParents.get(0));
			z = allKids.get(10).getSharedRanks();
			
			listString = "";
			for (Integer n : z)
			{
			    listString += n + "\t";
			}

			System.out.println(listString);
	}
	
	static Unit findParent(String theirName) {
		for (Unit x : allParents) {
			if (x.name.toLowerCase().equals(theirName.toLowerCase()))
				return x;
		}
		return null;
	}
	
	private static void loadFiles() {
		Child.vanilla = vanilla;
		Child.ASLForEveryone = false;
		try {
			parentReader = new Scanner(new File("parents.txt"));
			if (parentReader.next().equals("randomized"))
				Child.vanilla = false;
			if (parentReader.next().equals("ASL"))
				Child.ASLForEveryone = true;
			System.out.println("vanilla? " + Child.vanilla + "\tASL? " + Child.ASLForEveryone);
		}
		catch (IOException e) {
			System.out.println("'parents.txt' not found");
		}
		try {
			kidReader = new Scanner(new File("kids.txt"));
		}
		catch (IOException e) {
			System.out.println("'kids.txt' not found");
		}
		try {
			staticReader = new Scanner(new File("static.txt"));
		}
		catch (IOException e) {
			System.out.println("'static.txt' not found");
		}
	}
	
	private static Unit initialiseParent() {
		String tempName;
		int[] tempGrowths = new int[8];
		ArrayList <Integer> tempSkills = new ArrayList <Integer> ();
		ArrayList <HolyBlood> tempHB = new ArrayList <HolyBlood> ();
		int tempMyClass;

		for (int i=0; i<tempSkills.size(); i++)
			tempSkills.remove(i);
		for (int i=0; i<tempHB.size(); i++)
			tempHB.remove(i);
		tempName = parentReader.next();
		for (int i=0; i < 8; i++) {
			tempGrowths[i] = parentReader.nextInt();
		}
		int size = parentReader.nextInt();
		for (int i=0; i < size; i++) {
			tempSkills.add(parentReader.nextInt());
		}
		size = parentReader.nextInt();
		for (int i=0; i < size; i++) {
			tempHB.add(allHolyBlood[parentReader.nextInt()][parentReader.nextInt()]);
		}
		tempMyClass = determineMyClass(parentReader.nextInt(), parentReader.nextInt());
		if (tempName.equals("Quan")||tempName.equals("Sigurd")||tempName.equals("Claude")||tempName.equals("Briggid"))
			return new Unit(
				tempName,
				tempGrowths,
				tempSkills,
				tempHB,
				allMyClasses[tempMyClass], true);
		else
			return new Unit(
				tempName,
				tempGrowths,
				tempSkills,
				tempHB,
				allMyClasses[tempMyClass], false);
		//String name
		//int[] theirGrowths
		//ArrayList <Integer> theirSkills
		//ArrayList <HolyBlood> theirHB
		//UnitClass theirClass
		//boolean isPromoted
	}
	

	private static int determineMyClass(int base, int promo) {
		switch(base) {
			case 29:
				return determineMyT2Class(promo);
			case 0:
				return 0;
			case 1:
				return 1;
			case 2:
				return 2;
			case 3:
				if (promo==2)
					return 3;
				else
					return 4;
			case 4:
				return 5;
			case 5:
				return 6;
			//skip 7
			case 6: //Dancer (no promo - 25)
				return 8;
			case 7:
				return 9;
			case 8:
				return 10;
			case 9:
				return 11;
			case 10:
				return 12;
			case 11:
				return 13;
			case 12:
				return 14;
			case 13:
				return 15;
			case 14:
				return 16;
			case 15:
				return 17;
			case 16:
				return 18;
			case 17:
				return 19;
			case 18:
				return 20;
			case 19:
				return 21;
			case 20:
				return 22;
			case 21: //Fire Mage
				if (promo==17)
					return 23;
				else if(promo==18)
					return 24;
			case 22: //Thun Mage
				if (promo==17)
					return 25;
				else if(promo==18)
					return 26;
			case 23: //Wind Mage
				if (promo==17)
					return 27;
				else if(promo==18)
					return 28;
			case 24: //Mage
				if (promo==17)
					return 29;
				else if (promo==18)
					return 30;
				else
					return 31;
			case 25:
				return 32;
			case 26: 
				return 33;
			case 27: 
				return 34;
			//skip 35 and 36
			case 28: 
				return 37;
			default:
				return 38;
		}
	}
	
	private static int determineMyT2Class(int promo) {
		switch (promo) {
			case 29:
				return 38;
			case 0: //LK
				return 0;
			case 1: //MK
				return 1;
			case 2: //Swordmaster
				return 3;
			case 3: //Hero
				return 4;
			case 4: //Thief
				return 5;
			case 5:
				return 7;
			case 7:
				return 9;
			case 8:
				return 11;
			case 9:
				return 12;
			case 10:
				return 13;
			case 11:
				return 14;
			case 12:
				return 15;
			case 13:
				return 16;
			case 14:
				return 17;
			case 15:
				return 21;
			case 16:
				return 22;
			case 17:
				return 29;
			case 18:
				return 30;
			case 19:
				return 31;
			case 20:
				return 32;
			case 21:
				return 34;
			case 22:
				return 25;
			case 23:
				return 36;
			case 24:
				return 37;
			default: 
				return 38;
		}
	}
	
	private static void initialiseClasses() {
		allMyClasses[0] = new UnitClass(
				"Junior Lord",
				"Lord Knight",
				new int[] { 2, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
				new int[] { 3, 2, 0, 0, 0, 0, 0, 0, 0, 0 },
				new ArrayList<Integer>(Arrays.asList(22)),
				new ArrayList<Integer>(Arrays.asList(22))
				);
		allMyClasses[1] = new UnitClass(
				"Prince",
				"Master Knight",
				new int[] { 3, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
				new int[] { 3, 3, 3, 3, 3, 3, 3, 3, 1, 0 },
				new ArrayList<Integer>(Arrays.asList(22)),
				new ArrayList<Integer>(Arrays.asList(0))
				);
		allMyClasses[2] = new UnitClass(
				"Princess",
				"Master Knight",
				new int[] { 2, 0, 0, 0, 1, 0, 0, 0, 0, 0 },
				new int[] { 3, 3, 3, 3, 3, 3, 3, 3, 1, 0 },
				new ArrayList<Integer>(Arrays.asList(12)),
				new ArrayList<Integer>(Arrays.asList(0))
				);
		allMyClasses[3] = new UnitClass(
				"Swordfighter",
				"Swordmaster",
				new int[] { 3, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
				new int[] { 3, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
				new ArrayList<Integer>(Arrays.asList(0)),
				new ArrayList<Integer>(Arrays.asList(0, 5))
				);
		allMyClasses[4] = new UnitClass(
				"Swordfighter",
				"Hero",
				new int[] { 3, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
				new int[] { 3, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
				new ArrayList<Integer>(Arrays.asList(0)),
				new ArrayList<Integer>(Arrays.asList(0))
				);
		allMyClasses[5] = new UnitClass(
				"Thief",
				"Thief Fighter",
				new int[] { 1, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
				new int[] { 3, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
				new ArrayList<Integer>(Arrays.asList(18)),
				new ArrayList<Integer>(Arrays.asList(0, 18))
				);
		allMyClasses[6] = new UnitClass(
				"Dancer",
				"None",
				new int[] { 1, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
				new int[] { 1, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
				new ArrayList<Integer>(Arrays.asList(23)),
				new ArrayList<Integer>(Arrays.asList(23))
				);
		allMyClasses[7] = new UnitClass(
				"",
				"Dancer",
				new int[] { 1, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
				new int[] { 1, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
				new ArrayList<Integer>(Arrays.asList(23)),
				new ArrayList<Integer>(Arrays.asList(23))
				);
		allMyClasses[8] = new UnitClass(
				"Axe Fighter",
				"Warrior",
				new int[] { 0, 0, 3, 0, 0, 0, 0, 0, 0, 0 },
				new int[] { 0, 0, 3, 2, 0, 0, 0, 0, 0, 0 },
				new ArrayList<Integer>(Arrays.asList(22)),
				new ArrayList<Integer>(Arrays.asList(22))
				);
		allMyClasses[9] = new UnitClass(
				"Bow Fighter",
				"Sniper",
				new int[] { 0, 0, 0, 3, 0, 0, 0, 0, 0, 0 },
				new int[] { 0, 0, 0, 3, 0, 0, 0, 0, 0, 0 },
				new ArrayList<Integer>(Arrays.asList(0)),
				new ArrayList<Integer>(Arrays.asList(0))
				);
		allMyClasses[10] = new UnitClass(
				"Hunter",
				"Sniper",
				new int[] { 0, 0, 0, 3, 0, 0, 0, 0, 0, 0 },
				new int[] { 0, 0, 0, 3, 0, 0, 0, 0, 0, 0 },
				new ArrayList<Integer>(Arrays.asList(22)),
				new ArrayList<Integer>(Arrays.asList(0))
				);
		allMyClasses[11] = new UnitClass(
				"Cavalier",
				"Paladin (M)",
				new int[] { 2, 1, 0, 0, 0, 0, 0, 0, 0, 0 },
				new int[] { 2, 2, 0, 0, 0, 0, 0, 0, 0, 0 },
				new ArrayList<Integer>(Arrays.asList(22)),
				new ArrayList<Integer>(Arrays.asList(22))
				);
		allMyClasses[12] = new UnitClass(
				"Troubadour", 
				"Paladin (F)", 
				new int[] { 2, 0, 0, 0, 1, 0, 0, 0, 0, 0 },
				new int[] { 2, 1, 0, 0, 1, 0, 0, 0, 0, 0 }, 
				new ArrayList<Integer>(Arrays.asList(22)),
				new ArrayList<Integer>(Arrays.asList(22))
				);
		allMyClasses[13] = new UnitClass(
				"Sword Knight",
				"Forrest Knight",
				new int[] { 2, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
				new int[] { 3, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
				new ArrayList<Integer>(Arrays.asList(22)),
				new ArrayList<Integer>(Arrays.asList(5))
				);
		allMyClasses[14] = new UnitClass(
				"Lance Knight",
				"Duke Knight",
				new int[] { 0, 2, 0, 0, 0, 0, 0, 0, 0, 0 },
				new int[] { 0, 3, 0, 0, 0, 0, 0, 0, 0, 0 },
				new ArrayList<Integer>(Arrays.asList(22)),
				new ArrayList<Integer>(Arrays.asList(22))
				);
		allMyClasses[15] = new UnitClass(
				"Axe Knight",
				"Great Knight",
				new int[] { 0, 0, 2, 0, 0, 0, 0, 0, 0, 0 },
				new int[] { 0, 0, 3, 0, 0, 0, 0, 0, 0, 0 },
				new ArrayList<Integer>(Arrays.asList(22)),
				new ArrayList<Integer>(Arrays.asList(22))
				);
		allMyClasses[16] = new UnitClass(
				"Bow Knight",
				"Arch Knight",
				new int[] { 0, 0, 0, 2, 0, 0, 0, 0, 0, 0 },
				new int[] { 0, 0, 0, 3, 0, 0, 0, 0, 0, 0 },
				new ArrayList<Integer>(Arrays.asList(22)),
				new ArrayList<Integer>(Arrays.asList(22))
				);
		allMyClasses[17] = new UnitClass(
				"Sword Armour",
				"General",
				new int[] { 2, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
				new int[] { 3, 3, 2, 2, 0, 0, 0, 0, 0, 0 },
				new ArrayList<Integer>(Arrays.asList(22)),
				new ArrayList<Integer>(Arrays.asList(14))
				);
		allMyClasses[18] = new UnitClass(
				"Lance Armour",
				"General",
				new int[] { 0, 2, 0, 0, 0, 0, 0, 0, 0, 0 },
				new int[] { 3, 3, 2, 2, 0, 0, 0, 0, 0, 0 },
				new ArrayList<Integer>(Arrays.asList(22)),
				new ArrayList<Integer>(Arrays.asList(14))
				);
		allMyClasses[19] = new UnitClass(
				"Axe Armour",
				"General",
				new int[] { 0, 0, 2, 0, 0, 0, 0, 0, 0, 0 },
				new int[] { 3, 3, 2, 2, 0, 0, 0, 0, 0, 0 },
				new ArrayList<Integer>(Arrays.asList(22)),
				new ArrayList<Integer>(Arrays.asList(14))
				);
		allMyClasses[20] = new UnitClass(
				"Bow Armour",
				"General",
				new int[] { 0, 2, 0, 0, 0, 0, 0, 0, 0, 0 },
				new int[] { 3, 3, 2, 2, 0, 0, 0, 0, 0, 0 },
				new ArrayList<Integer>(Arrays.asList(22)),
				new ArrayList<Integer>(Arrays.asList(14))
				);
		allMyClasses[21] = new UnitClass(
				"Pegasus Knight",
				"Falcoknight",
				new int[] { 2, 2, 0, 0, 0, 0, 0, 0, 0, 0 },
				new int[] { 3, 3, 0, 0, 1, 0, 0, 0, 0, 0 },
				new ArrayList<Integer>(Arrays.asList(22)),
				new ArrayList<Integer>(Arrays.asList(5))
				);
		allMyClasses[22] = new UnitClass(
				"Dragon Knight", 
				"Dragon Master", 
				new int[] { 2, 2, 0, 0, 0, 0, 0, 0, 0, 0 },
				new int[] { 3, 3, 0, 0, 0, 0, 0, 0, 0, 0 }, 
				new ArrayList<Integer>(Arrays.asList(22)),
				new ArrayList<Integer>(Arrays.asList(0))
				);
		allMyClasses[23] = new UnitClass(
				"Fire Mage",
				"Mage Fighter (M)",
				new int[] { 0, 0, 0, 0, 0, 2, 0, 0, 0, 0 },
				new int[] { 1, 0, 0, 0, 0, 2, 2, 2, 0, 0 },
				new ArrayList<Integer>(Arrays.asList(22)),
				new ArrayList<Integer>(Arrays.asList(5))
				);
		allMyClasses[24] = new UnitClass(
				"Fire Mage",
				"Mage Fighter (F)",
				new int[] { 0, 0, 0, 0, 0, 2, 0, 0, 0, 0 },
				new int[] { 1, 0, 0, 0, 2, 2, 2, 2, 0, 0 },
				new ArrayList<Integer>(Arrays.asList(22)),
				new ArrayList<Integer>(Arrays.asList(5))
				);
		allMyClasses[25] = new UnitClass(
				"Thunder Mage",
				"Mage Fighter (M)",
				new int[] { 0, 0, 0, 0, 0, 0, 2, 0, 0, 0 },
				new int[] { 1, 0, 0, 0, 0, 2, 2, 2, 0, 0 },
				new ArrayList<Integer>(Arrays.asList(22)),
				new ArrayList<Integer>(Arrays.asList(5))
				);
		allMyClasses[26] = new UnitClass(
				"Thunder Mage",
				"Mage Fighter (F)",
				new int[] { 0, 0, 0, 0, 0, 0, 2, 0, 0, 0 },
				new int[] { 1, 0, 0, 0, 2, 2, 2, 2, 0, 0 },
				new ArrayList<Integer>(Arrays.asList(22)),
				new ArrayList<Integer>(Arrays.asList(5))
				);
		allMyClasses[27] = new UnitClass(
				"Wind Mage",
				"Mage Fighter (M)",
				new int[] { 0, 0, 0, 0, 0, 0, 0, 2, 0, 0 },
				new int[] { 1, 0, 0, 0, 0, 2, 2, 2, 0, 0 },
				new ArrayList<Integer>(Arrays.asList(22)),
				new ArrayList<Integer>(Arrays.asList(5))
				);
		allMyClasses[28] = new UnitClass(
				"Wind Mage",
				"Mage Fighter (F)",
				new int[] { 0, 0, 0, 0, 0, 0, 0, 2, 0, 0 },
				new int[] { 1, 0, 0, 0, 2, 2, 2, 2, 0, 0 },
				new ArrayList<Integer>(Arrays.asList(22)),
				new ArrayList<Integer>(Arrays.asList(5))
				);
		allMyClasses[29] = new UnitClass(
				"Mage",
				"Mage Fighter (M)",
				new int[] { 0, 0, 0, 0, 0, 1, 1, 1, 0, 0 },
				new int[] { 1, 0, 0, 0, 0, 2, 2, 2, 0, 0 },
				new ArrayList<Integer>(Arrays.asList(22)),
				new ArrayList<Integer>(Arrays.asList(5))
				);
		allMyClasses[30] = new UnitClass(
				"Mage",
				"Mage Fighter (F)",
				new int[] { 0, 0, 0, 0, 0, 1, 1, 1, 0, 0 },
				new int[] { 1, 0, 0, 0, 2, 2, 2, 2, 0, 0 },
				new ArrayList<Integer>(Arrays.asList(22)),
				new ArrayList<Integer>(Arrays.asList(5))
				);
		allMyClasses[31] = new UnitClass(
				"Mage",
				"Mage Knight",
				new int[] { 0, 0, 0, 0, 0, 1, 1, 1, 0, 0 },
				new int[] { 2, 0, 0, 0, 0, 2, 2, 2, 0, 0 },
				new ArrayList<Integer>(Arrays.asList(22)),
				new ArrayList<Integer>(Arrays.asList(22))
				);
		allMyClasses[32] = new UnitClass(
				"Bard",
				"Sage",
				new int[] { 0, 0, 0, 0, 0, 1, 1, 1, 1, 0 },
				new int[] { 0, 0, 0, 0, 2, 2, 2, 2, 3, 0 },
				new ArrayList<Integer>(Arrays.asList(22)),
				new ArrayList<Integer>(Arrays.asList(5))
				);
		allMyClasses[33] = new UnitClass(
				"Shaman",
				"Sage",
				new int[] { 0, 0, 0, 0, 2, 0, 0, 0, 2, 0 },
				new int[] { 0, 0, 0, 0, 2, 2, 2, 2, 3, 0 },
				new ArrayList<Integer>(Arrays.asList(22)),
				new ArrayList<Integer>(Arrays.asList(5))
				);
		allMyClasses[34] = new UnitClass(
				"Priest",
				"High Priest",
				new int[] { 0, 0, 0, 0, 2, 0, 0, 0, 0, 0 },
				new int[] { 0, 0, 0, 0, 3, 1, 1, 1, 0, 0 },
				new ArrayList<Integer>(Arrays.asList(22)),
				new ArrayList<Integer>(Arrays.asList(22))
				);
		allMyClasses[35] = new UnitClass(
				"",
				"Bishop",
				new int[] { 0, 0, 0, 0, 3, 2, 2, 2, 0, 0 },
				new int[] { 0, 0, 0, 0, 3, 2, 2, 2, 0, 0 },
				new ArrayList<Integer>(Arrays.asList(22)),
				new ArrayList<Integer>(Arrays.asList(22))
				);
		allMyClasses[36] = new UnitClass(
				"",
				"Queen",
				new int[] { 0, 0, 0, 0, 3, 3, 3, 3, 0, 0 },
				new int[] { 0, 0, 0, 0, 3, 3, 3, 3, 0, 0 },
				new ArrayList<Integer>(Arrays.asList(22)),
				new ArrayList<Integer>(Arrays.asList(12))
				);
		allMyClasses[37] = new UnitClass(
				"Dark Mage",
				"Dark Bishop",
				new int[] { 0, 0, 0, 0, 2, 0, 0, 0, 0, 2 },
				new int[] { 0, 0, 0, 0, 3, 3, 3, 3, 0, 3 },
				new ArrayList<Integer>(Arrays.asList(22)),
				new ArrayList<Integer>(Arrays.asList(0))
				);
		allMyClasses[38] = new UnitClass(
				"None",
				"None",
				new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
				new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
				new ArrayList<Integer>(Arrays.asList(22)),
				new ArrayList<Integer>(Arrays.asList(22))
				);
	}
	
	private static void initialiseHB() {
		//0Baldur, 1Odo, 2Hezul, 3Nova, 4Dain, 5Neir, 6Ulir
			//7Blaggi, 8Fala, 9Tordo, 10Forseti, 11Naga, 12Loptyr
		//0sword, 1lance, 2axe, 3bow, 4staff, 5fire, 6thunder, 7wind, 8light, 9dark
		HolyBlood miBaldur = new HolyBlood (0, 0, new int[] {20, 10, 0, 10, 0, 10, 0, 0}, false);
		HolyBlood maBaldur = new HolyBlood (0, 0, new int[] {40, 20, 0, 20, 0, 20, 0, 0}, true);
		allHolyBlood[0][0] = miBaldur; allHolyBlood[0][1] = maBaldur;
		HolyBlood miOdo = new HolyBlood (1, 0, new int[] {20, 0, 0, 30, 0, 0, 0, 0}, false);
		HolyBlood maOdo = new HolyBlood (1, 0, new int[] {40, 0, 0, 60, 0, 0, 0, 0}, true);
		allHolyBlood[1][0] = miOdo; allHolyBlood[1][1] = maOdo;
		HolyBlood miHezul = new HolyBlood (2, 0, new int[] {20, 30, 0, 0, 0, 0, 0, 0}, false);
		HolyBlood maHezul = new HolyBlood (2, 0, new int[] {40, 60, 0, 0, 0, 0, 0, 0}, true);
		allHolyBlood[2][0] = miHezul; allHolyBlood[2][1] = maHezul;
		HolyBlood miNova = new HolyBlood (3, 1, new int[] {20, 10, 0, 0, 10, 0, 10, 0}, false);
		HolyBlood maNova = new HolyBlood (3, 1, new int[] {40, 20, 0, 0, 20, 0, 20, 0}, true);
		allHolyBlood[3][0] = miNova; allHolyBlood[3][1] = maNova;
		HolyBlood miDain = new HolyBlood (4, 1, new int[] {20, 0, 0, 0, 30, 0, 0, 0}, false);
		HolyBlood maDain = new HolyBlood (4, 1, new int[] {40, 0, 0, 0, 60, 0, 0, 0}, true);
		allHolyBlood[4][0] = miDain; allHolyBlood[4][1] = maDain;
		HolyBlood miNeir = new HolyBlood (5, 2, new int[] {20, 0, 0, 0, 0, 0, 30, 0}, false);
		HolyBlood maNeir = new HolyBlood (5, 2, new int[] {40, 0, 0, 0, 0, 0, 60, 0}, true);
		allHolyBlood[5][0] = miNeir; allHolyBlood[5][1] = maNeir;
		HolyBlood miUlir = new HolyBlood (6, 3, new int[] {20, 0, 0, 0, 0, 30, 0, 0}, false);
		HolyBlood maUlir = new HolyBlood (6, 3, new int[] {40, 0, 0, 0, 0, 60, 0, 0}, true);
		allHolyBlood[6][0] = miUlir; allHolyBlood[6][1] = maUlir;
		HolyBlood miBlaggi = new HolyBlood (7, 4, new int[] {10, 0, 10, 0, 0, 10, 0, 20}, false);
		HolyBlood maBlaggi = new HolyBlood (7, 4, new int[] {20, 0, 20, 0, 0, 20, 0, 40}, true);
		allHolyBlood[7][0] = miBlaggi; allHolyBlood[7][1] = maBlaggi;
		HolyBlood miFala = new HolyBlood (8, 5, new int[] {20, 0, 30, 0, 0, 0, 0, 0}, false);
		HolyBlood maFala = new HolyBlood (8, 5, new int[] {40, 0, 60, 0, 0, 0, 0, 0}, true);
		allHolyBlood[8][0] = miFala; allHolyBlood[8][1] = maFala;
		HolyBlood miTordo = new HolyBlood (9, 6, new int[] {20, 0, 0, 30, 0, 0, 0, 0}, false);
		HolyBlood maTordo = new HolyBlood (9, 6, new int[] {40, 0, 0, 60, 0, 0, 0, 0}, true);
		allHolyBlood[9][0] = miTordo; allHolyBlood[9][1] = maTordo;
		HolyBlood miHolsety = new HolyBlood (10, 7, new int[] {20, 0, 0, 0, 30, 0, 0, 0}, false);
		HolyBlood maHolsety = new HolyBlood (10, 7, new int[] {40, 0, 0, 0, 60, 0, 0, 0}, true);
		allHolyBlood[10][0] = miHolsety; allHolyBlood[10][1] = maHolsety;
		HolyBlood miNaga = new HolyBlood (11, 8, new int[] {10, 0, 20, 0, 0, 0, 0, 20}, false);
		HolyBlood maNaga = new HolyBlood (11, 8, new int[] {20, 0, 40, 0, 0, 0, 0, 40}, true);
		allHolyBlood[11][0] = miNaga; allHolyBlood[11][1] = maNaga;
		HolyBlood miLoptyr = new HolyBlood (12, 9, new int[] {10, 0, 20, 0, 0, 0, 0, 20}, false);
		HolyBlood maLoptyr = new HolyBlood (12, 9, new int[] {20, 0, 40, 0, 0, 0, 0, 40}, true);
		allHolyBlood[12][0] = miLoptyr; allHolyBlood[12][1] = maLoptyr;
	}
	
	private static void initialiseKids() {
		
		Child Seliph = new Child (kidReader.next(), allParents.get(15), false);
		allKids.add(Seliph);
		Seliph.setClass(allMyClasses[determineMyClass(kidReader.nextInt(), kidReader.nextInt())], false);
		kidSetInventory(Seliph);
		Seliph.setSibling("--");
		
		Child Leif = new Child (kidReader.next(), allParents.get(16), true);
		allKids.add(Leif);
		Leif.setClass(allMyClasses[determineMyClass(kidReader.nextInt(), kidReader.nextInt())], false);
		kidSetInventory(Leif);
		Leif.setSibling("Altena");
		
		Child Altena = new Child (kidReader.next(), allParents.get(16), false);
		allKids.add(Altena);
		Altena.setClass(allMyClasses[determineMyClass(kidReader.nextInt(), kidReader.nextInt())], false);
		kidSetInventory(Altena);
		Altena.setSibling("Leif");
		
		Child Ulster = new Child (kidReader.next(), allParents.get(18), false);
		allKids.add(Ulster);
		Ulster.setClass(allMyClasses[determineMyClass(kidReader.nextInt(), kidReader.nextInt())], false);
		kidSetInventory(Ulster);
		Ulster.setSibling("Larcei");
		
		Child Faval = new Child (kidReader.next(), allParents.get(23), false);
		allKids.add(Faval);
		Faval.setClass(allMyClasses[determineMyClass(kidReader.nextInt(), kidReader.nextInt())], false);
		kidSetInventory(Faval);
		Faval.setSibling("Patty");
		
		Child Corpul = new Child (kidReader.next(), allParents.get(21), false);
		allKids.add(Corpul);
		Corpul.setClass(allMyClasses[determineMyClass(kidReader.nextInt(), kidReader.nextInt())], false);
		kidSetInventory(Corpul);
		Corpul.setSibling("Leen");
		
		
		Child Sety = new Child (kidReader.next(), allParents.get(19), false);
		allKids.add(Sety);
		Sety.setClass(allMyClasses[determineMyClass(kidReader.nextInt(), kidReader.nextInt())], true);
		kidSetInventory(Sety);
		Sety.setSibling("Fee");
		
		Child Delmudd = new Child (kidReader.next(), allParents.get(17), false);
		allKids.add(Delmudd);
		Delmudd.setClass(allMyClasses[determineMyClass(kidReader.nextInt(), kidReader.nextInt())], false);
		kidSetInventory(Delmudd);
		Delmudd.setSibling("Nanna");
		
		Child Lester = new Child (kidReader.next(), allParents.get(22), false);
		allKids.add(Lester);
		Lester.setClass(allMyClasses[determineMyClass(kidReader.nextInt(), kidReader.nextInt())], false);
		kidSetInventory(Lester);
		Lester.setSibling("Lana");
		
		Child Arthur = new Child (kidReader.next(), allParents.get(20), false);
		allKids.add(Arthur);
		Arthur.setClass(allMyClasses[determineMyClass(kidReader.nextInt(), kidReader.nextInt())], false);
		kidSetInventory(Arthur);
		Arthur.setSibling("Tinny");
		
		Child Patty = new Child (kidReader.next(), allParents.get(23), true);
		allKids.add(Patty);
		Patty.setClass(allMyClasses[determineMyClass(kidReader.nextInt(), kidReader.nextInt())], false);
		kidSetInventory(Patty);
		Patty.setSibling("Faval");
		
		Child Larcei = new Child (kidReader.next(), allParents.get(18), true);
		allKids.add(Larcei);
		Larcei.setClass(allMyClasses[determineMyClass(kidReader.nextInt(), kidReader.nextInt())], false);
		kidSetInventory(Larcei);
		Larcei.setSibling("Ulster");
		
		Child Lana = new Child (kidReader.next(), allParents.get(22), true);
		allKids.add(Lana);
		Lana.setClass(allMyClasses[determineMyClass(kidReader.nextInt(), kidReader.nextInt())], false);
		kidSetInventory(Lana);
		Lana.setSibling("Lester");
		
		Child Fee = new Child (kidReader.next(), allParents.get(19), true);
		allKids.add(Fee);
		Fee.setClass(allMyClasses[determineMyClass(kidReader.nextInt(), kidReader.nextInt())], false);
		kidSetInventory(Fee);
		Fee.setSibling("Sety");
		
		Child Tinny = new Child (kidReader.next(), allParents.get(20), true);
		allKids.add(Tinny);
		Tinny.setClass(allMyClasses[determineMyClass(kidReader.nextInt(), kidReader.nextInt())], false);
		kidSetInventory(Tinny);
		Tinny.setSibling("Arthur");
		
		Child Leen = new Child (kidReader.next(), allParents.get(21), true);
		allKids.add(Leen);
		Leen.setClass(allMyClasses[determineMyClass(kidReader.nextInt(), kidReader.nextInt())], false);
		kidSetInventory(Leen);
		Leen.setSibling("Corpul");
		
		Child Nanna = new Child (kidReader.next(), allParents.get(17), true);
		allKids.add(Nanna);
		Nanna.setClass(allMyClasses[determineMyClass(kidReader.nextInt(), kidReader.nextInt())], false);
		kidSetInventory(Nanna);
		Nanna.setSibling("Delmudd");
		
	}
	
	private static void staticSetInventory(StaticCharacter unit) {
		ArrayList <Weapon> temp = new ArrayList <Weapon> ();
		int i = staticReader.nextInt();
		for (int j=1; j<=i; j++) {
			temp.add(allWeapons.get(staticReader.nextInt()));
		}
		unit.setInventory(temp);
	}
	
	private static void kidSetInventory(Child unit) {
		ArrayList <Weapon> temp = new ArrayList <Weapon> ();
		int i = kidReader.nextInt();
		for (int j=1; j<=i; j++) {
			temp.add(allWeapons.get(kidReader.nextInt()));
		}
		unit.setInventory(temp);
	}
	
	private static StaticCharacter initialiseStatics() {
		String tempName;
		int[] tempGrowths = new int[8];
		ArrayList <Integer> tempSkills = new ArrayList <Integer> ();
		ArrayList <HolyBlood> tempHB = new ArrayList <HolyBlood> ();
		int tempMyClass;

		for (int i=0; i<tempSkills.size(); i++)
			tempSkills.remove(i);
		for (int i=0; i<tempHB.size(); i++)
			tempHB.remove(i);
		tempName = staticReader.next();
		
		
		for (int i=0; i < 8; i++) {
			tempGrowths[i] = staticReader.nextInt();
		}
		
		int size = staticReader.nextInt();
		for (int i=0; i < size; i++) {
			tempSkills.add(staticReader.nextInt());
		}
		
		size = staticReader.nextInt();
		for (int i=0; i < size; i++) {
			tempHB.add(allHolyBlood[staticReader.nextInt()][staticReader.nextInt()]);
		}
		
		tempMyClass = determineMyClass(staticReader.nextInt(), staticReader.nextInt());
		if (tempName.equals("Finn"))
			return new StaticCharacter(
					tempName,
					tempGrowths,
					tempSkills,
					tempHB,
					allMyClasses[tempMyClass], allParents.get(4).promoted,
					staticReader.next(), staticReader.next(), staticReader.next(), staticReader.next());
		else if (tempName.equals("Shannan")||tempName.equals("Hawk")
				||tempName.equals("Hannibal")||tempName.equals("Oifey"))
			return new StaticCharacter(
				tempName,
				tempGrowths,
				tempSkills,
				tempHB,
				allMyClasses[tempMyClass], true,
				staticReader.next(), staticReader.next(), staticReader.next(), staticReader.next());
		else
			return new StaticCharacter(
				tempName,
				tempGrowths,
				tempSkills,
				tempHB,
				allMyClasses[tempMyClass], false,
				staticReader.next(), staticReader.next(), staticReader.next(), staticReader.next());
	}
	
	private static void initialiseWeapons() {
		//0sword, 1lance, 2axe, 3bow, 4staff, 5fire, 6thunder, 7wind, 8light, 9dark
		//0 = None; 1 = C; 2 = B; 3 = A; 4+ = *
		allWeapons.add(new Weapon("Iron Sword", 0, 1));
		allWeapons.add(new Weapon("Steel Sword", 0, 2));
		allWeapons.add(new Weapon("Silver Sword", 0, 3));
		allWeapons.add(new Weapon("Iron Blade", 0, 3));
		allWeapons.add(new Weapon("Steel Blade", 0, 3));
		allWeapons.add(new Weapon("Silver Blade", 0, 3));
		allWeapons.add(new Weapon("Miracle Sword (Female only)", 0, 1));
		allWeapons.add(new Weapon("Thief Sword", 0, 1));
		allWeapons.add(new Weapon("Barrier Sword", 0, 1));
		allWeapons.add(new Weapon("Berserk Sword", 0, 1));
		allWeapons.add(new Weapon("Hero Sword", 0, 2));
		allWeapons.add(new Weapon("Silence Sword", 0, 1));
		allWeapons.add(new Weapon("Sleep Sword", 0, 1));
		allWeapons.add(new Weapon("Slim Sword", 0, 1));
		allWeapons.add(new Weapon("Defence Sword", 0, 1));
		allWeapons.add(new Weapon("Flame Sword", 0, 1));
		allWeapons.add(new Weapon("Earth Sword", 0, 1));
		allWeapons.add(new Weapon("Thunder Sword", 0, 1));
		allWeapons.add(new Weapon("Wind Sword", 0, 1));
		allWeapons.add(new Weapon("Light Sword", 0, 1));
		allWeapons.add(new Weapon("Mystletainn (Hezul)", 0, 4));
		allWeapons.add(new Weapon("Tyrfing (Odo)", 0, 4));
		allWeapons.add(new Weapon("Balmung (Baldur)", 0, 4));
		allWeapons.add(new Weapon("Armourslayer", 0, 2));
		allWeapons.add(new Weapon("Wingslayer", 0, 2));
		
		allWeapons.add(new Weapon("Iron Lance", 1, 1));
		allWeapons.add(new Weapon("Steel Lance", 1, 2));
		allWeapons.add(new Weapon("Silver Lance", 1, 3));
		allWeapons.add(new Weapon("Javelin", 1, 1));
		allWeapons.add(new Weapon("Horseslayer", 1, 1));
		allWeapons.add(new Weapon("Hero Lance", 1, 2));
		allWeapons.add(new Weapon("Slim Lance", 1, 1));
		allWeapons.add(new Weapon("Gungnir (Dain)", 1, 4));
		allWeapons.add(new Weapon("Gaeborg (Nova)", 1, 4));
		
		allWeapons.add(new Weapon("Iron Axe", 2, 1));
		allWeapons.add(new Weapon("Steel Axe", 2, 2));
		allWeapons.add(new Weapon("Silver Axe", 2, 3));
		allWeapons.add(new Weapon("Hero Axe", 2, 2));
		allWeapons.add(new Weapon("Swanchika (Neir)", 2, 4));
		allWeapons.add(new Weapon("Hand Axe", 2, 1));
		
		allWeapons.add(new Weapon("Iron Bow", 3, 1));
		allWeapons.add(new Weapon("Steel Bow", 3, 2));
		allWeapons.add(new Weapon("Silver Bow", 3, 3));
		allWeapons.add(new Weapon("Hero Bow", 3, 2));
		allWeapons.add(new Weapon("Killer Bow", 3, 2));
		allWeapons.add(new Weapon("Yewfelle (Ulir)", 3, 4));
		
		allWeapons.add(new Weapon("Fire", 5, 1));
		allWeapons.add(new Weapon("Elfire", 5, 2));
		allWeapons.add(new Weapon("Bolganone", 5, 3));
		allWeapons.add(new Weapon("Valflame (Fala)", 5, 4));
		allWeapons.add(new Weapon("Meteor", 5, 1));
		
		allWeapons.add(new Weapon("Thunder", 6, 1));
		allWeapons.add(new Weapon("Elthunder", 6, 2));
		allWeapons.add(new Weapon("Thoron", 6, 3));
		allWeapons.add(new Weapon("Mjolnir (Tordo)", 6, 4));
		allWeapons.add(new Weapon("Bolting", 6, 1));
		
		allWeapons.add(new Weapon("Wind", 7, 1));
		allWeapons.add(new Weapon("Elwind", 7, 2));
		allWeapons.add(new Weapon("Tornado", 7, 3));
		allWeapons.add(new Weapon("Holsety (Forseti)", 7, 4));
		allWeapons.add(new Weapon("Blizzard", 7, 1));
		
		allWeapons.add(new Weapon("Light", 8, 1));
		allWeapons.add(new Weapon("Nosferatu", 8, 3));
		allWeapons.add(new Weapon("Aura", 8, 3));
		allWeapons.add(new Weapon("Narga (Naga)", 8, 4));
		
		allWeapons.add(new Weapon("Jormugand", 9, 1));
		allWeapons.add(new Weapon("Fenrir", 9, 2));
		allWeapons.add(new Weapon("Hell", 9, 2));
		allWeapons.add(new Weapon("Loptuousu (Loptyr)", 9, 4));
		
		allWeapons.add(new Weapon("Heal", 4, 1));
		allWeapons.add(new Weapon("Mend", 4, 1));
		allWeapons.add(new Weapon("Recover", 4, 2));
		allWeapons.add(new Weapon("Physic", 4, 2));
		allWeapons.add(new Weapon("Fortify", 4, 3));
		allWeapons.add(new Weapon("Return", 4, 1));
		allWeapons.add(new Weapon("Warp", 4, 2));
		allWeapons.add(new Weapon("Rescue", 4, 3));
		//allWeapons.add(new Weapon("Charm", 4, 3));
		allWeapons.add(new Weapon("Restore", 4, 2));
		allWeapons.add(new Weapon("Valkyrie Staff (Blaggi)", 4, 4));
		allWeapons.add(new Weapon("Silence", 4, 2));
		allWeapons.add(new Weapon("Sleep", 4, 2));
		allWeapons.add(new Weapon("Berserk", 4, 2));
		//allWeapons.add(new Weapon("Thief", 4, 2));
		/**
		 *  = new ArrayList <Weapon> (Arrays.asList(tempWeapon, 
			IronSword, SteelSword, SilverSword, IronBlade, SteelBlade, SilverBlade, MiracleSword, 
			ThiefSword, BarrierSword, BerserkEdge, BraveSword, SilenceEdge, SleepEdge, SlimSword, 
			ShieldSword, FlameSword, EarthSword, BoltSword, WindSword, LightBrand, Mystletainn, 
			Tyrfing, Balmung, Armorslayer, Wingslayer, IronLance, SteelLance, SilverLance, Javelin, 
			Horseslayer, BraveLance, SlimLance, Gungnir, GaeBolg, IronAxe, SteelAxe, SilverAxe, 
			BraveAxe, Helswath, HandAxe, IronBow, SteelBow, SilverBow, BraveBow, KillerBow, Yewfelle,
			Fire, Elfire, Bolganone, Valflame, Meteor, Thunder, Elthunder, Thoron, Mjolnir, Bolting, 
			Wind, Elwind, Tornado, Forseti, Blizzard, Light, Nosferatu, Aura, Naga, Jormungand, 
			Fenrir, Hel, Loptyr, Heal, Mend, Recover, Physic, Fortify, Return, Warp, Rescue, Charm, 
			Restore, Valkyria, Silence, Sleep, Berserk, RenewalBand, ParagonBand, ThiefBand, 
			MiracleBand, PursuitBand, RecoverBand, BargainBand, KnightRing, ReturnBand, SpeedRing, 
			MagicRing, PowerRing, ShieldRing, BarrierRing, LegRing, SkillRing, Circlet))
		 */
	}
	
	
	public static void setActiveChild(String theirName) {
		//System.out.println("this is " + theirName);
		activeStatic = null;
		
		if (theirName.equals("Seliph")) {
			activeChild = allKids.get(0);
			Build.mother.setText("Deirdre");
			Build.sibling.setText("--");
			Build.father.setSelectedItem("Sigurd");
		}
		else if (theirName.equals("Leif")) {
			activeChild = allKids.get(1);
			Build.mother.setText("Ethlyn");
			Build.sibling.setText("Altena");
			Build.father.setSelectedItem("Quan");
		}
		else if (theirName.equals("Altena")) {
			activeChild = allKids.get(2);
			Build.mother.setText("Ethlyn");
			Build.sibling.setText("Leif");
			Build.father.setSelectedItem("Quan");
		}
		
		for (int i = 3; i < allKids.size(); i++) {
			if (allKids.get(i).name.equals(theirName)) {
				activeChild = allKids.get(i);
				Build.mother.setText(activeChild.getFParent());
				Build.sibling.setText(activeChild.getSibling());
			}
		}
		//System.out.println(activeChild.getWpnRnks());
	}
	
	public static void setActiveStatic(String theirName) {
		//System.out.println("this is " + theirName);
		activeChild = null;
		
		for (int i = 0; i < allStatics.size(); i++) {
			if (allStatics.get(i).name.equals(theirName)) {
				activeStatic = allStatics.get(i);
				Build.mother.setText(activeStatic.substituteData[3]);
				Build.sibling.setText(activeStatic.substituteData[0] + " (replaces " + activeStatic.substituteData[2] + ")");
				Build.staticExtra.setText("Replaces " + activeStatic.substituteData[1]);
			}
		}
		//System.out.println(activeStatic.getWpnRnks());
	}
	
	public static int getG(int i) {
		if (activeChild != null)
			return activeChild.growths[i];
		else {
			return activeStatic.baseGrowths[i];
		}
	}
}
