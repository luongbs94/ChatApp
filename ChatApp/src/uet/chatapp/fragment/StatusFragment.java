package uet.chatapp.fragment;


import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import android.support.v4.app.Fragment;
import android.text.format.Time;
import android.util.Log;
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
import uet.chatapp.adapter.StatusListAdapter;
import uet.chatapp.chatapp.R;
import uet.chatapp.interfaces.IAppManager;
import uet.chatapp.model.StatusItem;
import uet.chatapp.services.IMService;

public class StatusFragment extends Fragment  {
	private ArrayList<StatusItem> status;
	private IAppManager imService;
	ListView listView;
	StatusListAdapter adapter;
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
    
	@Override
	public void onPause() {
		getActivity().unbindService(mConnection);
		super.onPause();
	}

	@Override
	public void onResume() {
		super.onResume();
		getActivity().bindService(
				new Intent(this.getActivity(), IMService.class), mConnection,
				Context.BIND_AUTO_CREATE);
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
				String status = editText.getText().toString();
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
									imService.postStatus(imService.getUsername(), "danh che de");
									
								} catch (UnsupportedEncodingException e) {
									e.printStackTrace();
								}
							}						
						};
						thread.start();		
					}
				};
				thread.start();
				adapter.add(new StatusItem("minh", editText.getText().toString(), today.format3339(false).substring(0, 19)));
				adapter.notifyDataSetChanged();
				
				editText.setText("");        	   
           }
        });      
        
        return rootView;
    }
	
    @Override 
    public void onActivityCreated(Bundle savedInstanceState) {  
        super.onActivityCreated(savedInstanceState);  
        
        if (adapter == null){
            //Hard code: add status to list 
            status = new ArrayList<StatusItem>();
        	status.add(new StatusItem("luong", "Hello. Today I am happy", "12:00"));
        	status.add(new StatusItem("long", "good afternoon. Today I am worry", "12:00"));
        	status.add(new StatusItem("hao", "hi. Have a sweet dream ", "12:00")); 
               
            adapter = new StatusListAdapter(this.getActivity(), status); 
        }
        listView = (ListView) getActivity().findViewById(R.id.status_list);  
        listView.setAdapter(adapter);            	
    }  
}
