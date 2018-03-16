import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.*;


public class GUI extends JFrame implements ActionListener 
{
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

	private JComboBox cbScoreLimit;

	private JComboBox cbWinner;
	private JComboBox cbLoser;
	private JComboBox cbLoserScore;

	private JTextField tfScoreLimitCustom = new JTextField(6);

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
	private JButton btLogOff = new JButton();

	/* 
	Below, GUI Components for the 'ranking table'
	tab are declared.
	*/
	private DefaultTableModel rankingTableModel;
	private JTable rankingTable;
	private JScrollPane rankingTableScroll;

	private JLabel lblRankingTableTabDescription;



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
		setUpLoginTab();


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
		myTabs.addTab("Login",loginTab);
		
		this.setLayout(new GridLayout(1,1));
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(800,500);
		this.setVisible(true);
		
		this.add(myTabs);
	}

	public void setUpCreateAnAccountTab()
	{
		lblEnterUsr.setLocation(75,100);
		lblEnterUsr.setSize(100,50);
		lblEnterUsr.setOpaque(true);
		lblEnterUsr.setText("Enter Name:");
		
		lblEnterPasswd.setLocation(75,200);
		lblEnterPasswd.setSize(100,50);
		lblEnterPasswd.setOpaque(true);
		lblEnterPasswd.setText("Enter Password:");
		
		lblReEnterPasswd.setLocation(75,300);
		lblReEnterPasswd.setSize(125,50);
		lblReEnterPasswd.setOpaque(true);
		lblReEnterPasswd.setText("Re-enter Password:");
		
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
		btSubmitUserDetails.setText("Submit");
		
		btBack.setLocation(10,400);
		btBack.setSize(200,50);
		btBack.addActionListener(this);
		btBack.setText("Back");
		
		createAnAccountTab.add(btBack);
		createAnAccountTab.add(tfEnterUsr);
		createAnAccountTab.add(pfEnterPasswd);
		createAnAccountTab.add(pfReEnterPasswd);
		createAnAccountTab.add(lblEnterUsr);
		createAnAccountTab.add(lblEnterPasswd);
		createAnAccountTab.add(lblReEnterPasswd);
		createAnAccountTab.add(btSubmitUserDetails);
		
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
/*
	public void setupRankingTable()
	{
		//lblRankingTableTabDescription.setLocation(10,0);
		//lblRankingTableTabDescription.setSize(300,50);
		//lblRankingTableTabDescription.setOpaque(true);
		//lblrankingTableTabDescription.setFont(new Font("Courier",Font.PLAIN,30));
		//lblRankingTableTabDescription.setText("Ranking Table");	

		String[] rankingTableHeadings = {"Rank","Name of Fencer","Ranking Points"};

		String[] rankingDataRecords = HomePage.getRankingData();

		int amountOfRecords = rankingDataRecords.length;

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


		rankingTableModel = new DefaultTableModel(rankingTableData,rankingTableHeadings);

		rankingTable = new JTable(rankingTableModel);

		rankingTableScroll = new JScrollPane(rankingTable);

		rankingTableScroll.setSize(700,500);
		rankingTableScroll.setLocation(10,10);	

		//rankingTableTab.add(lblRankingTableTabDescription);
		rankingTableTab.add(rankingTableScroll);
	}

	public void setupHomePage()
	{
		lblPageTitle.setLocation(10,0);
		lblPageTitle.setSize(300,50);
		lblPageTitle.setOpaque(true);
		lblPageTitle.setFont(new Font("Courier",Font.PLAIN,30));
		lblPageTitle.setText("Home Page");

		btLogOff.setLocation(300,0);
		btLogOff.setSize(200,50);
		btLogOff.addActionListener(this);
		btLogOff.setFont(new Font("Courier",Font.PLAIN,30));
		btLogOff.setText("Log off");

		homePageTab.add(lblPageTitle);
		homePageTab.add(btLogOff);

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

		String[] allUserNames = HomePage.getAllUserNames();

		String[] DataCBScoreLimit = {
								"5",
								"10",
								"15"
							};

		String[] DataCBWinner = allUserNames;

		String[] DataCBLoser = allUserNames;

		String[] DataCBLoserScore = new String[15];
		
		for(int i=0;i<15;i++)
		{
			DataCBLoserScore[i] = Integer.toString(i+1);
		}



		cbScoreLimit = new JComboBox(DataCBScoreLimit);
		cbWinner = new JComboBox(DataCBWinner);
		cbLoser = new JComboBox(DataCBLoser);
		cbLoserScore = new JComboBox(DataCBLoserScore); 

		cbScoreLimit.setBounds(100,115,50,25);
		cbWinner.setBounds(10,250,100,25);
		cbLoser.setBounds(10,350,100,25);
		cbLoserScore.setBounds(10,450,50,25);


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

		fightsTab.add(btSubmit);
		fightsTab.add(fightsTableScroll);
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
										
		validationSuccessful = presenceCheck
								&&
								usernameLengthCheck
								&&
								passwordLengthCheck;

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

		int option = JOptionPane.showConfirmDialog(null, newUserInformation, "User Input Error", JOptionPane.OK_CANCEL_OPTION);
	}
/*
	public void displaySystem(String tempPermission)
	{
		permissions = tempPermission;
	
		if(permissions.equals("Root"))
		{
			myTabs.remove(0);
			
			setUpFightsTab();
			setupHomePage();
			setupRankingTable();
			
			myTabs.addTab("Home Page",homePageTab);
			myTabs.addTab("Managing Fights",fightsTab);
			myTabs.addTab("Ranking Table",rankingTableTab);
			

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
			setupRankingTable();

			myTabs.addTab("Home Page",homePageTab);
			myTabs.addTab("Managing Fights",fightsTab);
			myTabs.addTab("Ranking Table",rankingTableTab);
			

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
			setupRankingTable();
			myTabs.addTab("Home Page",homePageTab);
			myTabs.addTab("Managing Fights",fightsTab);
			myTabs.addTab("Ranking Table",rankingTableTab);
			

			this.setLayout(new GridLayout(1,1));
			this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			this.setSize(800,700);
			this.setVisible(true);
			this.add(myTabs);
		}
		else
		{
			myTabs.remove(0);
			setUpFightsTab();
			setupHomePage();
			setupRankingTable();
			myTabs.addTab("Home Page",homePageTab);
			myTabs.addTab("Managing Fights",fightsTab);
			myTabs.addTab("Ranking Table",rankingTableTab);
			

			this.setLayout(new GridLayout(1,1));
			this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			this.setSize(800,700);
			this.setVisible(true);
			this.add(myTabs);
		}
		
	}
*/
	public void actionPerformed(ActionEvent e)
 	{
/*
 		String[] recordClickedOn = new String[4];
 		String field;

 		if(e.getSource()==btLogin)
		{
			
			System.out.println("login");

			String username = tfUsr.getText();
			String password = pfPasswd.getText();

			boolean checkSuccessful = Login.checkUserDetails(username,password);

			if(checkSuccessful)
			{
				displaySystem("Fencer");
							
			}

			else if(checkSuccessful==false)
			{
				System.out.println("Incorrect Details");
				setUpPopUpBox("Incorrect details, please try again.");
			}


		}
*/
		if(e.getSource()==btCreateAnAccount)
		{
			myTabs.remove(0);		
			setUpCreateAnAccountTab();
			this.setSize(500,600);
			myTabs.addTab("Create an Account",createAnAccountTab);

		}
/*
		else if(e.getSource()==btSubmitUserDetails)
		{
			String username = tfEnterUsr.getText();
			String password = pfEnterPasswd.getText();

			boolean validationCheck = true;

			// Perform validation on the new data
			validationCheck = validateUserDetails(username,password);
			
			if(validationCheck)
			{
				GenerateAccount newUser = new GenerateAccount(username,password);
				newUser.assignID();
				newUser.saveNewUserDetails(newUser);

				myTabs.remove(0);
				myTabs.addTab("Login",loginTab);
				this.setLayout(new GridLayout(1,1));
				this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				this.setSize(800,500);
				this.setVisible(true);
		
				this.add(myTabs);
			}

			else
			{
				setUpPopUpBox("Please make sure you follow the validation rules.");
			}
			
		}
		*/

		else if(e.getSource()==btBack)
		{
			myTabs.remove(0);		
			setUpLoginTab();
			this.setSize(800,500);
			myTabs.addTab("Login",loginTab);

		}
/*
		else if(e.getSource()==btLogOff)
		{
			myTabs.remove(0);		
			setUpLoginTab();
			this.setSize(800,500);
			myTabs.addTab("Login",loginTab);
		}


		else if(e.getSource()==btSubmit)
		{
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
		}

		
		
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