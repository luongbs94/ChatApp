package uet.chatapp.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import uet.chatapp.fragment.ContactFragment;
import uet.chatapp.fragment.MusicFragment;
import uet.chatapp.fragment.NewsFragment;
import uet.chatapp.fragment.StatusFragment;

public class TabsPagerAdapter extends FragmentPagerAdapter {
	public static final int TAB_COUNT = 4;
	
	public TabsPagerAdapter(FragmentManager fm) {
		super(fm);
	}

	@Override
	public Fragment getItem(int index) {

		switch (index) {
		case 0:
			// Contact fragment activity
			return ContactFragment.getInstance();
		case 1:
			// Status fragment activity
			return StatusFragment.getInstance();
		case 2:
			// Music fragment activity
			return MusicFragment.getInstance();		
		case 3:
			// News fragment activity
			return NewsFragment.getInstance();
		}

		return null;
	}

	@Override
	public int getCount() {
		// get item count - equal to number of tabs
		return TAB_COUNT;
	}

}
