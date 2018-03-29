import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.*;


public class GUI extends JFrame implements ActionListener 
{
	User currentUser;
	/*
	All attributes regarding the current user.
	*/

	String permissions;
	
	// 'myTabs' are where all the tabs are added.
	private JTabbedPane myTabs = new JTabbedPane();

	// All the different panels for the system
	private JPanel loginTab = new JPanel(null);
	private JPanel fightsTab = new JPanel(null);
	private JPanel homePageTab = new JPanel(null);
	private JPanel rankingTableTab = new JPanel(null);
	private JPanel managingAccountsTab = new JPanel(null);
	private JPanel targets_adminTab = new JPanel(null);
	private JPanel targets_fencerTab = new JPanel(null);
	private JPanel competition_adminTab = new JPanel(null);
	
	

	/* 
	Below, GUI Components for the 'managing accounts'
	tab are declared.
	*/
	private DefaultTableModel userTableModel;
	private JTable userTable;
	private JScrollPane userTableScroll;
	private JLabel lblManagingAccountsTitle = new JLabel();
	private JButton btPermissions = new JButton();
	private JButton btDeleteManagingAccounts = new JButton();

	/* 
	Below, GUI Components for the 'login'
	tab are declared.
	*/
	private JButton btLogin = new JButton();
	private JTextField tfUsr = new JTextField(6);
	private JPasswordField pfPasswd = new JPasswordField(10);
	private JLabel lblUsr = new JLabel();
	private JLabel lblPasswd = new JLabel();
	private JLabel lblWelcomeMessage = new JLabel();
	private JLabel lblOr = new JLabel();
	private JLabel lblvalidationRules = new JLabel();
	private JButton btCreateAnAccount = new JButton();
	private JPanel createAnAccountTab = new JPanel(null);

	
	
	
	/* 
	Below, GUI Components for the 'create an account'
	tab are declared.
	*/
	private JLabel lblEnterUsr = new JLabel();
	private JLabel lblEnterPasswd = new JLabel();
	private JLabel lblReEnterPasswd = new JLabel();
	private JTextField tfEnterUsr = new JTextField(6);
	private JPasswordField pfEnterPasswd = new JPasswordField(10);
	private JPasswordField pfReEnterPasswd = new JPasswordField(10);
	private JButton btSubmitUserDetails = new JButton();
	private JButton btBack = new JButton();
	private JLabel promptUser = new JLabel();



	/* 
	Below, GUI Components for the 'managing fights'
	tab are declared.
	*/

	private JLabel lblAddFight = new JLabel();
	private JLabel lblDeleteFight = new JLabel();

	private JLabel lblPromptScoreLimit = new JLabel();
	private JLabel lblScoreLimit = new JLabel();	
	private JLabel lblScoreLimitCustom = new JLabel();
	private JLabel lblPromptWinner = new JLabel();
	private JLabel lblPromptLoser = new JLabel();	
	private JLabel lblPromptLoserScore = new JLabel();
	private JLabel lblLoserScoreCustom = new JLabel();

	private JComboBox cbScoreLimit;

	private JComboBox cbWinner;
	private JComboBox cbLoser;
	private JComboBox cbLoserScore;

	private JTextField tfScoreLimitCustom = new JTextField(6);
	private JTextField tfLoserScoreCustom = new JTextField(6);

	private DefaultTableModel fightsTableModel;
	private JTable fightsTable;
	private JScrollPane fightsTableScroll;

	private JButton btSubmit = new JButton();
	private JButton btEdit = new JButton();
	private JButton btDelete = new JButton();

	private String[][] fightRecords;

	/*

	GUI components for pop-up for edit fights.

	*/

	private JTextField tfScoreLimit = new JTextField();
	private JTextField tfWinnerName = new JTextField();
	private JTextField tfLoserName = new JTextField();
	private JTextField tfLoserScore = new JTextField();

	private JLabel lblScoreLimitEdit  = new JLabel();
	private JLabel lblSelectWinnerEdit = new JLabel();
	private JLabel lblSelectLoserEdit = new JLabel();
	private JLabel lblSelectLoserScoreEdit = new JLabel();

	/*
	JComboBox t4_NewFencerGender = new JComboBox(genders);
	JComboBox t4_NewDominantHand = new JComboBox(dominantHand);
	JComboBox t4_NewBirthDay = new JComboBox(days);
	JComboBox t4_NewBirthMonth = new JComboBox(months);
	JComboBox t4_NewBirthYear = new JComboBox(years);
	*/

	/* 
	Below, GUI Components for the 'home page'
	tab are declared.
	*/
	private JLabel lblPageTitle = new JLabel();
	private JLabel lblPtsRequired = new JLabel();
	private JLabel lblCurrentRank = new JLabel();
	private JLabel lblLosses = new JLabel();
	private JLabel lblWins = new JLabel();

	private JButton btLogOff = new JButton();


	/* 
	Below, GUI Components for the 'ranking table'
	tab are declared.
	*/
	private DefaultTableModel rankingTableModel;
	private JTable rankingTable;
	private JScrollPane rankingTableScroll;

	private JLabel lblRankingTableTabDescription;

	/* 
	Below, GUI Components for the 'Target (Admin)'
	tab are declared.
	*/

	private JLabel lblTargetTitle = new JLabel();
	private JLabel lblSelectFencer = new JLabel();

	private JTextField tfTargetTitle = new JTextField();
	private JTextField tfTarget = new JTextField();

	private JButton btLoad = new JButton();
	private JButton btViewTarget = new JButton();
	private JButton btDeleteTarget = new JButton();
	private JButton btSubmitTarget = new JButton();

	private JComboBox cbSelectFencerTargets;

	private DefaultTableModel targetsTableModel;
	private JTable targetsTable;
	private JScrollPane targetsTableScroll;


	/* 
	Below, GUI Components for the 'Target (Fencer)'
	tab are declared.
	*/

	private JButton btViewTarget_User = new JButton();

	private DefaultTableModel targetUser_TableModel;
	private JTable targetUserTable;
	private JScrollPane targetUserTableScroll;

	/* 
	Below, GUI Components for the 'Competition (Admin)'
	tab are declared.
	*/

	private JLabel lblCompetitionTitle_Admin = new JLabel();
	private JLabel lblCompetitionSelectFencer = new JLabel();
	private JLabel lblCompetitionSelectedFencer = new JLabel();

	private JButton btAddSelectedFencer = new JButton();
	private JButton btRemoveSelectedFencer = new JButton();
	private JButton btSubmitCompetition = new JButton();

	private JComboBox cbSelectFencer;

	private DefaultTableModel competitionAdmin_TableModel;
	private JTable competitionAdminTable;
	private JScrollPane competitionAdminTableScroll;



	public void setupCompetitionAdmin(String[][] tableContents)
	{
		// Setup for Competition Title (Admin Tab)
		lblCompetitionTitle_Admin.setLocation(10,10);
		lblCompetitionTitle_Admin.setSize(300,50);
		lblCompetitionTitle_Admin.setOpaque(true);
		lblCompetitionTitle_Admin.setFont(new Font("Courier",Font.PLAIN,35));
		lblCompetitionTitle_Admin.setText("Competition");

		// Setup for sub-title 'Select Fencer' (Admin Tab)
		lblCompetitionSelectFencer.setLocation(10,100);
		lblCompetitionSelectFencer.setSize(300,50);
		lblCompetitionSelectFencer.setOpaque(true);
		lblCompetitionSelectFencer.setFont(new Font("Courier",Font.PLAIN,35));
		lblCompetitionSelectFencer.setText("Select Fencer");

		// Setup for sub-title 'Selected Fencer' (Admin Tab)
		lblCompetitionSelectedFencer.setLocation(400,100);
		lblCompetitionSelectedFencer.setSize(350,50);
		lblCompetitionSelectedFencer.setOpaque(true);
		lblCompetitionSelectedFencer.setFont(new Font("Courier",Font.PLAIN,35));
		lblCompetitionSelectedFencer.setText("Selected Fencers");

		// Setup for add fencer button
		btAddSelectedFencer.setLocation(100,200);
		btAddSelectedFencer.setSize(100,50);
		btAddSelectedFencer.addActionListener(this);
		btAddSelectedFencer.setFont(new Font("Courier",Font.PLAIN,25));
		btAddSelectedFencer.setText("Add");

		// Setup for remove fencer button
		btRemoveSelectedFencer.setLocation(100,300);
		btRemoveSelectedFencer.setSize(150,50);
		btRemoveSelectedFencer.addActionListener(this);
		btRemoveSelectedFencer.setFont(new Font("Courier",Font.PLAIN,25));
		btRemoveSelectedFencer.setText("Remove");

		// Setup for submit competition configuration
		btSubmitCompetition.setLocation(500,500);
		btSubmitCompetition.setSize(200,50);
		btSubmitCompetition.addActionListener(this);
		btSubmitCompetition.setFont(new Font("Courier",Font.PLAIN,35));
		btSubmitCompetition.setText("Submit");

		// Setup for the select fencer combo box
		cbSelectFencer = new JComboBox(new String[] {"-"});
		cbSelectFencer.setBounds(10,200,100,25);

		// Setup for the table for selected fencers.
		String[] headings = {"Selected Fencers"};

		competitionAdmin_TableModel = new DefaultTableModel(tableContents,headings);

		competitionAdminTable = new JTable(competitionAdmin_TableModel);
		competitionAdminTable.setFont(new Font("Courier",Font.PLAIN,30));

		competitionAdminTableScroll = new JScrollPane(competitionAdminTable);
		competitionAdminTableScroll.setSize(300,350);
		competitionAdminTableScroll.setLocation(400,150);

		competition_adminTab.add(competitionAdminTableScroll);
		competition_adminTab.add(cbSelectFencer);
		competition_adminTab.add(btSubmitCompetition);
		competition_adminTab.add(btAddSelectedFencer);
		competition_adminTab.add(btRemoveSelectedFencer);
		competition_adminTab.add(lblCompetitionSelectedFencer);
		competition_adminTab.add(lblCompetitionSelectFencer);
		competition_adminTab.add(lblCompetitionTitle_Admin);

	}

	public void setupTargetsFencer(String[][] tableContents)
	{
		String[] headings = {"Target Title","Set By"};

		targetUser_TableModel = new DefaultTableModel(tableContents,headings);

		targetUserTable = new JTable(targetUser_TableModel);
		targetUserTable.setFont(new Font("Courier",Font.PLAIN,14));

		targetUserTableScroll = new JScrollPane(targetUserTable);
		targetUserTableScroll.setSize(700,300);
		targetUserTableScroll.setLocation(10,100);

		btViewTarget_User.setLocation(500,500);
		btViewTarget_User.setSize(305,50);
		btViewTarget_User.addActionListener(this);
		btViewTarget_User.setFont(new Font("Courier",Font.PLAIN,25));
		btViewTarget_User.setText("View");

		targets_fencerTab.add(btViewTarget_User);
		targets_fencerTab.add(targetUserTableScroll);
	}



	public void setupTargetsAdmin(String[][] tableContents)
	{
		// Setup for title
		lblTargetTitle.setLocation(10,10);
		lblTargetTitle.setSize(375,50);
		lblTargetTitle.setOpaque(true);
		lblTargetTitle.setFont(new Font("Courier",Font.PLAIN,45));
		lblTargetTitle.setText("Targets");

		// Setup for the select fencer
		lblSelectFencer.setLocation(10,90);
		lblSelectFencer.setSize(350,50);
		lblSelectFencer.setOpaque(true);
		lblSelectFencer.setFont(new Font("Courier",Font.PLAIN,35));
		lblSelectFencer.setText("Select Fencer");

		// Setup for the target (text field)

		tfTarget.setFont(new Font("Courier",Font.PLAIN,35));
		tfTarget.setText("Target Content");
		tfTarget.setBounds(400,150,375,375);

		// Setup for the target title (text field)

		tfTargetTitle.setFont(new Font("Courier",Font.PLAIN,35));
		tfTargetTitle.setText("Target Title");
		tfTargetTitle.setBounds(400,50,375,50);

		// Setup for the load button
		btLoad.setLocation(200,150);
		btLoad.setSize(150,50);
		btLoad.addActionListener(this);
		btLoad.setFont(new Font("Courier",Font.PLAIN,25));
		btLoad.setText("Load");

		// Setup for the view button
		btViewTarget.setLocation(10,550);
		btViewTarget.setSize(150,50);
		btViewTarget.addActionListener(this);
		btViewTarget.setFont(new Font("Courier",Font.PLAIN,25));
		btViewTarget.setText("View");

		// Setup for the delete button
		btDeleteTarget.setLocation(200,550);
		btDeleteTarget.setSize(150,50);
		btDeleteTarget.addActionListener(this);
		btDeleteTarget.setFont(new Font("Courier",Font.PLAIN,25));
		btDeleteTarget.setText("Delete");

		// Setup for the submit button
		btSubmitTarget.setLocation(600,550);
		btSubmitTarget.setSize(150,50);
		btSubmitTarget.addActionListener(this);
		btSubmitTarget.setFont(new Font("Courier",Font.PLAIN,25));
		btSubmitTarget.setText("Submit");

		String[] headings = {"Target Title","Set By"};

		targetsTableModel = new DefaultTableModel(tableContents,headings);

		targetsTable = new JTable(targetsTableModel);
		targetsTable.setFont(new Font("Courier",Font.PLAIN,14));

		targetsTableScroll = new JScrollPane(targetsTable);
		targetsTableScroll.setSize(350,300);
		targetsTableScroll.setLocation(10,225);

		cbSelectFencerTargets = new JComboBox(new String[] {"-"});
		cbSelectFencerTargets.setBounds(10,175,100,25);

		targets_adminTab.add(btLoad);
		targets_adminTab.add(btViewTarget);
		targets_adminTab.add(btSubmitTarget);
		targets_adminTab.add(lblSelectFencer);
		targets_adminTab.add(lblTargetTitle);
		targets_adminTab.add(tfTargetTitle);
		targets_adminTab.add(targetsTableScroll);
		targets_adminTab.add(cbSelectFencerTargets);
		targets_adminTab.add(tfTarget);
		targets_adminTab.add(btDeleteTarget);



	}

	public void setUpManagingAccounts(String[][] tableContents)
	{
		
		String[] headings = {"Username","Ranking Points","No. of Games","Permissions"};

		userTableModel = new DefaultTableModel(tableContents,headings);

		userTable = new JTable(userTableModel);
		userTable.setFont(new Font("Courier",Font.PLAIN,14));

		userTableScroll = new JScrollPane(userTable);
		userTableScroll.setSize(700,300);
		userTableScroll.setLocation(10,100);
		
		
		// Setup for the title (Jlabel)
		lblManagingAccountsTitle.setLocation(10,10);
		lblManagingAccountsTitle.setSize(600,50);
		lblManagingAccountsTitle.setOpaque(true);
		lblManagingAccountsTitle.setFont(new Font("Courier",Font.PLAIN,45));
		lblManagingAccountsTitle.setText("Managing Accounts");

		// Setup for the buttons
		btPermissions.setLocation(10,500);
		btPermissions.setSize(305,50);
		btPermissions.addActionListener(this);
		btPermissions.setFont(new Font("Courier",Font.PLAIN,25));
		btPermissions.setText("Change Permissions");

		btDeleteManagingAccounts.setLocation(500,500);
		btDeleteManagingAccounts.setSize(200,50);
		btDeleteManagingAccounts.addActionListener(this);
		btDeleteManagingAccounts.setFont(new Font("Courier",Font.PLAIN,25));
		btDeleteManagingAccounts.setText("Delete");

		managingAccountsTab.add(userTableScroll);
		managingAccountsTab.add(lblManagingAccountsTitle);
		managingAccountsTab.add(btDeleteManagingAccounts);
		managingAccountsTab.add(btPermissions);
		
	}

	public boolean setUpEditPopUp(String[] tempRecordUserClickedOn)
	{
		tfScoreLimitCustom.setBounds(270,115,50,25);	

		lblScoreLimitEdit.setLocation(10,50);
		lblScoreLimitEdit.setSize(400,50);
		lblScoreLimitEdit.setOpaque(true);
		lblScoreLimitEdit.setFont(new Font("Courier",Font.PLAIN,20));
		lblScoreLimitEdit.setText("Enter Score Limit");

		lblSelectWinnerEdit.setLocation(10,50);
		lblSelectWinnerEdit.setSize(400,50);
		lblSelectWinnerEdit.setOpaque(true);
		lblSelectWinnerEdit.setFont(new Font("Courier",Font.PLAIN,20));
		lblSelectWinnerEdit.setText("Enter Winner's Name");

		lblSelectLoserEdit.setLocation(10,50);
		lblSelectLoserEdit.setSize(400,50);
		lblSelectLoserEdit.setOpaque(true);
		lblSelectLoserEdit.setFont(new Font("Courier",Font.PLAIN,20));
		lblSelectLoserEdit.setText("Enter Loser's Name");

		lblSelectLoserScoreEdit.setLocation(10,50);
		lblSelectLoserScoreEdit.setSize(400,50);
		lblSelectLoserScoreEdit.setOpaque(true);
		lblSelectLoserScoreEdit.setFont(new Font("Courier",Font.PLAIN,20));
		lblSelectLoserScoreEdit.setText("Enter Loser's Score");
	
		tfScoreLimit.setText(tempRecordUserClickedOn[0]);
		tfWinnerName.setText(tempRecordUserClickedOn[1]);
		tfLoserName.setText(tempRecordUserClickedOn[2]);
		tfLoserScore.setText(tempRecordUserClickedOn[3]);

		Object[] newUserInformation = {
		    "", lblScoreLimitEdit,
		    "", tfScoreLimit,
		    "", lblSelectWinnerEdit,
		    "", tfWinnerName,
		    "", lblSelectLoserEdit,
		    "", tfLoserName,
		    "", lblSelectLoserScoreEdit,
		    "", tfLoserScore

		};

		int option = JOptionPane.showConfirmDialog(null, newUserInformation, "Edit Fight", JOptionPane.OK_CANCEL_OPTION);

		boolean confirmed = false;	
		
		/*
			If 'cancel' clicked then option=2
			If 'ok' clicked then option=0
			If 'X' clicked then option=-1
		*/
		
		if(option==0)
		{
			confirmed = true;
		}

		// 'confirmed' is true if they want to make those changes

		return confirmed;
	}

	public boolean setUpDeletePopUp()
	{

		promptUser.setLocation(10,50);
		promptUser.setSize(400,50);
		promptUser.setOpaque(true);
		promptUser.setFont(new Font("Courier",Font.PLAIN,20));
		promptUser.setText("Are you sure you want to delete?");

		Object[] newUserInformation = {
		    "", promptUser
		};

		int option = JOptionPane.showConfirmDialog(null, newUserInformation, "Delete Fight", JOptionPane.OK_CANCEL_OPTION);

		boolean isUserSure = false;	
	
		if(option==0)
		{
			isUserSure = true;
		}

		return isUserSure;	
	}	


	public void startGUI()
	{
	/*
		myTabs.addTab("Home Page",homePageTab);
		myTabs.addTab("Managing Fights",fightsTab);
		myTabs.addTab("Ranking Table",rankingTableTab);
		

		this.setLayout(new GridLayout(1,1));
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	
		this.setSize(800,700);
		this.setVisible(true);
		
		this.add(myTabs);

	*/
		setUpLoginTab();
		myTabs.addTab("Login",loginTab);
		
		this.setLayout(new GridLayout(1,1));
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(800,500);
		this.setVisible(true);
		
		this.add(myTabs);
	}

	public void setUpCreateAnAccountTab()
	{

		lblEnterUsr.setFont(new Font("Courier",Font.PLAIN,20));
		lblEnterUsr.setLocation(75,100);
		lblEnterUsr.setSize(100,50);
		lblEnterUsr.setOpaque(true);
		lblEnterUsr.setText("Enter Name:");
		
		lblEnterPasswd.setLocation(75,200);
		lblEnterPasswd.setSize(100,50);
		lblEnterPasswd.setOpaque(true);
		lblEnterPasswd.setFont(new Font("Courier",Font.PLAIN,20));
		lblEnterPasswd.setText("Enter Password:");
		
		lblReEnterPasswd.setLocation(75,300);
		lblReEnterPasswd.setSize(125,50);
		lblReEnterPasswd.setOpaque(true);
		lblEnterUsr.setFont(new Font("Courier",Font.PLAIN,20));
		lblReEnterPasswd.setText("Re-enter Password:");

		lblvalidationRules.setLocation(550,10);
		lblvalidationRules.setSize(400,200);
		lblvalidationRules.setOpaque(true);
		lblvalidationRules.setFont(new Font("Courier",Font.PLAIN,20));
		lblvalidationRules.setText("<html><span>- Username must have a length greater than 2 but less than 21.<br>- Username must contain not symbols.<br>- Passwords must be greater than 9 characters<br>- Password must contain atleast one special character, number and letter.</span></html>");
		
		pfEnterPasswd.addActionListener(this);
		pfEnterPasswd.setLocation(200,200);
		pfEnterPasswd.setSize(200,50);
		
		pfReEnterPasswd.addActionListener(this);
		pfReEnterPasswd.setLocation(200,300);
		pfReEnterPasswd.setSize(200,50);

		tfEnterUsr.addActionListener(this);
		tfEnterUsr.setLocation(200,100);
		tfEnterUsr.setSize(200,50);

		btSubmitUserDetails.setLocation(200,400);
		btSubmitUserDetails.setSize(200,50);
		btSubmitUserDetails.addActionListener(this);
		lblEnterUsr.setFont(new Font("Courier",Font.PLAIN,20));
		btSubmitUserDetails.setText("Submit");
		
		btBack.setLocation(10,400);
		btBack.setSize(200,50);
		btBack.addActionListener(this);
		lblEnterUsr.setFont(new Font("Courier",Font.PLAIN,20));
		btBack.setText("Back");
		
		createAnAccountTab.add(btBack);
		createAnAccountTab.add(tfEnterUsr);
		createAnAccountTab.add(pfEnterPasswd);
		createAnAccountTab.add(pfReEnterPasswd);
		createAnAccountTab.add(lblEnterUsr);
		createAnAccountTab.add(lblEnterPasswd);
		createAnAccountTab.add(lblReEnterPasswd);
		createAnAccountTab.add(btSubmitUserDetails);
		createAnAccountTab.add(lblvalidationRules);
		
	}

	public void setUpLoginTab()
	{
		btLogin.setLocation(110,300);
		btLogin.setSize(200,50);
		btLogin.addActionListener(this);
		btLogin.setText("Login");

		btCreateAnAccount.setLocation(500,200);
		btCreateAnAccount.setSize(200,50);
		btCreateAnAccount.addActionListener(this);
		btCreateAnAccount.setText("Create an Account");
		
		pfPasswd.addActionListener(this);
		pfPasswd.setLocation(110,200);
		pfPasswd.setSize(200,50);
		
		tfUsr.addActionListener(this);
		tfUsr.setLocation(110,100);
		tfUsr.setSize(200,50);
		
		lblUsr.setLocation(10,100);
		lblUsr.setSize(100,50);
		lblUsr.setOpaque(true);
		lblUsr.setText("Enter Username:");
		
		lblPasswd.setLocation(10,200);
		lblPasswd.setSize(100,50);
		lblPasswd.setOpaque(true);
		lblPasswd.setText("Enter Password:");

		lblWelcomeMessage.setLocation(10,10);
		lblWelcomeMessage.setSize(600,50);
		lblWelcomeMessage.setOpaque(true);
		lblWelcomeMessage.setFont(new Font("Courier",Font.PLAIN,30));
		lblWelcomeMessage.setText("Welcome to your Ranking System");

		lblOr.setLocation(400,200);
		lblOr.setSize(100,50);
		lblOr.setOpaque(true);
		lblOr.setFont(new Font("Courier",Font.PLAIN,30));
		lblOr.setText("Or");
		
		loginTab.add(lblPasswd);
		loginTab.add(lblUsr);
		loginTab.add(pfPasswd);
		loginTab.add(tfUsr);
		loginTab.add(btLogin);
		loginTab.add(lblWelcomeMessage);
		loginTab.add(lblOr);
		loginTab.add(btCreateAnAccount);
		
		System.out.println("Login Page has been setup.");
	}

	public void setupRankingTable(String[][] tableOfContents)
	{
		String[] headings = {"Rank","Name of Fencer","No. of Fights","Ranking Points"};

		//String[] rankingDataRecords = HomePage.getRankingData();

		/*int amountOfRecords = rankingDataRecords.length;

		String[][] rankingTableData = new String[0][0];

		if(!rankingDataRecords[0].equals(""))
		{
			rankingTableData = new String[amountOfRecords][4];

			for(int i=0;i<amountOfRecords;i++)
			{
				String[] recordArr = rankingDataRecords[i].split(",");
				rankingTableData[i][0] = recordArr[0];
				rankingTableData[i][1] = recordArr[1];
				rankingTableData[i][2] = recordArr[2];
				rankingTableData[i][3] = recordArr[3];
			}
		}
		*/;

		rankingTableModel = new DefaultTableModel(tableOfContents,headings);

		rankingTable = new JTable(rankingTableModel);
		rankingTable.setFont(new Font("Courier",Font.PLAIN,14));

		rankingTableScroll = new JScrollPane(rankingTable);
		rankingTableScroll.setSize(700,500);
		rankingTableScroll.setLocation(10,10);	

		rankingTableTab.add(rankingTableScroll);
	}

	public void setupHomePage()
	{
		ImageIcon icon = new ImageIcon("settingsIcon.png");
		JButton btSettings = new JButton(icon);

		btSettings.setLocation(375,500);
		btSettings.setSize(200,50);
		btSettings.addActionListener(this);

		lblPageTitle.setLocation(10,0);
		lblPageTitle.setSize(400,50);
		lblPageTitle.setOpaque(true);
		lblPageTitle.setFont(new Font("Courier",Font.PLAIN,35));
		lblPageTitle.setText("You are logged on as <Username>");

		lblCurrentRank.setLocation(10,100);
		lblCurrentRank.setSize(400,50);
		lblCurrentRank.setOpaque(true);
		lblCurrentRank.setFont(new Font("Courier",Font.PLAIN,22));
		lblCurrentRank.setText("Your Current Rank is: <Rank No.>");

		lblPtsRequired.setLocation(10,200);
		lblPtsRequired.setSize(400,50);
		lblPtsRequired.setOpaque(true);
		lblPtsRequired.setFont(new Font("Courier",Font.PLAIN,22));
		lblPtsRequired.setText("Points Required for Rank <Rank No.-1>:<Points>");

		lblWins.setLocation(10,300);
		lblWins.setSize(400,50);
		lblWins.setOpaque(true);
		lblWins.setFont(new Font("Courier",Font.PLAIN,22));
		lblWins.setText("Wins: <No. of Wins>");

		lblLosses.setLocation(10,400);
		lblLosses.setSize(400,50);
		lblLosses.setOpaque(true);
		lblLosses.setFont(new Font("Courier",Font.PLAIN,22));
		lblLosses.setText("Losses: <No. of Losses>");

		btLogOff.setLocation(450,500);
		btLogOff.setSize(200,50);
		btLogOff.addActionListener(this);
		btLogOff.setFont(new Font("Courier",Font.PLAIN,35));
		btLogOff.setText("Logout");


		homePageTab.add(lblPageTitle);
		homePageTab.add(btLogOff);
		homePageTab.add(lblPtsRequired);
		homePageTab.add(lblCurrentRank);
		homePageTab.add(lblLosses);
		homePageTab.add(lblWins);
		homePageTab.add(btSettings);

	}

	public void setUpFightsTab()
	{
		lblAddFight.setLocation(10,0);
		lblAddFight.setSize(300,50);
		lblAddFight.setOpaque(true);
		lblAddFight.setFont(new Font("Courier",Font.PLAIN,30));
		lblAddFight.setText("Add Fight");

		lblDeleteFight.setLocation(400,0);
		lblDeleteFight.setSize(350,50);
		lblDeleteFight.setOpaque(true);
		lblDeleteFight.setFont(new Font("Courier",Font.PLAIN,30));
		lblDeleteFight.setText("Delete/Edit Fight");

		lblPromptScoreLimit.setLocation(10,50);
		lblPromptScoreLimit.setSize(400,50);
		lblPromptScoreLimit.setOpaque(true);
		lblPromptScoreLimit.setFont(new Font("Courier",Font.PLAIN,20));
		lblPromptScoreLimit.setText("What does the score go upto?");

		lblScoreLimit.setLocation(10,100);
		lblScoreLimit.setSize(200,50);
		lblScoreLimit.setOpaque(true);
		lblScoreLimit.setFont(new Font("Courier",Font.PLAIN,15));
		lblScoreLimit.setText("Suggested:");

		lblScoreLimitCustom.setLocation(200,100);
		lblScoreLimitCustom.setSize(200,50);
		lblScoreLimitCustom.setOpaque(true);
		lblScoreLimitCustom.setFont(new Font("Courier",Font.PLAIN,15));
		lblScoreLimitCustom.setText("Custom:");

		lblPromptWinner.setLocation(10,200);
		lblPromptWinner.setSize(200,50);
		lblPromptWinner.setOpaque(true);
		lblPromptWinner.setFont(new Font("Courier",Font.PLAIN,20));
		lblPromptWinner.setText("Select Winner");

		lblPromptLoser.setLocation(10,300);
		lblPromptLoser.setSize(200,50);
		lblPromptLoser.setOpaque(true);
		lblPromptLoser.setFont(new Font("Courier",Font.PLAIN,20));
		lblPromptLoser.setText("Select Loser");	

		lblPromptLoserScore.setLocation(10,400);
		lblPromptLoserScore.setSize(300,50);
		lblPromptLoserScore.setOpaque(true);
		lblPromptLoserScore.setFont(new Font("Courier",Font.PLAIN,20));
		lblPromptLoserScore.setText("Select Loser Score");

		lblLoserScoreCustom.setLocation(200,450);
		lblLoserScoreCustom.setSize(200,25);
		lblLoserScoreCustom.setOpaque(true);
		lblLoserScoreCustom.setFont(new Font("Courier",Font.PLAIN,15));
		lblLoserScoreCustom.setText("Custom:");

		btSubmit.setLocation(100,500);
		btSubmit.setSize(200,50);
		btSubmit.addActionListener(this);
		btSubmit.setFont(new Font("Courier",Font.PLAIN,20));
		btSubmit.setText("Submit");

		btEdit.setLocation(400,500);
		btEdit.setSize(200,50);
		btEdit.addActionListener(this);
		btEdit.setFont(new Font("Courier",Font.PLAIN,20));
		btEdit.setText("Edit");

		btDelete.setLocation(600,500);
		btDelete.setSize(200,50);
		btDelete.addActionListener(this);
		btDelete.setFont(new Font("Courier",Font.PLAIN,20));
		btDelete.setText("Delete");

		tfScoreLimitCustom.setBounds(270,115,50,25);	
		tfLoserScoreCustom.setBounds(270,450,50,25);	

		UserList userLoggedIn = new UserList();
		String[] allUserNames = userLoggedIn.getAllFencerNames();

		String[] DataCBScoreLimit = {
								"-",
								"5",
								"10",
								"15"
							};

		String[] DataCBWinner = allUserNames;

		String[] DataCBLoser = allUserNames;

		String[] DataCBLoserScore = new String[17];
		
		DataCBLoserScore[0] = "-";

		for(int i=1;i<17;i++)
		{
			DataCBLoserScore[i] = Integer.toString(i-1);
		}

		cbScoreLimit = new JComboBox(DataCBScoreLimit);
		cbWinner = new JComboBox(DataCBWinner);
		cbLoser = new JComboBox(DataCBLoser);
		cbLoserScore = new JComboBox(DataCBLoserScore); 

		cbScoreLimit.setBounds(100,115,50,25);
		cbWinner.setBounds(10,250,100,25);
		cbLoser.setBounds(10,350,100,25);
		cbLoserScore.setBounds(10,450,50,25);

		/*
		String[] headings = {"Winner","Winner Points","Loser","Loser Points"};

		boolean areThereFights = false;

		areThereFights = HomePage.doFightsExist();

		if(areThereFights == true)
		{
			fightRecords = HomePage.getFightData();
		}
		else
		{
			fightRecords = new String[0][4];
		}

		fightsTableModel = new DefaultTableModel(fightRecords,headings);

		fightsTable = new JTable(fightsTableModel);

		fightsTableScroll = new JScrollPane(fightsTable);

		fightsTableScroll.setSize(300,400);
		fightsTableScroll.setLocation(400,50);
		*/

		fightsTab.add(btSubmit);
		fightsTab.add(tfLoserScoreCustom);
		fightsTab.add(lblLoserScoreCustom);
		//fightsTab.add(fightsTableScroll);
		fightsTab.add(cbLoserScore);
		fightsTab.add(lblPromptLoserScore);
		fightsTab.add(cbLoser);
		fightsTab.add(lblPromptLoser);
		fightsTab.add(cbWinner);
		fightsTab.add(lblPromptWinner);
		fightsTab.add(tfScoreLimitCustom);
		fightsTab.add(lblScoreLimitCustom);
		fightsTab.add(cbScoreLimit);
		fightsTab.add(lblScoreLimit);
		fightsTab.add(lblPromptScoreLimit);
		fightsTab.add(lblDeleteFight);
		fightsTab.add(lblAddFight);
		fightsTab.add(btDelete);
		fightsTab.add(btEdit);
		
	}

/*
	public void updateFightTable(
		String tempWinner,
		String tempLoser,
		String tempWinnerPts,
		String tempLoserPts
		)
	{
		fightsTableModel.addRow(new Object[] { tempWinner,
									tempWinnerPts,
									tempLoser,
									tempLoserPts });
			
	}

	public void output2DArray(String[][] tempArray)
	{
		String record ="";
		for(int i=0;i<tempArray.length;i++)
		{
			for(int j=0;j<5;j++)
			{
				record=tempArray[i][j]+record;
			}
			System.out.println(record);
		}
	}
*/
	public boolean validateUserDetails(String tempUsername,String tempPassword)
	{
		boolean validationSuccessful = false;
		String specialChars = "/*!@#$%^&*()\"{}_[]|\\?/<>,.''";
		String numbers = "0123456789";
		String alphabet = "abcdefghijklmnopqrstuvwxyx";

		int amountOfSpecialChars = 0;
		int amountOfNumbers = 0;
		int amountOfLetters = 0;

		boolean contentsCheck;

		// Presence Check
		// 'presenceCheck' will turn true if the test succeeds
		boolean presenceCheck = (!tempUsername.equals(""))
		 						&&
		 						(!tempPassword.equals(""));

		// Length Check 
		// Username MIN=2 MAX=20
		// Password MIN=10 MAX=50

		boolean usernameLengthCheck = (tempUsername.length()<21)
									&&
									(tempUsername.length()>1);
		boolean passwordLengthCheck = (tempPassword.length()<51)
									&&
									(tempPassword.length()>9);

		


		// Contents Check
		/*
		No digits or symbols can be in the username.
		Passwords must have atleast one digit, one symbol and one letter.
		*/
		//boolean usernameContentsCheck = (tempUsername.contains("[a-zA-Z]+"))
										
		for(int i=0;i<tempPassword.length();i++)
		{
			if(specialChars.contains(tempPassword.substring(i,1)))
			{
				amountOfSpecialChars++;
			}
			else if(numbers.contains(tempPassword.substring(i,1)))
			{
				amountOfNumbers++;
			}
			else if(alphabet.contains(tempPassword.substring(i,1)))
			{
				amountOfLetters++;
			}
		}

		contentsCheck = (amountOfLetters>1)&&(amountOfNumbers>1)&&(amountOfSpecialChars>1);


		validationSuccessful = presenceCheck
								&&
								usernameLengthCheck
								&&
								passwordLengthCheck
								&&
								contentsCheck;

		return validationSuccessful;

	}


	public void setUpPopUpBox(String message)
	{
		promptUser.setLocation(10,50);
		promptUser.setSize(400,50);
		promptUser.setOpaque(true);
		promptUser.setFont(new Font("Courier",Font.PLAIN,20));
		promptUser.setText(message);
								

		Object[] newUserInformation = {
		    "", promptUser
		};

		//int option = JOptionPane.showConfirmDialog(null, newUserInformation, "User Input Error", JOptionPane.OK_CANCEL_OPTION);
		int option = JOptionPane.showConfirmDialog(null, newUserInformation, "User Input Error", JOptionPane.OK_CANCEL_OPTION);
	}

	public boolean doPasswordsMatch(String password1, String password2)
	{
		boolean match = false;

		if(password1.equals(password2))
		{
			match = true;
		}

		return match;
	}

	public void resetCreateAnAccountFields()
	{
		tfEnterUsr.setText("");
		pfEnterPasswd.setText("");
		pfReEnterPasswd.setText("");	
	}

	public void displaySystem(User user)
	{
		Fight fight = new Fight();

		String[][] tableOfContents = fight.getTableContents();

		permissions = user.getPermission();

	
		if(permissions.equals("Root"))
		{
			myTabs.remove(0);
			
			setUpFightsTab();
			setupHomePage();
			setUpManagingAccounts(tableOfContents);
			setupRankingTable(new String[0][4]);
			setupTargetsAdmin(new String[0][2]);
			setupCompetitionAdmin(new String[0][0]);
			
			myTabs.addTab("Home Page",homePageTab);
			myTabs.addTab("Managing Fights",fightsTab);
			myTabs.addTab("Ranking Table",rankingTableTab);
			myTabs.addTab("Managing Accounts",managingAccountsTab);
			myTabs.addTab("Targets",targets_adminTab);
			myTabs.addTab("Competitions",competition_adminTab);
			

			this.setLayout(new GridLayout(1,1));
			this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			this.setSize(800,700);
			this.setVisible(true);
			this.add(myTabs);
		}
		else if(permissions.equals("Admin"))
		{
			myTabs.remove(0);

			setUpFightsTab();
			setupHomePage();
			setupRankingTable(new String[0][4]);
			setupTargetsAdmin(new String[0][2]);
			setupCompetitionAdmin(new String[0][0]);

			myTabs.addTab("Home Page",homePageTab);
			myTabs.addTab("Managing Fights",fightsTab);
			myTabs.addTab("Ranking Table",rankingTableTab);
			myTabs.addTab("Targets",targets_adminTab);
			myTabs.addTab("Competitions",competition_adminTab);

			this.setLayout(new GridLayout(1,1));
			this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			this.setSize(800,700);
			this.setVisible(true);
			this.add(myTabs);
		}
		else if(permissions.equals("Fencer"))
		{
			myTabs.remove(0);

			setUpFightsTab();
			setupHomePage();
			setupTargetsFencer(new String[0][4]);
			setupRankingTable(new String[0][4]);

			myTabs.addTab("Home Page",homePageTab);
			myTabs.addTab("Managing Fights",fightsTab);
			myTabs.addTab("Ranking Table",rankingTableTab);
			myTabs.addTab("Targets",targets_fencerTab);

			this.setLayout(new GridLayout(1,1));
			this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			this.setSize(800,700);
			this.setVisible(true);
			this.add(myTabs);
		}
		else
		{
			myTabs.remove(0);
			setupHomePage();
			setupRankingTable(new String[0][4]);

			myTabs.addTab("Home Page",homePageTab);
			myTabs.addTab("Ranking Table",rankingTableTab);

			this.setLayout(new GridLayout(1,1));
			this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			this.setSize(800,700);
			this.setVisible(true);
			this.add(myTabs);
		}
		
	}

	public void actionPerformed(ActionEvent e)
 	{
/*
 		String[] recordClickedOn = new String[4];
 		String field;
*/
 		if(e.getSource()==btLogin)
		{
			UserList existingUser = new UserList();
			User user = new User();

			String hashedPassword;
			String username = tfUsr.getText();
			String password = pfPasswd.getText();

			user.setPassword(password);
			user.hashUserPassword();
			hashedPassword = user.getHashedPassword();

			boolean usernameMatch = existingUser.search(username,0);
			boolean passwordMatch = existingUser.search(hashedPassword,1); 

			boolean checkSuccessful = usernameMatch&&passwordMatch;

			if(checkSuccessful)
			{
				user.setUsername(username);
				user.findID();
				user.findRankingPts();
				user.findPermissions();

				displaySystem(user);
				setUpPopUpBox("Login Successful");
				


			}

			else if(checkSuccessful==false)
			{
				System.out.println("Incorrect Details");
				setUpPopUpBox("Incorrect details, please try again.");
			}

			currentUser = user;
		}

		if(e.getSource()==btCreateAnAccount)
		{
			myTabs.remove(0);		
			setUpCreateAnAccountTab();
			this.setSize(500,900);
			myTabs.addTab("Create an Account",createAnAccountTab);

		}

		else if(e.getSource()==btSubmitUserDetails)
		{
			User newUser = new User();
			UserList savingNewUser = new UserList();

			boolean passwordsMatch;
			boolean uniqueUsername;
			boolean validationCheck = true;
			String username = tfEnterUsr.getText();
			String password = pfEnterPasswd.getText();
			String reEnteredPassword = pfReEnterPasswd.getText(); 

			

			// Performs validation on the new data
			validationCheck = validateUserDetails(username,password);

			// Checks that the passwords match, so wrong password is not saved
			passwordsMatch = doPasswordsMatch(password,reEnteredPassword);

			// Unique Username Check
			uniqueUsername = !(savingNewUser.search(username,0));
			
			if((validationCheck&&passwordsMatch)&&uniqueUsername)
			{
				newUser.setPassword(password);
				newUser.setUsername(username);
				newUser.buildNewUserAccount();
				savingNewUser.saveNewUserDetails(newUser);

				
				/*
					After the user has created an account,
					the fields are reset.
				*/
				resetCreateAnAccountFields();
				
				// A confirmation message is produced.
				String message = "Hi, "+username+". You account has been created";
				setUpPopUpBox(message);
				
			}

			else
			{
				if(passwordsMatch == false)
				{
					setUpPopUpBox("Passwords do not match, please try again.");

				}
				else if(validationCheck == false)
				{
					setUpPopUpBox("Please make sure you follow the validation rules.");
				}
				else
				{
					setUpPopUpBox("That username already exists, please make yours unique in some way.");	
				}
			}
			
		}
		

		else if(e.getSource()==btBack)
		{
			myTabs.remove(0);		
			setUpLoginTab();
			this.setSize(800,500);
			myTabs.addTab("Login",loginTab);
			resetCreateAnAccountFields();

		}
/*
		else if(e.getSource()==btLogOff)
		{
			myTabs.remove(0);		
			setUpLoginTab();
			this.setSize(800,500);
			myTabs.addTab("Login",loginTab);
		}

*/
		else if(e.getSource()==btPermissions)
		{
			String filename = "userDetails.txt";
			UserList user = new UserList();
			String fileContent;
			String[] userRecords;
			String[] userFields;
			String toWrite;

			String field;
			String[] recordClickedOn = new String[4];
			for(int i=0;i<4;i++)
			{
				field = userTable.getValueAt(userTable.getSelectedRow(), i).toString();
				recordClickedOn[i] = field;
				System.out.println(field);
			}

			fileContent = user.getUserRecords();
			userRecords = fileContent.split("-1");

			for(int j=0;j<userRecords.length;j++)
			{
				userFields = userRecords[j].split(",");
				
				if(recordClickedOn[0].equals(userFields[0]))
				{
					userFields[4] = recordClickedOn[3];
				}

				userRecords[j] = userFields[0]+","+
									userFields[1]+","+
									userFields[2]+","+
									userFields[3]+","+
									userFields[4];

				ReadWriteToTxt.overwrite(filename,userRecords);
			}



		}


		else if(e.getSource()==btSubmit)
		{
			/*
				Validation of the drop-boxes
				and text fields when adding a fight.  
			*/
			String loggedInUser = currentUser.getUsername();
			String winnerName = cbWinner.getSelectedItem().toString();
			String loserName = cbLoser.getSelectedItem().toString();
			String[] verificationStatus = new String[] {"",""};
			/*
				This is to see if the current user logged in,
			 	is one of the fencers in the fight. If they are in the fight,
			 	then only one other person will have to verify the fight.
			*/
			
			if(loggedInUser.equals(winnerName))
			{
				verificationStatus = new String[] {"0",loserName};
			}
			else if(loggedInUser.equals(loserName))
			{
				verificationStatus = new String[] {"0",winnerName};
			}


			else
			{
				verificationStatus = new String[] {winnerName,loserName};
			}


			String winnerScore = "";
			String loserScore = "";
			int winnerScoreInt = 0;
			int loserScoreInt = 0;;
			boolean cbScoreLimitPopulated = !cbScoreLimit.getSelectedItem().toString().equals("-"); 
			boolean tfScoreLimitCustomPopulated = !tfScoreLimitCustom.getText().equals("");

			boolean cbLoserScorePopulated = !cbLoserScore.getSelectedItem().toString().equals("-"); 
			boolean tfLoserScoreCustomPopulated = !tfLoserScoreCustom.getText().equals("");

			if((cbScoreLimitPopulated)&&(tfScoreLimitCustomPopulated))
			{
				setUpPopUpBox("Only enter a score limit in either the suggested or custom boxes.");
			}
			else if((cbLoserScorePopulated)&&(tfLoserScoreCustomPopulated))
			{
				setUpPopUpBox("Only enter the losers score limit in either the drop down or custom boxes.");
			}
			else
			{
				if(cbScoreLimitPopulated)
				{
					winnerScore = cbScoreLimit.getSelectedItem().toString();
				}
				else if(tfScoreLimitCustomPopulated)
				{
					winnerScore = tfScoreLimitCustom.getText();
				}
				if(cbLoserScorePopulated)
				{
					loserScore = cbLoserScore.getSelectedItem().toString();
				}
				else if(tfLoserScoreCustomPopulated)
				{	
					loserScore = tfLoserScoreCustom.getText();
				}
				else
				{
					setUpPopUpBox("Enter the loser score and the score limit.");
				}
			}

			try
			{
				winnerScoreInt = Integer.parseInt(winnerScore);
				loserScoreInt = Integer.parseInt(loserScore);
			}
			catch(Exception ex)
			{
				setUpPopUpBox("Only enter integer scores.");
			}

			if(winnerScoreInt<loserScoreInt)
			{
				setUpPopUpBox("Loser score cannot be greater than the score limit.");
			}
			else if(winnerScoreInt==loserScoreInt)
			{
				setUpPopUpBox("Loser score cannot be the same as the score limit.");
			}
			else if(winnerName.equals(loserName))
			{
				setUpPopUpBox("The fencers cannot be the same.");
			}
			else
			{
				Fight newFight = new Fight();
				FightList fight = new FightList();
				newFight.setWinnerName(winnerName);
				newFight.setLoserName(loserName);
				newFight.setLoserPts(loserScore);
				newFight.setWinnerPts(winnerScore);
				newFight.setVerificationStatus(verificationStatus);

				newFight.processNewFight();
				fight.storeFight(newFight);


			}

			/*
			// Creating a new instance of a Fight.

			GenerateFight newFight;
			
			// Gathering all the relevant information to be able to store the fight.

			String winnerName = cbWinner.getSelectedItem().toString();
			String loserName = cbLoser.getSelectedItem().toString();

			String customScoreLimit = tfScoreLimitCustom.getText();

			String winnerScore;

			if(customScoreLimit.equals(""))
			{
				winnerScore = cbScoreLimit.getSelectedItem().toString();
			}

			else
			{
				winnerScore = customScoreLimit;
			}

			String loserScore = cbLoserScore.getSelectedItem().toString();

			updateFightTable(winnerName,loserName,winnerScore,loserScore);

			// All the relevant information that we need to create a fight has been gathered.
			newFight = new GenerateFight(winnerName,loserName,winnerScore,loserScore);

			newFight.processFightScores();

			newFight.storeFight();
			*/
		}

/*		
		
		else if(e.getSource()==btEdit)
		{
			boolean editConfirmed = false;

			
			
			try
			{
				for(int i=0;i<4;i++)
				{
					field = fightsTable.getValueAt(fightsTable.getSelectedRow(), i).toString();
					recordClickedOn[i] = field;
				}

				editConfirmed = setUpEditPopUp(recordClickedOn);
			}
			catch(Exception ex)
			{
				System.out.println("Error, Code=1.1");
			}	
			/*
			if(editConfirmed)
			{
				editFight()
			}
			
		}

		else if(e.getSource()==btDelete)
		{
			try
			{

				for(int i=0;i<4;i++)
				{
					field = fightsTable.getValueAt(fightsTable.getSelectedRow(), i).toString();
					recordClickedOn[i] = field;
				}

				boolean confirmDelete = setUpDeletePopUp();

			}
			catch(Exception ex)
			{
				System.out.println("Error, Code=1.2");
			}


				
		}
		*/
		
	}

	
}