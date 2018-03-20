package com.example.ncolas.activitystarter;

/*-------------------------Last update on this activity: 28/02/18 by Nick-------------------------*/

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemSelectedListener;

import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static android.view.View.GONE;
import static android.view.View.inflate;
import static com.example.ncolas.activitystarter.R.id.robot_btn_list;
import static com.example.ncolas.activitystarter.R.id.robot_name;


//TODO: Discovered a bug: when I try to comeback from "TelaTorneio" to "MainActivity", app crashes. Figure out why. (FIXED, don't know how...)

public class TelaTorneio extends AppCompatActivity
{
    String selected_robot;
    //TODO: search the adequate modifier for each class attribute

    DBRobots dbr;

    Spinner sp_category;
    ArrayAdapter<String> dataAdapter;
    List<String> categoryList;
    Cursor cursor;

    View update_robot_layout;
    View add_robot_layout;
    View delete_robot_layout;


    EditText et_robot_name;
    EditText et_robot_category;

    //TextView for robot name
    TextView tv_robot_name;

    //TODO: change the button below's name, not used for this purpose anymore
    Button btn_ok;

    //Floating Action Menu to hold floating action buttons to perform changes into the robot's database
    FloatingActionMenu fab_menu_robot_settings;
    FloatingActionButton fab_button_robot_add;
    FloatingActionButton fab_button_robot_update;
    FloatingActionButton fab_button_robot_delete;

    //TODO: finish robot buttons > LinearLayout btnRobotList =

    //THESE HAVE TO GO
    ImageButton imgbtn_metalgarurumon;
    ImageButton imgbtn_bernadete;
    ImageButton imgbtn_lobo;
    ImageButton imgbtn_sonic;
    ImageButton imgbtn_sombra;
    ImageButton imgbtn_tonto;
    ImageButton imgbtn_lego;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_torneio);

        dbr = new DBRobots(this);
        categoryList = new ArrayList<String>();
        cursor = dbr.getAllCategories(dbr.getTABLE_NAME());

        //Creating  OK Button
        btn_ok = (Button) findViewById(R.id.goStrategy);

        //Creating TextView to display robot name
        tv_robot_name = (TextView) findViewById(robot_name);

        //Creating the spinner filled with all the robot categories from database
        sp_category = (Spinner) findViewById(R.id.sp_category);
        while(cursor.moveToNext()){
            categoryList.add(cursor.getString(0));
        }
        cursor.close();

        dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categoryList);

        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        sp_category.setAdapter(dataAdapter);

        //Creating Object of the FAB and FAB Menu classes and link to respectives XML ID's

        fab_menu_robot_settings = (FloatingActionMenu) findViewById(R.id.fab_menu_robot_settings);
        fab_button_robot_add = (FloatingActionButton) findViewById(R.id.item_add_robot);
        fab_button_robot_update = (FloatingActionButton) findViewById(R.id.item_update_robot);
        fab_button_robot_delete = (FloatingActionButton) findViewById(R.id.item_delete_robot);

        //Creating ImageButtons(THESE TOO HAVE TO GO)

        imgbtn_bernadete = (ImageButton) findViewById(R.id.Bernadete);
        imgbtn_lobo = (ImageButton) findViewById(R.id.Lobo);
        imgbtn_metalgarurumon = (ImageButton) findViewById(R.id.MetalGarurumon);
        imgbtn_sonic = (ImageButton) findViewById(R.id.Sonic);
        imgbtn_lego =  (ImageButton) findViewById(R.id.Lego);
        imgbtn_sombra = (ImageButton) findViewById(R.id.Sombra);
        imgbtn_tonto = (ImageButton)  findViewById(R.id.Tonto);

        fab_button_robot_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LayoutInflater inflater = getLayoutInflater(); //inflates dialog box for adding strategy
                add_robot_layout = inflater.inflate(R.layout.robot_add, null);
                et_robot_name = (EditText) add_robot_layout.findViewById(R.id.robot_name);
                et_robot_category = (EditText) add_robot_layout.findViewById(R.id.robot_category);

                //Setting up dialog box
                AlertDialog.Builder alert = new AlertDialog.Builder(TelaTorneio.this);
                alert.setTitle("Add Robot");
                // this is set the view from XML inside AlertDialog
                alert.setView(add_robot_layout);
                // disallow cancel of AlertDialog on click of back button and outside touch
                alert.setCancelable(false);
                alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //Toast.makeText(getBaseContext(), "Cancel clicked", Toast.LENGTH_SHORT).show();
                    }
                });

                alert.setPositiveButton("Save", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {

                        //TODO: actually create the robots buttons
                        Random r = new Random();
                        int id = r.nextInt(1001-100)+100;
                        String name = et_robot_name.getText().toString();
                        String category = et_robot_category.getText().toString();

                        Boolean result = dbr.addRobot(id, name, category);
                        if (result)
                        {
                            Toast.makeText(TelaTorneio.this, "Data Inserted Successfully", Toast.LENGTH_SHORT).show();
                        }

                        else
                        {
                            Toast.makeText(TelaTorneio.this, "Data Insertion Failed", Toast.LENGTH_SHORT).show();
                        }

                        Toast.makeText(TelaTorneio.this, "Build robot database to use the 'add robot' functionality :)", Toast.LENGTH_SHORT).show();
                    }
                });
                AlertDialog dialog = alert.create();
                dialog.show();
            }
        });

        fab_button_robot_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LayoutInflater inflater = getLayoutInflater(); //inflates dialog box for updating strategy
                update_robot_layout = inflater.inflate(R.layout.robot_update, null);
                et_robot_name = (EditText) update_robot_layout.findViewById(robot_name); //same ID in two different XML files, watch out
                et_robot_category = (EditText) update_robot_layout.findViewById(R.id.new_category);

                AlertDialog.Builder update_dialog = new AlertDialog.Builder(TelaTorneio.this);
                update_dialog.setTitle("Edit Robot");

                //final EditText new_strategy_name = new EditText(TelaEstrategia.this);

                update_dialog.setView(update_robot_layout);
                update_dialog.setCancelable(false);

                update_dialog.setPositiveButton("Update", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {
                        String name = et_robot_name.getText().toString();
                        String character = et_robot_category.getText().toString();

                        Boolean result = dbr.updateRobot(Integer.parseInt(dbr.getCOL_1()), name, character);
                        if(result)
                        {
                            Toast.makeText(TelaTorneio.this, "Data Updated Successfully", Toast.LENGTH_SHORT).show();
                        }

                        else
                        {
                            Toast.makeText(TelaTorneio.this,"No Rows Affected", Toast.LENGTH_SHORT).show();
                        }

                        Toast.makeText(TelaTorneio.this, "Build robot database to use the 'update robot' functionality :)", Toast.LENGTH_SHORT).show();
                    }
                });

                update_dialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //Toast.makeText(getBaseContext(), "Cancel clicked", Toast.LENGTH_SHORT).show();
                    }
                });

                AlertDialog dialog = update_dialog.create();
                dialog.show();
            }
        });

        fab_button_robot_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LayoutInflater inflater = getLayoutInflater(); //inflates dialog box for adding strategy
                delete_robot_layout = inflater.inflate(R.layout.robot_delete, null);

                et_robot_name = (EditText) delete_robot_layout.findViewById(R.id.robot_name);

                AlertDialog.Builder delete_dialog = new AlertDialog.Builder(TelaTorneio.this);
                delete_dialog.setTitle("Delete Robot");

                delete_dialog.setView(delete_robot_layout);
                delete_dialog.setCancelable(false);

                delete_dialog.setPositiveButton("Delete", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {
                        /*
                        TODO: Code below will depend on the robot database, that has not been created yet.
                        String id = et_strategy_id.getText().toString();
                        int result = db_my_strategies.deleteData(id);

                        Toast.makeText(TelaEstrategia.this, result+" :Row(s) Deleted", Toast.LENGTH_SHORT).show();
                        */
                        Toast.makeText(TelaTorneio.this, "Build robot database to use the 'delete robot' functionality :)", Toast.LENGTH_SHORT).show();
                    }
                });

                delete_dialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //Toast.makeText(getBaseContext(), "Cancel clicked", Toast.LENGTH_SHORT).show();
                    }
                });

                AlertDialog dialog = delete_dialog.create();
                dialog.show();


            }
        });

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

    public void updateRobotList(String category){

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
        startActivity(tela_estrategia); //IF doesn't work, put this uncomment this line and erase below


    }

}










