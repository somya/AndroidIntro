package com.androidIntro.kittenviewer;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends Activity
{

	private GridView m_grid;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		m_grid = (GridView) findViewById(R.id.grid);
		m_grid.setAdapter(
			new BaseAdapter()
			{
				@Override public int getCount()
				{
					return 200;
				}

				@Override public Object getItem(final int position)
				{
					return "http://placekitten.com/g/200/300";
				}

				@Override public long getItemId(final int position)
				{
					return position;
				}

				@Override public View getView(final int position, final View convertView, final ViewGroup parent)
				{
					ImageView imageView;
					if (null == convertView)
					{
						imageView = new ImageView(MainActivity.this);
					}
					else
					{
						imageView = (ImageView) convertView;
					}

//					imageView.setLayoutParams(new AbsListView.LayoutParams(200, 300));

					String urlString = (String) getItem(position);

					loadImage(imageView, urlString);

					return imageView;
				}

				private void loadImage(final ImageView imageView, final String urlString)
				{
					FetchImageTask fetchImageTask = new FetchImageTask(imageView);
					fetchImageTask.execute(urlString);
				}
			});

	}



	public class FetchImageTask extends AsyncTask<String, Void, Bitmap>
	{
		ImageView m_imageView;

		public FetchImageTask(final ImageView imageView)
		{
			m_imageView = imageView;
		}

		@Override protected Bitmap doInBackground(final String... params)
		{
			try
			{

				URL url = new URL(params[0]);
				Bitmap bmp = BitmapFactory.decodeStream(url.openConnection().getInputStream());
				return bmp;
			}
			catch (MalformedURLException e)
			{
				e.printStackTrace();
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
			return null;
		}

		@Override protected void onPostExecute(final Bitmap bitmap)
		{
			super.onPostExecute(bitmap);
			m_imageView.setImageBitmap(bitmap);
		}
	}


}
