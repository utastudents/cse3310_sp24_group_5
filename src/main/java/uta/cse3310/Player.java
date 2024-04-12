package uta.cse3310; 
import java.util.Objects; 
public class Player { 
    private String nick; 
    private int score; 
    public Player(String nick) { 
        this.nick = nick; 
        this.score = 0; 
    }

    public String getNick() { // allow other classes to retrieve the player's nickname.
        return nick; 
    }

    public void setNick(String nick) { // allows other classes to set or change the player's nickname.
        this.nick = nick; 
    }

    public int getScore() { // allows other classes to access the player's score.
        return score; 
    }

    public void setScore(int score) { // allows other classes to set or change the player's score.
        this.score = score; 
    }

    public void addScore(int points) { // allows for incrementing the score by a specific number of points.
        this.score += points; 
    }

    @Override 
    public boolean equals(Object o) { 
        if (this == o) return true; 
        if (o == null || getClass() != o.getClass()) return false; 
        Player player = (Player) o; 
        return Objects.equals(nick, player.nick); 
    }

    @Override 
    public int hashCode() { 
        return Objects.hash(nick); 
    }

    @Override 
    public String toString() { 
        return "Player{" +
               "nick='" + nick + '\'' +
               ", score=" + score +
               '}'; 
    }
}
