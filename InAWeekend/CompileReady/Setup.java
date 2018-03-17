public class Setup 
{
	public static void main(String[] args)
	{
		Setup beforeStartUp = new Setup();
		beforeStartUp.checkFiles();
		
		GUI newInstance = new GUI();
		newInstance.startGUI();

	//	FencerList testing2 = new FencerList();
	}

	
	/*
		Method: checkFiles()
		Checks that all the files needed for the
		system to operate exist.
		If they do not (e.g. first time start up)
		they will be generated.
	*/
	public void checkFiles()
	{
		String filename;
		boolean fileExist = false;
		// Checking the file userDetails.txt exists
		filename = "userDetails.txt";
		
		fileExist = ReadWriteToTxt.checkFile(filename);
		
		if(fileExist == false)
		{
			ReadWriteToTxt.write(filename,"");
		}

		filename = "fightData.txt";

		fileExist = ReadWriteToTxt.checkFile(filename);

		if(fileExist == false)
		{
			ReadWriteToTxt.write(filename,"");
		}
	}
/*
	public static boolean checkUserDetails(String tempUsername,String tempPassword)
	{
		String filename = "userDetails.txt";
		String userDetails = ReadWriteToTxt.read(filename);

		String username;
		String password;

		boolean match = false;

		String[] userDetailsArr = userDetails.split("-1");

		for(int i=0;i<userDetailsArr.length&&(match==false);i++)
		{
			String[] RecordArr = userDetailsArr[i].split(",");
			username = RecordArr[1];
			password = RecordArr[2];

			if(username.equals(tempUsername))
			{
				if(password.equals(tempPassword))
				{
					match = true;
				}
			}

		}

		return match;
	}

	*/
}