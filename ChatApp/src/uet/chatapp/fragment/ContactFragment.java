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
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import uet.chatapp.activity.MessageBoxActivity;
import uet.chatapp.adapter.ContactListAdapter;
import uet.chatapp.chatapp.R;
import uet.chatapp.model.ContactItem;

public class ContactFragment extends Fragment  {
	private ArrayList<ContactItem> contact;
	ListView listView;
	ContactListAdapter adapter;
	private static ContactFragment instance = null;
	
	public ContactFragment(){}
	
	public static ContactFragment getInstance(){
		if (instance == null){
			instance = new ContactFragment();
		}
		return instance;
	}
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
 
        final View rootView = inflater.inflate(R.layout.fragment_contact, container, false);  
        
        /** Handle when the user clicks the Add button */
        Button addButton = (Button) rootView.findViewById(R.id.btn_addfriend);
        addButton.setOnClickListener(new Button.OnClickListener() {
           public void onClick(View v) {
       		EditText editText = (EditText) rootView.findViewById(R.id.contact_edit_text);
    		adapter.add(new ContactItem(editText.getText().toString()));
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
            //Hard code: add contact to list 
            contact = new ArrayList<ContactItem>();
            contact.add(new ContactItem("luong"));
            contact.add(new ContactItem("long"));
            contact.add(new ContactItem("hao")); 
            adapter = new ContactListAdapter(this.getActivity(), contact);         	
        }

        listView = (ListView) getActivity().findViewById(R.id.contact_list);  
        listView.setAdapter(adapter);  
        
        listView.setOnItemClickListener(new OnItemClickListener() {  
   
            @Override 
            public void onItemClick(AdapterView<?> parent, View view,  
                    int position, long id) {  
                Intent intent = new Intent(getActivity(), MessageBoxActivity.class);
                TextView name = (TextView) view.findViewById(R.id.contact_name);
                
                String friend_name = name.getText().toString();
                
                intent.putExtra("message", "");
                intent.putExtra("friend_name", friend_name);
                
                startActivity(intent);
            }  
        });            
    }  
}
