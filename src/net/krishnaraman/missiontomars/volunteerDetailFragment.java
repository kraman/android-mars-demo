package net.krishnaraman.missiontomars;

import net.krishnaraman.missiontomars.data.Volunteer;
import net.krishnaraman.missiontomars.data.VolunteerContent;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

public class volunteerDetailFragment extends Fragment {

    public static final String ARG_ITEM_ID = "item_id";

    Volunteer mItem;

    public volunteerDetailFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments().containsKey(ARG_ITEM_ID)) {
            mItem = VolunteerContent.ITEM_MAP.get(getArguments().getString(ARG_ITEM_ID));
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_volunteer_detail, container, false);
        if (mItem != null) {
            ((TextView) rootView.findViewById(R.id.volunteer_email)).setText(mItem.email);
           	((TextView) rootView.findViewById(R.id.volunteer_name)).setText(mItem.name == "null" ? "Not provided": mItem.name);
            ((TextView) rootView.findViewById(R.id.mars_born)).setText(mItem.mars_born? "Yes" : "No");
            ((ProgressBar) rootView.findViewById(R.id.avoid_people)).setProgress(mItem.avoid_people);
            ((ProgressBar) rootView.findViewById(R.id.locked_up)).setProgress(mItem.locked_up);
            ((ProgressBar) rootView.findViewById(R.id.round_trip)).setProgress(mItem.round_trip);
            ((ProgressBar) rootView.findViewById(R.id.like_brown)).setProgress(mItem.like_brown);
            ((TextView) rootView.findViewById(R.id.lat_long)).setText(mItem.lat_long.toString());
            ((TextView) rootView.findViewById(R.id.score)).setText(mItem.score.toString());
            ((TextView) rootView.findViewById(R.id.percentile)).setText(mItem.percentile.toString());
        }
        return rootView;
    }
}
