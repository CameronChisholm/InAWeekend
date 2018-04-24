public class Fight
{
	String fightData;
	String[] fightDataArr;
	
	private String winnerExtraRankingPts;
	private String loserExtraRankingPts;
	private String winnerCurrentRankingPts;
	private String loserCurrentRankingPts;
	private String winnerIDNo;
	private String loserIDNo;
	private String winnerName;
	private String loserName;
	private String winnerPts;
	private String loserPts;
	private int fightID;
	private String[] verificationStatus;
	
	public String[][] getUnverifiedFights (String username)
	{
		String fileContent_Fights;
		String[] fightContentArr;
		String[] fightRecordArr;

		String[][] unverifiedFights;

		FightList fight = new FightList();

		fileContent_Fights = fight.getFightRecords();

		fightContentArr = fileContent_Fights.split("-1");

		unverifiedFights = new String[fightContentArr.length][8];

		for(int i=0;i<fightContentArr.length;i++)
		{
			fightRecordArr = fightContentArr[i].split(",");
			if(fileContent_Fights.equals(""))
			{
				unverifiedFights = new String[0][0];
			}
			
			else if(fightRecordArr[6].contains(username))
			{
				unverifiedFights[i] = fightRecordArr;
			}
			
		}

		return unverifiedFights;
	}


	/*
		Everything to do with populating 
		the permissions table.
	*/
	public String[][] getTableContents()
	{
		int amountOfFights;

		String[][] tableOfContents;

		String fileContent_User;
		String fileContent_Fights;

		String[] userContentArr;
		String[] userRecordArr;

		String[] fightContentArr;
		String[] fightRecordArr;

		UserList user = new UserList();
		fileContent_User = user.getUserRecords();

		FightList fight = new FightList();
		fileContent_Fights = fight.getFightRecords();

		System.out.println(fileContent_User);
		System.out.println(fileContent_Fights);

		userContentArr = fileContent_User.split("-1");
		fightContentArr = fileContent_Fights.split("-1");

		tableOfContents = new String[userContentArr.length][4];

		for(int i=0;i<userContentArr.length;i++)
		{
			amountOfFights = 0;

			userRecordArr = userContentArr[i].split(",");

			tableOfContents[i][0] = userRecordArr[0];
			tableOfContents[i][1] = userRecordArr[2];
			tableOfContents[i][3] = userRecordArr[4];

			for(int j=0;j<fightContentArr.length;j++)
			{
				fightRecordArr = fightContentArr[j].split(",");

				if(fileContent_Fights.equals(""))
				{
					amountOfFights = 0;
				}
				else if(userRecordArr[0].equals(fightRecordArr[2])||userRecordArr[0].equals(fightRecordArr[3]))
				{
					amountOfFights++;
				}
			}

			tableOfContents[i][2] = Integer.toString(amountOfFights);
		}

		return tableOfContents;
	}

	public String[][] getFightTableContent()
	{
		String[][] tableOfContents;

		String fileContent_User;
		String fileContent_Fights;
		String winnerID_;
		String loserID_;
		String winnerName_;
		String loserName_;

		String[] userContentArr;
		String[] userRecordArr;

		String[] fightContentArr;
		String[] fightRecordArr;

		UserList user = new UserList();

		FightList fight = new FightList();
		fileContent_Fights = fight.getFightRecords();


		fightContentArr = fileContent_Fights.split("-1");

		tableOfContents = new String[fightContentArr.length][6];

		for(int i=0;i<fightContentArr.length;i++)
		{
			fightRecordArr = fightContentArr[i].split(",");
			if(fileContent_Fights.equals("")){}
			else if(fightRecordArr[6].equals("0-0"))
			{
				winnerID_ = fightRecordArr[2];
				loserID_ = fightRecordArr[3];

				winnerName_ = user.searchAndReturnData(winnerID_,3,0);
				loserName_ = user.searchAndReturnData(loserID_,3,0);

				tableOfContents[i][0] = "Verified";
				tableOfContents[i][1] = winnerName_;
				tableOfContents[i][2] = fightRecordArr[4];
				tableOfContents[i][3] = loserName_;
				tableOfContents[i][4] = fightRecordArr[5];
				tableOfContents[i][5] = fightRecordArr[7];
			}
			else
			{
				winnerID_ = fightRecordArr[2];
				loserID_ = fightRecordArr[3];

				winnerName_ = user.searchAndReturnData(winnerID_,3,0);
				loserName_ = user.searchAndReturnData(loserID_,3,0);

				tableOfContents[i][0] = "Unverified";
				tableOfContents[i][1] = winnerName_;
				tableOfContents[i][2] = fightRecordArr[4];
				tableOfContents[i][3] = loserName_;
				tableOfContents[i][4] = fightRecordArr[5];
				tableOfContents[i][5] = fightRecordArr[7];
			}
			
		}

		return tableOfContents;
	}
	

	public String getAmountOfFights(String userID)
	{
		String fileContent;
		String[] fightContentArr;
		String[] fightRecordArr;
		int amountOfFights = 0;

		FightList user = new FightList();
		fileContent = user.getFightRecords();

		fightContentArr = fileContent.split("-1");

		for(int i=0;i<fightContentArr.length;i++)
		{
			fightRecordArr = fightContentArr[i].split(",");

			if(userID.equals(fightRecordArr[2]))
			{
				amountOfFights++;
			}
		}

		return Integer.toString(amountOfFights);

	}
	

	/*
		Everything to do with processing
		a fight.
	*/
	public void processNewFight()
	{		
		assignID();
		findFencersID();
		findFencersCurrentRankingPts();
		processFightScore();
		//store everything including how many pts each user got from each fight 
	}
	public void processFightScore()
	{
		RankingPointsAlgorithm newFight = new RankingPointsAlgorithm();
		//System.out.println(winnerCurrentRankingPts);
		//System.out.println(loserCurrentRankingPts);
		newFight.setRankingPoints1(Integer.parseInt(winnerCurrentRankingPts));
		newFight.setRankingPoints2(Integer.parseInt(loserCurrentRankingPts));

		newFight.setScore1(Integer.parseInt(winnerPts));
		newFight.setScore2(Integer.parseInt(loserPts));

		newFight.calculateRankingPoint();

		winnerExtraRankingPts = Integer.toString(newFight.getRankingPoints1());
		loserExtraRankingPts = Integer.toString(newFight.getRankingPoints2());
	}

	public void assignID()
	{
		boolean existingRecords;
		
		FightList newFight = new FightList();
		fightData = newFight.getFightRecords();
		fightDataArr = fightData.split("-1");
		existingRecords = newFight.anyPreviousRecords(fightData);
		
		if(existingRecords)
		{
			fightID = highestID();
		}
		else
		{
			fightID = 1;
		}
	}

	public int highestID()
	{
		int tempFightID;
		int highest = 0;
		String[] RecordArr;

		for(int i=0;i<fightDataArr.length;i++)
		{
			RecordArr = fightDataArr[i].split(",");
			tempFightID = Integer.parseInt(RecordArr[7]);

			if(tempFightID>highest)
			{
				highest = tempFightID;
			}
		}
		
		return highest+1;
	}
	

	public void findFencersID()
	{
		User winner = new User();
		User loser = new User();

		winner.setUsername(winnerName);
		winner.findID();
		winnerIDNo = Integer.toString(winner.getUserID());

		loser.setUsername(loserName);
		loser.findID();
		loserIDNo = Integer.toString(loser.getUserID());
			
	}

	public void findFencersCurrentRankingPts()
	{
		User winner = new User();
		User loser = new User();

		winner.setUserID(Integer.parseInt(winnerIDNo));
		loser.setUserID(Integer.parseInt(loserIDNo));

		winner.setUsername(winnerName);
		loser.setUsername(loserName);

		winner.findRankingPts();
		loser.findRankingPts();

		winnerCurrentRankingPts = Integer.toString(winner.getUserRankingPts());
		loserCurrentRankingPts = Integer.toString(loser.getUserRankingPts());

	}

	/*
		Getters and Setters
	*/

	public void setWinnerName(String tempWinnerName)
	{
		winnerName = tempWinnerName;
	}

	public void setLoserName(String tempLoserName)
	{
		loserName = tempLoserName;
	}
	public void setLoserPts(String tempLoserPts)
	{
		loserPts = tempLoserPts;
	}
	public void setWinnerPts(String tempWinnerPts)
	{
		winnerPts = tempWinnerPts;
	}
	
	public String getWinnerID()
	{
		return winnerIDNo;
	}
	public String getLoserID()
	{
		return loserIDNo;
	}
	public String getLoserPts()
	{
		return loserPts;
	}
	public String getWinnerPts()
	{
		return winnerPts;
	}
	public String getWinnerExtraRankingPts()
	{
		return winnerExtraRankingPts;
	}
	public String getLoserExtraRankingPts()
	{
		return loserExtraRankingPts;
	}
	public int getFightID()
	{
		return fightID;
	}

	public String[] getVerificationStatus()
	{
		return verificationStatus;
	}
	public void setVerificationStatus(String[] tempVerificationStatus)
	{
		verificationStatus = tempVerificationStatus;
	}

	

}