package uet.chatapptest.fragment;

import uet.chatapp.activity.*;
import android.app.Activity;
import android.support.v4.view.ViewPager;
import android.test.ActivityInstrumentationTestCase2;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import uet.chatapp.chatapp.R;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;











import com.robotium.solo.Solo;

public class ContactFragmentTest  extends ActivityInstrumentationTestCase2<MainScreenActivity> {
	
	public static final String CONTACT = "luongnguyen";

	// Solo is a class in Robotium lib support android test
	private Solo solo;
	
	
	public ContactFragmentTest() {
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

	/**
	 * Test if it is possible to add a contact.
	 */	
	public void testAddContact(){
		// Check pre-conditions
		solo.waitForActivity("MainScreen");
		solo.assertCurrentActivity("MainScreen expected", MainScreenActivity.class);

		// Change to contact tab
		solo.clickOnText("Contact");
		solo.sleep(200);

		// Get view of contact fragment
		Activity mainActivity = this.getActivity();
		ViewPager viewPager = (ViewPager) mainActivity.findViewById(R.id.pager);
		View ContactFragView = viewPager.getFocusedChild();
		
		// Add a contact
		EditText editText = (EditText) ContactFragView.findViewById(R.id.contact_edit_text);
		solo.enterText(editText, CONTACT);
		solo.clickOnButton("Add");
		solo.sleep(200);
		
		// Check if contact is added in contact list
		ListView listContact = (ListView) ContactFragView.findViewById(R.id.contact_list);
		View newContactView = listContact.getChildAt(0);
		TextView contactName = (TextView) newContactView.findViewById(R.id.contact_name);
		assertEquals(contactName.getText().toString(), CONTACT);
	}
	
	/**
	 * Test if it change to MessageBoxActivity when click to a contact.
	 */		
	public void testClickToAContact(){	
		// Check pre-conditions
		solo.waitForActivity("MainScreen");
		solo.assertCurrentActivity("MainScreen expected", MainScreenActivity.class);

		// Change to contact tab
		solo.clickOnText("Contact");
		solo.sleep(200);

		// Get view of contact fragment
		Activity mainActivity = this.getActivity();
		ViewPager viewPager = (ViewPager) mainActivity.findViewById(R.id.pager);
		View ContactFragView = viewPager.getFocusedChild();
		
		// Get view of a contact
		ListView listContact = (ListView) ContactFragView.findViewById(R.id.contact_list);
		View contactView = listContact.getChildAt(0);
		
		// Check if it change to MessageBoxActivity when click to a contact
		solo.clickOnView(contactView);
		solo.waitForActivity("MessageBoxActivity");
		solo.sleep(200);
		solo.assertCurrentActivity("MessageBoxActivity Expected", MessageBoxActivity.class);
	}	
}
