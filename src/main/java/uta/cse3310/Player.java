package uta.cse3310;


public class Player 
{
    
    private String nick=new String();
    private int score;
    private PlayerStatus status; // Using enum for clearer status management
    private int totalPoints;
    private int gameWins; // Renamed for clarity
    private String color;
    public enum PlayerStatus 
    {
        ACTIVE, INACTIVE, DISCONNECTED
    }

    // Constructor
    public Player(String nick) 
    {
        this.nick = nick;
        this.score = 0;
        this.status = PlayerStatus.ACTIVE;
        this.totalPoints = 0;
        this.gameWins = 0;
    }

    public Player()
    {
        this.score = 0;
        this.status = PlayerStatus.ACTIVE;
        this.totalPoints = 0;
        this.gameWins = 0;   
    }
    // Getter for nickname
    public String getNick() 
    {
        return nick;
    }

    // Setter for nickname
    public void setNick(String nick) 
    {
        this.nick = nick;
    }

    // Setter for score
    public void setScore(int score) 
    {
        this.score = score;
    }

    // Getter for score
    public int getScore() 
    {
        return score;
    }

    // Method to increase player score
    public void increaseScore(int points)
    {
        if (points >= 0) {
            this.score += points;
            this.totalPoints += points;
        } else {
            throw new IllegalArgumentException("Points must be non-negative.");
        }
    }

    // Getter for player status
    public PlayerStatus getStatus() 
    {
        return status;
    }

    // Setter for status
    public void setStatus(PlayerStatus status) 
    {
        this.status = status;
    }

    // Getter for total game wins
    public int getGameWins() 
    {
        return gameWins;
    }

    // Method to increment the number of games won
    public void addGameWin() 
    {
        this.gameWins++;
    }


    @Override
    public String toString() 
    {
        return "Player{" +
               "nick='" + nick + '\'' +
               ", score=" + score +
               ", status=" + status +
               ", totalPoints=" + totalPoints +
               ", gameWins=" + gameWins +
               '}';
    }

    public String getColor()
    {
        return color;
    }
}
