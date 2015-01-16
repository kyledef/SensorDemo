package org.kyledef.sensordemos.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import org.kyledef.sensordemos.R;

/**
 * A fragment representing a single Sensor detail screen.
 * This fragment is either contained in a {@link org.kyledef.sensordemos.SensorListActivity}
 * in two-pane mode (on tablets) or a {@link org.kyledef.sensordemos.SensorDetailActivity}
 * on handsets.
 */
public class SensorDetailFragment extends Fragment {
    /**
     * The fragment argument representing the item ID that this fragment
     * represents.
     */
    public static final String SENSOR_POSITION = "sensor_position";

    protected String mItem = "";

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public SensorDetailFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_sensor_detail, container, false);

        // Show the dummy content as text in a TextView.
        if (mItem != null) {
            ((TextView) rootView.findViewById(R.id.sensor_detail)).setText(mItem);
        }

        return rootView;
    }
}
