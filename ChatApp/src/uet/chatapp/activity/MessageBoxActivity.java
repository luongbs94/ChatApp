package uet.chatapp.activity;

import java.io.UnsupportedEncodingException;

import uet.chatapp.adapter.MessageBoxAdapter;
import uet.chatapp.chatapp.R;
import uet.chatapp.interfaces.IAppManager;
import uet.chatapp.model.MessageItemInMessageBox;
import uet.chatapp.services.IMService;
import uet.chatapp.tool.FriendController;
import uet.chatapp.tool.LocalStorageHandler;
import uet.chatapp.type.FriendInfo;
import uet.chatapp.type.MessageInfo;
import android.app.Activity;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnKeyListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;


public class MessageBoxActivity extends Activity {

	private MessageBoxAdapter adapter;
	private ListView lv;
	private EditText messageText;
	private Button sendMessageButton;
	public String username;
	private IAppManager imService;
	private FriendInfo friend = new FriendInfo();
	private LocalStorageHandler localstoragehandler; 
	private Cursor dbCursor;
	
	private ServiceConnection mConnection = new ServiceConnection() {
      	
		public void onServiceConnected(ComponentName className, IBinder service) {          
            imService = ((IMService.IMBinder)service).getService();
        }
        public void onServiceDisconnected(ComponentName className) {
        	imService = null;
            Toast.makeText(MessageBoxActivity.this, R.string.local_service_stopped,
                    Toast.LENGTH_SHORT).show();
        }
    };
	
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
	
		super.onCreate(savedInstanceState);	
		
		setContentView(R.layout.activity_message_box);

		sendMessageButton = (Button) findViewById(R.id.btn_send);
		messageText = (EditText) findViewById(R.id.edit_message);		
		messageText.requestFocus();		
		lv = (ListView) findViewById(R.id.message_box_list);
		adapter = new MessageBoxAdapter(getApplicationContext(),
				R.layout.message_box_list_row);
		lv.setAdapter(adapter);
		
		Bundle extras = this.getIntent().getExtras();
		
		if (extras == null) 
			return;
		
		friend.userName = extras.getString(FriendInfo.USERNAME);
		friend.ip = extras.getString(FriendInfo.IP);
		friend.port = extras.getString(FriendInfo.PORT);
		String msg = extras.getString(MessageInfo.MESSAGETEXT);
		 
		
		
		setTitle("Messaging with " + friend.userName);
		
		localstoragehandler = new LocalStorageHandler(this);
		dbCursor = localstoragehandler.get(friend.userName, IMService.USERNAME );
		
		if (dbCursor.getCount() > 0){
		int noOfScorer = 0;
		dbCursor.moveToFirst();
		    while ((!dbCursor.isAfterLast())&&noOfScorer<dbCursor.getCount()) 
		    {
		        noOfScorer++;

				this.appendToMessageHistory(dbCursor.getString(2) , dbCursor.getString(3));
		        dbCursor.moveToNext();
		    }
		}
		localstoragehandler.close();
		
		if (msg != null) 
		{
			this.appendToMessageHistory(friend.userName , msg);
			Log.i("msg", msg);
			((NotificationManager)getSystemService(NOTIFICATION_SERVICE))
			.cancel((friend.userName+msg).hashCode());
		}
		
		sendMessageButton.setOnClickListener(new OnClickListener(){
			CharSequence message;
			Handler handler = new Handler();
			public void onClick(View arg0) {
				message = messageText.getText();
				if (message.length()>0) 
				{		
					appendToMessageHistory(imService.getUsername(), message.toString());
					
					localstoragehandler.insert(imService.getUsername(), 
							friend.userName, message.toString());
								
					messageText.setText("");
					Thread thread = new Thread(){					
						public void run() {
							try {
								if (imService.sendMessage(imService.getUsername(),
										friend.userName, message.toString()) == null)
								{									
									handler.post(new Runnable(){	
										public void run() {										
									        Toast.makeText(getApplicationContext(),
											"message_cannot_be_sent", Toast.LENGTH_LONG).show();										
										}										
									});
								}
							} catch (UnsupportedEncodingException e) {
								e.printStackTrace();
							}
						}						
					};
					thread.start();										
				}				
			}});
		
		messageText.setOnKeyListener(new OnKeyListener(){
			public boolean onKey(View v, int keyCode, KeyEvent event) 
			{
				if (keyCode == 66){
					sendMessageButton.performClick();
					return true;
				}
				return false;
			}			
		});				
	}

	@Override
	protected void onPause() {
		super.onPause();
		unregisterReceiver(messageReceiver);
		unbindService(mConnection);
		
		FriendController.setActiveFriend(null);
		
	}

	@Override
	protected void onResume() 
	{		
		super.onResume();
		bindService(new Intent(MessageBoxActivity.this, IMService.class), 
				mConnection , Context.BIND_AUTO_CREATE);
				
		IntentFilter i = new IntentFilter();
		i.addAction(IMService.TAKE_MESSAGE);
		
		registerReceiver(messageReceiver, i);
		
		FriendController.setActiveFriend(friend.userName);		
				
	}
		
	public class  MessageReceiver extends BroadcastReceiver {

		@Override
		public void onReceive(Context context, Intent intent) 
		{		
			Bundle extra = intent.getExtras();
			String username = extra.getString(MessageInfo.USERID);			
			String message = extra.getString(MessageInfo.MESSAGETEXT);
			Log.i("Receiver Message", username+ " "+ message);
			
			if (username != null && message != null)
			{
				if (friend.userName.equals(username)) {
					appendToMessageHistory(username, message);
					localstoragehandler.insert(username,imService.getUsername(), message);
					
				}
				else {
					if (message.length() > 15) {
						message = message.substring(0, 15);
					}	
				}
			}			
		}		
	};
	private MessageReceiver messageReceiver = new MessageReceiver();
	
	public  void appendToMessageHistory(String username, String message) {
		if (username != null && message != null) {
			if (username != null && message != null) {
				if(username.equals(friend.userName)){
					adapter.add(new MessageItemInMessageBox(true, message));
					adapter.notifyDataSetChanged();					
				}else{
					adapter.add(new MessageItemInMessageBox(false, message));
					adapter.notifyDataSetChanged();					
				}		
			}
		}
	}
	
	@Override
	protected void onDestroy() {
	    super.onDestroy();
	    if (localstoragehandler != null) {
	    	localstoragehandler.close();
	    }
	    if (dbCursor != null) {
	    	dbCursor.close();
	    }
	}
}
