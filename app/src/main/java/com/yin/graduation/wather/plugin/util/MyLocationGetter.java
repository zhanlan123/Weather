package com.yin.graduation.wather.plugin.util;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;

import java.util.List;
import java.util.Locale;

public class MyLocationGetter extends Handler implements LocationListener {
	private static final int MSG_REGISTLISTENER_GPS = 2002;
	private static final int MSG_REGISTLISTENER_NETWORK = 2001;
	private Address mAddr = null;
	private Context mContext;
	private Location mLocation = null;
	private LocationManager mLocationManager;

	public MyLocationGetter(Context context, HandlerThread handlerThread) {
		super(handlerThread.getLooper());
		mContext = context;
		mLocationManager = ((LocationManager) mContext
				.getSystemService(Context.LOCATION_SERVICE));
	}

	private static Address convertLocationToAddress(Context context,
			Location location) {
		Geocoder geocoder = new Geocoder(context, Locale.getDefault());
		try {
			List<Address> lists = geocoder.getFromLocation(
					location.getLatitude(), location.getLongitude(), 1);
			if (lists.size() > 0)
				return lists.get(0);
		} catch (Exception e) {
		}
		return null;
	}

	public Address getAddr() {
		return mAddr;
	}

	public Location getLocation() {
		return mLocation;
	}

	public boolean getSomeResults() {
		return ((mAddr != null) || (mLocation != null));
	}

	public void handleMessage(Message message) {
		if (message.what == MSG_REGISTLISTENER_NETWORK)
			mLocationManager.requestLocationUpdates(
					LocationManager.NETWORK_PROVIDER, 1000L, 10.0F, this);
		if (message.what == MSG_REGISTLISTENER_GPS)
			this.mLocationManager.requestLocationUpdates(
					LocationManager.GPS_PROVIDER, 1000L, 10.0F, this);
	}

	public void onLocationChanged(Location location) {
		if (location != null) {
			mLocation = location;
			mAddr = convertLocationToAddress(mContext, location);
		}
	}

	public void onProviderDisabled(String paramString) {
	}

	public void onProviderEnabled(String paramString) {
	}

	public void onStatusChanged(String paramString, int paramInt, Bundle bundle) {
	}

	public void registerLocationListener(boolean isUseNetwork) {
		Message message;
		if (isUseNetwork) {
			message = obtainMessage(MSG_REGISTLISTENER_NETWORK);
		} else {
			message = obtainMessage(MSG_REGISTLISTENER_GPS);
		}
		sendMessage(message);
	}

	public void unregisterLocationlistener() {
		mLocationManager.removeUpdates(this);
	}
}