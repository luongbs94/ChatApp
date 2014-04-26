package uet.chatapp.model.test;

import junit.framework.TestCase;
import uet.chatapp.model.*;

public class MessageItemInListTest extends TestCase{
	
	private final String DEFAULT_FRIEND_NAME = "long";
	private final String DEFAULT_MESSAGE = "hello";
	private final String DEFAULT_TIME_ARRIVAL = "10pm";

	public MessageItemInListTest() {
		super();
	}

	public void setUp() throws Exception{
		super.setUp();
	}
	

	public void testGetFriendName() {
		MessageItemInList message = new MessageItemInList(DEFAULT_FRIEND_NAME,
														  DEFAULT_MESSAGE,
														  DEFAULT_TIME_ARRIVAL);
		message.setFriend_name(DEFAULT_FRIEND_NAME);
		assertTrue(message.getFriend_name().equals("long"));
	}
	
	public void testGetMessageContent() {
		MessageItemInList message = new MessageItemInList(DEFAULT_FRIEND_NAME,
														  DEFAULT_MESSAGE,
														  DEFAULT_TIME_ARRIVAL);
		message.setMessage_content(DEFAULT_MESSAGE);
		assertTrue(message.getMessage_content().equals("hello"));
	}
	
	public void testGetTimeArrival() {
		MessageItemInList message = new MessageItemInList(DEFAULT_FRIEND_NAME,
														  DEFAULT_MESSAGE,
														  DEFAULT_TIME_ARRIVAL);
		message.setTime_arrival(DEFAULT_TIME_ARRIVAL);
		assertTrue(message.getTime_arrival().equals("10pm"));
	}
}
