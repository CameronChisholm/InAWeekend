public class Target
{

	public Target(String userID,
					String targetID,
					String target)
	{

	}

	public Target(){}

	public String[][] getUserTargets(String userID)
	{
		String filename = "targetDetails.txt";
		String fileContents = ReadWriteToTxt.read(filename);

		String[][] targets;
		String[] targetFields;
		String[] targetRecords;
		int amountOfUserTargets = 0;

		
		if(!fileContents.equals(""))
		{
			targetRecords = fileContents.split("-1");
			targets = new String[targetRecords.length][5];

			for(int i=0;i<targetRecords.length;i++)
			{
				targetFields = targetRecords[i].split(",");

				if(targetFields[0].equals(userID))
				{
					targets[amountOfUserTargets] = targetRecords[i].split(",");
				}
			}

		}
		else
		{
			targets = new String[][] {{"-","-","-","-","-"}};
		}

		return targets;
	}
}