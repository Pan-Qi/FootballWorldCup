import java.util.Scanner;
import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
/** 
 * The game class is used to describe the priciples of game football world cup.
 * The method named startGame() is the main method.
 * Call startGame() method to start the whole program.
 * The game rules follow assignment 2 requirements.
 * Assumption: Name of team can't be longer than 12 characters.
 *
 * @author (Pan Qi)
 * @version (21/05/2018)
 */
public class Game
{
    private ArrayList<Team> teamList;//Collection of teams.

    /**
     * Default constructor for objects of class Game
     */
    public Game()
    {
        teamList = new ArrayList<Team>();
    }

    /**
     * Constructor for objects of class Game
     * 
     * @param ArrayList<Team> which is the collection of teams.
     */
    public Game(ArrayList<Team> list)
    {
        teamList = list;      
    }

    /**
     * Randomly generate if a team have a red card or yellow card in the game.
     * Then output the result.
     * 
     * @param RandomGoalsGenerator which is an object of RandomGoalsGenerator class.
     * @param Team which is an object of Team class that indicate which award cards to which team.
     */
    private void awardTeamCards(RandomGoalsGenerator randomGenerator,Team team)
    {
        if (randomGenerator.cardGenerator(4) == 0)      
        {
            team.setCardScore(team.getCardScore() + 1);
            System.out.println("Cards awarded: " + team.getName() + " - 1 yellow card");
        }

        if (randomGenerator.cardGenerator(16) == 0)
        {
            team.setCardScore(team.getCardScore() + 2);      
            System.out.println("Cards awarded: " + team.getName() + " - 1 red card");
        }
    }

    /**
     * Display the game result of each team.
     */
    private void displayCupResult()
    {
        System.out.println("\u000c");       
        printDividingLine(88);
        String header = String.format("|%-16s|%-8s|%-8s|%-8s|%-8s|%-8s|%-8s|%-15s|"," ","Played","  Won","  Lost"," Drawn"," Goals"," Points","Fair Play Score");
        //Header of outprint table when chose C.
        System.out.println(header);
        printDividingLine(88); 
        for (int i = 0;i < teamList.size();i++)
        {
            teamList.get(i).displayTeam();
            printDividingLine(88);
        }
    }

    /**
     * Display the final awards the the whole game.
     */
    private void displayFinalAwards(String teamName)
    {
        System.out.println("\u000c");
        ArrayList<StringBuffer> finalAwards = setFinalAwards(teamName);//Collection of final award result.
        for (int i = 0;i < finalAwards.size();i++)
            System.out.println(finalAwards.get(i));
    }

    /**
     * Display the game result of each game.
     * 
     * @param RandomGoalsGenerator which is an object of RandomGoalsGenerator class.
     * @param Team which is the team who played in this game.
     * @param Team which is the team who played in this game.
     * @param Collection of intgers which is the collection of goals of teams played in the game.
     */
    private void displayGameResult(RandomGoalsGenerator randomGenerator,Team team1,Team team2,int[] teamGoals)
    {
        System.out.println("Game result: " + team1.getName() + " " + teamGoals[0] + " VS. " + team2.getName() + " " + teamGoals[1]);
        awardTeamCards(randomGenerator,team1);
        awardTeamCards(randomGenerator,team2);
    }

    /**
     * Display the name, team and total goals of each player.
     */
    private void displayPlayer()
    {
        System.out.println("\u000c");
        System.out.println("--------------Goals of each player!---------------");
        printDividingLine(50);
        String playerHeader = String.format("|%-40s|%-8s|","                   Player","Goals");//Header of player table of option D.
        System.out.println(playerHeader);
        printDividingLine(50);
        for (int i = 0;i < teamList.size();i++)
        {
            teamList.get(i).displayPlayer();
            printDividingLine(50);
        }
    }

    /**
     * Select which team get fair play award,and add the team name to String Buffer.
     * 
     * @param ArrayList<StringBuffer> which is the record of awards.
     */
    private void fairPlayAward(ArrayList<StringBuffer> finalAward)
    {
        int minFairPlayScore = 100;//Minimum fair play score of all teams.
        for (int i = 0;i < teamList.size();i++)  
            if (teamList.get(i).getCardScore() < minFairPlayScore)
                minFairPlayScore = teamList.get(i).getCardScore();
 
        for (int i = 0;i < teamList.size();i++) 
            if (teamList.get(i).getCardScore() == minFairPlayScore)
                finalAward.get(3).append(teamList.get(i).getName() + " ");        
    }

    /**
     * Get collection of teams.
     * 
     * @return ArrayList<Team> which is the collection of teams.
     */
    public ArrayList<Team> getTeamList()
    {
        return teamList;
    }

    /**
     * Select which player get golden boot award,and write the player's name in the record.
     * 
     * @param ArrayList<StringBuffer> which is the record of awards.
     */
    private void goldenBootAward(ArrayList<StringBuffer> finalAward)
    {
        int maxPlayerGoals = 0;//Maximum player goals of all teams.
        for (int i = 0;i < teamList.size();i++) 
            for (int j = 0;j < teamList.get(i).getPlayerList().size();j++)
                if (teamList.get(i).getPlayerList().get(j).getGoals() > maxPlayerGoals)
                    maxPlayerGoals = teamList.get(i).getPlayerList().get(j).getGoals();

        for (int i = 0;i < teamList.size();i++) 
            for (int j = 0;j < teamList.get(i).getPlayerList().size();j++)
                if (teamList.get(i).getPlayerList().get(j).getGoals() == maxPlayerGoals)    
                    finalAward.get(2).append(teamList.get(i).getPlayerList().get(j).getName() + " from " + teamList.get(i).getName() + " ");
    }

    /**
     * Ask user to press enter to continue.
     */
    private void ifContinue()
    {
        String input = " ";//User input.
        System.out.println("");
        System.out.println("Press enter to continue");
        Scanner console = new Scanner(System.in);
        input = console.nextLine();
        while (input.length() != 0)
        {
            System.out.println("Wrong input,Please press enter to continue");
            input = console.nextLine();                
        }
    }

     /**
     * Test if the player name is valid.
     * 
     * @param String which is the player's name.
     * @return Boolean true when name valid,false when name invalid.
     */
    private boolean ifNameValid(String playerName)
    {
        int hyphenNumber = 0;//The number of hyphen in the player name.
        int alphabetNumber = 0;//The number of alphabet in the player name.
        for (int i = 0;i < playerName.length();i++)
        {
            if (playerName.toUpperCase().charAt(i) >= 'A' && playerName.toUpperCase().charAt(i) <= 'Z')       
                alphabetNumber++;

            if (playerName.charAt(i) == '-')
                hyphenNumber++;
        }           
                
        if (alphabetNumber + hyphenNumber != playerName.length())
        {
            System.out.println("Error!Player name can contain only alphabetical characters and hyphen!");
            return false;
        }

        if (alphabetNumber < 2)
        {
            System.out.println("Error!At least two alphabetical characters should be used!");
            return false;
        }
        
        if (alphabetNumber + hyphenNumber > 20)
        {
            System.out.println("Error!Name length can't be longer than 20!");
            return false;            
        }
        
        if (hyphenNumber > 1 || playerName.charAt(0) == '-' || playerName.charAt(playerName.length() - 1) == '-')
        {
            System.out.println("Error!Hyphen can't be at begin or end of the name!At most one hyphen can be used!");
            return false;
        }
           
        return true;     
    }
    
    /**
     * Ask user if clear the previous record and replay the game.
     * 
     * @return Boolean true if user want replay,false if user quit replay.
     */
    private boolean ifReplay()
    {
        System.out.println("If you replay the preliminary stage,the previous record will be cleared");
        System.out.println("Enter 'Y' to continue,Enter 'N' go back to menu");
        Scanner console = new Scanner(System.in);
        String input = console.nextLine().trim().toUpperCase();//User input.
        while (input.length() != 1 || input.charAt(0) != 'Y' && input.charAt(0) != 'N')
        {
            System.out.println("Wrong input.Plesae choose 'Y' OR 'N'");
            input = console.nextLine().trim().toUpperCase();
        }

        if (input.charAt(0) == 'Y')
            return true;
   
        return false;
    }
    
    /**
     * Test if team details read from file is valid.(Team's name can't be either same or empty,
     * rankings can't be same and must between 1 to 4.)
     * 
     * @return Boolean true when details valid,false when details invalid.
     */
    private boolean ifTeamDetailsValid()
    {
        for (int i = 0;i < teamList.size();i++)
            for (int j = i + 1;j < teamList.size();j++)
                if (teamList.get(i).getName().equals(teamList.get(j).getName()) || teamList.get(i).getRanking() == teamList.get(j).getRanking())
                    return false;       
        
        for (int i = 0;i < teamList.size();i++)
            if (teamList.get(i).getName().length() < 2 || teamList.get(i).getName().length() > 12 || teamList.get(i).getRanking() < 1 || 
                teamList.get(i).getRanking() > teamList.size())
                return false;  
                
        return true;            
    }
    
    /**
     * Play the final stage of the game.
     * 
     * @return String which is the name of the team who win the final stage.
     */
    private String playFinal()
    {
        System.out.println("\u000c");
        RandomGoalsGenerator randomGenerator = new RandomGoalsGenerator();
        int[] teamGoals = new int[2];//Collection of goals of two teams who played in the game.
        playGame(teamList.get(0),teamList.get(1),teamGoals,randomGenerator);
        setGameResult(teamList.get(0),teamList.get(1),teamGoals,randomGenerator);
        if (teamGoals[0] == teamGoals[1])
            playPenaltyShootOut(teamGoals,randomGenerator);

        System.out.println("            Final game result!");
        displayGameResult(randomGenerator,teamList.get(0),teamList.get(1),teamGoals);
        if (teamGoals[0] > teamGoals[1])
            return teamList.get(0).getName();

        return teamList.get(1).getName();
    }

    /**
     * Game play rules.
     * 
     * @param Team which is the team which play in this round.
     * @param Team which is the team which play in this round.
     * @param int[] which is the collection of goals that each team get in this game.
     * @param RandomGoalsGenerator which can randomly generate goals of each team.
     */
    private void playGame(Team team1,Team team2,int[] teamGoals,RandomGoalsGenerator randomGenerator)
    {
        teamGoals[0] = randomGenerator.generateTeamGoals(0);
        teamGoals[1] = randomGenerator.generateTeamGoals(Math.abs(team1.getRanking() - team2.getRanking()));
        if (team1.getRanking() > team2.getRanking())
        {
            int goals = teamGoals[1];//Team goals.
            teamGoals[1] = teamGoals[0];
            teamGoals[0] = goals;
        }
    }

    /**
     * Play penalty shoot out stage.
     * 
     * @param int[] which is the collection of goals that each team get in this game.
     * @param RandomGoalsGenerator which can randomly generate goals of each team.
     */
    private void playPenaltyShootOut(int[] teamGoals,RandomGoalsGenerator randomGenerator)
    {
        teamGoals[0] = randomGenerator.generatePlayerGoals(5) + teamGoals[0];
        teamGoals[1] = randomGenerator.generatePlayerGoals(5) + teamGoals[1];
        while (teamGoals[0] == teamGoals[1])
        {
            teamGoals[0] = teamGoals[0] + randomGenerator.generatePlayerGoals(1);
            teamGoals[1] = teamGoals[1] + randomGenerator.generatePlayerGoals(1);
        }
    }

    /**
     * Play preliminary stage of the game.
     */
    private void playPreStage()
    {
        System.out.println("\u000c");
        RandomGoalsGenerator randomGenerator = new RandomGoalsGenerator();
        int gameNumber = 1;//The number of game that has been played.
        for (int i = 0;i < teamList.size();i++)
        {
            for (int j = 0;j < teamList.get(i).getPlayerList().size();j++)
                teamList.get(i).getPlayerList().get(j).setGoals(0);
                
            teamList.get(i).setCardScore(0);
            teamList.get(i).setTotalWin(0);
            teamList.get(i).setTotalLost(0);
            teamList.get(i).setTotalDrawn(0);
        }

        System.out.println("Preliminary stage game result!");
        for (int i = 0;i < teamList.size();i++)
        {
            for (int j = i + 1;j < teamList.size();j++)
            {
                int[] teamGoals = new int[2];
                playGame(teamList.get(i),teamList.get(j),teamGoals,randomGenerator);
                System.out.println("              Game " + gameNumber);
                displayGameResult(randomGenerator,teamList.get(i),teamList.get(j),teamGoals);
                setGameResult(teamList.get(i),teamList.get(j),teamGoals,randomGenerator);
                gameNumber++;
                ifContinue();
            }
        }
        
        System.out.println("Preliminary stage game finished!");
    }

    /**
     * Print hyphen as a line of the output table.
     * 
     * @param Integer which is the length of dividing line.
     */
    private void printDividingLine(int lineLength)
    {
        StringBuffer dividingLine = new StringBuffer("-");
        for (int i = 1;i < lineLength;i++)
            dividingLine.append('-');

        System.out.println(dividingLine);
    }

    /**
     * Output welcome to user.
     */
    private void printWelcome()
    {
        System.out.println("***********************************************************");
        System.out.println("                      Welcome to FIFA");
        System.out.println("***********************************************************");
    }

    /**
     * Rank the order of teams in the teamList with higher points higher position,
     * if same points higher team goals higher position.
     */
    private void rankTeam()
    {
        for (int i = 0;i < teamList.size();i++)
        {
            for (int j = i + 1;j < teamList.size();j++)
            {
                if (teamList.get(j).getTotalPoints() > teamList.get(i).getTotalPoints() || 
                    (teamList.get(j).getTotalPoints() == teamList.get(i).getTotalPoints() &&
                    teamList.get(j).getTeamGoals() >= teamList.get(i).getTeamGoals()))
                {
                    Collections.swap(teamList,i,j);
                }
            }
        }
    }

    /**
     * Read team details from file.
     * 
     * @param String which is the name of the file that will be read.
     */
    private void readTeamDetails(String fileName)
    {       
        try
        {
            FileReader inputFile = new FileReader(fileName);//FileReader object.
            try
            {
                Scanner parser = new Scanner(inputFile);
                for (int i = 0;i < teamList.size();i++)
                {
                    String teamDetails = parser.nextLine();
                    String[] details = teamDetails.split(",");
                    teamList.get(i).setName(details[0].trim());
                    teamList.get(i).setRanking(Integer.parseInt(details[1]));
                }
            }
            catch(Exception exception)
            {
                System.out.println(fileName + " content illegal.Please check file content!");
            }
            finally
            {
                inputFile.close();
            } 
        }
        catch(FileNotFoundException exception)
        {
            System.out.println(fileName + " not found");
        }
        catch(IOException exception)
        {
            System.out.println("Unexpected I/O exception occurs.Please use another file");
        }
    }

    /**
     * Collection of final awards.
     * 
     * @param String which is the name of team who win the cup.
     */
    private ArrayList<StringBuffer> setFinalAwards(String teamName)
    {
        ArrayList<StringBuffer> finalAwards = new ArrayList<StringBuffer>();//Collection of final award statements.
        finalAwards.add(new StringBuffer("-------------FINAL AWARDS-------------"));
        finalAwards.add(new StringBuffer("Football World Cup Winner: " + teamName));
        finalAwards.add(new StringBuffer("Golden Boot Award: "));
        goldenBootAward(finalAwards);
        finalAwards.add(new StringBuffer("Fair Play Award: "));
        fairPlayAward(finalAwards);     
        return finalAwards;
    }

    /**
     * Set game result of each team.
     * 
     * @param Team which is the team who played in this game.
     * @param Team which is the team who played in this game.
     * @param int[] which is the collection of goals of teams played in the game.
     * @param RandomGoalsGenerator which is an object of RandomGoalsGenerator class.
     */
    private void setGameResult(Team team1,Team team2,int[] teamGoals,RandomGoalsGenerator randomGenerator)
    {
        if (teamGoals[0] > teamGoals[1])
        {
            team1.setTotalWin(team1.getTotalWin() + 1);
            team2.setTotalLost(team2.getTotalLost() + 1);
        }

        if (teamGoals[0] < teamGoals[1])
        {
            team1.setTotalLost(team1.getTotalLost() + 1);
            team2.setTotalWin(team2.getTotalWin() + 1);
        }

        if (teamGoals[0] == teamGoals[1])
        {
            team1.setTotalDrawn(team1.getTotalDrawn() + 1);
            team2.setTotalDrawn(team2.getTotalDrawn() + 1);
        }

        setPlayerGoals(team1,teamGoals[0],randomGenerator.generatePlayerGoals(teamGoals[0]));
        setPlayerGoals(team2,teamGoals[1],randomGenerator.generatePlayerGoals(teamGoals[1]));
    }

    /**
     * Set goals of each player of the team.
     * 
     * @param Team whiich is the team whose players' goals will be set.
     * @param Integer which is the goals of the team.
     * @param Inteeger which is the goals of one player.
     */
    private void setPlayerGoals(Team team,int teamGoals,int playerGoals)
    {
        team.getPlayerList().get(0).setGoals(playerGoals + team.getPlayerList().get(0).getGoals());
        team.getPlayerList().get(1).setGoals(teamGoals - playerGoals + team.getPlayerList().get(1).getGoals());
    }

    /**
     * Let user input the name of each player.
     */
    private void setPlayerName()
    {
        System.out.println("----------Please set player's names of each team!----------");
        Scanner console = new Scanner(System.in);
        for (int i = 0;i < teamList.size();i++)
        {
            for (int j = 1;j <= teamList.get(i).getPlayerList().size();j++)
            {
                int inputTimes = 1;//The time of user input player names of each player.
                printDividingLine(60);
                System.out.println("*Please input the player" + j + "'s name from " + teamList.get(i).getName());
                String playerName = console.nextLine().trim();
                while(inputTimes < 2 && (!ifNameValid(playerName) || teamList.get(i).ifPlayerNameSame(j,playerName)))
                {
                    System.out.println("^^Please enter a new name!");
                    playerName = console.nextLine().trim();
                    inputTimes++;
                }

                if (ifNameValid(playerName) && !teamList.get(i).ifPlayerNameSame(j,playerName))
                {
                    teamList.get(i).getPlayerList().get(j - 1).setName(playerName);
                    System.out.println("**Name valid!Player" + j + "'s name set!");
                }
                else
                {
                    teamList.get(i).getPlayerList().get(j - 1).setName("player" + j + "-" + teamList.get(i).getName());
                    System.out.println("Name invalid.Default name (" + "player" + j + "-" + teamList.get(i).getName() + ") set！");
                }
            }
        }

        System.out.println("All players' names set!");
        ifContinue();
    }

    /**
     * Set team details and initialize teamList and playerList.
     */
    private void setTeamDetails()
    {
        int teamNumber = 4;//The number of teams in the team collection.
        while(teamList.size() < teamNumber)
            teamList.add(new Team());
            
        for (int i = 0;i < teamNumber;i++)
            teamList.get(i).initializePlayerList();
            
        readTeamDetails("teams.txt");
        
        while (!ifTeamDetailsValid())
        {
            System.out.println("File content invalid!Please give another file name to reread team details!");
            Scanner console = new Scanner(System.in);
            String fileName = console.nextLine().trim();
            readTeamDetails(fileName);
        }
        
        System.out.println("File read succeed.Team details updated!");
        ifContinue();
    }
    
    /**
     * Set teamList.
     * 
     * @param ArrayList<Team> which is the collection of teams.
     */
    public void setTeamList(ArrayList<Team> list)
    {   
        teamList = list;
    }

    /**
     * This method is used to start playing the whole game.
     */
    public void startGame()
    {
        printWelcome();
        setTeamDetails();
        boolean playPreStage = false;//If user have played preliminary stage.
        boolean playFinalStage = false;//If user have played final stage.
        String cupWinner = "";//The team name of cup winner.
        setPlayerName();
        char option = 'N';//User's option.
        while (option != 'X')
        {
            option = Menu.getChoice();         
            if (option == 'A' && (!playPreStage || ifReplay()))
            {
                playPreStage();
                rankTeam();
                playPreStage = true;
                playFinalStage = false;
            }

            if (option == 'B' && playFinalStage)
                System.out.println("Final Stage have been played this round!");
            
            if (option == 'B' && !playFinalStage && playPreStage)
            {
                cupWinner = playFinal();
                rankTeam();
                playFinalStage = true;
            }
            
            if (option == 'C' && playPreStage)
                displayCupResult();    

            if (option == 'D' && playPreStage)
                displayPlayer();
                
            if (!playPreStage && (option == 'B' || option == 'C' || option == 'D'))
                System.out.println("You should play the preliminary stage first!");
                
            if (option == 'E' && !playFinalStage)
                System.out.println("Final stage not played this round.Please play final stage first!");

            if (option == 'E' && playFinalStage)
                displayFinalAwards(cupWinner);
                
            ifContinue();
        }
        
        if (playFinalStage)     
            writeCupResult(cupWinner); 
        else
            System.out.println("Final stage havn't been played,no new record written in the file!");
               
        System.out.println("Bye Bye!");    
    }

    /**
     * Write cup result to a file called "statistics.txt".
     * 
     * @param String which is the name of team who win the cup.
     */
    private void writeCupResult(String teamName)
    {
        ArrayList<StringBuffer> finalAwards = setFinalAwards(teamName);
        try
        {
            PrintWriter outputFile = new PrintWriter("statistics.txt");            
            for (int i = 0;i < finalAwards.size();i++)
                outputFile.println(finalAwards.get(i)); 

            outputFile.close();
        }
        catch(IOException exception)
        {
            System.out.println("I/O error happend when write file");
        }
        System.out.println("Result written succeed!");
    }    
}
