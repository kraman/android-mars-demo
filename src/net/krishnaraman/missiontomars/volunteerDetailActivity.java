package net.krishnaraman.missiontomars;

import net.krishnaraman.missiontomars.data.VolunteerContent;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.NavUtils;
import android.view.MenuItem;

public class volunteerDetailActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_volunteer_detail);

        getActionBar().setDisplayHomeAsUpEnabled(true);

        if (savedInstanceState == null) {
            Bundle arguments = new Bundle();
            arguments.putString(volunteerDetailFragment.ARG_ITEM_ID,
                    getIntent().getStringExtra(volunteerDetailFragment.ARG_ITEM_ID));
            volunteerDetailFragment fragment = new volunteerDetailFragment();
            fragment.setArguments(arguments);
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.volunteer_detail_container, fragment)
                    .commit();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            NavUtils.navigateUpTo(this, new Intent(this, volunteerListActivity.class));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
