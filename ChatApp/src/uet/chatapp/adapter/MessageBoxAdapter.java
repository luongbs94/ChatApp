package uet.chatapp.adapter;

import java.util.ArrayList;
import java.util.List;

import uet.chatapp.chatapp.R;
import uet.chatapp.model.MessageItemInMessageBox;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MessageBoxAdapter extends ArrayAdapter<MessageItemInMessageBox>{
	private TextView message;
	private List<MessageItemInMessageBox> message_list = new ArrayList<MessageItemInMessageBox>();
	private LinearLayout wrapper;
	@Override
	public void add(MessageItemInMessageBox object) {
		message_list.add(object);
		super.add(object);
	}

	public MessageBoxAdapter(Context context, int textViewResourceId) {
		super(context, textViewResourceId);
	}

	public int getCount() {
		return this.message_list.size();
	}

	public MessageItemInMessageBox getItem(int index) {
		return this.message_list.get(index);
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		View row = convertView;
		if (row == null) {
			LayoutInflater inflater = (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			row = inflater.inflate(R.layout.message_box_list_row, parent, false);
		}

		wrapper = (LinearLayout) row.findViewById(R.id.wrapper);

		MessageItemInMessageBox coment = getItem(position);

		message = (TextView) row.findViewById(R.id.message_in_box);

		message.setText(coment.message);

		message.setBackgroundResource(coment.left ? R.drawable.bubble_yellow : R.drawable.bubble_green);
		wrapper.setGravity(coment.left ? Gravity.LEFT : Gravity.RIGHT);

		return row;
	}

	public Bitmap decodeToBitmap(byte[] decodedByte) {
		return BitmapFactory.decodeByteArray(decodedByte, 0, decodedByte.length);
	}
}
