public class FightList
{
	private String filename = "fightData.txt";

	/*
		This methods gets all fight data.
	*/
	public void deleteFight(String fightID)
	{
		String fileContent_Fights = getFightRecords();

		String[] fightContentArr;
		String[] newFightContentArr;
		String[] fightRecordArr;
		fightContentArr = fileContent_Fights.split("-1");
		newFightContentArr = new String[fightContentArr.length];
		int j=0;
		for(int i=0;i<fightContentArr.length;i++)
		{
			fightRecordArr = fightContentArr[i].split(",");
			if(fightRecordArr.length>6)
			{
				if(!fightRecordArr[7].equals(fightID))
				{
					newFightContentArr[j] = fightRecordArr[0]+","+
										fightRecordArr[1]+","+
										fightRecordArr[2]+","+
										fightRecordArr[3]+","+
										fightRecordArr[4]+","+
										fightRecordArr[5]+","+
										fightRecordArr[6]+","+
										fightRecordArr[7];
					j++;
				}
			}
		}

		ReadWriteToTxt.overwrite(filename,newFightContentArr);
	}

	public String getFightRecords()
	{	
		String fightData = ReadWriteToTxt.read(filename);	
		return fightData;
	}	

	public int changeFightStatus(String fightID,String otherFencer,String fencer)
	{
		UserList user = new UserList();

		String fileContent_Fights = getFightRecords();

		String[] fightContentArr;
		String[] newFightContentArr;
		String[] fightRecordArr;
		fightContentArr = fileContent_Fights.split("-1");
		newFightContentArr = new String[fightContentArr.length];
		int rankingPtsToAdd = 0;

		for(int i=0;i<fightContentArr.length;i++)
		{
			fightRecordArr = fightContentArr[i].split(",");

			if(fightRecordArr[7].equals(fightID))
			{
				newFightContentArr[i] = fightRecordArr[0]+","+
										fightRecordArr[1]+","+
										fightRecordArr[2]+","+
										fightRecordArr[3]+","+
										fightRecordArr[4]+","+
										fightRecordArr[5]+","+
										"0-"+otherFencer+","+
										fightRecordArr[7];

				String fencer1 = user.searchAndReturnData(fightRecordArr[2],3,0);
				String fencer2 = user.searchAndReturnData(fightRecordArr[3],3,0);

				if(fencer1.equals(fencer))
				{
					rankingPtsToAdd = rankingPtsToAdd+Integer.parseInt(fightRecordArr[0]);
					if(otherFencer.equals("0"))
					{
						rankingPtsToAdd = rankingPtsToAdd+Integer.parseInt(fightRecordArr[1]);
					}
				}
				else if(fencer2.equals(fencer))
				{
					rankingPtsToAdd = rankingPtsToAdd+Integer.parseInt(fightRecordArr[1]);
				
					if(otherFencer.equals("0"))
					{
						rankingPtsToAdd = rankingPtsToAdd+Integer.parseInt(fightRecordArr[0]);
					}
				}

			}
			else
			{
				newFightContentArr[i] = fightRecordArr[0]+","+
										fightRecordArr[1]+","+
										fightRecordArr[2]+","+
										fightRecordArr[3]+","+
										fightRecordArr[4]+","+
										fightRecordArr[5]+","+
										fightRecordArr[6]+","+
										fightRecordArr[7];
			}
		}

		ReadWriteToTxt.overwrite(filename,newFightContentArr);
		return rankingPtsToAdd;
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