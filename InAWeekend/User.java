public User
{
	
	public void assignID()
	{

		// Checking if file is empty
		boolean isFileEmpty;
		String userDetails = ReadWriteToTxt.read(filename);	

		String[] userDetailsArr = userDetails.split("-1");
		
		int highest = 0;
		int tempUserID;

		isFileEmpty = !(checkingFileContent(userDetails));

		if(isFileEmpty)
		{
			userID = "1";
		}

		else
		{
			for(int i=0;i<userDetailsArr.length;i++)
			{
				String[] RecordArr = userDetailsArr[i].split(",");
				tempUserID = Integer.parseInt(RecordArr[0]);

				if(tempUserID>highest)
				{
					highest = tempUserID;
				}
			}
			userID = Integer.toString(highest+1);	
		}
	}

	



}