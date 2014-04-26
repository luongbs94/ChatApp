package uet.chatapp.activity;

import uet.chatapp.chatapp.R;
import uet.chatapp.interfaces.IAppManager;
import uet.chatapp.services.IMService;
import uet.chatapp.type.FriendInfo;
import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.SparseBooleanArray;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

public class UnApprovedFriendList extends Activity implements OnClickListener {

	private String[] friendUsernames;
	private IAppManager imService;
	private ListView listView;
	ArrayAdapter<String> adapter;
	private Button submitButton;

	String approvedFriendNames = new String();
	String discardedFriendNames = new String();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.unapproved_friendlist);
		listView = (ListView) findViewById(R.id.list);
		submitButton = (Button) findViewById(R.id.testbutton);

		Bundle extras = getIntent().getExtras();
		String names = extras.getString(FriendInfo.FRIEND_LIST);
		friendUsernames = names.split(",");

		adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_multiple_choice,
				friendUsernames);
		listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
		listView.setAdapter(adapter);

		submitButton.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		SparseBooleanArray checked = listView.getCheckedItemPositions();
		for (int i = 0; i < checked.size(); i++) {
			int position = checked.keyAt(i);
			if (checked.valueAt(i)) {
				approvedFriendNames = approvedFriendNames.concat(
						friendUsernames[position]).concat(",");
			} else {
				discardedFriendNames = discardedFriendNames.concat(
						friendUsernames[position]).concat(",");
			}
		}

		Thread thread = new Thread() {
			@Override
			public void run() {
				if (approvedFriendNames.length() > 0
						|| discardedFriendNames.length() > 0) {
					imService.sendFriendsReqsResponse(approvedFriendNames,
							discardedFriendNames);

				}
			}
		};
		thread.start();

		Toast.makeText(UnApprovedFriendList.this, R.string.response_friend_request_sent,
				Toast.LENGTH_SHORT).show();
		finish();
		
	}

	@Override
	protected void onPause() {
		unbindService(mConnection);
		super.onPause();
	}

	@Override
	protected void onResume() {
		super.onResume();
		bindService(new Intent(UnApprovedFriendList.this, IMService.class),
				mConnection, Context.BIND_AUTO_CREATE);
	}

	private ServiceConnection mConnection = new ServiceConnection() {

		public void onServiceConnected(ComponentName className, IBinder service) {
			imService = ((IMService.IMBinder) service).getService();

		}

		public void onServiceDisconnected(ComponentName className) {
			imService = null;
			Toast.makeText(UnApprovedFriendList.this,
					R.string.local_service_stopped, Toast.LENGTH_SHORT).show();
		}
	};

}
