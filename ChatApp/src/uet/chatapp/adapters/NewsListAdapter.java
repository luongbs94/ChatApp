package uet.chatapp.adapters;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import uet.chatapp.models.RssItem;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import uet.chatapp.chatapp.R;

public class NewsListAdapter extends ArrayAdapter<RssItem> {

	private Context context;
	private List<RssItem> items;

	public NewsListAdapter(Context context, int textViewResourceId,
			List<RssItem> items) {
		super(context, textViewResourceId, items);
		this.context = context;
		this.items = items;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		LayoutInflater inflater = (LayoutInflater) context
				.getSystemService(context.LAYOUT_INFLATER_SERVICE);
		View rowView = inflater.inflate(R.layout.newslist, null);

		ImageView ivIcon = (ImageView) rowView.findViewById(R.id.ivIcon);

		try {
			InputStream is = (InputStream) new URL(items.get(position)
					.getImage()).getContent();
			if (is != null) {
				Drawable drawable = Drawable.createFromStream(is, "icon");
				ivIcon.setImageDrawable(drawable);
			}
		} catch (Exception e) {
			ivIcon.setImageResource(R.drawable.paper);
			e.printStackTrace();
		}

		TextView tvTitle = (TextView) rowView.findViewById(R.id.tvTitle);
		tvTitle.setText(items.get(position).getTitle());

		TextView tvPubDate = (TextView) rowView.findViewById(R.id.tvPubDate);
		tvPubDate.setText(items.get(position).getPubDate());

		return rowView;
	}
}
