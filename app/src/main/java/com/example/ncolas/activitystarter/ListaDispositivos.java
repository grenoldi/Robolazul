package com.example.ncolas.activitystarter;

import android.app.ListActivity;

public class ListaDispositivos extends ListActivity
{/*
    private BluetoothAdapter bt_adapter;
    private ArrayList<BluetoothObject> found_devices_array;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_dispositivos);

        bt_adapter = BluetoothAdapter.getDefaultAdapter();

        displayFoundBTDevices();
    }

    private void displayFoundBTDevices()
    {
        found_devices_array = new ArrayList<BluetoothObject>();
        bt_adapter.startDiscovery();

        final BroadcastReceiver mreceiver = new BroadcastReceiver()
        {
            @Override
            public void onReceive(Context context, Intent intent)
            {
                String action = intent.getAction();

                if(BluetoothDevice.ACTION_FOUND.equals(action))
                {
                    BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                    //TODO: what the fuck is the line below ?
                    // Get the "RSSI" to get the signal strength as integer,
                    // but should be displayed in "dBm" units
                    int rssi= intent.getShortExtra(BluetoothDevice.EXTRA_RSSI, Short.MIN_VALUE);

                    //Create Device Objcet and add it to the arraylist of devices
                    BluetoothObject bluetoothObject = new BluetoothObject();
                    bluetoothObject.setBluetoothName(device.getName());
                    bluetoothObject.setBluetoothAddress(device.getAddress());
                    bluetoothObject.setBluetoothState(device.getBondState());
                    bluetoothObject.setBluetoothType(device.getType()); //TODO: requires minimun 18 API level, fix it
                    bluetoothObject.setBluetooth_uuids(device.getUuids());
                    bluetoothObject.setBluetooth_rssi(rssi);

                    found_devices_array.add(bluetoothObject);

                    //Pass context and data to the custom adpater
                    ListaDispositivosAdadpter adapter = new ListaDispositivosAdapter(getApplicationContext(), found_devices_array);

                    setListAdapter(adapter);

                }
            }
        };

        //Register the BroadcastReceiver
        IntentFilter filter = new IntentFilter(BluetoothDevice.ACTION_FOUND);
        registerReceiver(mreceiver, filter);
    }

    @Override
    protected void onPause()
    {
        super.onPause();
        bt_adapter.cancelDiscovery();
    }
    */
}
