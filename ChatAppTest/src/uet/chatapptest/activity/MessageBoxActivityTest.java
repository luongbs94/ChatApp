package uet.chatapptest.activity;

import uet.chatapp.activity.*;
import uet.chatapp.chatapp.R;
import android.test.ActivityInstrumentationTestCase2;
import android.widget.EditText;
import com.robotium.solo.Solo;

public class MessageBoxActivityTest  extends
			ActivityInstrumentationTestCase2<MessageBoxActivity> {
	
	public static final String MESSAGE = "hello, how are you";
	
	// Solo is a class in Robotium lib support android test	
	private Solo solo;
	
	
	public MessageBoxActivityTest() {
		super(MessageBoxActivity.class);
	}

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		solo = new Solo(getInstrumentation(), getActivity());
	}
	
	@Override
	public void tearDown() throws Exception {
		// finish all activities that have been opened during test execution.
		solo.finishOpenedActivities();
	}
	
	/**
	 * Test if it is possible to send message.
	 * 
	 * Compose a message and click button send
	 * Check if message is occur in message box in the right side
	 */			
	public void testSendMessage(){
		// Check current activity
		solo.assertCurrentActivity("MessageBoxActivity expected", MessageBoxActivity.class);
		// Enter a message
		EditText messageEditText = (EditText) solo.getView(R.id.edit_message);
		solo.enterText(messageEditText, MESSAGE);
		solo.clickOnButton("Send");
		solo.sleep(100);
		
		// Check if message is sent and displayed in list message
		boolean messageSent = solo.searchText(MESSAGE);
		assertTrue(messageSent);
	}
}
