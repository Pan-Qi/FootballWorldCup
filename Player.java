/**
 * The player class is used to create player class which can store player's name and number of goals
 *
 * @author (Pan Qi)
 * @version (19/05/2018)
 */
public class Player
{
    private String name;//Name of player.
    private int goals;//Total goals of player.

    /**
     * Default constructor Player to create a player with default name "" and 0 goal.
     */
    public Player()
    {
        name = "";
        goals = 0;
    }

    /**
     * Constructor Player which is used to create a player.
     * 
     * @param String which is the name of a player.
     * @param Integer which is the total goals of the player.
     */
    public Player(String playerName,int playerGoals)
    {
        name = playerName;
        goals = playerGoals;
    }

    /**
     * Get total goals of player.
     * 
     * @return Integer which is the total goals of player.
     */
    public int getGoals()
    {
        return goals;
    }

    /**
     * Get the player's name.
     * 
     * @return String which is the name of player.
     */
    public String getName()
    {
        return name;
    }

    /**
     * Set the goals numner of player.
     * 
     * @param Integer which is the goals number of the player.
     */
    public void setGoals(int playerGoals)
    {
        if (playerGoals >= 0)
            goals = playerGoals;
        else
        {
            goals = 0;
            System.out.println("Player goals invalid.Default value set!");
        }
    }

    /**
     * Set the name of player.
     * 
     * @param String which is the name of player.
     */
    public void setName(String playerName)
    {
        if (playerName.trim().length() >= 2)
            name = playerName;
        else
        {
            name = "";
            System.out.println("Player name invalid.Default value set!");
        }
    }
}
