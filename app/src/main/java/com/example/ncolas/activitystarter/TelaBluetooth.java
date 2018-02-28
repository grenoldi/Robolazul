package com.example.ncolas.activitystarter;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

import static android.bluetooth.BluetoothDevice.ACTION_FOUND;


public class TelaBluetooth extends AppCompatActivity {

    private ListView found_BT_devices;
    private ArrayList<String> mDeviceList;
    private Button btn_scanBTdevices;


    //TODO: this activity doesn't work at all... Figure out why.
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_bluetooth);

        BluetoothAdapter bt_adapter = BluetoothAdapter.getDefaultAdapter();

        found_BT_devices = (ListView) findViewById(R.id.found_devices);
        bt_adapter.startDiscovery();

        btn_scanBTdevices = (Button) findViewById(R.id.scanBTDevices);

        IntentFilter filter = new IntentFilter(ACTION_FOUND);
        registerReceiver(bt_broadcastReceiver, filter);
    }

    @Override
    public void onDestroy()
    {
        //CAUTION: ALWAYS REMEMBER TO UNREGISTER BROADCAST RECEIVER at onDestroy() method
        unregisterReceiver(bt_broadcastReceiver);
        super.onDestroy();
    }

    private final BroadcastReceiver bt_broadcastReceiver = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (BluetoothDevice.ACTION_FOUND.equals(action)) {
                BluetoothDevice device = intent
                        .getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                mDeviceList.add(device.getName() + "\n" + device.getAddress());
                Log.i("BT", device.getName() + "\n" + device.getAddress());
                found_BT_devices.setAdapter(new ArrayAdapter<String>(context,
                        android.R.layout.simple_list_item_1, mDeviceList));
            }
        }
    };

    public void scanForBluetoothDevices(View view)
    {
        Intent i = new Intent(this, ListaDispositivos.class);
        startActivity(i);
    }


}
