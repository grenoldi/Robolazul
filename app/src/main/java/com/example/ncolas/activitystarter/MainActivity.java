package com.example.ncolas.activitystarter;

import android.bluetooth.BluetoothAdapter;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.app.Activity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import static android.R.attr.mode;


public class MainActivity extends AppCompatActivity
{
    private final static String TAG = "MainActivity";

    boolean bt_state; // get initial BT state

    BluetoothAdapter bt_adapter;


    // Create a BroadcastReceiver for ACTION_FOUND
    private final BroadcastReceiver bt_broadcastReceiver = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent)
        {
            String action = intent.getAction();
            if(action.equals(bt_adapter.ACTION_STATE_CHANGED))
            {
                final int state = intent.getIntExtra(BluetoothAdapter.EXTRA_STATE, bt_adapter.ERROR);

                switch(state)
                {
                    case BluetoothAdapter.STATE_OFF:
                        Log.d(TAG, "onReceive: STATE OFF"); //when it's  OFF, shows this log
                        break;
                    case BluetoothAdapter.STATE_TURNING_OFF:
                        Log.d(TAG, "bt_broadcastReceiver1: STATE TURNING OFF"); //when it's done turning OFF, shows this log
                        break;
                    case BluetoothAdapter.STATE_ON:
                        Log.d(TAG, "bt_broadcastReceiver: STATE ON"); //when it's ON, shows this log
                        break;
                    case BluetoothAdapter.STATE_TURNING_ON:
                        Log.d(TAG, "bt_broadcastReceiver: STATE TURNING ON"); //when it's done turning ON, shows this log
                        break;
                }
            }
        }

    };

    //TODO: Figure out if my device must be discoverable or not
    //Broadcast Receiver below allow devices to discover mine
    private final BroadcastReceiver broadRcvr_discoverable = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            final String action_discoverable = intent.getAction();


            if(action_discoverable.equals(BluetoothAdapter.ACTION_SCAN_MODE_CHANGED))
            {
                final int mode = intent.getIntExtra(BluetoothAdapter.EXTRA_SCAN_MODE, BluetoothAdapter.ERROR);
                switch(mode)
                {
                    //Device is in discoverable mode
                    case BluetoothAdapter.SCAN_MODE_CONNECTABLE_DISCOVERABLE:
                        Log.d(TAG, "broadRcvr_discoverable: Discoverability Enabled");
                        break;
                    case BluetoothAdapter.SCAN_MODE_CONNECTABLE:
                        Log.d(TAG, "broadRcvr_discoverable: Discoverability Disabled. Able to receive connections");
                        break;
                    case BluetoothAdapter.SCAN_MODE_NONE:
                        Log.d(TAG, "broadRcvr_discoverable:Discoverability Disabled. Not able to receive connections");
                        break;
                    case BluetoothAdapter.STATE_CONNECTING:
                        Log.d(TAG, "broadRcvr_discoverable: Connecting...");
                        break;

                    case BluetoothAdapter.STATE_CONNECTED:
                        Log.d(TAG,"broadRcvr_discoverable: Connected");
                        break;
                }
            }
        }
    };

    @Override
    protected void onDestroy()
    {
        Log.d(TAG, "onDestroy: called");
        super.onDestroy();
        unregisterReceiver(bt_broadcastReceiver); //stops broadcast receiver when the app is closed
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        //TODO: TRY TO UNDERSTAND WHY I DON'T HAVE TO CREATE THE BUTTONS
         final Button btn_torneio;
         final Button btn_testes;

        //Getting bluetooth adapter
        bt_adapter = BluetoothAdapter.getDefaultAdapter();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar tb_home_toolbar = (Toolbar) findViewById(R.id.toolbar);
        btn_torneio = (Button) findViewById(R.id.tournament);
        btn_testes = (Button) findViewById(R.id.debugging);


        setSupportActionBar(tb_home_toolbar);

    }

    public boolean checkBTState()
    {
        if(bt_adapter.isEnabled())
        {
            bt_state = true; //bluetooth already enabled
        }

        else if(!bt_adapter.isEnabled())
        {
            bt_state = false; //bluetooth is disabled
        }
        return bt_state;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        //Inflate the menu; this adds items to the action bar, if it is present
        MenuInflater home_menu_inflater = getMenuInflater();
        home_menu_inflater.inflate(R.menu.home_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        //TODO: Think about some ideas to this icon
        /*if(item.getItemId() == R.id.action_setting)
        {
            Toast.makeText(MainActivity.this, "You have clicked on setting action menu",Toast.LENGTH_SHORT).show();
        }*/

        //TODO: Same as above
         if(item.getItemId() == R.id.action_about_us)
        {
            Toast.makeText(MainActivity.this, "You have clicked on about us action menu",Toast.LENGTH_SHORT).show();
        }

        else if(item.getItemId() == R.id.action_bluetooth_on)
        {
            //TODO: Bug: The off button is not created at runtime (if bluetooth was already enabled on the app start). Solve this.

            if(checkBTState())
            {
                Log.d(TAG, "onClick: disabling bluetooth");
                item.setIcon(R.drawable.ic_bluetooth_white_18dp);
                disableBT();
            }
           else if(!checkBTState())
            {
                Log.d(TAG, "onClick: enabling bluetooth");
                item.setIcon(R.drawable.ic_bluetooth_off_white_18dp);
                //enableBT();
                enableBT_discoverable();
            }
        }

        /*
        else if(item.getItemId() == R.id.action_bluetooth_off)
        {
            Log.d(TAG, "onClick: disabling bluetooth");
            disableBT();
        }
        */
        //TODO:replace this icon
        else if (item.getItemId() == R.id.action_bluetooth_settings)
        {
            Intent bt_screen = new Intent(this, TelaBluetooth.class);
            startActivity(bt_screen);
        }

        return super.onOptionsItemSelected(item);
    }

    public void enableBT()
    {
        if(bt_adapter == null) //this case refers to a device that doesn't support bluetooth communication
        {
            Log.d(TAG, "enableBT: Does not have BT capability");
        }

        /*else if(bt_adapter.isEnabled())
        {
            Toast.makeText(MainActivity.this,"Bluetooth is already on!", Toast.LENGTH_SHORT).show();
        }*/

        else if(!bt_adapter.isEnabled())
        {
            Log.d(TAG, "enableBT: enabling bluetooth");
            Intent intent_enableBT = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivity(intent_enableBT);

            IntentFilter btIntent = new IntentFilter(BluetoothAdapter.ACTION_STATE_CHANGED);
            registerReceiver(bt_broadcastReceiver, btIntent);
        }

    }

    public void disableBT()
    {
        if(bt_adapter == null) //this case refers to a device that doesn't support bluetooth communication
        {
            Log.d(TAG, "enableBT: Does not have BT capability");
        }

        else if(bt_adapter.isEnabled())
        {
            Log.d(TAG, "enableBT: disabling bluetooth");
            bt_adapter.disable();
            IntentFilter btIntent = new IntentFilter(BluetoothAdapter.ACTION_STATE_CHANGED);
            registerReceiver(bt_broadcastReceiver, btIntent);
        }

       /* else if(!bt_adapter.isEnabled())
        {
            Toast.makeText(MainActivity.this,"Bluetooth already off!", Toast.LENGTH_SHORT).show();
        }*/
    }

    //Self-explained method title
    public void enableBT_discoverable()
    {
        Log.d(TAG, "broadRcvr_discoverable: Making device discoverable for 300 seconds");
        Intent discoverableIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
        //TODO: Amount of time the device is discoverable, in seconds. Find out which amount best fits.
        discoverableIntent.putExtra(BluetoothAdapter.EXTRA_DISCOVERABLE_DURATION, 120);
        startActivity(discoverableIntent);

        //Intent below will search for the method ACTION_SCAN_MODE_CHANGED. It is analysed on top of this class, on a if statement
        IntentFilter intentFilter = new IntentFilter(BluetoothAdapter.ACTION_SCAN_MODE_CHANGED);
        registerReceiver(broadRcvr_discoverable, intentFilter);
    }

    public void doChooseMode(View view)
    {
        if(view.getId() == R.id.tournament)
        {
            Intent tela_torneio = new Intent(this,TelaTorneio.class);
            startActivity(tela_torneio);
        }

        else if(view.getId() == R.id.debugging)
        {
            Intent tela_testes = new Intent(this,TelaTestes.class);
            startActivity(tela_testes);
        }

    }

}

