package uet.chatapp.model;

public class ContactItem {
	public ContactItem(String contact_name) {
		this.contact_name = contact_name;
	}

	private String contact_name;
	
	
	public String getContact_name() {
		return contact_name;
	}

	public void setContact_name(String contact_name) {
		this.contact_name = contact_name;
	}
	
}
