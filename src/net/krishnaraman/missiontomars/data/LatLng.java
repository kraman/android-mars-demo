package net.krishnaraman.missiontomars.data;

import org.json.JSONArray;
import org.json.JSONException;

public class LatLng {
	public Double lat;
	public Double lng;
	
	public LatLng(){
		lat = 0.0;
		lng = 0.0;
	}

	
	public LatLng(JSONArray jsonArray) throws JSONException {
		lat = jsonArray.getDouble(0);
		lng = jsonArray.getDouble(1);
	}

	public String toString(){
		return "lat:" + lat + "\nlng:" + lng;
	}
	
}
