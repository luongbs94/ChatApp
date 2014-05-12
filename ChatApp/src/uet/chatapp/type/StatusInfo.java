package uet.chatapp.type;

public class StatusInfo {
	public static final String STATUS_LIST = "statusList";
	public static final String USERNAME = "username";
	public static final String STATUSTEXT = "text";
	
	public String text;
	public String userMame;
	
	public String getName(){
		return userMame;
	}
	
	public String getText(){
		return text;
	}
}
