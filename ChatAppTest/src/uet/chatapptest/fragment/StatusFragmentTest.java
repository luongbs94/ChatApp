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

public class StatusFragmentTest  extends ActivityInstrumentationTestCase2<MainScreenActivity> {
	
	public static final String STATUS_CONTENT = "Testing status fragment";
	
	// Solo is a class in Robotium lib support android test
	private Solo solo;
	
	
	public StatusFragmentTest() {
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
	 * Test if it is possible to post a status.
	 */				
	public void testPostStatus(){
		// Get view of status fragment
		Activity mainActivity = this.getActivity();
		ViewPager viewPager = (ViewPager) mainActivity.findViewById(R.id.pager);
		int id = viewPager.getCurrentItem();
		
		Fragment frag = ((FragmentActivity) mainActivity).
							getSupportFragmentManager().
								findFragmentByTag("android:switcher:"+viewPager.getId()+":"+id); 
		View statusFragView = frag.getView();
		
		// Check pre-conditions
		solo.waitForActivity("MainScreen");
		solo.assertCurrentActivity("MainScreen expected", MainScreenActivity.class);
		
		// Post status
		EditText editText = (EditText) statusFragView.findViewById(R.id.status_edit_text);
		solo.enterText(editText, STATUS_CONTENT);
		solo.clickOnButton("post");
		solo.sleep(200);
		
		// Check if status is added in status list
		ListView listStatus = (ListView) statusFragView.findViewById(R.id.status_list);
		View newStatusView = listStatus.getChildAt(0);
		TextView statusContent = (TextView) newStatusView.findViewById(R.id.status_content);
		assertEquals(statusContent.getText().toString(), STATUS_CONTENT);
	}
}
