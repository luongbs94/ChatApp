package uet.chatapp.fragment;


import java.util.ArrayList;

import android.support.v4.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;
import uet.chatapp.activity.MessageBoxActivity;
import uet.chatapp.adapter.MessageListAdapter;
import uet.chatapp.chatapp.R;
import uet.chatapp.model.MessageItemInList;

public class MessageFragment extends Fragment  {
	private ArrayList<MessageItemInList> message;
	ListView listView;
	MessageListAdapter adapter;
	private static MessageFragment instance = null;

	public MessageFragment(){}

	public static MessageFragment getInstance(){
		if (instance == null){
			instance = new MessageFragment();
		}
		return instance;
	}
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
 
        View rootView = inflater.inflate(R.layout.fragment_message, container, false);        
        return rootView;
    }
    @Override 
    public void onActivityCreated(Bundle savedInstanceState) {  
        super.onActivityCreated(savedInstanceState);  
        
        if (adapter == null){
            // Hard code: add message to list  
            message = new ArrayList<MessageItemInList>();
        	message.add(new MessageItemInList("luong", "good morning", "06:00"));
        	message.add(new MessageItemInList("long", "good afternoon", "12:00"));
        	message.add(new MessageItemInList("hao", "hi", "12:00"));    
            adapter = new MessageListAdapter(this.getActivity(), message);         	
        }
        listView = (ListView) getActivity().findViewById(R.id.message_list);  
        listView.setAdapter(adapter);  
        
        listView.setOnItemClickListener(new OnItemClickListener() {  
   
            @Override 
            public void onItemClick(AdapterView<?> parent, View view,  
                    int position, long id) {  
                Intent intent = new Intent(getActivity(), MessageBoxActivity.class);
                TextView message_content = (TextView) view.findViewById(R.id.message_content);
                TextView name = (TextView) view.findViewById(R.id.message_friend_name);
                
                String message = message_content.getText().toString();
                String friend_name = name.getText().toString();

                intent.putExtra("message", message);
                intent.putExtra("friend_name", friend_name);
          
                startActivity(intent);
            }  
        });            
    }  
}
