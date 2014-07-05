package edu.gatech.convertor;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RadioButton;
import android.os.Build;

public class TemperatureActivity extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_temperature);

		if (savedInstanceState == null) {
			getSupportFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.temperature, menu);
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
			View rootView = inflater.inflate(R.layout.fragment_temperature,
					container, false);
			return rootView;
		}
	}
	public String celsiusToFahr(double celsius){
		double fahr= (celsius*9)/5 - 32 ;
		return String.valueOf(fahr);
		
	}
	public String fahrToCelsius(double fahr){
		double celsius = (fahr -32 )*5/9;
		return String.valueOf(celsius);
	}
	public void handleClick(View view){
		boolean checked = ((RadioButton)view).isChecked();
		EditText txt = (EditText) findViewById(R.id.textTemp);
		double temperature = Double.parseDouble(txt.getText().toString());
		switch(view.getId()){
		case R.id.radio0:
		if(checked)
			txt.setText(celsiusToFahr(temperature));
		    break;
		 case R.id.radio1:
			if(checked)
		   txt.setText(fahrToCelsius(temperature));
		break;
			
		}
		
	}


}
