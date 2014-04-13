package uet.chatapp.adapter;


import java.util.ArrayList;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import uet.chatapp.chatapp.R;
import uet.chatapp.model.ContactItem;

public class ContactListAdapter extends BaseAdapter {
	
	private ArrayList<ContactItem> list_contact;
	private Activity activity;
	
	public ContactListAdapter(Activity activity, ArrayList<ContactItem> list_contact){
		this.activity = activity;
		this.list_contact = list_contact ;
	}

	@Override
	public int getCount() {
		return list_contact.size();
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
			view = inflater.inflate(R.layout.contact_list_row, null);
		}
		
		TextView contact_name = (TextView) view.findViewById(R.id.contact_name);
	
        ContactItem contact = list_contact.get(position);
 
        // Setting all values in list view
        contact_name.setText(contact.getContact_name());
        return view;
	}
	

}
