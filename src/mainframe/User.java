package mainframe;

public class User {

	String username;
	String name;
	
	public User(String username,String name)
	{
		this.username = username;
		this.name = name;
	}
	
	public String getName()
	{
		return name;
	}
	
	public String getUserName()
	{
		return username;
	}
	
}
