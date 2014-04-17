package uet.chatapp.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import uet.chatapp.chatapp.*;

public class TabsPagerAdapter extends FragmentPagerAdapter {

	public TabsPagerAdapter(FragmentManager fm) {
		super(fm);
	}

	@Override
	public Fragment getItem(int index) {

		switch (index) {
		case 0:
			// Top Rated fragment activity
			return StatusFragment.getInstance();
		case 1:
			// Games fragment activity
			return MessageFragment.getInstance();
		case 2:
			// Movies fragment activity
			return ContactFragment.getInstance();
		}

		return null;
	}

	@Override
	public int getCount() {
		// get item count - equal to number of tabs
		return 3;
	}

}
