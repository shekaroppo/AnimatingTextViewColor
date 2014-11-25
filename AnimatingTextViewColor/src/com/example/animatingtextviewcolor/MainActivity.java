package com.example.animatingtextviewcolor;

import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.util.Property;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		if (savedInstanceState == null) {
			getFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {

		private TextView mTextView;
		private Button mStart;
		private TextView mTextView1;

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_main, container,
					false);
			
			mTextView=(TextView)rootView.findViewById(R.id.textview);
			mTextView1=(TextView)rootView.findViewById(R.id.textview1);
			mTextView.setTextColor(getResources().getColor(R.color.black1_transparent_100));
			final Property<TextView, Integer> property = new Property<TextView, Integer>(int.class, "textColor") {
				   @Override
				    public Integer get(TextView object) {
				        return object.getCurrentTextColor();
				    }

				   @Override
				    public void set(TextView object, Integer value) {
				        object.setTextColor(value);
				    }
				};

				final ObjectAnimator animator = ObjectAnimator.ofInt(mTextView, property,getResources().getColor(R.color.black1_transparent_4));
				animator.setDuration(1000);
				animator.setEvaluator(new ArgbEvaluator());
				animator.setInterpolator(new DecelerateInterpolator(2));
				
				mStart=(Button)rootView.findViewById(R.id.start);
				mStart.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View v) {
						mTextView1.animate().alpha(0.2f).setDuration(1000).start();
						animator.start();
						
					}
				});
			return rootView;
		}
	}

}
