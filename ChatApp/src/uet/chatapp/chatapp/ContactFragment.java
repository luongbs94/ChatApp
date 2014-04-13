package uet.chatapp.chatapp;


import java.util.ArrayList;

import android.support.v4.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import uet.chatapp.adapter.ContactListAdapter;
import uet.chatapp.model.ContactItem;

public class ContactFragment extends Fragment  {
	private ArrayList<ContactItem> contact;
	ListView listView;
	ContactListAdapter adapter;
	
	public ContactFragment(){}
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
 
        View rootView = inflater.inflate(R.layout.fragment_contact, container, false);        
        return rootView;
    }
    @Override 
    public void onActivityCreated(Bundle savedInstanceState) {  
        super.onActivityCreated(savedInstanceState);  
           
        //Hard code: add message to ListView  
        contact = new ArrayList<ContactItem>();
        contact.add(new ContactItem("luong"));
        contact.add(new ContactItem("long"));
        contact.add(new ContactItem("hao")); 
           
        adapter = new ContactListAdapter(this.getActivity(), contact); 
        listView = (ListView) getActivity().findViewById(R.id.contact_list);  
        listView.setAdapter(adapter);  
        
//        listView.setOnItemClickListener(new OnItemClickListener() {  
//   
//            @Override 
//            public void onItemClick(AdapterView<?> parent, View view,  
//                    int position, long id) {  
//                Intent intent = new Intent(getActivity(), MessageBoxActivity.class);
//                TextView tv1 = (TextView) view.findViewById(R.id.content);
//                TextView tv2 = (TextView) view.findViewById(R.id.friend_name);
//                
//                String message = tv1.getText().toString();
//                String friend_name = tv2.getText().toString();
//
//                intent.putExtra("message", message);
//                intent.putExtra("friend_name", friend_name);
//          
//                startActivity(intent);
//            }  
//        });            
    }  
}
