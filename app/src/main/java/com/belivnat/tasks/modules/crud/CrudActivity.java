package com.belivnat.tasks.modules.crud;

import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.belivnat.tasks.BaseActivity;
import com.belivnat.tasks.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;

public class CrudActivity extends BaseActivity implements CrudAdapter.CrudOnClickListener{
    AppDatabase db;
    FloatingActionButton fab_additem;
    RecyclerView crudRecyclerView;
    CrudAdapter crudAdapter;
    List<String> someDataList;
    TextView txtNoData;
    String enteredData;
    CrudEntity crudEntity;
    List<CrudEntity> crudEntities = new ArrayList<>();
    ProgressBar progressBar;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crud);
        someDataList = new ArrayList<>();
        progressBar = findViewById(R.id.loading_spinner);
        crudRecyclerView = findViewById(R.id.rv_crud);
        txtNoData = findViewById(R.id.txt_crud_nodata);
        fab_additem = findViewById(R.id.fab_additem);
        fab_additem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fabAction();
            }
        });
        getDataFromLocalDB();
        crudRecyclerView.setLayoutManager(
                new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        crudAdapter = new CrudAdapter(getApplicationContext(),this, someDataList);
        crudRecyclerView.setAdapter(crudAdapter);
    }
    public void getDataFromLocalDB() {
        progressBar.setVisibility(View.VISIBLE);
        db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "crud").fallbackToDestructiveMigration().build();
        Executors.newSingleThreadExecutor().execute(new Runnable() {
            @Override
            public void run() {
                crudEntities =  db.crudDao().getAll();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        initDataFromDB();
                    }
                });
            }
        });
    }
    public void initDataFromDB() {
        for(CrudEntity crudEntity: crudEntities) {
            someDataList.add(crudEntity.someDataList);
        }
        progressBar.setVisibility(View.GONE);
    }
    public void fabAction() {
        LayoutInflater layoutInflater = LayoutInflater.from(this);
        final View view = layoutInflater.inflate(R.layout.crud_add_dialog, null);
        final AlertDialog alertDialog = new AlertDialog.Builder(this).create();
        alertDialog.setTitle("Add Data");
        alertDialog.setIcon(getResources().getDrawable(android.R.drawable.ic_input_add));
        alertDialog.setCancelable(false);
        alertDialog.setMessage("Please Add some Data below");


        final EditText someData = view.findViewById(R.id.et_Some_datatoadd);

        alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                crudEntity = new CrudEntity();
                enteredData = someData.getText().toString();
                crudEntity.someDataList = enteredData;
                Executors.newSingleThreadExecutor().execute(new Runnable() {
                    @Override
                    public void run() {
                        db.crudDao().insertAll(crudEntity);
                        crudEntities =  db.crudDao().getAll();
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                someDataList.add(enteredData);
                                if (crudEntities.size() == 0) {
                                    initDataFromDB();
                                }
                                updateList(false,0);
                            }
                        });
                    }
                });
            }
        });


        alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                alertDialog.dismiss();
            }
        });


        alertDialog.setView(view);
        alertDialog.show();
    }
    public void showDeleteConfirmation(final int position) {
        new AlertDialog.Builder(this)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setTitle("Delete?")
                .setMessage("Are you sure to Delete")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Executors.newSingleThreadExecutor().execute(new Runnable() {
                            @Override
                            public void run() {
                                db.crudDao().delete(crudEntities.get(position));
                                crudEntities =  db.crudDao().getAll();
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        someDataList.remove(position);
                                        if (crudEntities.size() == 0) {
                                            initDataFromDB();
                                        }
                                        updateList(false,0);
                                    }
                                });
                            }
                        });
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //set what should happen when negative button is clicked
                        Toast.makeText(getApplicationContext(),"Nothing Happened",Toast.LENGTH_LONG).show();
                    }
                })
                .show();
    }
    public void updateList(boolean shouldItemChange, int position) {
        if(shouldItemChange) {
            crudAdapter.notifyItemChanged(position);
        } else {
            crudAdapter.notifyDataSetChanged();
        }
        if (someDataList.size() == 0) {
            txtNoData.setVisibility(View.VISIBLE);
            crudRecyclerView.setVisibility(View.GONE);
        } else {
            txtNoData.setVisibility(View.GONE);
            crudRecyclerView.setVisibility(View.VISIBLE);
        }

    }
    @Override
    public void onClickedCrudList(int position) {
        //Click listener for whole row
    }

    @Override
    public void onEdit(int position) {
        //something is edited over here
        //in future if we want to do search we can do it here
    }

    @Override
    public void onDelete(final int position) {
       showDeleteConfirmation(position);
    }

    @Override
    public void onSave(final int position, final String updatedData) {
        crudEntities.get(position).someDataList = updatedData;
        Executors.newSingleThreadExecutor().execute(new Runnable() {
            @Override
            public void run() {
                someDataList.set(position, updatedData);
                db.crudDao().update(crudEntities.get(position));
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        updateList(true, position);
                        Toast.makeText(getApplicationContext(),"Saved the Data!", Toast.LENGTH_SHORT)
                                .show();
                    }
                });
            }
        });
    }
}
