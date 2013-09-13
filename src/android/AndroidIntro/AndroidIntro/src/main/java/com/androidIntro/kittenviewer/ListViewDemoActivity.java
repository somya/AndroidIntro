package com.androidIntro.kittenviewer;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;

import java.util.Random;

/**
 * Created by somya on 8/18/13.
 */
public class ListViewDemoActivity extends Activity
{
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);

		setContentView(R.layout.list_view_demo);

		ListView demoListView = (ListView) findViewById(R.id.demo_list_view);

		demoListView.setAdapter(new DemoListAdapter());

	}

	private class DemoListAdapter extends BaseAdapter
	{

		private final LayoutInflater m_layoutInflater;

		private DemoListAdapter()
		{
			m_layoutInflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
		}

		@Override public int getCount()
		{
			return 100;
		}

		@Override public Object getItem(final int position)
		{
			return null;
		}

		@Override public long getItemId(final int position)
		{
			return position;
		}

		@Override public View getView(final int position, final View convertView, final ViewGroup parent)
		{
			View listItem = convertView;
			if (null == listItem)
			{
				Log.d("DemoListAdapter", "Creating view for position " + position);

				listItem = m_layoutInflater.inflate(R.layout.demo_list_item_view, parent, false);
			}

			View random_color_view = listItem.findViewById(R.id.random_color_view);

			Random rnd = new Random();
			int color = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
			random_color_view.setBackgroundColor(color);

			return listItem;
		}
	}
}
