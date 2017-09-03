package com.example.ncolas.activitystarter;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

import static android.R.attr.id;
import static android.view.View.GONE;
import static com.example.ncolas.activitystarter.R.id.parent;
import static com.example.ncolas.activitystarter.R.id.visible;
import static com.example.ncolas.activitystarter.R.styleable.View;
import android.view.View;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.TextView;
import android.widget.Toast;


//TODO: Discovered a bug: when I try to comeback from "TelaTorneio" to "MainActivity", app crashes. Figure out why.

public class TelaTorneio extends AppCompatActivity
{
    String selected_robot;
    //TODO: search the adequate modifier for each class attribute
    Spinner sp_category;
    ArrayAdapter<CharSequence> dataAdapter;
    ImageButton imgbtn_metalgarurumon;
    ImageButton imgbtn_bernadete;
    ImageButton imgbtn_lobo;
    ImageButton imgbtn_sonic;
    ImageButton imgbtn_sombra;
    ImageButton imgbtn_tonto;
    ImageButton imgbtn_lego;

    //TextView for robot name
    TextView tv_robot_name;

    //TODO: change the button below's name, not used for this purpose anymore
    Button btn_ok;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_torneio);


        //Creating ImageButtons

        imgbtn_bernadete = (ImageButton) findViewById(R.id.Bernadete);
        imgbtn_lobo = (ImageButton) findViewById(R.id.Lobo);
        imgbtn_metalgarurumon = (ImageButton) findViewById(R.id.MetalGarurumon);
        imgbtn_sonic = (ImageButton) findViewById(R.id.Sonic);
        imgbtn_lego =  (ImageButton) findViewById(R.id.Lego);
        imgbtn_sombra = (ImageButton) findViewById(R.id.Sombra);
        imgbtn_tonto = (ImageButton)  findViewById(R.id.Tonto);


        //Creating Button
        btn_ok = (Button) findViewById(R.id.goStrategy);

        //Creating TextView to display robot name
        tv_robot_name = (TextView) findViewById(R.id.robot_name);

        //Creating spinner element and array adapter
        sp_category = (Spinner) findViewById(R.id.sp_category);

        dataAdapter = ArrayAdapter.createFromResource(this, R.array.categories, android.R.layout.simple_spinner_item);

        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        sp_category.setAdapter(dataAdapter);

        sp_category.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View selectedItemView, int i, long id)
            {
                //Toast.makeText(parent.getContext(), parent.getItemAtPosition(i).toString(), Toast.LENGTH_SHORT).show();
                //TODO: search about the method getChildAt()
                ((TextView) parent.getChildAt(0)).setTextColor(Color.WHITE);
                ((TextView) parent.getChildAt(0)).setTextSize(20);

                String chosen_category = parent.getItemAtPosition(i).toString();
                switch(chosen_category)
                {
                    case "3 kg":

                                        imgbtn_lobo.setVisibility(android.view.View.VISIBLE);
                                        imgbtn_metalgarurumon.setVisibility(android.view.View.VISIBLE);
                                        imgbtn_bernadete.setVisibility(android.view.View.VISIBLE);
                                        imgbtn_lego.setVisibility(GONE);
                                        imgbtn_tonto.setVisibility(GONE);
                                        imgbtn_sombra.setVisibility(GONE);
                                        imgbtn_sonic.setVisibility(GONE);

                                        break;
                    case "500 g":

                                        imgbtn_lobo.setVisibility(GONE);
                                        imgbtn_metalgarurumon.setVisibility(GONE);
                                        imgbtn_bernadete.setVisibility(GONE);
                                        imgbtn_lego.setVisibility(GONE);
                                        imgbtn_tonto.setVisibility(GONE);
                                        imgbtn_sombra.setVisibility(android.view.View.VISIBLE);
                                        imgbtn_sonic.setVisibility(android.view.View.VISIBLE);

                                        break;
                    case "Follow Line":

                                        imgbtn_lobo.setVisibility(GONE);
                                        imgbtn_metalgarurumon.setVisibility(GONE);
                                        imgbtn_bernadete.setVisibility(GONE);
                                        imgbtn_lego.setVisibility(GONE);
                                        imgbtn_tonto.setVisibility(android.view.View.VISIBLE);
                                        imgbtn_sombra.setVisibility(GONE);
                                        imgbtn_sonic.setVisibility(GONE);

                                        break;
                    case "Lego":

                                        imgbtn_lobo.setVisibility(GONE);
                                        imgbtn_metalgarurumon.setVisibility(GONE);
                                        imgbtn_bernadete.setVisibility(GONE);
                                        imgbtn_lego.setVisibility(android.view.View.VISIBLE);
                                        imgbtn_tonto.setVisibility(GONE);
                                        imgbtn_sombra.setVisibility(GONE);
                                        imgbtn_sonic.setVisibility(GONE);

                                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView)
            {

            }

        });


    }



    public void showRobotName(View view)
    {


        String name = getResources().getResourceEntryName(view.getId());
        switch(view.getId())
        {
            case R.id.MetalGarurumon:   tv_robot_name.setText(name);
                                        break;

            case R.id.Lobo:             tv_robot_name.setText(name);
                                        break;

            case R.id.Bernadete:        tv_robot_name.setText(name);
                                        break;

            case R.id.Sonic:            tv_robot_name.setText(name);
                                        break;

            case R.id.Sombra:           tv_robot_name.setText(name);
                                        break;

            case R.id.Lego:             tv_robot_name.setText(name);
                                        break;

            case R.id.Tonto:            tv_robot_name.setText(name);
                                        break;
        }


    }

    public void goStrategy(View view)
    {
        Intent tela_estrategia = new Intent(this, TelaEstrategia.class);
        Bundle bd = new Bundle();
        bd.putString("RobotName",tv_robot_name.getText().toString());
        tela_estrategia.putExtras(bd);
        startActivity(tela_estrategia);
    }

}










