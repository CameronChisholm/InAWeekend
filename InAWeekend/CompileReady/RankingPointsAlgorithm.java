public class RankingPointsAlgorithm
{
	// Fencer 1 attributes
	int currentRankingPts1;
	int score1;
	/* 
	Fencer 1 ranking points gained from the fight,
	not total ranking points. Same for 'rankingPoints2'.
	*/
	int rankingPoints1 = 0;	

	// Fencer 2 attributes
	int currentRankingPts2;
	int score2;
	int rankingPoints2 = 0;

	public RankingPointsAlgorithm(){}

	public void calculateRankingPoint()
	{
		if(score1>score2)
		{
			rankingPoints1+=2;
		}
		else if(score1<score2)
		{
			rankingPoints2+=2;
		}

		if(currentRankingPts1>currentRankingPts2)
		{
			rankingPoints2+=1;
		}
		else if(currentRankingPts1<currentRankingPts2)
		{
			rankingPoints1+=1;
		}
	}

	public int getRankingPoints1()
	{
		return rankingPoints1;
	}

	public int getRankingPoints2()
	{
		return rankingPoints2;
	}

	public void setRankingPoints1(int tempRankingPts1)
	{
		currentRankingPts1 = tempRankingPts1;
	}
	public void setRankingPoints2(int tempRankingPts2)
	{
		currentRankingPts2 = tempRankingPts2;
	}
	public void setScore1(int tempScore1)
	{
		score1 = tempScore1;
	}
	public void setScore2(int tempScore2)
	{
		score2 = tempScore2;
	}

}