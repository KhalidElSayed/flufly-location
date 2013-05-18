package com.orleonsoft.android.lookme;

import com.littlefluffytoys.littlefluffylocationlibrary.LocationInfo;
import com.littlefluffytoys.littlefluffylocationlibrary.LocationLibraryConstants;

import android.os.Bundle;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.view.Menu;
import android.widget.TextView;

public class MainActivity extends Activity {
	TextView textView;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		textView=(TextView)findViewById(android.R.id.text1);
	}
	@Override
	protected void onResume() {		
		super.onResume();
		refreshDisplay();
		final IntentFilter lftIntentFilter = new IntentFilter(LocationLibraryConstants.getLocationChangedPeriodicBroadcastAction());
		registerReceiver(lftBroadcastReceiver, lftIntentFilter);
		
	}
	
	@Override
	protected void onPause() {
	
		super.onPause();
		unregisterReceiver(lftBroadcastReceiver);
		
	}
	
	 private void refreshDisplay() {
	        refreshDisplay(new LocationInfo(this));
	    }

	
	public void refreshDisplay(LocationInfo locationInfo) {
        if (locationInfo.anyLocationDataReceived()) {
        	textView.setText(locationInfo.toString());
		}
     }
	
	 private final BroadcastReceiver lftBroadcastReceiver = new BroadcastReceiver() {
	        @Override
	        public void onReceive(Context context, Intent intent) {
	            // extract the location info in the broadcast
	            final LocationInfo locationInfo = (LocationInfo) intent.getSerializableExtra(LocationLibraryConstants.LOCATION_BROADCAST_EXTRA_LOCATIONINFO);
	            // refresh the display with it
	          refreshDisplay(locationInfo);
	        }
	    };

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
