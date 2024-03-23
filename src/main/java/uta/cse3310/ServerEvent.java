package uta.cse3310;

public class ServerEvent 
{
    private String type;
    private Object data;
    
    public ServerEvent(String type, Object data) 
    {
       this.type = type;
       this.data = data;
    }

    public String getType() 
    {
       return type;
    }

    public Object getData() 
    {
       return data;
    }
}
