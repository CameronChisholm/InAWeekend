public class User
{
		
	String userDetails;
	String[] userDetailsArr;
	
	int userID;
	int userRankingPts;
	String username;

	
	// Attributes specific to new users.
	String hashedPassword;
	String password;
	String permission;
	
	
	public User(){}
	
	/*
		Getters and Setters
	*/
	public int getUserID()
	{
		return userID;
	}
	public void setUserID(int tempUserID)
	{
		userID = tempUserID;
	}

	public String getUsername()
	{
		return username;
	}

	public String getPermission()
	{
		return permission;
	}

	public void setPermission(String tempPermission)
	{
		permission = tempPermission;
	}

	public int getUserRankingPts()
	{
		return userRankingPts;
	}

	public void setPassword(String tempPassword)
	{
		password = tempPassword;
	}

	public void setUsername(String tempUsername)
	{
		username = tempUsername;
	}

	
	/*
		Building a new user's account

	*/

	public void buildNewUserAccount()
	{
		/*
			All new users will start
			with zero ranking pts
		*/

		userRankingPts = 0;
		
		assignID();
		hashUserPassword();
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
			permission = "New User";
		}
		else
		{
			userID = 1;
			permission = "Root";
		}
	}
	
	public int highestID()
	{
		int tempUserID;
		int highest = 0;
		String[] RecordArr;
		for(int i=0;i<userDetailsArr.length;i++)
		{
			RecordArr = userDetailsArr[i].split(",");
			tempUserID = Integer.parseInt(RecordArr[3]);

			if(tempUserID>highest)
			{
				highest = tempUserID;
			}
		}
		
		return highest+1;
	}
	
	/*
		Everything regarding password hashing.
	*/
	public void hashUserPassword()
	{
		MD5Hashing hashingUserPassword = new MD5Hashing();
		hashedPassword = hashingUserPassword.hash(password);
	}
	
	public String getHashedPassword()
	{
		return hashedPassword;
	}

	/*
		Everything regarding searching for
		existing user details.
	*/

	public void findID()
	{
		UserList user = new UserList();

		boolean doesUserExist = user.search(username,0);
		if(doesUserExist)
		{
			userID = Integer.parseInt(user.searchAndReturnData(username,0,3));
		}
		else
		{
			System.out.println("Error, User: "+username+" .");
			System.out.println("Does not exist.");
		}
	}

	public void findRankingPts()
	{
		UserList user = new UserList();
		String userIDStr = Integer.toString(userID);

		boolean doesUserExist = user.search(userIDStr,3);
		if(doesUserExist)
		{
			userRankingPts = Integer.parseInt(user.searchAndReturnData(userIDStr,3,2));
		}
		else
		{
			System.out.println("Error, User: "+username+" .");
			System.out.println("Does not exist.");
		}
	}

	public void findPermissions()
	{
		UserList user = new UserList();
		String userIDStr = Integer.toString(userID);

		boolean doesUserExist = user.search(userIDStr,3);
		if(doesUserExist)
		{
			permission = user.searchAndReturnData(userIDStr,3,4);
		}
		else
		{
			System.out.println("Error, User: "+username+" .");
			System.out.println("Does not exist.");
		}
	}


	public String[][] sortRankingPoints()
	{
		// quick sort
		UserList user = new UserList();
		String[][] userPts = user.getAllFencerPoints();
		int lowIndex = 0;
		int highIndex = userPts.length-1;
		userPts = quickSort(userPts,lowIndex,highIndex);

		for(int i=0;i<highIndex+1;i++)
		{
			System.out.println(userPts[i][1]);
		}
		
		return userPts;
	}

	public String[][] quickSort(String[][] rankingPoints,int lowIndex,int highIndex)
	{
		if(lowIndex<highIndex)
		{
			int partitioningIndex = partitioningIndex(rankingPoints,lowIndex,highIndex);

			quickSort(rankingPoints,lowIndex, partitioningIndex-1);
			quickSort(rankingPoints,partitioningIndex+1,highIndex);
		}
		return rankingPoints;
	}

	public int partitioningIndex(String[][] rankingPts,int lowIndex, int highIndex)
	{
		int pivot = Integer.parseInt(rankingPts[highIndex][1]);
		String[] tempValue;

		int i = (lowIndex-1);

		for(int j=lowIndex; j<=highIndex-1; j++)
		{
			if(Integer.parseInt(rankingPts[j][1])<=pivot)
			{
				i++;
				tempValue = rankingPts[i];
				rankingPts[i] = rankingPts[j];
				rankingPts[j] = tempValue;
			}
		}

		tempValue = rankingPts[i+1];
		rankingPts[i+1] = rankingPts[highIndex];
		rankingPts[highIndex] = tempValue;

		return (i+1);
	}




}