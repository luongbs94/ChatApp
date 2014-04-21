package uet.chatapp.adapter;


import uet.chatapp.chatapp.R;
import uet.chatapp.type.FriendInfo;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class ContactListAdapter extends BaseAdapter {
	
	private FriendInfo[] friends;
	private Activity activity;
	
	public ContactListAdapter(Activity activity, FriendInfo[] friends){
		this.activity = activity;
		this.friends = friends ;
	}
	
	@Override
	public int getCount() {
		return friends.length;
	}

	@Override
	public FriendInfo getItem(int position) {
		return friends[position];
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View view, ViewGroup parent) {
		if(view ==null){
			LayoutInflater inflater = (LayoutInflater) activity.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
			view = inflater.inflate(R.layout.contact_list_row, null);
		}
		
		TextView friendName = (TextView) view.findViewById(R.id.contact_name);
	
        FriendInfo friend = friends[position];
 
        // Setting all values in list view
        friendName.setText(friend.userName);
        return view;
	}
	

}
