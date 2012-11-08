package net.krishnaraman.missiontomars;

import net.krishnaraman.missiontomars.data.VolunteerContent;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.NavUtils;
import android.view.MenuItem;

public class volunteerListActivity extends FragmentActivity
        implements volunteerListFragment.Callbacks {

    private boolean mTwoPane;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_volunteer_list);

        if (findViewById(R.id.volunteer_detail_container) != null) {
            mTwoPane = true;
            ((volunteerListFragment) getSupportFragmentManager()
                    .findFragmentById(R.id.volunteer_list))
                    .setActivateOnItemClick(true);
        }
    }
    
    @Override
    protected void onStart(){
    	super.onStart();
    	VolunteerContent.updateVolunteerList(this);
    }

    public void onItemSelected(String id) {
        if (mTwoPane) {
            Bundle arguments = new Bundle();
            arguments.putString(volunteerDetailFragment.ARG_ITEM_ID, id);
            volunteerDetailFragment fragment = new volunteerDetailFragment();
            fragment.setArguments(arguments);
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.volunteer_detail_container, fragment)
                    .commit();

        } else {
            Intent detailIntent = new Intent(this, volunteerDetailActivity.class);
            detailIntent.putExtra(volunteerDetailFragment.ARG_ITEM_ID, id);
            startActivity(detailIntent);
        }
    }
}
