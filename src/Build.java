import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.*;
import static javax.swing.GroupLayout.Alignment.BASELINE;

//@SuppressWarnings("unused")

public class Build extends JFrame implements ItemListener {
	private JPanel header = new JPanel();
	private JRadioButton childRB, staticRB;
	private ButtonGroup childsubGroup;

	private JComboBox<String> childDropdown;
	private JComboBox<String> staticDropdown;
	
	JLabel portrait = new JLabel(new ImageIcon("images/Other/--.gif"));
	
	ImageIcon swdIcon = new ImageIcon("images/WeaponTypes/sword.gif");
	ImageIcon lncIcon = new ImageIcon("images/WeaponTypes/lance.gif");
	ImageIcon axeIcon = new ImageIcon("images/WeaponTypes/axe.gif");
	ImageIcon bowIcon = new ImageIcon("images/WeaponTypes/bow.gif");
	ImageIcon stfIcon = new ImageIcon("images/WeaponTypes/staff.gif");
	ImageIcon fireIcon = new ImageIcon("images/WeaponTypes/fire.gif");
	ImageIcon thunderIcon = new ImageIcon("images/WeaponTypes/thunder.gif");
	ImageIcon windIcon = new ImageIcon("images/WeaponTypes/wind.gif");
	ImageIcon lightIcon = new ImageIcon("images/WeaponTypes/light.gif");
	ImageIcon darkIcon = new ImageIcon("images/WeaponTypes/dark.gif");
	
	JLabel swdR = new JLabel(""), lncR = new JLabel(""), axeR = new JLabel(""), bowR = new JLabel(""),
			stfR = new JLabel(""), frR = new JLabel(""), thnR = new JLabel(""), wndR = new JLabel(""),
			lgtR = new JLabel(""), drkR = new JLabel("");
	
	JPanel sharedRankBox = new JPanel();
	JLabel swdSR = new JLabel(""), lncSR = new JLabel(""), axeSR = new JLabel(""), bowSR = new JLabel(""),
			stfSR = new JLabel(""), frSR = new JLabel(""), thnSR = new JLabel(""), wndSR = new JLabel(""),
			lgtSR = new JLabel(""), drkSR = new JLabel("");
	
	
	private static String[] kidNames = new String[] { "--", "Seliph", "Leif", "Altena", "Ulster", "Faval", "Corpul", "Sety", "Delmudd",
			"Lester", "Arthur", "Patty", "Larcei", "Lana", "Fee", "Tinny", "Leen", "Nanna" };
	private static String[] staticNames = new String[] { "--", "Shannan", "Roddlevan", "Asaello", "Johan", "Sharlow", "Hawk",
			"Tristan", "Finn", "Dimna", "Hannibal", "Ares", "Amid", "Oifey", "Daisy", "Radney", "Mana", "Julia" };
	private static String[] fatherNames = new String[] {"Sigurd", "Noish", "Alec", "Arden", "Finn", "Quan", "Midayle", "Lewyn", "Holyn", "Azel", "Jamke", "Claude", "Beowulf", "Lex", "Dew"};
	
	private JPanel childBox = new JPanel();

	private JPanel staticBox = new JPanel();
		public static JLabel staticExtra = new JLabel("");


		private JLabel motherN = new JLabel("Mother");
			public static JLabel mother = new JLabel("None");
		private JLabel siblingN = new JLabel("Sibling");
			public static JLabel sibling = new JLabel("None");	
		private JLabel fatherN = new JLabel("Father");
			public static JComboBox<String> father = new JComboBox<String>(fatherNames);
			
	private JPanel growthsSet = new JPanel();
	private JLabel HPG = new JLabel("0"), StrG = new JLabel("0"), MagG = new JLabel("0"),
			SklG = new JLabel("0"), SpdG = new JLabel("0"), LckG = new JLabel("0"), 
			DefG = new JLabel("0"), ResG = new JLabel("0");

	 JTextArea area = new JTextArea();
     JScrollPane spane = new JScrollPane(area);
     
     JPanel rankBox = new JPanel();
     JLabel classlbl, inv = new JLabel("Inventory"), skills, wpnR, shR, classIc, className, skillsLbl;
     JLabel wpn1 = new JLabel(""), wpn2 = new JLabel("");
     JPanel inventoryBox = new JPanel();
     JPanel skillsBox = new JPanel();

     JPanel classBox = new JPanel();
     
     JLabel hbLbl, hb;
     JPanel holyBlood = new JPanel();
     
     ArrayList <JLabel> wpnLabels = new ArrayList <JLabel> (Arrays.asList(swdR, lncR, axeR, bowR, stfR, frR, thnR, wndR, lgtR, drkR));
		ArrayList <JLabel> wpnSLabels = new ArrayList <JLabel> (Arrays.asList(swdSR, lncSR, axeSR, bowSR, stfSR, frSR, thnSR, wndSR, lgtSR, drkSR));
		ArrayList <ImageIcon> wpnIcons = new ArrayList <ImageIcon> (Arrays.asList(swdIcon, lncIcon, axeIcon, bowIcon, stfIcon, fireIcon, thunderIcon, windIcon, lightIcon, darkIcon));
	public Build() {

		setTitle("Second Generation");
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		ImageIcon icon = new ImageIcon("images/Other/icon.png");
		setIconImage(icon.getImage());

		initUI();
	}

	private void initUI() {

		childsubGroup = new ButtonGroup();
		childRB = new JRadioButton("Child", true);
		staticRB = new JRadioButton("Static");
		childsubGroup.add(childRB);
		childsubGroup.add(staticRB);

		childDropdown = new JComboBox<String>(kidNames);
		childDropdown.addItemListener(this);
		staticDropdown = new JComboBox<String>(staticNames);
		staticDropdown.addItemListener(this);
		
		father.addItemListener(this);

		childBox.add(motherN);
		childBox.add(mother);
		childBox.add(siblingN);
		childBox.add(sibling);
		childBox.add(fatherN);
		childBox.add(father);

		staticBox.add(staticExtra);
		
		
		header.add(childRB);
		header.add(childDropdown);
		header.add(staticRB);
		header.add(staticDropdown);

			JLabel hp = new JLabel("HP");
			JLabel str = new JLabel("Str");
			JLabel mag = new JLabel("Mag");
			JLabel skl = new JLabel("Skl");
			JLabel spd = new JLabel("Spd");
			JLabel lck = new JLabel("Lck");
			JLabel def = new JLabel("Def");
			JLabel res = new JLabel("Res");

			createGLayout(hp, HPG, str, StrG, mag, MagG, skl, SklG, spd, SpdG, lck, LckG, def, DefG, res, ResG);
			
			classlbl = new JLabel("Class ");
			inv = new JLabel("Inventory");
			skills = new JLabel("");
			skillsLbl = new JLabel("Skills: ");
			wpnR = new JLabel("Weapon ranks: ");
			shR = new JLabel("Shared ranks: ");
			classIc = new JLabel(new ImageIcon("images/Other/default.gif"));
			className = new JLabel("");
			hbLbl = new JLabel("Holy blood: ");
			hb = new JLabel("");
			
			createCLayout(classlbl, classIc, className);
			//classBox.add(classlbl);
			//classBox.add(classIc);
			
			skillsBox.add(skillsLbl);
			skillsBox.add(skills);
			
			rankBox.add(wpnR);
			rankBox.add(swdR);
			rankBox.add(lncR);
			rankBox.add(axeR);
			rankBox.add(bowR);
			rankBox.add(stfR);
			rankBox.add(frR);
			rankBox.add(thnR);
			rankBox.add(wndR);
			rankBox.add(lgtR);
			rankBox.add(drkR);
			
			sharedRankBox.add(shR);
			sharedRankBox.add(swdSR);
			sharedRankBox.add(lncSR);
			sharedRankBox.add(axeSR);
			sharedRankBox.add(bowSR);
			sharedRankBox.add(stfSR);
			sharedRankBox.add(frSR);
			sharedRankBox.add(thnSR);
			sharedRankBox.add(wndSR);
			sharedRankBox.add(lgtSR);
			sharedRankBox.add(drkSR);
			
			createInvLayout(inv, wpn1, wpn2);
			
			Font f = motherN.getFont();
			mother.setFont(f.deriveFont(f.getStyle() & ~Font.BOLD));
			sibling.setFont(f.deriveFont(f.getStyle() & ~Font.BOLD));
			wpn1.setFont(f.deriveFont(f.getStyle() & ~Font.BOLD));
			wpn2.setFont(f.deriveFont(f.getStyle() & ~Font.BOLD));
			skills.setFont(f.deriveFont(f.getStyle() & ~Font.BOLD));
			className.setFont(f.deriveFont(f.getStyle() & ~Font.BOLD));
			HPG.setFont(f.deriveFont(f.getStyle() & ~Font.BOLD));
			StrG.setFont(f.deriveFont(f.getStyle() & ~Font.BOLD));
			MagG.setFont(f.deriveFont(f.getStyle() & ~Font.BOLD));
			SklG.setFont(f.deriveFont(f.getStyle() & ~Font.BOLD));
			SpdG.setFont(f.deriveFont(f.getStyle() & ~Font.BOLD));
			LckG.setFont(f.deriveFont(f.getStyle() & ~Font.BOLD));
			DefG.setFont(f.deriveFont(f.getStyle() & ~Font.BOLD));
			ResG.setFont(f.deriveFont(f.getStyle() & ~Font.BOLD));
			hb.setFont(f.deriveFont(f.getStyle() & ~Font.BOLD));

			holyBlood.add(hbLbl);
			holyBlood.add(hb);
			
		createLayout(header, childBox, staticBox, portrait, inventoryBox, classBox, growthsSet, skillsBox, holyBlood, rankBox, sharedRankBox);

	}
	
	private void createInvLayout(JComponent...arg) {
		GroupLayout invlayout = new GroupLayout(inventoryBox);
		inventoryBox.setLayout(invlayout);
		
		invlayout.setAutoCreateContainerGaps(true);
		invlayout.setAutoCreateGaps(true);

		invlayout.setHorizontalGroup(invlayout.createParallelGroup()
				.addComponent(arg[0])
				.addComponent(arg[1])
				.addComponent(arg[2])
		);
		
		invlayout.setVerticalGroup(invlayout.createSequentialGroup()
				.addComponent(arg[0])
				.addComponent(arg[1])
				.addComponent(arg[2])
			);
				
	}
	
	private void createCLayout(JComponent...arg) {
		GroupLayout clayout = new GroupLayout(classBox);
		classBox.setLayout(clayout);
		
		clayout.setAutoCreateContainerGaps(true);
		clayout.setAutoCreateGaps(true);

		clayout.setHorizontalGroup(clayout.createParallelGroup()
				.addGroup(clayout.createSequentialGroup()
					.addComponent(arg[0])
					.addComponent(arg[1])
				)
				.addComponent(arg[2])
		);
		
		clayout.setVerticalGroup(clayout.createSequentialGroup()
				.addGroup(clayout.createParallelGroup()
						.addComponent(arg[0])
						.addComponent(arg[1])
				)
				.addComponent(arg[2])
			);
				
	}
	
	private void createGLayout(JComponent...arg) {
		GroupLayout glayout = new GroupLayout(growthsSet);
		growthsSet.setLayout(glayout);
		
		glayout.setAutoCreateContainerGaps(true);
		glayout.setAutoCreateGaps(true);

		glayout.setHorizontalGroup(glayout.createSequentialGroup()
				.addGroup(glayout.createParallelGroup()
						.addComponent(arg[0])
						.addComponent(arg[1]))
				.addGroup(glayout.createParallelGroup()
						.addComponent(arg[2])
						.addComponent(arg[3]))
				.addGroup(glayout.createParallelGroup()
						.addComponent(arg[4])
						.addComponent(arg[5]))
				.addGroup(glayout.createParallelGroup()
						.addComponent(arg[6])
						.addComponent(arg[7]))
				.addGroup(glayout.createParallelGroup()
						.addComponent(arg[8])
						.addComponent(arg[9]))
				.addGroup(glayout.createParallelGroup()
						.addComponent(arg[10])
						.addComponent(arg[11]))
				.addGroup(glayout.createParallelGroup()
						.addComponent(arg[12])
						.addComponent(arg[13]))
				.addGroup(glayout.createParallelGroup()
						.addComponent(arg[14])
						.addComponent(arg[15]))
				);

		glayout.setVerticalGroup(glayout.createParallelGroup()
				.addGroup(glayout.createSequentialGroup()
						.addComponent(arg[0])
						.addComponent(arg[1]))
				.addGroup(glayout.createSequentialGroup()
						.addComponent(arg[2])
						.addComponent(arg[3]))
				.addGroup(glayout.createSequentialGroup()
						.addComponent(arg[4])
						.addComponent(arg[5]))
				.addGroup(glayout.createSequentialGroup()
						.addComponent(arg[6])
						.addComponent(arg[7]))
				.addGroup(glayout.createSequentialGroup()
						.addComponent(arg[8])
						.addComponent(arg[9]))
				.addGroup(glayout.createSequentialGroup()
						.addComponent(arg[10])
						.addComponent(arg[11]))
				.addGroup(glayout.createSequentialGroup()
						.addComponent(arg[12])
						.addComponent(arg[13]))
				.addGroup(glayout.createSequentialGroup()
						.addComponent(arg[14])
						.addComponent(arg[15]))
				);

		pack();
	}

	private void createLayout(JComponent... arg) {

		Container pane = getContentPane();
		GroupLayout gl = new GroupLayout(pane);
		pane.setLayout(gl);

		gl.setAutoCreateContainerGaps(true);
		gl.setAutoCreateGaps(true);

		gl.setHorizontalGroup(gl.createParallelGroup()
						.addComponent(arg[0])
						.addComponent(arg[1])
						.addComponent(arg[2])
						.addGroup(gl.createSequentialGroup()
								.addGroup(gl.createParallelGroup()
										.addComponent(arg[3])
										.addComponent(arg[4])
								)
								.addGroup(gl.createParallelGroup()
										.addGroup(gl.createSequentialGroup()
												.addComponent(arg[5])
												.addComponent(arg[6])
										)
										.addComponent(arg[7])
										.addComponent(arg[8])
										.addComponent(arg[9])
										.addComponent(arg[10])
								)
						)
				);

		gl.setVerticalGroup(gl.createSequentialGroup()
						.addComponent(arg[0])
						.addComponent(arg[1])
						.addComponent(arg[2])
						.addGroup(gl.createParallelGroup()
								.addGroup(gl.createSequentialGroup()
										.addComponent(arg[3])
										.addComponent(arg[4])
								)
								.addGroup(gl.createSequentialGroup()
										
										.addGroup(gl.createParallelGroup()
												.addComponent(arg[5])
												.addComponent(arg[6])
										)
										.addComponent(arg[7])
										.addComponent(arg[8])
										.addComponent(arg[9])
										.addComponent(arg[10])
								)
								
						)
				);

		pack();
	}

	public void itemStateChanged(ItemEvent e) {
		if (e.getSource().equals(childDropdown)) {
			motherN.setText("Mother");
			staticExtra.setText("");
			staticDropdown.setSelectedItem("--");
			childRB.setSelected(true);
			staticRB.setSelected(false);
			father.setEnabled(true);
			
			setChild();
			updateGrowths();
			//changeRanks(false);
			setSharedRanks(true);
			setInventory(true);
			setHolyBlood(true);
			setWpnRanks(true);
			setSharedRanks(true);
			portrait.setIcon(new ImageIcon("images/Children/" + GenDriver.activeChild.name + ".gif"));
			classIc.setIcon(new ImageIcon("images/Classes/" + GenDriver.activeChild.getUnitClass() + ".gif"));
			className.setText(GenDriver.activeChild.getUnitClass());
		}
		if (e.getSource().equals(staticDropdown)) {
			motherN.setText("Replaced mother");
			childDropdown.setSelectedItem("--");
			childRB.setSelected(false);
			staticRB.setSelected(true);
			father.setEnabled(false);
			setStatic();
			
			setInventory(false);
			setWpnRanks(false);
			setSharedRanks(false);
			setHolyBlood(false);
			updateGrowths();
			portrait.setIcon(new ImageIcon("images/StaticCharacters/" + GenDriver.activeStatic.name + ".gif"));
			classIc.setIcon(new ImageIcon("images/Classes/" + GenDriver.activeStatic.getUnitClass() + ".gif"));
			className.setText(GenDriver.activeStatic.getUnitClass());
			area.setText(GenDriver.activeStatic.toString());
			//changeRanks(true);
			
		}
		if (e.getSource().equals(father)) {
			GenDriver.activeChild.setOtherParent(GenDriver.findParent((String)father.getSelectedItem()));
			area.setText(GenDriver.activeChild.toString());
			updateGrowths();
			//changeRanks(false);
			setSharedRanks(true);
			setWpnRanks(true);
			setInventory(true);
			setHolyBlood(true);
		}
	}
	
	char[] tsRanks = {'-', 'C', 'B', 'A', '*'};
	
	public void setSharedRanks(boolean child) {		
		if (child) {
			String parentName = GenDriver.activeChild.getEqHBParent();
			int[] theirRanks = GenDriver.activeChild.getSharedRanks();
			if (parentName != null) {
				shR.setText("Ranks shared with " + parentName + ": ");
				for (int i=0; i<10; i++) {
					if (theirRanks[i] > 0) {
						wpnSLabels.get(i).setText(""+tsRanks[theirRanks[i]]);
						wpnSLabels.get(i).setIcon(wpnIcons.get(i));
					}
					else {
						wpnSLabels.get(i).setText("");
						wpnSLabels.get(i).setIcon(new ImageIcon());
					}
				}
			}
		}
		else {
			shR.setText("");
			for (int i=0; i<10; i++) {
				wpnSLabels.get(i).setText("");
				wpnSLabels.get(i).setIcon(new ImageIcon());
			}
		}
	}
	
	public void setWpnRanks(boolean child) {
		int[] theirRanks;
		if (child)
			theirRanks = GenDriver.activeChild.getWpnRanks();
		else {
			theirRanks = GenDriver.activeStatic.getWpnRanks();
		}
		if (!child) {
			for (int i=0; i<10; i++) {
				if (theirRanks[i] > 0) {
					wpnLabels.get(i).setText(""+tsRanks[theirRanks[i]]);
					wpnLabels.get(i).setIcon(wpnIcons.get(i));
				}
				else {
					wpnLabels.get(i).setText("");
					wpnLabels.get(i).setIcon(new ImageIcon());
				}
			}
		}
		if (child && GenDriver.activeChild.getOtherParent() != null) {
			for (int i=0; i<10; i++) {
				if (theirRanks[i] > 0) {
					wpnLabels.get(i).setText(""+tsRanks[theirRanks[i]]);
					wpnLabels.get(i).setIcon(wpnIcons.get(i));
				}
				else {
					wpnLabels.get(i).setText("");
					wpnLabels.get(i).setIcon(new ImageIcon());
				}
			}
		}
	}
	
	public void setInventory(boolean child) {
		ArrayList <Weapon> inventory;
		if (child) {
			inventory = GenDriver.activeChild.getInventory();
		}
		else { //static char
			inventory = GenDriver.activeStatic.getInventory();
		}
		switch (inventory.size()) {
			case 1:
				wpn1.setText(inventory.get(0).getName());
				wpn2.setText("");
				break;
			case 2:
				wpn1.setText(inventory.get(0).getName());
				wpn2.setText(inventory.get(1).getName());
				break;
			default:
				wpn1.setText("");
				wpn2.setText("");
				break;
		}
	}
	
/*	
	private int changeRanks(boolean staticChar, int i) {
		if (i == 9)
			return 0;
		else {
			if (staticChar)
				ranks = GenDriver.activeStatic.getWpnRnks();
			else
				ranks = GenDriver.activeChild.getWpnRnks();
			int pos;
			
			
			switch (i) {
				case 1: 
					pos = ranks.indexOf("Swd-");
				default:
					pos = -1;
			}
			
		
			if (pos != -1) {
				wpnLabels.get(i).setText(""+ranks.charAt(pos+4));
				wpnLabels.get(i).setIcon(swdIcon);
				System.out.println("x " + ranks.charAt(pos+4));
				int key = ranks.indexOf("[");
				if (pos + 5 == key) {
					swdSR.setText(""+ranks.charAt(key+1));
					swdSR.setIcon(swdIcon);
				}
				else {
					swdSR.setText("");
					swdSR.setIcon(new ImageIcon());
				}
				key = ranks.indexOf("(");
				if (pos + 5 == key || pos + 8 == key) {
					wpnLabels.get(i).setText(wpnLabels.get(i).getText() + "(" + ranks.charAt(key+1) + ")");
				}
			}
			else {
				wpnLabels.get(i).setText("");
				wpnLabels.get(i).setIcon(new ImageIcon());
				swdSR.setText("");
				swdSR.setIcon(new ImageIcon());
			}
			
		}
		return changeRanks(staticChar, i);
	}
	
	private void changeRanks(boolean staticChar) {
		String ranks;
		if (staticChar) {
			ranks = GenDriver.activeStatic.getWpnRnks();
		}
		else {
			ranks = GenDriver.activeChild.getWpnRnks();
		}
			int pos = ranks.indexOf("Swd-");
			if (pos != -1) {
				swdR.setText(""+ranks.charAt(pos+4));
				swdR.setIcon(swdIcon);
				System.out.println("x " + ranks.charAt(pos+4));
				int key = ranks.indexOf("[");
				if (pos + 5 == key) {
					swdSR.setText(""+ranks.charAt(key+1));
					swdSR.setIcon(swdIcon);
				}
				else {
					swdSR.setText("");
					swdSR.setIcon(new ImageIcon());
				}
				key = ranks.indexOf("(");
				if (pos + 5 == key || pos + 8 == key) {
					swdR.setText(swdR.getText() + "(" + ranks.charAt(key+1) + ")");
				}
			}
			else {
				swdR.setText("");
				swdR.setIcon(new ImageIcon());
				swdSR.setText("");
				swdSR.setIcon(new ImageIcon());
			}
			pos = ranks.indexOf("Lnc-");
			if (pos != -1) {
				lncR.setText(""+ranks.charAt(pos+4));
				lncR.setIcon(lncIcon);
				System.out.println("x " + ranks.charAt(pos+4));
				int key = ranks.indexOf("(");
				if (pos + 5 == key) {
					lncR.setText(lncR.getText() + "(" + ranks.charAt(key+1) + ")");
				}
			}
			else {
				lncR.setText("");
				lncR.setIcon(new ImageIcon());
			}
			pos = ranks.indexOf("Axe-");
			if (pos != -1) {
				axeR.setText(""+ranks.charAt(pos+4));
				axeR.setIcon(axeIcon);
				System.out.println("x " + ranks.charAt(pos+4));
				int key = ranks.indexOf("(");
				if (pos + 5 == key) {
					axeR.setText(axeR.getText() + "(" + ranks.charAt(key+1) + ")");
				}
			}
			else {
				axeR.setText("");
				axeR.setIcon(new ImageIcon());
			}
			pos = ranks.indexOf("Bow-");
			if (pos != -1) {
				bowR.setText(""+ranks.charAt(pos+4));
				bowR.setIcon(bowIcon);
				System.out.println("x " + ranks.charAt(pos+4));
				int key = ranks.indexOf("(");
				if (pos + 5 == key) {
					bowR.setText(bowR.getText() + "(" + ranks.charAt(key+1) + ")");
				}
			}
			else {
				bowR.setText("");
				bowR.setIcon(new ImageIcon());
			}
			pos = ranks.indexOf("Stf-");
			if (pos != -1) {
				stfR.setText(""+ranks.charAt(pos+4));
				stfR.setIcon(stfIcon);
				System.out.println("x " + ranks.charAt(pos+4));
				int key = ranks.indexOf("(");
				if (pos + 5 == key) {
					stfR.setText(stfR.getText() + "(" + ranks.charAt(key+1) + ")");
				}
			}
			else {
				stfR.setText("");
				stfR.setIcon(new ImageIcon());
			}
			pos = ranks.indexOf("Fr-");
			if (pos != -1) {
				frR.setText(""+ranks.charAt(pos+3));
				frR.setIcon(fireIcon);
				System.out.println("x " + ranks.charAt(pos+4));
				int key = ranks.indexOf("(");
				if (pos + 4 == key) {
					frR.setText(frR.getText() + "(" + ranks.charAt(key+1) + ")");
				}
			}
			else {
				frR.setText("");
				frR.setIcon(new ImageIcon());
			}
			pos = ranks.indexOf("Thn-");
			if (pos != -1) {
				thnR.setText(""+ranks.charAt(pos+4));
				thnR.setIcon(thunderIcon);
				System.out.println("x " + ranks.charAt(pos+4));
				int key = ranks.indexOf("(");
				if (pos + 5 == key) {
					thnR.setText(thnR.getText() + "(" + ranks.charAt(key+1) + ")");
				}
			}
			else {
				thnR.setText("");
				thnR.setIcon(new ImageIcon());
			}
			pos = ranks.indexOf("Wnd-");
			if (pos != -1) {
				wndR.setText(""+ranks.charAt(pos+4));
				wndR.setIcon(windIcon);
				System.out.println("x " + ranks.charAt(pos+4));
				int key = ranks.indexOf("(");
				if (pos + 5 == key) {
					wndR.setText(wndR.getText() + "(" + ranks.charAt(key+1) + ")");
				}
			}
			else {
				wndR.setText("");
				wndR.setIcon(new ImageIcon());
			}
			pos = ranks.indexOf("Lgt-");
			if (pos != -1) {
				lgtR.setText(""+ranks.charAt(pos+4));
				lgtR.setIcon(lightIcon);
				System.out.println("x " + ranks.charAt(pos+4));
				int key = ranks.indexOf("(");
				if (pos + 5 == key) {
					lgtR.setText(lgtR.getText() + "(" + ranks.charAt(key+1) + ")");
				}
			}
			else {
				lgtR.setText("");
				lgtR.setIcon(new ImageIcon());
			}
			pos = ranks.indexOf("Drk-");
			if (pos != -1) {
				drkR.setText(""+ranks.charAt(pos+4));
				drkR.setIcon(darkIcon);
				System.out.println("x " + ranks.charAt(pos+4));
				int key = ranks.indexOf("(");
				if (pos + 5 == key) {
					drkR.setText(drkR.getText() + "(" + ranks.charAt(key+1) + ")");
				}
			}
			else {
				drkR.setText("");
				drkR.setIcon(new ImageIcon());
			}
	}
	*/
	
	private void updateGrowths() {
		HPG.setText(""+GenDriver.getG(0)+"%");
		StrG.setText(""+GenDriver.getG(1)+"%");
		MagG.setText(""+GenDriver.getG(2)+"%");
		SklG.setText(""+GenDriver.getG(3)+"%");
		SpdG.setText(""+GenDriver.getG(4)+"%");
		LckG.setText(""+GenDriver.getG(5)+"%");
		DefG.setText(""+GenDriver.getG(6)+"%");
		ResG.setText(""+GenDriver.getG(7)+"%");
	}
	
	
	private void setChild() {
		GenDriver.setActiveChild((String)childDropdown.getSelectedItem());
		GenDriver.activeChild.setOtherParent(GenDriver.findParent((String)father.getSelectedItem()));
		skills.setText(GenDriver.activeChild.getSkills());
	}
	
	private void setStatic() {
		GenDriver.setActiveStatic((String)staticDropdown.getSelectedItem());
		skills.setText(GenDriver.activeStatic.getSkills());
		for (JLabel x : wpnSLabels) {
			x.setText("");
			x.setIcon(new ImageIcon());
		}
	}
	
	private void setHolyBlood(boolean child) {
		String x;
		if (child)
			x = GenDriver.activeChild.getHolyBlood();
		else
			x = GenDriver.activeStatic.getHolyBlood();
		hb.setText(x);
	}

}
