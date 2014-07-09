package edu.gatech.coffeecart;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.os.Build;

public class ManageItems extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_manage_items);

		if (savedInstanceState == null) {
			getSupportFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
		}
		
		retrieveItemList();
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.manage_items, menu);
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

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_manage_items,
					container, false);
			return rootView;
		}
	}
	
	public void handleClick(View view)
    {            	
        switch(view.getId())
        {

            case R.id.itemAdder:
            	Intent intentItemAdder= new Intent(ManageItems.this, ItemAdder.class);
            	ManageItems.this.startActivity(intentItemAdder);
                break;

        }

    }
	
	private void retrieveItemList()
	{
		//Clean out the empty list text
		TextView emptyListText = (TextView) findViewById(R.id.emptyListText);
		emptyListText.setVisibility(View.GONE) ;
		
		//Dynamically add some of the item list layouts
		for (Integer i = 1; i < 5; i++)
		{
			LayoutInflater vi = (LayoutInflater) getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			View v = vi.inflate(R.layout.layout_single_menu_item, null);
	
			// fill in any details dynamically here
			TextView textView = (TextView) v.findViewById(R.id.itemName);
			textView.setText("testname " + i.toString());
	
			// insert into main view
			View insertPoint = findViewById(R.id.itemListLayout);
			((ViewGroup) insertPoint).addView(v, 0, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.FILL_PARENT));
		}
	}

}
