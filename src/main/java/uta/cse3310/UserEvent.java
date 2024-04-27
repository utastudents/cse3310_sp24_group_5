package uta.cse3310;
// User events are sent from the webpage to the server

public class UserEvent {
    public String gameId;//id of the game in which the event took place
    public Player player=new Player();//the player that made the event
    public int cell;//refers to the cell containing a selected letter
    public int action; //click=0,hover=1,release=2
    /*  in the script section of index.html there will
        a function called select word that uses 3 other
        functions:buttonClick,buttonhover,and button release
        These 3 functions when executed one after the other
        in order will create the selecting functionality

    */
   public String toString()
   {
        return "action: "+action;
   }
}
