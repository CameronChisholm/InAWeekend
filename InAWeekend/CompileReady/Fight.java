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
	private String verificationStatus;
	

	/*
		Everything to do with processing
		a fight.
	*/
	public void processNewFight()
	{
		verificationStatus = "unverified by both fencers"
		assignID();
		findFencersID();
		findFencersCurrentRankingPts();
		calculateRankingPts();
		//store everything including how many pts each user got from each fight 
	}
	public void calculateRankingPts()
	{
		RankingPointsAlgorithm newFight = new RankingPointsAlgorithm();
		newFight.setRankingPoints1(winnerCurrentRankingPts);
		newFight.setRankingPoints2(loserCurrentRankingPts);

		newFight.setScore1(Integer.parseInt(winnerPts));
		newFight.setScore2(Integer.parseInt(loserPts));

		calculateRankingPts();

		winnerExtraRankingPts = newFight.getRankingPoints1();
		loserExtraRankingPts = newFight.getRankingPoints2();
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

		winnerCurrentRankingPts = winner.findRankingPts();
		loserCurrentRankingPts = loser.findRankingPts();

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

	public String getVerificationStatus()
	{
		return verificationStatus;
	}


	

}