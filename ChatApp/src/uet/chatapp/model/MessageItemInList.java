package uet.chatapp.model;

public class MessageItemInList {
	private String friend_name;
	private String message_content;
	private String time_arrival;
	
	public MessageItemInList(String name, String content, String time){
		friend_name = name;
		message_content = content;
		time_arrival = time;
	}

	public String getFriend_name() {
		return friend_name;
	}

	public void setFriend_name(String friend_name) {
		this.friend_name = friend_name;
	}

	public String getMessage_content() {
		return message_content;
	}

	public void setMessage_content(String message_content) {
		this.message_content = message_content;
	}

	public String getTime_arrival() {
		return time_arrival;
	}

	public void setTime_arrival(String time_arrival) {
		this.time_arrival = time_arrival;
	}
	
	
}
