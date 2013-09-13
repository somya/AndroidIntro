using System;
using Android.App;
using Android.Content;
using Android.Runtime;
using Android.Views;
using Android.Widget;
using Android.OS;

namespace AndroidIntro
{
	[Activity (Label = "AndroidIntro", MainLauncher = true)]
	public class MainActivity : Activity
	{
		int count = 1;

		protected override void OnCreate (Bundle bundle)
		{
			base.OnCreate (bundle);

			// Set our view from the "main" layout resource
			SetContentView (Resource.Layout.Main);

			// Get our button from the layout resource,
			// and attach an event to it
			Button button = FindViewById<Button> (Resource.Id.myButton);
			
			button.Click += delegate {
				button.Text = string.Format ("{0} clicks!", count++);
			};

			FindViewById<Button> (Resource.Id.btn_layoutDemo).Click += (sender, e) => StartActivity (typeof(LayoutDemoActivity));
			FindViewById<Button> (Resource.Id.btn_listDemo).Click += (sender, e) => StartActivity (typeof(ListViewDemoActivity));

		}
	}
}


