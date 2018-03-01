package com.laioffer.eventreporter;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

/**
 * Skeleton of an Android Things activity.
 * <p>
 * Android Things peripheral APIs are accessible through the class
 * PeripheralManagerService. For example, the snippet below will open a GPIO pin and
 * set it to HIGH:
 * <p>
 * <pre>{@code
 * PeripheralManagerService service = new PeripheralManagerService();
 * mLedGpio = service.openGpio("BCM6");
 * mLedGpio.setDirection(Gpio.DIRECTION_OUT_INITIALLY_LOW);
 * mLedGpio.setValue(true);
 * }</pre>
 * <p>
 * For more complex peripherals, look for an existing user-space driver, or implement one if none
 * is available.
 *
 * @see <a href="https://github.com/androidthings/contrib-drivers#readme">https://github.com/androidthings/contrib-drivers#readme</a>
 */
public class EventActivity extends AppCompatActivity {

    private Fragment mEventsFragment;
    private Fragment mEventMapFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);
        if (mEventsFragment == null) {
            mEventsFragment = new EventsFragment();
        }



        getSupportFragmentManager().beginTransaction().
                add(R.id.relativelayout_event, mEventsFragment).commit();

        BottomNavigationView bottomNavigationView = (BottomNavigationView)
                findViewById(R.id.navigation);

        // Set Item click listener to the menu items
        bottomNavigationView.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.action_event_list:
                                getSupportFragmentManager().beginTransaction()
                                        .replace(R.id.relativelayout_event,
                                                mEventsFragment).commit();
                                break;
                            case R.id.action_event_map:
                                if (mEventMapFragment == null) {
                                    mEventMapFragment = new EventMapFragment();
                                }
                                getSupportFragmentManager().beginTransaction()
                                        .replace(R.id.relativelayout_event,
                                                mEventMapFragment).commit();

                        }
                        return false;
                    }
                });


    }
}
