public UserList
{
	public boolean checkingFileContent(String tempFileContents)
	{
		// Local Variables
		boolean doRecordsExist;

		// Checks if the file is empty or not
		if(tempFileContents.equals(""))
		{
			doRecordsExist = false;
		}

		else
		{
			doRecordsExist = true;
		}

		// This methods only checks if the file is COMPLETELY empty or not

		// Doesn't account for if the file is corrupt

		return doRecordsExist;
	}
}