package uet.chatapp.model.test;

import android.R.menu;
import junit.framework.TestCase;
import uet.chatapp.model.*;

public class MessageItemInMessageBoxTest extends TestCase{

	public MessageItemInMessageBoxTest() {
		super();
	}

	public void setUp() throws Exception{
		super.setUp();
	}
	
	public void testMessage () {
		MessageItemInMessageBox message = new MessageItemInMessageBox(true,"hello");
		assertTrue(message.message.equals("hello"));
	}
	
	public void testLeft () {
		MessageItemInMessageBox message = new MessageItemInMessageBox(true,"hello");
		assertTrue(message.left == true);
	}
}
