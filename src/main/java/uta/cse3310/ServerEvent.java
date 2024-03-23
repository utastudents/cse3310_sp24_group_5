package uta.cse3310;

public class ServerEvent 
{
    private String type;
    private Object data;

    //constructor
    public ServerEvent(String type, Object data) 
    {
       this.type = type;
       this.data = data;
    }

    //method getType that returns a string value
    public String getType() 
    {
       return type;
    }

    //method getData that returns an object value
    public Object getData() 
    {
       return data;
    }
}
