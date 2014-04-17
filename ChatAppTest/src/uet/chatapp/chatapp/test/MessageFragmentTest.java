package uet.chatapp.chatapp.test;

import com.robotium.solo.Solo;

import uet.chatapp.chatapp.MainScreen;
import uet.chatapp.chatapp.MessageBoxActivity;
import uet.chatapp.chatapp.R;
import android.app.Activity;
import android.support.v4.view.ViewPager;
import android.test.ActivityInstrumentationTestCase2;
import android.view.View;
import android.widget.ListView;

public class MessageFragmentTest extends ActivityInstrumentationTestCase2<MainScreen> {
	private Solo solo;
	
	public MessageFragmentTest() {
		super(MainScreen.class);
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
	
	public void testChangeToMessageBoxActivityWhenClickToAMessage(){	
		// Check pre-conditions
		solo.waitForActivity("MainScreen");
		solo.assertCurrentActivity("MainScreen expected", MainScreen.class);	
		
		// Change to message tab
		solo.clickOnText("Message");
		solo.sleep(200);
		
		// Get view of message fragment
		Activity mainActivity = this.getActivity();
		ViewPager viewPager = (ViewPager) mainActivity.findViewById(R.id.pager);
		View MessageFragView = viewPager.getFocusedChild();
		
		// Get view of a message
		ListView listMessage = (ListView) MessageFragView.findViewById(R.id.message_list);
		View messageView = listMessage.getChildAt(0);
		
		solo.clickOnView(messageView);
		solo.waitForActivity("MessageBoxActivity");
		solo.assertCurrentActivity("MessageBoxActivity Expected", MessageBoxActivity.class);
		solo.sleep(200);
	}
}
