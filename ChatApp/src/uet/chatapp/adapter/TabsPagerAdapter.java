package uet.chatapp.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import uet.chatapp.activity.*;
import uet.chatapp.fragment.ContactFragment;
import uet.chatapp.fragment.MessageFragment;
import uet.chatapp.fragment.StatusFragment;

public class TabsPagerAdapter extends FragmentPagerAdapter {

	public TabsPagerAdapter(FragmentManager fm) {
		super(fm);
	}

	@Override
	public Fragment getItem(int index) {

		switch (index) {
		case 0:
			// Status fragment activity
			return StatusFragment.getInstance();
		case 1:
			// Message fragment activity
			return MessageFragment.getInstance();
		case 2:
			// Contact fragment activity
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
