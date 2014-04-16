package uet.chatapp.chatapp.test;


import uet.chatapp.chatapp.*;
import android.app.ActionBar;
import android.app.Activity;
import android.support.v4.view.ViewPager;
import android.test.ActivityInstrumentationTestCase2;
import uet.chatapp.chatapp.R;

import com.robotium.solo.Solo;


public class MainScreenTest extends ActivityInstrumentationTestCase2<MainScreen>{
	public static final int TAG_STATUS_FRAGMENT_VIEW_PAGER_ID = 0;
	public static final int TAG_MESSAGE_FRAGMENT_VIEW_PAGER_ID = 1;
	public static final int TAG_CONTACT_FRAGMENT_VIEW_PAGER_ID = 2;

	
	private Solo solo;
	public MainScreenTest() {
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

	/**
	 * Test if main activity is in correct state.
	 */
	public void testState() {
		Activity mainActivity = this.getActivity();
		ActionBar actionBar = mainActivity.getActionBar();

		// Check if the correct navigation mode is set
		assertTrue(actionBar.getNavigationMode() == ActionBar.NAVIGATION_MODE_TABS);
	}

	
	/**
	 * Test if the tab switching functionality is functioning properly.
	 */
	public void testSwitchTabs(){
		Activity mainActivity = this.getActivity();

		// Get the id of the view pager handling the tabs
		ViewPager viewPager = (ViewPager) mainActivity
				.findViewById(R.id.pager);
		int viewPagerId = viewPager.getId();

		solo.assertCurrentActivity("Current activity is not MainActivity",
				MainScreen.class);
		
		// Click Status-tab and check if it's viewed
		boolean StatusFragmentViewed = solo
				.waitForFragmentByTag("android:switcher:" + viewPagerId + ":"
						+ TAG_STATUS_FRAGMENT_VIEW_PAGER_ID);
		solo.clickOnText("Status");
		assertTrue(StatusFragmentViewed);

		// Click Message-tab and check if it's viewed
		boolean messageFragmentViewed = solo
				.waitForFragmentByTag("android:switcher:" + viewPagerId + ":"
						+ TAG_MESSAGE_FRAGMENT_VIEW_PAGER_ID);
		solo.clickOnText("Message");
		assertTrue(messageFragmentViewed);		
		
		// Click Contact-tab and check if it's viewed
		boolean contactFragmentViewed = solo
				.waitForFragmentByTag("android:switcher:" + viewPagerId + ":"
						+ TAG_CONTACT_FRAGMENT_VIEW_PAGER_ID);
		solo.clickOnText("Contact");
		assertTrue(contactFragmentViewed);	
	}
}
