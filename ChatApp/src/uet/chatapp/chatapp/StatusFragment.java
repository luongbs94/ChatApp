package uet.chatapp.chatapp;


import java.util.ArrayList;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import uet.chatapp.adapter.StatusListAdapter;
import uet.chatapp.model.StatusItem;

public class StatusFragment extends Fragment  {
	private ArrayList<StatusItem> status;
	ListView listView;
	StatusListAdapter adapter;
	
	public StatusFragment(){}
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
 
        View rootView = inflater.inflate(R.layout.fragment_status, container, false);   
        
        return rootView;
    }
    @Override 
    public void onActivityCreated(Bundle savedInstanceState) {  
        super.onActivityCreated(savedInstanceState);  
           
        //Hard code: add message to ListView  
        status = new ArrayList<StatusItem>();
    	status.add(new StatusItem("luong", "Hello. Today I am happy", "12:00"));
    	status.add(new StatusItem("long", "good afternoon. Today I am worry", "12:00"));
    	status.add(new StatusItem("hao", "hi. Have a sweet dream ", "12:00")); 
           
        adapter = new StatusListAdapter(this.getActivity(), status); 
        listView = (ListView) getActivity().findViewById(R.id.status_list);  
        listView.setAdapter(adapter);    
    }  
}
