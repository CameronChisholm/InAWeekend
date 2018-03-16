public class UserList
{	
	/*
		Everything regarding user records.
	*/
	
	/*
		This methods, gets all user data
		and splits it into records.
	*/
	public String getUserRecords()
	{
		String filename = "userDetails.txt";
		
		String userDetails = ReadWriteToTxt.read(filename);	
		//String[] userDetailsArr = userDetails.split("-1");
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
}