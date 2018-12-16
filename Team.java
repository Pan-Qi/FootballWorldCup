import java.util.ArrayList;
/**
 * The Team class is used to store attributes of each team.
 * And also initialize collection of players,test if players' name are same,display team result.
 *
 * @author (Pan Qi)
 * @version (20/5/2018)
 */
public class Team
{
    private String name;//Name of team.
    private int ranking;//The ranking of team.
    private int cardScore;//The total score of cards of the team.
    private ArrayList<Player> playerList;//Collection of players in the team.
    private int totalWin;//Total win times of the team in all games.
    private int totalLost;//Total lost times of the team in all games.
    private int totalDrawn;//Total drawn times of the team in all games.
    
    /**
     * Default constructor for objects of class Team
     */
    public Team()
    {
        name = " ";
        ranking = 0;
        cardScore = 0;
        playerList = new ArrayList<Player>();
        totalWin = 0;
        totalLost = 0;
        totalDrawn = 0;
    }

    /**
     * Constructor for objects of class Team
     * 
     * @param String which is the name of team.
     * @param Integer which is the rank of the name.
     * @param Integer which is the card card score of team.
     * @param ArrayList<Player> whih is the collection of players of the team.
     * @param Integer which is the total win times of the team in all games.
     * @param Integer which is the total lost times of the team in all games.
     * @param Integer which is the total drawn times of the team in all games.
     */
    public Team(String teamName,int rank,int score,ArrayList<Player> list,int winNumber,int lostNumber,int drawnNumber)
    {
        name = teamName;
        ranking = rank;
        cardScore = score;
        playerList = list;
        totalWin = winNumber;
        totalLost = lostNumber;
        totalDrawn = drawnNumber;
    }

    /**
     * Display the players' name,team and goals result.
     */
    public void displayPlayer()
    {
        for (int i = 0;i < playerList.size();i++)
        {
            String playerDetails = String.format("|%-40s|%-8s|",playerList.get(i).getName() + " (" + name + ")","   " + playerList.get(i).getGoals());
            System.out.println(playerDetails);
        }
    }

    /**
     * Display the team result of all games.
     * String.format from google.
     */
    public void displayTeam()
    {
        int totalPlay = totalWin + totalLost + totalDrawn;//How many games a team played totally.
        String teamDetails = String.format("|%-16s|%-8s|%-8s|%-8s|%-8s|%-8s|%-8s|%-15s|",
                                           name,"   " + totalPlay,"   " + totalWin,"   " + totalLost,"   " + 
                                           totalDrawn,"   " + getTeamGoals(),"   " + getTotalPoints(),"      " + cardScore);
        System.out.println(teamDetails);
    }

    /**
     * Get total card score (fair play score).
     * 
     * @return Integer which is the total card score.
     */
    public int getCardScore()
    {
        return cardScore;
    }

    /**
     * Get name of the team.
     * 
     * @return String which is the name of team.
     */
    public String getName()
    {
        return name;
    }

    /**
     * Get the collection of players.
     * 
     * @return ArrayList<Player> which is the collection of players.
     */
    public ArrayList<Player> getPlayerList()
    {
        return playerList;
    }

    /**
     * Get the ranking of team.
     * 
     * @return Integer which is the ranking of team.
     */
    public int getRanking()
    {
        return ranking;
    }

    /**
     * Get total goals of the team.
     * 
     * @return Integer which is the total goals of the team.
     */
    public int getTeamGoals()
    {
        int teamGoals = 0;//Total goals of team.
        for (int i = 0;i < playerList.size();i++)
            teamGoals =  teamGoals + playerList.get(i).getGoals();

        return teamGoals;
    }

    /**
     * Get the total drawn number.
     * 
     * @return Integer which is the total drawn number of team.
     */
    public int getTotalDrawn()
    {
        return totalDrawn;
    }

    /**
     * Get the total lost number.
     * 
     * @return Integer which is the total lost number of team.
     */
    public int getTotalLost()
    {
        return totalLost;
    }

    /**
     * Get the total points.
     * 
     * @return Integer which is the total points of team.
     */
    public int getTotalPoints()
    {
        return totalWin * 3 + totalDrawn;
    }

    /**
     * Get the total win number.
     * 
     * @return Integer which is the total win number of team.
     */
    public int getTotalWin()
    {
        return totalWin;
    }

    /**
     * Test if the player's name is same as the previous players' names.
     * 
     * @param Integer which is the number of player.
     * @param String which is the name of player.
     * @return Boolean true when name same as previous player,false when name not same as previous players' names.
     */
    public boolean ifPlayerNameSame(int playerNumber,String playerName)
    {
        if (playerNumber > 1)
        {
            for (int i = playerNumber - 2;i >= 0;i--)
                if (playerList.get(i).getName().toUpperCase().equals(playerName.toUpperCase()))
                {
                    System.out.println("Name same with other players!");
                    return true;
                }
        }

        return false;
    }

    /**
     * Initialize the collection of players.
     */
    public void initializePlayerList()
    {
        int playerNumber = 2;//Number of players that each team have.
        while (playerList.size() < playerNumber)
            playerList.add(new Player());
    }

    /**
     * Set total card score of team.
     * 
     * @param Integer which is the total card score of team.
     */
    public void setCardScore(int score)
    {
        cardScore = score;
    }

    /**
     * Set name of team
     * 
     * @param String which is the name of the team.
     */
    public void setName(String teamName)
    {
        name = teamName;
    }

    /**
     * Set the collection of players
     * 
     * @param ArrayList<Player> which is the collecton of players.
     */
    public void setPlayerList(ArrayList<Player> list)
    {
        playerList = list;
    }

    /**
     * Set the team ranking.
     * 
     * @param Integer which is the ranking of the team.
     */
    public void setRanking(int rank)
    {
        ranking = rank;
    }

    /**
     * Set the total number of drawn of team.
     * 
     * @param Integer which is the total drawn number of team.
     */
    public void setTotalDrawn(int drawnNumber)
    {
        totalDrawn = drawnNumber;
    }

    /**
     * Set the total number of lost of team.
     * 
     * @param Integer which is the total lost number of team.
     */
    public void setTotalLost(int lostNumber)
    {
        totalLost = lostNumber;
    }

    /**
     * Set the total number of win of team.
     * 
     * @param Integer which is the total win number of team.
     */
    public void setTotalWin(int winNumber)
    {
        totalWin = winNumber;
    }
}

