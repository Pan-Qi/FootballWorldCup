import java.util.Random;
/**
 * This class generate random goals for each team in different games.
 * Randomly distribute team goals to players.
 * Generate random number from 0 to a given max value.
 *
 * @author (Pan Qi)
 * @version (20/5/2018)
 */
public class RandomGoalsGenerator
{
    
    /**
     * Default constructor for objects of class RandomGoalsGenerator
     */
    public RandomGoalsGenerator()
    {   
    }
    
    /**
     * Randomly distribute team goals to player.
     * 
     * @param Integer which is the goals of team.
     * @return Integer which is goals of a player.
     */
    public int generatePlayerGoals(int teamGoals)
    {
        Random random = new Random();
        return random.nextInt(teamGoals + 1); 
    }
    
    /**
     * Randomly generate goals of each team in a game.
     * 
     * @param Integer which is the difference of rankings of teams in a game.
     * @return Integer which is the goals of the team.
     */
    public int generateTeamGoals(int rankingDifference)
    {
        Random goals = new Random();
        return goals.nextInt(6 - rankingDifference + randomUpset());
    }
    
    /**
     * Generate a random number from 0 to max value.
     * 
     * @param Integer which is the max value of random number.
     * @return Integer which is the random number from 0 to max value.
     */
    public int cardGenerator(int maxValue)
    {
        Random random = new Random();
        return random.nextInt(maxValue);        
    }
    
    /**
     * Randomly generate a upset number.
     * 
     * @return Integer which is the upset number.
     */
    private int randomUpset()
    {
        Random upset = new Random();
        return upset.nextInt(3);
    }
}
