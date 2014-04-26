package uet.chatapp.model.test;

import junit.framework.TestCase;
import uet.chatapp.model.*;

public class StatusItemTest extends TestCase{
	
	private final String DEFAULT_FRIEND_NAME = "long";
	private final String DEFAULT_STATUS = "hello";
	private final String DEFAULT_TIME_ARRIVAL = "10pm";

	public StatusItemTest() {
		super();
	}

	public void setUp() throws Exception{
		super.setUp();
	}
	
	public void testGetFriendName() {
		StatusItem status = new StatusItem(DEFAULT_FRIEND_NAME,
									   	   DEFAULT_STATUS,
									       DEFAULT_TIME_ARRIVAL);
		status.setFriend_name(DEFAULT_FRIEND_NAME);
		assertTrue(status.getFriend_name().equals("long"));
	}
	
	public void testGetMessageContent() {
		MessageItemInList status = new MessageItemInList(DEFAULT_FRIEND_NAME,
														 DEFAULT_STATUS,
														 DEFAULT_TIME_ARRIVAL);
		status.setMessage_content(DEFAULT_STATUS);
		assertTrue(status.getMessage_content().equals("hello"));
	}
	
	public void testGetTimeArrival() {
		MessageItemInList status = new MessageItemInList(DEFAULT_FRIEND_NAME,
														 DEFAULT_STATUS,
														 DEFAULT_TIME_ARRIVAL);
		status.setTime_arrival(DEFAULT_TIME_ARRIVAL);
		assertTrue(status.getTime_arrival().equals("10pm"));
	}
}
