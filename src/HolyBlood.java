public class HolyBlood {
	
	private int crusader;
		//0Baldur, 1Hezul, 2Odo, 3Nova, 4Dain, 5Neir, 6Ulir
		//7Blaggi, 8Fala, 9Tordo, 10Forseti, 11Naga, 12Loptyr
	
	private int wep;
		//0sword, 1lance, 2axe, 3bow, 4staff, 5fire, 6thunder, 7wind, 8light, 9dark
	
	private boolean major;
	
	private int[] minorGrowths = new int[8];
	private int[] majorGrowths = new int[8];
	
	public HolyBlood(int crusaderNo, int wepType, int[] theGrowths, boolean isMajor) {
		crusader = crusaderNo;
		wep = wepType;
		major = isMajor;
		
		if (major) {
			for (int i=0; i<8; i++) {
				majorGrowths[i] = theGrowths[i];
				minorGrowths[i] = theGrowths[i]/2;
			}
		}
		else {
			for (int i=0; i<8; i++) {
				majorGrowths[i] = theGrowths[i] * 2;
				minorGrowths[i] = theGrowths[i];
			}
		}
			
	}

	public int getCrusader() {
		return crusader;
	}
	
	public int getWepType() {
		return wep;
	}
	
	public int[] getGrowths() {
			if (major)
				return majorGrowths;
			else
				return minorGrowths;
	}
	
	public int[] getCertainGrowths(boolean isMajor) {
		if (isMajor)
			return majorGrowths;
		else
			return minorGrowths;
	}
	
	public boolean isMajor() {
		return major;
	}
	
	public boolean typeMatch (HolyBlood h) {
		if (this.getCrusader() == h.getCrusader())
			return true;
		else
			return false;
	}
	
	public boolean equals(HolyBlood h) {
		if (typeMatch(h) && (this.isMajor() == h.isMajor()) )
			return true;
		else
			return false;
	}
}