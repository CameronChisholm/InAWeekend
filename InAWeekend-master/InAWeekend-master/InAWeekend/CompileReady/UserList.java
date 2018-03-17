public class UserList
{	
	String filename = "userDetails.txt";
	/*
		Everything regarding user records.
	*/

	/*
		
	*/
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
		String username,hashedPassword,userRankingPts,userID,userRecord;

		username = newUser.getUsername();
		hashedPassword = newUser.getHashedPassword();
		userRankingPts = Integer.toString(newUser.getUserRankingPts());
		userID = Integer.toString(newUser.getUserID());	
		userRecord = username+","+
					hashedPassword+","+
					userRankingPts+","+
					userID+","+"-1";
		ReadWriteToTxt.write(filename,userRecord);
	}
}