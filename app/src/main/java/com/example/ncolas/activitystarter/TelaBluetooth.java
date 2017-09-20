package com.example.ncolas.activitystarter;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import static android.R.attr.action;


public class TelaBluetooth extends AppCompatActivity {

    //TODO: this activity doesn't work at all... Figure out why.
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_bluetooth);

        BluetoothAdapter bt_adapter = BluetoothAdapter.getDefaultAdapter();
        IntentFilter filter = new IntentFilter();

        filter.addAction(BluetoothDevice.ACTION_FOUND);
        filter.addAction(BluetoothAdapter.ACTION_DISCOVERY_STARTED);
        filter.addAction(BluetoothAdapter.ACTION_DISCOVERY_FINISHED);

        registerReceiver(br_discovery, filter);
        if(bt_adapter.isDiscovering())
        {
            bt_adapter.cancelDiscovery();
        }
        bt_adapter.startDiscovery();

    }

    private final BroadcastReceiver br_discovery = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction(); //TODO: What is this string ?


            if (BluetoothAdapter.ACTION_DISCOVERY_STARTED.equals(action))
            {
                //discovery starts, we can show progress dialog or perform other tasks
            }

            else if (BluetoothAdapter.ACTION_DISCOVERY_FINISHED.equals(action))
            {
                //discovery finishes, dismiss progress dialog
            }

            else if (BluetoothDevice.ACTION_FOUND.equals(action))
            {
                //bluetooth device found
                BluetoothDevice device =  intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                String device_list = device.getName() + " - " + device.getAddress();
                Toast.makeText(context, device_list,Toast.LENGTH_SHORT).show();
            }
        }
    };

    @Override
    public void onDestroy()
    {
        //CAUTION: ALWAYS REMEMBER TO UNREGISTER BROADCAST RECEIVER at onDestroy() method
        unregisterReceiver(br_discovery);
        super.onDestroy();
    }
}
