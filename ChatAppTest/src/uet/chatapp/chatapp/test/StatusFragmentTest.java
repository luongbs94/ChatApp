package uet.chatapp.chatapp.test;

import uet.chatapp.chatapp.*;
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

public class StatusFragmentTest  extends ActivityInstrumentationTestCase2<MainScreen> {
	private Solo solo;
	public static final String STATUS_CONTENT = "Testing status fragment";
	
	public StatusFragmentTest() {
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
	
	public void testPostStatus(){
		// Get view of status fragment
		Activity mainActivity = this.getActivity();
		ViewPager viewPager = (ViewPager) mainActivity.findViewById(R.id.pager);
		int id = viewPager.getCurrentItem();
		Fragment frag = ((FragmentActivity) mainActivity).getSupportFragmentManager()
										.findFragmentByTag("android:switcher:"+viewPager.getId()+":"+id); 
		View statusFragView = frag.getView();
		
		// Check pre-conditions
		solo.waitForActivity("MainScreen");
		solo.assertCurrentActivity("MainScreen expected", MainScreen.class);
		
		// Post status
		EditText editText = (EditText) statusFragView.findViewById(R.id.status_edit_text);
		solo.enterText(editText, STATUS_CONTENT);
		solo.clickOnButton("post");
		solo.sleep(200);
		
		// Check if status is added in status list
		ListView listStatus = (ListView) statusFragView.findViewById(R.id.status_list);
		View newStatusView = listStatus.getChildAt(0);
		TextView status_content = (TextView) newStatusView.findViewById(R.id.status_content);
		assertEquals(status_content.getText().toString(), STATUS_CONTENT);
		
//		StatusListAdapter adapter = (StatusListAdapter) listStatus.getAdapter();
//		StatusItem status = (StatusItem) adapter.getItem(0);
//		String status_content = status.getstatus_content();
//		assertEquals(status_content, STATUS_CONTENT);
	}
}
