package uet.chatapp.activity;

import uet.chatapp.adapter.MessageBoxAdapter;
import uet.chatapp.chatapp.R;
import uet.chatapp.model.MessageItemInMessageBox;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;

public class MessageBoxActivity extends Activity {
	private MessageBoxAdapter adapter;
	private ListView lv;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_message_box);

		lv = (ListView) findViewById(R.id.message_box_list);

		adapter = new MessageBoxAdapter(getApplicationContext(), R.layout.message_box_list_row);

		addItems();
		lv.setAdapter(adapter);
	}

	private void addItems() {
	    Intent intent = getIntent();
	    if (intent != null){
		    String message = intent.getStringExtra("message");
		    if (message == null || message.equals(""))
		    	return;
		    adapter.add(new MessageItemInMessageBox(true, message));	
		    getActionBar().setTitle(intent.getStringExtra("friend_name"));
	    }     
	}
	
	// Called when the user clicks the Send button
	public void sendMessage(View view) {
		EditText editText = (EditText) findViewById(R.id.edit_message);
		adapter.add(new MessageItemInMessageBox(false, editText.getText().toString()));
		adapter.notifyDataSetChanged();
		editText.setText("");
	}
}
