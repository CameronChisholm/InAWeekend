public class FightList
{
	private String filename = "fightData.txt";

	/*
		This methods gets all fight data.
	*/
	public String getFightRecords()
	{	
		String fightData = ReadWriteToTxt.read(filename);	
		return fightData;
	}	
	
	/*
		Checks for if there are any previous records.
		This will prevent out of bounds exception.
	*/

	public boolean anyPreviousRecords(String fightData)
	{
		/* 
			Variable: existingRecords
			If true, then there are records.
			If false, then there are not records.
		*/
		
		boolean existingRecords = false;
		
		if(fightData.equals(""))
		{
			existingRecords = false;
		}
		
		else
		{
			existingRecords = true;
		}
		
		return existingRecords;
	}

	public void storeFight(Fight tempFight)
	{
		String winnerExtraRankingPts,loserExtraRankingPts;
		String winnerIDNo,loserIDNo,winnerPts,loserPts,fightID;
		String[] verificationStatus;
		String verificationStatusStr;
		
		winnerExtraRankingPts = tempFight.getWinnerExtraRankingPts();
		loserExtraRankingPts = tempFight.getLoserExtraRankingPts();
		winnerIDNo = tempFight.getWinnerID();
		loserIDNo = tempFight.getLoserID();
		winnerPts = tempFight.getWinnerPts();
		loserPts = tempFight.getLoserPts();
		verificationStatus = tempFight.getVerificationStatus();
		fightID = Integer.toString(tempFight.getFightID());

		verificationStatusStr = verificationStatus[0]+"-"+verificationStatus[1];

		String fightData = winnerExtraRankingPts+","+
							loserExtraRankingPts+","+
							winnerIDNo+","+
							loserIDNo+","+
							winnerPts+","+
							loserPts+","+
							verificationStatusStr+","+
							fightID+","+"-1";
							

		ReadWriteToTxt.write(filename,fightData);

	}
	
}