package uet.chatapp.chatapp;


import java.util.ArrayList;

import android.support.v4.app.Fragment;
import android.text.format.Time;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import uet.chatapp.adapter.StatusListAdapter;
import uet.chatapp.model.StatusItem;

public class StatusFragment extends Fragment  {
	private ArrayList<StatusItem> status;
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
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
 
        final View rootView = inflater.inflate(R.layout.fragment_status, container, false); 
        
        /** Handle when the user clicks the Send button */
        Button postButton = (Button) rootView.findViewById(R.id.status_button_post);
        postButton.setOnClickListener(new Button.OnClickListener() {
           public void onClick(View v) {
				EditText editText = (EditText) rootView.findViewById(R.id.status_edit_text);
				Time today = new Time(Time.getCurrentTimezone());
				today.setToNow();
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
