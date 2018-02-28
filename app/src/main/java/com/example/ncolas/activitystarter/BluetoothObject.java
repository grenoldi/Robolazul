package com.example.ncolas.activitystarter;

import android.os.Parcel;
import android.os.ParcelUuid;
import android.os.Parcelable;

/**
 * Created by Nícolas on 15/10/2017.
 */

public class BluetoothObject implements Parcelable
{
    private String bluetooth_name;
    private String bluetooth_address;
    private int bluetooth_state;
    private int bluetooth_type;
    private ParcelUuid[] bluetooth_uuids;
    private int bluetooth_rssi;

    public String getBluetoothName()
    {
        return bluetooth_name;
    }

    public void setBluetoothName(String bluetooth_name)
    {
        this.bluetooth_name = bluetooth_name;
    }

    public String getBlueoothAddress()
    {
        return bluetooth_address;
    }

    public void setBluetoothAddress(String bluetooth_address)
    {
        this.bluetooth_address = bluetooth_address;
    }

    public int getBluetoothState()
    {
        return bluetooth_state;
    }

    public void setBluetoothState(int bluetooth_state)
    {
        this.bluetooth_state = bluetooth_state;
    }

    public void setBluetoothType(int bluetooth_type)
    {
        this.bluetooth_type = bluetooth_type;
    }

    public ParcelUuid[] getBluetooth_uuids()
    {
        return bluetooth_uuids;
    }

    public void setBluetooth_uuids(ParcelUuid[] bluetooth_uuids)
    {
        this.bluetooth_uuids = bluetooth_uuids;
    }

    public int getBluetooth_rssi() {
        return bluetooth_rssi;
    }

    public void setBluetooth_rssi(int bluetooth_rssi) {
        this.bluetooth_rssi = bluetooth_rssi;
    }

    //parcelable part (What is it again ? Understand)

    //Why the hell have two constructors ?? And an empty one ????
    public BluetoothObject()
    {

    }

    public BluetoothObject(Parcel in)
    {
        super();
        readFromParcel(in);
    }

    public void readFromParcel(Parcel in)
    {
        bluetooth_name = in.readString();
    }

    public static final Parcelable.Creator<BluetoothObject> CREATOR = new Parcelable.Creator<BluetoothObject>()
    {
        public BluetoothObject createFromParcel(Parcel in)
        {
            return new BluetoothObject(in);
        }

        public BluetoothObject[] newArray(int size)
        {
            return new BluetoothObject[size];
        }
    };

    @Override
    public int describeContents()
    {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel out, int flags)
    {
        out.writeString(bluetooth_name);
    }
}
