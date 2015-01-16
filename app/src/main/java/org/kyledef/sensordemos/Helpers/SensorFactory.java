package org.kyledef.sensordemos.Helpers;

import org.kyledef.sensordemos.fragments.AccelerometerFragment;
import org.kyledef.sensordemos.fragments.BarometerFragment;
import org.kyledef.sensordemos.fragments.GPSFragment;
import org.kyledef.sensordemos.fragments.GyroscopeFragment;
import org.kyledef.sensordemos.fragments.LightFragment;
import org.kyledef.sensordemos.fragments.NetworkFragment;
import org.kyledef.sensordemos.fragments.SensorDetailFragment;

/**
 * Created by kyledef on 1/13/15.
 */
public class SensorFactory {
    private static SensorFactory instance = null;

    private SensorFactory(){

    }

    public SensorDetailFragment getSensorFragment(int fragment_id){
        switch (fragment_id){
            case 0:
                return new AccelerometerFragment();
            case 1:
                return new BarometerFragment();
            case 2:
                return new GPSFragment();
            case 3:
                return new GyroscopeFragment();
            case 4:
                return new LightFragment();
            case 5:
                return new NetworkFragment();
            default:
                return null;
        }
    }

    public static SensorFactory getInstance(){
        if (instance == null)instance = new SensorFactory();
        return instance;
    }
}
