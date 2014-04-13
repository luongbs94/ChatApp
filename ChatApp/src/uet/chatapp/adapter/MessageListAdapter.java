package uet.chatapp.adapter;


import java.util.ArrayList;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import uet.chatapp.chatapp.R;
import uet.chatapp.model.MessageItemInList;

public class MessageListAdapter extends BaseAdapter {
	
	private ArrayList<MessageItemInList> list_message;
	private Activity activity;
	
	public MessageListAdapter(Activity activity, ArrayList<MessageItemInList> list_message){
		this.activity = activity;
		this.list_message = list_message ;
	}

	@Override
	public int getCount() {
		return list_message.size();
	}

	@Override
	public Object getItem(int position) {
		return position;
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View view, ViewGroup parent) {
		if(view ==null){
			LayoutInflater inflater = (LayoutInflater) activity.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
			view = inflater.inflate(R.layout.message_list_row, null);
		}
		
		TextView friend_name = (TextView) view.findViewById(R.id.message_friend_name);
	    TextView message_content = (TextView) view.findViewById(R.id.message_content);
	    TextView time = (TextView) view.findViewById(R.id.message_time_arrival);
	
        MessageItemInList mess = list_message.get(position);
 
        // Setting all values in list view
        friend_name.setText(mess.getFriend_name());
        message_content.setText(mess.getMessage_content());
        time.setText(mess.getTime_arrival());
        return view;
	}
	

}
