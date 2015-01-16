package org.kyledef.sensordemos;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.support.v4.app.NavUtils;
import android.view.MenuItem;

import org.kyledef.sensordemos.Helpers.SensorFactory;
import org.kyledef.sensordemos.fragments.SensorDetailFragment;


/**
 * An activity representing a single Sensor detail screen. This
 * activity is only used on handset devices. On tablet-size devices,
 * item details are presented side-by-side with a list of items
 * in a {@link SensorListActivity}.
 * <p/>
 * This activity is mostly just a 'shell' activity containing nothing
 * more than a {@link org.kyledef.sensordemos.fragments.SensorDetailFragment}.
 */
public class SensorDetailActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensor_detail);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        if (savedInstanceState == null) {

            Bundle arguments = new Bundle();
            arguments.putInt(SensorDetailFragment.SENSOR_POSITION, getIntent().getIntExtra(SensorDetailFragment.SENSOR_POSITION, 0));

            int position = getIntent().getIntExtra(SensorDetailFragment.SENSOR_POSITION, 0);
            Fragment fragment = SensorFactory.getInstance().getSensorFragment(position);
            fragment.setArguments(arguments);
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.sensor_detail_container, fragment)
                    .commit();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            // This ID represents the Home or Up button. In the case of this
            // activity, the Up button is shown. Use NavUtils to allow users
            // to navigate up one level in the application structure. For
            // more details, see the Navigation pattern on Android Design:
            //
            // http://developer.android.com/design/patterns/navigation.html#up-vs-back
            //
            NavUtils.navigateUpTo(this, new Intent(this, SensorListActivity.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
