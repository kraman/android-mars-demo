package net.krishnaraman.missiontomars.data;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import net.krishnaraman.missiontomars.R;
import net.krishnaraman.missiontomars.volunteerListActivity;
import net.krishnaraman.missiontomars.volunteerListFragment;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.ListFragment;
import android.content.Context;
import android.os.AsyncTask;
import android.os.AsyncTask.Status;
import android.util.Log;
import android.widget.ArrayAdapter;

public class VolunteerContent {
	public static ArrayList<Volunteer> ITEMS = new ArrayList<Volunteer>();
    public static Map<String, Volunteer> ITEM_MAP = new HashMap<String, Volunteer>();

    public static void addItem(Volunteer item) {
        ITEMS.add(item);
        ITEM_MAP.put(item.id, item);
    }
    
    public static void clearItems(){
    	ITEM_MAP.clear();
		ITEMS.clear();
    }
    
    private static GetVolunteerList update_task = null;
    public static void updateVolunteerList(FragmentActivity volunteerListActivity){
    	if(update_task == null || update_task.getStatus() != Status.RUNNING){
    		update_task = new GetVolunteerList(volunteerListActivity);
    		update_task.execute();
    	}
    }
}

class GetVolunteerList extends AsyncTask <Void, Void, String> {
	private FragmentActivity activity;

	public GetVolunteerList(FragmentActivity volunteerListActivity) {
		this.activity = volunteerListActivity;
	}

	protected String getASCIIContentFromEntity(HttpEntity entity) throws IllegalStateException, IOException {
		InputStream in = entity.getContent();
		StringBuffer out = new StringBuffer();
		int n = 1;
		while (n>0) {
			byte[] b = new byte[4096];
			n =  in.read(b);
			if (n>0) out.append(new String(b, 0, n));
		}
		return out.toString();
	}
	
	@Override
	protected String doInBackground(Void... params) {
		HttpClient httpClient = new DefaultHttpClient();
		HttpContext localContext = new BasicHttpContext();
		HttpGet httpGet = new HttpGet("http://mars2-localns.example.com/volunteers.json");
		String text = null;
		try {
			HttpResponse response = httpClient.execute(httpGet, localContext);
			HttpEntity entity = response.getEntity();
			text = getASCIIContentFromEntity(entity);
		} catch (Exception e) {
			return e.getLocalizedMessage();
		}
		
		return text;
	}
	
	protected void onPostExecute(String results) {
		if (results!=null) {
			try{
				JSONArray jArray = new JSONArray(results);
				VolunteerContent.clearItems();
				for(int i=0;i<jArray.length();i++){
					JSONObject jObj = jArray.getJSONObject(i);
					VolunteerContent.addItem(
							new Volunteer(
									jObj.getString("_id"),
									jObj.getString("email"),
									jObj.getString("name"),
									jObj.getBoolean("mars_born"), 
									jObj.getInt("avoid_people"),
									jObj.getInt("locked_up"),
									jObj.getInt("round_trip"),
									jObj.getInt("like_brown"),
									jObj.get("lat_long").toString().equals("null") ? new LatLng() : new LatLng(jObj.getJSONArray("lat_long")),
									jObj.getInt("score"),
									jObj.get("percentile").toString().equals("null") ? 0.0 : jObj.getDouble("percentile")));
				}
				volunteerListFragment volunteerListFragment = ((volunteerListFragment) this.activity.getSupportFragmentManager().findFragmentById(R.id.volunteer_list));
				volunteerListFragment.setListAdapter(
					new ArrayAdapter<Volunteer>(this.activity,
						android.R.layout.simple_list_item_activated_1,
						android.R.id.text1,
		                VolunteerContent.ITEMS));
			}catch(JSONException e){
				Log.e("log_tag", "Error parsing data "+e.toString());
			}
		}
	}
}
