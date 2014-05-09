package uet.chatapp.activity;

import uet.chatapp.adapter.PaperAdapter;
import uet.chatapp.models.Variables;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import uet.chatapp.chatapp.R;
public class MainActivity extends ListActivity {

	private PaperAdapter adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		adapter = new PaperAdapter(this, R.layout.activity_main,
				Variables.PAPERS);
		setListAdapter(adapter);
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
			Intent intent = new Intent("android.intent.action.CATEGORY");
			intent.putExtra(Variables.paper, position);
			startActivity(intent);
		
		overridePendingTransition(R.anim.zoom_enter, R.anim.zoom_enter);
		super.onListItemClick(l, v, position, id);
	}
}
