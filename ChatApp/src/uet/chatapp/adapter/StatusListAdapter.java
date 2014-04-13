package uet.chatapp.adapter;


import java.util.ArrayList;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import uet.chatapp.chatapp.R;
import uet.chatapp.model.StatusItem;

public class StatusListAdapter extends BaseAdapter {
	
	private ArrayList<StatusItem> list_status;
	private Activity activity;
	
	public StatusListAdapter(Activity activity, ArrayList<StatusItem> list_status){
		this.activity = activity;
		this.list_status = list_status ;
	}

	@Override
	public int getCount() {
		return list_status.size();
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
			view = inflater.inflate(R.layout.status_list_row, null);
		}
		
		TextView friend_name = (TextView) view.findViewById(R.id.status_friend_name);
	    TextView status_content = (TextView) view.findViewById(R.id.status_content);
	    TextView time = (TextView) view.findViewById(R.id.status_time);
	
        StatusItem status = list_status.get(position);
 
        // Setting all values in list view
        friend_name.setText(status.getFriend_name());
        status_content.setText(status.getstatus_content());
        time.setText(status.gettime());
        return view;
	}
	

}
