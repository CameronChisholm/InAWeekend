public class UserList
{	
	String filename = "userDetails.txt";
	/*
		Everything regarding user records.
	*/

	/*
		
	*/

	public void updateFencerRankingPts(String fencerID,String rankingPts)
	{
		String userRecords = getUserRecords();
		String[] userRecordsArr = userRecords.split("-1");
		String[] newUserRecordsArr = new String[userRecordsArr.length];
	
		for(int i=0;i<userRecordsArr.length;i++)
		{
			String[] fencerRecord = userRecordsArr[i].split(",");
			if(fencerRecord[3].equals(fencerID))
			{
				String newTotalRankingPts = Integer.toString(Integer.parseInt(rankingPts)+Integer.parseInt(fencerRecord[2]));
				newUserRecordsArr[i] = fencerRecord[0]+","+
										fencerRecord[1]+","+
										newTotalRankingPts+","+
										fencerRecord[3]+","+
										fencerRecord[4];
			}
			else
			{
				newUserRecordsArr[i] = fencerRecord[0]+","+
										fencerRecord[1]+","+
										fencerRecord[2]+","+
										fencerRecord[3]+","+
										fencerRecord[4];	
			}
		}

		ReadWriteToTxt.overwrite(filename,newUserRecordsArr);
	}
	public String[] getAllFencerNames()
	{
		String userRecords = getUserRecords();
		String[] userRecordsArr = userRecords.split("-1");

		String[] allFencerNames = new String[userRecordsArr.length];

		for(int i=0;i<userRecordsArr.length;i++)
		{
			String[] fencerRecord = userRecordsArr[i].split(",");
			allFencerNames[i] = fencerRecord[0];
		}

		return allFencerNames;
	}

	public String[][] getAllFencerPoints()
	{
		String userRecords = getUserRecords();
		String[] userRecordsArr = userRecords.split("-1");

		String[][] allFencerRankingPts = new String[userRecordsArr.length][2];

		for(int i=0;i<userRecordsArr.length;i++)
		{
			String[] fencerRecord = userRecordsArr[i].split(",");
			allFencerRankingPts[i][0] = fencerRecord[0];
			allFencerRankingPts[i][1] = fencerRecord[2];

		}

		return allFencerRankingPts;
	}

	/*
		Get all unverified fights a user is in.		
	*/

	public String[][] getUnverifiedFights(String fencerName)
	{
		String filename = "fightData.txt";

		String fightDetails = ReadWriteToTxt.read(filename);	

		String[] fightRecordsArr = fightDetails.split("-1");
		String[] RecordArr;
		String[] fencerNames;
		String field;
		boolean match = false;
		String[][] listOfFights = new String[fightRecordsArr.length][7];


		for(int i=0;i<fightRecordsArr.length;i++)
		{
			RecordArr = fightRecordsArr[i].split(",");
			
			if(RecordArr.length>6)
			{
				field = RecordArr[6];
				if(field.equals("0-0"))
				{}
				else
				{
					fencerNames = field.split("-");

					match = fencerNames[0].equals(fencerName)||fencerNames[1].equals(fencerName);
					System.out.println(fencerNames[0]);
					System.out.println(fencerName);
					System.out.println(match);

					if(match)
					{
						String winner = searchAndReturnData(RecordArr[2],3,0);
						String loser = searchAndReturnData(RecordArr[3],3,0);
						listOfFights[i][0] = winner;
						listOfFights[i][1] = RecordArr[4];
						listOfFights[i][2] = loser;
						listOfFights[i][3] = RecordArr[5];
						listOfFights[i][4] = RecordArr[7];
						listOfFights[i][5] = RecordArr[6];
					}
				}

				
			}
		}

			return listOfFights;
			
	}

	/*
		Searches for the user records
	*/

	public boolean search(String searchTerm,int fieldNumber)
	{
		String userRecords = getUserRecords();
		String[] userRecordsArr = userRecords.split("-1");
		String[] RecordArr;
		String field;
		boolean match = false;

		for(int i=0;i<userRecordsArr.length;i++)
		{
			RecordArr = userRecordsArr[i].split(",");
			field = RecordArr[fieldNumber];
			if(searchTerm.equals(field))
			{
				match = true;
			}
		}
		return match;
	}

	public String searchAndReturnData(String searchTerm,
										int searchTermFieldNo,
										int dataToReturnFieldNo)
	{
		String userRecords = getUserRecords();
		String[] userRecordsArr = userRecords.split("-1");
		String[] RecordArr;
		String field;
		boolean match = false;
		String dataToReturn = "";

		for(int i=0;i<userRecordsArr.length;i++)
		{
			RecordArr = userRecordsArr[i].split(",");
			field = RecordArr[searchTermFieldNo];
			if(searchTerm.equals(field))
			{
				match = true;
				dataToReturn = RecordArr[dataToReturnFieldNo];
			}
		}
		return dataToReturn;
	}
	
	/*
		This methods gets all user data.
	*/
	public String getUserRecords()
	{	
		String userDetails = ReadWriteToTxt.read(filename);	
		return userDetails;
	}
	/*
		Checks for if there are any previous records.
		This will prevent out of bounds exception.
	*/
	public boolean anyPreviousRecords(String userDetails)
	{
		/* 
			Variable: existingRecords
			If true, then there are records.
			If false, then there are not records.
		*/
		
		boolean existingRecords = false;
		
		if(userDetails.equals(""))
		{
			existingRecords = false;
		}
		
		else
		{
			existingRecords = true;
		}
		
		return existingRecords;
	}
	/*
		Saves new users details to text file.
	*/
	public void saveNewUserDetails(User newUser)
	{
		String username,hashedPassword,userRankingPts,userID,userRecord,permission;

		username = newUser.getUsername();
		hashedPassword = newUser.getHashedPassword();
		userRankingPts = Integer.toString(newUser.getUserRankingPts());
		userID = Integer.toString(newUser.getUserID());	
		permission = newUser.getPermission();


		userRecord = username+","+
					hashedPassword+","+
					userRankingPts+","+
					userID+","+
					permission+"-1";
		ReadWriteToTxt.write(filename,userRecord);
	}
}