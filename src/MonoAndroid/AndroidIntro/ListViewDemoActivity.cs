using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using Android.App;
using Android.Content;
using Android.OS;
using Android.Runtime;
using Android.Views;
using Android.Widget;
using Android.Util;
using Android;
using Android.Graphics;

namespace AndroidIntro
{
	[Activity (Label = "ListViewDemoActivity")]			
	public class ListViewDemoActivity : Activity
	{
		protected override void OnCreate (Bundle bundle)
		{
			base.OnCreate (bundle);

			SetContentView (Resource.Layout.ListViewDemo);

			FindViewById<ListView> (Resource.Id.demo_list_view).Adapter = new DemoListAdapter(this);



		}
	}

	class DemoListAdapter : BaseAdapter
	{

		private LayoutInflater m_layoutInflater;

		public DemoListAdapter (Context context)
		{
			m_layoutInflater = (LayoutInflater) context.GetSystemService(Context.LayoutInflaterService);
		}

		public override int Count {
			get {
				return 100;
			}
		}

		public override long GetItemId (int position)
		{
			return position;
		}

		public override Java.Lang.Object GetItem (int position)
		{
			return null;
		}

		public override View GetView (int position, View convertView, ViewGroup parent)
		{
			View listItem = convertView;
			if (null == listItem) {
				Log.Debug ("DemoListAdapter", "Creating view for position " + position);

				listItem = m_layoutInflater.Inflate (Resource.Layout.ListViewItemDemo, parent, false);
			}

			View random_color_view = listItem.FindViewById (Resource.Id.random_color_view);
			
			Random rnd = new Random ();
			int color = Color.Argb (255, rnd.Next (256), rnd.Next (256), rnd.Next (256));
			random_color_view.SetBackgroundColor(new Color(color));

			return listItem;
		}
	}
}

