package com.example.ncolas.activitystarter;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;

import static com.example.ncolas.activitystarter.R.id.strategy_name;


public class TelaEstrategia extends AppCompatActivity {
    DBStrategies db_my_strategies;
    TextView tv_robot;
    FloatingActionMenu materialDesignFAM;
    FloatingActionButton floatingActionButton1, floatingActionButton2, floatingActionButton3;
    View alertLayout;
    View delete_layout;
    View update_layout;
    TextView tv_strategy;
    Button btn_refresh;


    //TODO: the programmer that supplied this example used "final" attribute, but Android Studio doesn't let me, only private (and others). Why?
    private EditText et_strategy_name;
    private EditText et_character;
    private EditText et_strategy_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_estrategia);

        db_my_strategies = new DBStrategies(this);

        materialDesignFAM = (FloatingActionMenu) findViewById(R.id.material_design_android_floating_action_menu);
        floatingActionButton1 = (FloatingActionButton) findViewById(R.id.material_design_floating_action_menu_item1); //Add
        floatingActionButton2 = (FloatingActionButton) findViewById(R.id.material_design_floating_action_menu_item2); //Edit
        floatingActionButton3 = (FloatingActionButton) findViewById(R.id.material_design_floating_action_menu_item3); //Delete

        btn_refresh = (Button) findViewById(R.id.refresh_strategies);

        floatingActionButton1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //TODO: when possible, create a method to inflate the alert dialog
                LayoutInflater inflater = getLayoutInflater(); //inflates dialog box for adding strategy
                alertLayout = inflater.inflate(R.layout.strategy_custom_dialog, null);
                et_strategy_name = (EditText) alertLayout.findViewById(strategy_name);
                et_character = (EditText) alertLayout.findViewById(R.id.character);

                //Setting up dialog box
                AlertDialog.Builder alert = new AlertDialog.Builder(TelaEstrategia.this);
                alert.setTitle("Add Strategy");
                // this is set the view from XML inside AlertDialog
                alert.setView(alertLayout);
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
                        String name = et_strategy_name.getText().toString();
                        String character = et_character.getText().toString();
                        String rName = tv_robot.getText().toString();
                        Boolean result = db_my_strategies.insertData(name,character, rName);
                        if (result)
                        {
                            Toast.makeText(TelaEstrategia.this, "Data Inserted Successfully", Toast.LENGTH_SHORT).show();
                        }

                        else
                        {
                            Toast.makeText(TelaEstrategia.this, "Data Insertion Failed", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                AlertDialog dialog = alert.create();
                dialog.show();
            }
        });

        floatingActionButton2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //Toast.makeText(TelaEstrategia.this, "You Clicked edit button", Toast.LENGTH_SHORT).show();
                LayoutInflater inflater = getLayoutInflater(); //inflates dialog box for updating strategy
                update_layout = inflater.inflate(R.layout.strategy_update, null);
                et_strategy_name = (EditText) update_layout.findViewById(R.id.new_strategy_name);
                et_character = (EditText) update_layout.findViewById(R.id.new_character);


                AlertDialog.Builder update_dialog = new AlertDialog.Builder(TelaEstrategia.this);
                update_dialog.setTitle("Edit Strategy");

                //final EditText new_strategy_name = new EditText(TelaEstrategia.this);

                update_dialog.setView(update_layout);
                update_dialog.setCancelable(false);

                update_dialog.setPositiveButton("Update", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {
                        String id = et_strategy_id.getText().toString();
                        String name = et_strategy_name.getText().toString();
                        String character = et_character.getText().toString();

                        Boolean result = db_my_strategies.updateData(name, character);
                        if(result)
                        {
                            Toast.makeText(TelaEstrategia.this, "Data Updated Successfully", Toast.LENGTH_SHORT).show();
                        }

                        else
                        {
                            Toast.makeText(TelaEstrategia.this,"No Rows Affected", Toast.LENGTH_SHORT).show();
                        }
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

        floatingActionButton3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //Toast.makeText(TelaEstrategia.this, "You Clicked delete button", Toast.LENGTH_SHORT).show();

                LayoutInflater inflater = getLayoutInflater(); //inflates dialog box for adding strategy
                delete_layout = inflater.inflate(R.layout.strategy_delete, null);

                et_strategy_id = (EditText) delete_layout.findViewById(R.id.strategy_id_delete);

                AlertDialog.Builder delete_dialog = new AlertDialog.Builder(TelaEstrategia.this);
                delete_dialog.setTitle("Delete Strategy");

                delete_dialog.setView(delete_layout);
                delete_dialog.setCancelable(false);

                delete_dialog.setPositiveButton("Delete", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {
                        String id = et_strategy_id.getText().toString();
                        int result = db_my_strategies.deleteData(id);

                        Toast.makeText(TelaEstrategia.this, result+" :Row(s) Deleted", Toast.LENGTH_SHORT).show();

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

            tv_robot = (TextView) findViewById(R.id.chosen_robot);

        Intent intent = getIntent();
        if(getIntent() != null)
        {
             Bundle bd = intent.getExtras();

            if(bd != null)
            {
                String robot = bd.getString("RobotName");
                tv_robot = (TextView) findViewById(R.id.chosen_robot);
                tv_robot.setText(robot);
                //Toast.makeText(TelaEstrategia.this, robot, Toast.LENGTH_SHORT).show();
            }
        }

    }

    public void listStrategies(View view)
    {
        //Toast.makeText(TelaEstrategia.this,"Testing button", Toast.LENGTH_SHORT).show();
        Cursor res = db_my_strategies.getAllStrategies();
        StringBuffer stringBuffer = new StringBuffer();
        tv_strategy = (TextView) findViewById(R.id.strategy);

        if(res!=null && res.getCount()>0)
        {
            while (res.moveToNext())
                {
                    //stringBuffer.append("Id: "+res.getString(0)+"\n");
                    stringBuffer.append("Strategy Name: "+res.getString(1)+"\n");
                    stringBuffer.append("Character Sent: "+res.getString(2)+"\n");
                }
                //Toast.makeText(TelaEstrategia.this,stringBuffer, Toast.LENGTH_SHORT).show();
                tv_strategy.setText(stringBuffer);

                tv_strategy.setMovementMethod(new ScrollingMovementMethod());
                Toast.makeText(this,"Data Retrieved Successfully",Toast.LENGTH_SHORT).show();
        }

        else
        {
            tv_strategy.setText(null);
            Toast.makeText(this,"No Data to Retrieve",Toast.LENGTH_SHORT).show();
        }

    }
}
