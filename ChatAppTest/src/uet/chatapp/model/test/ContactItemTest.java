package uet.chatapp.model.test;

import junit.framework.TestCase;
import uet.chatapp.model.*;

public class ContactItemTest extends TestCase{
	
	public ContactItemTest() {
		super();
	}

	public void setUp() throws Exception {
		super.setUp();
	}

	public void testGetContactName() {
		ContactItem contactItem = new ContactItem("long");
		contactItem.setContact_name("long");
		assertTrue(contactItem.getContact_name().equals("long"));
		assertFalse(contactItem.getContact_name().equals("luong"));
	}
	
}
