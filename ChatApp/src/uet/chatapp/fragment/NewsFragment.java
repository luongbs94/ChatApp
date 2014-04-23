package uet.chatapp.fragment;


import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import uet.chatapp.chatapp.R;

public class NewsFragment extends Fragment  {
	private static NewsFragment instance = null;

	public NewsFragment(){}

	public static NewsFragment getInstance(){
		if (instance == null){
			instance = new NewsFragment();
		}
		return instance;
	}
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
 
        final View rootView = inflater.inflate(R.layout.fragment_news, container, false); 
        return rootView;
    }
	
    @Override 
    public void onActivityCreated(Bundle savedInstanceState) {  
        super.onActivityCreated(savedInstanceState);  
             	
    }  
}
