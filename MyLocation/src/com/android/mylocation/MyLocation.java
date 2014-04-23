package com.android.mylocation;

import android.app.Activity;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class MyLocation extends Activity {
	private Button button_locate;
	private LocationManager lm;
	Location location;
	
	//private ProgressDialog dialog;
	LocationListener listener; 
	TextView tv;
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_loc);
		
		button_locate = (Button) this.findViewById(R.id.button_locate);
		tv = (TextView) this.findViewById(R.id.location_text);
		button_locate.setOnClickListener(new OnClickListener(){

			public void onClick(View arg0) {
				Locating(MyLocation.this);
			}
			
		});
	}
	
	public void Locating(Context contxt){
		//dialog = ProgressDialog.show(MyLocation.this, "", "«Î…‘∫Ú");
		lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
		if (needLocate(LocationManager.GPS_PROVIDER, contxt)){
			updateLocation(location, contxt);
		}
	}
	
	public boolean needLocate(String provider, final Context contxt){
		Location loc = lm.getLastKnownLocation(provider);
		listener = new LocationListener(){
			public void onLocationChanged(android.location.Location arg0) {
				updateLocation(location, contxt);
				
			}

			public void onProviderDisabled(String arg0) {
				// TODO Auto-generated method stub
				System.out.println("Provider Disabled");
			}

			public void onProviderEnabled(String arg0) {
				// TODO Auto-generated method stub
				System.out.println("provider enabled");
				
			}

			public void onStatusChanged(String arg0, int arg1, Bundle arg2) {
				// TODO Auto-generated method stub
				System.out.println("onStatusChanged");
			}
		};
		lm.requestLocationUpdates(provider, 1000, 0, listener);
		if (loc != null){
			location = loc;
			return true;
		}
		else
			return false;
	}
	
	public void updateLocation(Location loc, Context contxt){
		tv.setText(loc.getLongitude() + "\n\t" + loc.getLatitude());
		lm.removeUpdates(listener);
	}
	

}
