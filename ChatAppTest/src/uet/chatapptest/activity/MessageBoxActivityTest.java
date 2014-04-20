package uet.chatapptest.activity;

import uet.chatapp.activity.*;
import android.test.ActivityInstrumentationTestCase2;

import com.robotium.solo.Solo;

public class MessageBoxActivityTest  extends 
			ActivityInstrumentationTestCase2<MainScreenActivity> {
	
	public static final String MESSAGE = "hello, how are you";
	
	// Solo is a class in Robotium lib support android test	
	private Solo solo;
	
	
	public MessageBoxActivityTest() {
		super(MainScreenActivity.class);
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
	
	public void testSendMessage(){
		// Check current activity
		//solo.assertCurrentActivity("MessageBoxActivity expected", MessageBoxActivity.class);

//		// Get view of contact fragment
//		Activity mainActivity = this.getActivity();
//		ViewPager viewPager = (ViewPager) mainActivity.findViewById(R.id.pager);
//		View ContactFragView = viewPager.getFocusedChild();
//		
//		// Add a contact
//		EditText editText = (EditText) ContactFragView.findViewById(R.id.contact_edit_text);
//		solo.enterText(editText, CONTACT);
//		solo.clickOnButton("Add");
//		solo.sleep(200);
//		
//		// Check if contact is added in contact list
//		ListView listContact = (ListView) ContactFragView.findViewById(R.id.contact_list);
//		View newContactView = listContact.getChildAt(0);
//		TextView contact_name = (TextView) newContactView.findViewById(R.id.contact_name);
//		assertEquals(contact_name.getText().toString(), CONTACT);
	}
}
