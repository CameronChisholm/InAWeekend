public class User
{
	
	String userDetails;
	String[] userDetailsArr;
	
	int userID;
	
	
	public User()
	{
	
	}
	
	/*
		Getters and Setters
	*/
	public int getUserID()
	{
		return userID;
	}
	/*
		Everything regarding assigning a new user an ID.
	*/
	public void assignID()
	{
		boolean existingRecords;
		
		UserList newUser = new UserList();
		userDetails = newUser.getUserRecords();
		userDetailsArr = userDetails.split("-1");
		existingRecords = newUser.anyPreviousRecords(userDetails);
		
		if(existingRecords)
		{
			userID = highestID();
		}
		else
		{
			userID = 1;
		}
	}
	
	public int highestID()
	{
		int tempUserID;
		int highest = 0;
		for(int i=0;i<userDetailsArr.length;i++)
		{
			String[] RecordArr = userDetailsArr[i].split(",");
			tempUserID = Integer.parseInt(RecordArr[0]);

			if(tempUserID>highest)
			{
				highest = tempUserID;
			}
		}
		
		return highest;
	}
	
	
	
	

	



}