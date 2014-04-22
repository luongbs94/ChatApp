package uet.chatapp.fragment;

import uet.chatapp.activity.MessageBoxActivity;
import uet.chatapp.adapter.ContactListAdapter;
import uet.chatapp.chatapp.R;
import uet.chatapp.interfaces.IAppManager;
import uet.chatapp.services.IMService;
import uet.chatapp.tool.FriendController;
import uet.chatapp.type.FriendInfo;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class ContactFragment extends Fragment {
	ListView listView;
	ContactListAdapter friendAdapter;
	private static ContactFragment instance = null;
	private IAppManager imService = null;
	private EditText nameFriend;

	public String ownUsername = new String();

	public ContactFragment() {
	}

	public static ContactFragment getInstance() {
		if (instance == null) {
			instance = new ContactFragment();
		}
		return instance;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		final View rootView = inflater.inflate(R.layout.fragment_contact,
				container, false);

		/** Handle when the user clicks the Add button */
		Button addButton = (Button) rootView.findViewById(R.id.btn_addfriend);
		addButton.setOnClickListener(new Button.OnClickListener() {
			public void onClick(View v) {
				nameFriend = (EditText) rootView
						.findViewById(R.id.contact_edit_text);
				friendAdapter.notifyDataSetChanged();
				addFriend();
			}
		});

		return rootView;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);

		listView = (ListView) getActivity().findViewById(R.id.contact_list);
		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Intent intent = new Intent(getActivity(),
						MessageBoxActivity.class);
				FriendInfo friend = friendAdapter.getItem(position);
				intent.putExtra(FriendInfo.USERNAME, friend.userName);
				intent.putExtra(FriendInfo.PORT, friend.port);
				intent.putExtra(FriendInfo.IP, friend.ip);
				startActivity(intent);
			}
		});
	}

	public void updateData(FriendInfo[] friends, FriendInfo[] unApprovedFriends) {
		if (friends != null) {
			for (int i = 0; i < friends.length; i++) {
				Log.d("friend " + i, friends[i].getUsername());
			}

			friendAdapter = new ContactListAdapter(this.getActivity(), friends);
			listView.setAdapter(friendAdapter);
		}

		if (unApprovedFriends != null) {

		}
	}

	public class MessageReceiver extends BroadcastReceiver {

		@Override
		public void onReceive(Context context, Intent intent) {
			Log.i("Broadcast receiver ", "received a message");
			Bundle extras = intent.getExtras();
			if (extras != null) {
				String action = intent.getAction();
				if (action.equals(IMService.FRIEND_LIST_UPDATED)) {
					updateData(FriendController.getFriendsInfo(),
							FriendController.getUnapprovedFriendsInfo());
				}
			}
		}
	}

	private ServiceConnection mConnection = new ServiceConnection() {

		@Override
		public void onServiceDisconnected(ComponentName name) {
			imService = null;
			Log.d("imService", "imService is null");

		}

		@Override
		public void onServiceConnected(ComponentName name, IBinder service) {
			imService = ((IMService.IMBinder) service).getService();

			FriendInfo[] friends = FriendController.getFriendsInfo();
			if (friends != null) {
				updateData(friends, null);
			}

			getActivity().setTitle(imService.getUsername() + "'s friend list");
			ownUsername = imService.getUsername();
		}
	};

	public MessageReceiver messageReceiver = new MessageReceiver();

	@Override
	public void onPause() {
		getActivity().unregisterReceiver(messageReceiver);
		getActivity().unbindService(mConnection);
		super.onPause();
	}

	@Override
	public void onResume() {
		super.onResume();
		getActivity().bindService(
				new Intent(this.getActivity(), IMService.class), mConnection,
				Context.BIND_AUTO_CREATE);

		IntentFilter i = new IntentFilter();
		i.addAction(IMService.FRIEND_LIST_UPDATED);

		getActivity().registerReceiver(messageReceiver, i);
	}

	public void addFriend() {
		if (nameFriend.length() > 0) {
			Thread thread = new Thread() {
				public void run() {
					imService.addNewFriendRequest(nameFriend.getText()
							.toString());
				}
			};
			thread.start();
			nameFriend.setText("");
			Toast.makeText(getActivity(), R.string.requset_sent,
					Toast.LENGTH_SHORT).show();
		}
	}
}
