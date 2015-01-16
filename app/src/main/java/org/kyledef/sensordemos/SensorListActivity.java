package org.kyledef.sensordemos;

import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;

import org.kyledef.sensordemos.Helpers.SensorFactory;
import org.kyledef.sensordemos.fragments.SensorDetailFragment;
import org.kyledef.sensordemos.fragments.SensorListFragment;

import java.util.Iterator;
import java.util.List;


/**
 * An activity representing a list of Sensors. This activity
 * has different presentations for handset and tablet-size devices. On
 * handsets, the activity presents a list of items, which when touched,
 * lead to a {@link SensorDetailActivity} representing
 * item details. On tablets, the activity presents the list of items and
 * item details side-by-side using two vertical panes.
 * <p/>
 * The activity makes heavy use of fragments. The list of items is a
 * {@link org.kyledef.sensordemos.fragments.SensorListFragment} and the item details
 * (if present) is a {@link org.kyledef.sensordemos.fragments.SensorDetailFragment}.
 * <p/>
 * This activity also implements the required
 * {@link org.kyledef.sensordemos.fragments.SensorListFragment.SelectionCallback} interface
 * to listen for item selections.
 */
public class SensorListActivity extends ActionBarActivity  implements SensorListFragment.SelectionCallback {

    /**
     * Whether or not the activity is in two-pane mode, i.e. running on a tablet
     * device.
     */
    private boolean mTwoPane;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensor_list);

        List<Sensor> deviceSensors = ((SensorManager) getSystemService(Context.SENSOR_SERVICE)).getSensorList(Sensor.TYPE_ALL);
        System.out.println("Total Number of Sensors Available: " + deviceSensors.size());
        Iterator<Sensor> i = deviceSensors.iterator();
        while(i.hasNext()){
            Sensor sensor = i.next();
            System.out.printf("Sensor %s v %d is provided by %s \n", sensor.getName(),sensor.getVersion(), sensor.getVendor());
//            System.out.printf("Sensor %s has a reporting mode of %d. Is it a wake up sensor: %1$b\n", sensor.getName(), sensor.getReportingMode(), sensor.isWakeUpSensor());
        }

        if (findViewById(R.id.sensor_detail_container) != null) {
            // The detail container view will be present only in the
            // large-screen layouts (res/values-large and
            // res/values-sw600dp). If this view is present, then the
            // activity should be in two-pane mode.
            mTwoPane = true;

            // In two-pane mode, list items should be given the
            // 'activated' state when touched.
            ((SensorListFragment) getSupportFragmentManager()
                    .findFragmentById(R.id.sensor_list))
                    .setActivateOnItemClick(true);
        }


    }

    /**
     * Callback method from {@link org.kyledef.sensordemos.fragments.SensorListFragment.SelectionCallback}
     * indicating that the item with the given ID was selected.
     */
    @Override
    public void onItemSelected(int position) {

        if (mTwoPane) {
            Bundle arguments = new Bundle();
            arguments.putInt(SensorDetailFragment.SENSOR_POSITION, position);

            Fragment fragment = SensorFactory.getInstance().getSensorFragment(position);
            fragment.setArguments(arguments);
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.sensor_detail_container, fragment)
                    .commit();

        } else {
            // In single-pane mode, simply start the detail activity
            // for the selected item ID.
            Intent detailIntent = new Intent(this, SensorDetailActivity.class);
            detailIntent.putExtra(SensorDetailFragment.SENSOR_POSITION, position);
            startActivity(detailIntent);
        }
    }
}
