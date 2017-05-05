import java.util.ArrayList;
import java.util.Arrays;

public class Program {/**
	public static HolyBlood[][] allHolyBlood = new HolyBlood [13][2];
	
	public static void main (String[] args) {
		initialiseHB();
		//0Baldur, 1Odo, 2Hezul, 3Nova, 4Dain, 5Neir, 6Ulir
		//7Blaggi, 8Fala, 9Tordo, 10Forseti, 11Naga, 12Loptyr
		UnitClass troub = new UnitClass(
				"Troubadour", 
				"Paladin (F)", 
				new int[] { 2, 0, 0, 0, 1, 0, 0, 0, 0, 0 },
				new int[] { 2, 1, 0, 0, 1, 0, 0, 0, 0, 0 }, 
				new ArrayList<Integer>(Arrays.asList(22)),
				new ArrayList<Integer>(Arrays.asList(22)));
			
		Unit Ethlyn = new Unit ("Ethlyn", new int[] {40,20,5,20,30,10,20,10}, 
				new ArrayList <Integer> (Arrays.asList(1)), 
				new ArrayList <HolyBlood> (Arrays.asList(allHolyBlood[0][0])), 
				troub, false); //0sword, 1lance, 2axe, 3bow, 4staff, 5fire, 6thunder, 7wind, 8light, 9dark
		Unit Quan = new Unit ("Quan", new int[] {70,30,5,30,20,10,30,5}, 
				new ArrayList <Integer> (Arrays.asList(5)),
				new ArrayList <HolyBlood> (Arrays.asList(allHolyBlood[3][1])),
				new int[] {0,4,0,0,0,0,0,0,0,0});
		
		Child Leif = new Child ("Leif", Ethlyn, true);
		Child Altena = new Child ("Altena", Quan, true);
			//0sword, 1lance, 2axe, 3bow, 4staff, 5fire, 6thunder, 7wind, 8light, 9dark
		UnitClass prince = new UnitClass("Prince", "Master Knight", new int[] { 3, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
				new int[] { 3, 3, 3, 3, 3, 3, 3, 3, 1, 0 }, new ArrayList<Integer>(Arrays.asList(22)),
				new ArrayList<Integer>(Arrays.asList(0)));
		UnitClass dragonKnight = new UnitClass("Dragon Knight", "Dragon Master", new int[] { 2, 2, 0, 0, 0, 0, 0, 0, 0, 0 },
				new int[] { 3, 3, 0, 0, 0, 0, 0, 0, 0, 0 }, new ArrayList<Integer>(Arrays.asList(22)),
				new ArrayList<Integer>(Arrays.asList(0)));
		Leif.setClass(prince, false);
		Altena.setClass(dragonKnight, false);

		Leif.setOtherParent(Quan);
		Altena.setOtherParent(Ethlyn);

		System.out.println(Leif.toString());
		System.out.println(Altena.toString());
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
	}*/
}