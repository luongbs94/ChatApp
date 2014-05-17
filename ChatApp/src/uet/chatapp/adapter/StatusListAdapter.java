package uet.chatapp.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import uet.chatapp.chatapp.R;
import uet.chatapp.type.StatusInfo;

public class StatusListAdapter extends BaseAdapter {
	
	private StatusInfo[] list_status;
	private Activity activity;
	
	public StatusListAdapter(Activity activity, StatusInfo[] list_status){
		this.activity = activity;
		this.list_status = list_status ;
	}

	@Override
	public int getCount() {
		return list_status.length;
	}

	@Override
	public Object getItem(int position) {
		return list_status[position];
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
        StatusInfo status = list_status[position];
 
        // Setting all values in list view
        friend_name.setText(status.getName());
        status_content.setText(status.getText());
        time.setText(status.getTime());
        return view;
	}
	

}
