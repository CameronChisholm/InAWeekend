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
	
	/*
		Everything to do with processing
		a fight.
	*/
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
			tempFightID = Integer.parseInt(RecordArr[3]);

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