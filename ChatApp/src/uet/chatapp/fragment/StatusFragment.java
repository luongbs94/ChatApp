package uet.chatapp.fragment;


import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import android.support.v4.app.Fragment;
import android.text.format.Time;
import android.util.Log;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import uet.chatapp.adapter.ContactListAdapter;
import uet.chatapp.adapter.StatusListAdapter;
import uet.chatapp.chatapp.R;
import uet.chatapp.fragment.ContactFragment.MessageReceiver;
import uet.chatapp.interfaces.IAppManager;
import uet.chatapp.model.StatusItem;
import uet.chatapp.services.IMService;
import uet.chatapp.tool.StatusController;
import uet.chatapp.type.FriendInfo;
import uet.chatapp.type.StatusInfo;;

public class StatusFragment extends Fragment  {
	private ArrayList<StatusItem> status;
	private IAppManager imService;
	ListView listView;
	StatusListAdapter adapter;
	private static String statuspost;
	private static StatusFragment instance = null;
	public StatusFragment(){}

	public static StatusFragment getInstance(){
		if (instance == null){
			instance = new StatusFragment();
		}
		return instance;
	}
	
private ServiceConnection mConnection = new ServiceConnection() {
      	
		public void onServiceConnected(ComponentName className, IBinder service) {          
            imService = ((IMService.IMBinder)service).getService();
        }
        public void onServiceDisconnected(ComponentName className) {
        	imService = null;
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
		i.addAction(IMService.STATUS_LIST_UPDATED);

		getActivity().registerReceiver(messageReceiver, i);
	}    
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
 
        final View rootView = inflater.inflate(R.layout.fragment_status, container, false); 
        
        /** Handle when the user clicks the Send button */
        Button postButton = (Button) rootView.findViewById(R.id.status_button_post);
        postButton.setOnClickListener(new Button.OnClickListener() {
           public void onClick(View v) {
				EditText editText = (EditText) rootView.findViewById(R.id.status_edit_text);
				statuspost = editText.getText().toString();
				Time today = new Time(Time.getCurrentTimezone());
				today.setToNow();
			
				
				Thread thread = new Thread() {
					public void run() {
						if(imService == null){
							Log.d("imService","imService is null");
						}
						Thread thread = new Thread(){					
							public void run() {
								try {
									Time today = new Time(Time.getCurrentTimezone());
									today.setToNow();
									imService.postStatus(imService.getUsername(), statuspost, today.format3339(false).substring(0, 19));
									
								} catch (UnsupportedEncodingException e) {
									e.printStackTrace();
								}
							}						
						};
						thread.start();		
					}
				};
				thread.start();
				adapter.notifyDataSetChanged();
				editText.setText("");        	   
           }
        });      
        
        return rootView;
    }
	
    @Override 
    public void onActivityCreated(Bundle savedInstanceState) {  
        super.onActivityCreated(savedInstanceState);  
        
        listView = (ListView) getActivity().findViewById(R.id.status_list);  
        listView.setAdapter(adapter);            	
    }  
        
    
    
	public void updateStatus(StatusInfo[] statuses) {
		if (statuses != null) {
			for (int i=0;i<statuses.length-1;i++)
				for (int j=i+1;j<statuses.length;j++)
					if (statuses[j].time.compareTo(statuses[i].time)==1){
						StatusInfo tmp = statuses[i];
						statuses[i] = statuses[j];
						statuses[j] = tmp;
					}
			
			adapter = new StatusListAdapter(this.getActivity(), statuses);
			listView.setAdapter(adapter);
		} 
	}	
	
    public class MessageReceiver extends BroadcastReceiver {

		@Override
		public void onReceive(Context context, Intent intent) {
			Log.i("Broadcast receiver ", "received a status");
			Bundle extras = intent.getExtras();
			if (extras != null) {
				String action = intent.getAction();
				if (action.equals(IMService.STATUS_LIST_UPDATED)) {
					updateStatus(StatusController.getStatusesInfo());
					Log.i("yyyyyyyyyyyyy",StatusController.getStatusesInfo()[0].text);
				}
			}
		}
	}

    
}
