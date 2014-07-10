package edu.gatech.coffeecart;

import java.util.List;

import edu.gatech.coffeecart.DAL.Models.Item;
import edu.gatech.coffeecart.DAL.Repositories.ItemsDataRepository;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.os.Build;
import android.app.ListActivity;

import java.util.Random;

public class UserManager extends ActionBarActivity {
	private ItemsDataRepository datasource;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_user_manager);

		if (savedInstanceState == null) {
			getSupportFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
		}
		
		datasource = new ItemsDataRepository(this);
        datasource.open();

        List<Item> values = datasource.getAllComments();

        // use the SimpleCursorAdapter to show the
        // elements in a ListView
        ArrayAdapter<Item> adapter = new ArrayAdapter<Item>(this,
            android.R.layout.simple_list_item_1, values);
        ////setListAdapter(adapter);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.user_manager, menu);
		return true;
	}

    @Override
    protected void onResume() {
      datasource.open();
      super.onResume();
    }

    @Override
    protected void onPause() {
      datasource.close();
      super.onPause();
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
			View rootView = inflater.inflate(R.layout.fragment_user_manager,
					container, false);
			return rootView;
		}
	}
	
    public void handleClick(View view)
    {            	
        switch(view.getId())
        {

            case R.id.userDetails:
            	Intent intentAddEditUser = new Intent(UserManager.this, ViewEditUser.class);
                UserManager.this.startActivity(intentAddEditUser);
                break;
                
            case R.id.btnEditUser:
/*            	Intent intentEditUser = new Intent(UserManager.this, ViewEditUser.class);
                UserManager.this.startActivity(intentEditUser);*/
                @SuppressWarnings("unchecked")
                ////ArrayAdapter<Item> adapter = (ArrayAdapter<Item>) getListAdapter();
                Item comment = null;

                  String[] items = new String[] { "first", "second", "third" };
                  
                  // save the new comment to the database
                  comment = datasource.createComment(items[1]);
                  ////adapter.add(comment);


/*                  if (getListAdapter().getCount() > 0) {
                    comment = (Comment) getListAdapter().getItem(0);
                    datasource.deleteComment(comment);
                    adapter.remove(comment);
                  }*/

                
                ////adapter.notifyDataSetChanged();
                break;        
            
        }

    }

}
