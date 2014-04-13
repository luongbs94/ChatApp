package uet.chatapp.model;

public class StatusItem {
	private String friend_name;
	private String status_content;
	private String time;
	
	public StatusItem(String friend_name, String status_content,
			String time) {
		this.friend_name = friend_name;
		this.status_content = status_content;
		this.time = time;
	}

	public String getFriend_name() {
		return friend_name;
	}
	
	public void setFriend_name(String friend_name) {
		this.friend_name = friend_name;
	}
	
	public String getstatus_content() {
		return status_content;
	}
	
	public void setstatus_content(String status_content) {
		this.status_content = status_content;
	}
	
	public String gettime() {
		return time;
	}
	
	public void settime(String time) {
		this.time = time;
	}
}
