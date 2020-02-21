package com.icon.moksh.sqliteexample;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView lv;
    FloatingActionButton fab_add;
    ArrayList<Integer> sids = new ArrayList<>();
    ArrayList<String> snames = new ArrayList<>();
    ArrayList<String> sareas = new ArrayList<>();
    ArrayList<String> sphones = new ArrayList<>();
    StudentAdapter sadp;

    DBO obj;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lv = findViewById(R.id.listview);
        obj = new DBO(MainActivity.this);

        sadp = new StudentAdapter(MainActivity.this,sids,snames);
        getalldata();
        lv.setAdapter(sadp);
        sadp.notifyDataSetChanged();



        fab_add = findViewById(R.id.fab_add);


        fab_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this,Add_StudentActivity.class);
                startActivity(i);
//                finish();
            }
        });
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(MainActivity.this,Add_StudentActivity.class);
                i.putExtra("action","edit");
                i.putExtra("id",sids.get(position));
                i.putExtra("name",snames.get(position));
                i.putExtra("area",sareas.get(position));
                i.putExtra("phone",sphones.get(position));


                startActivity(i);
//                finish();
            }
        });

        lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setMessage("Do you want to delete this record?");
                builder.setCancelable(false);
                builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
//                        Toast.makeText(getApplicationContext(),"id="+sids.get(position),Toast.LENGTH_LONG).show();
                        obj.delete(sids.get(position));
                        sids.clear();
                        snames.clear();
                        sareas.clear();
                        sphones.clear();
                        getalldata();

                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

                builder.show();
                return true;
            }
        });
    }


    void getalldata()
    {
        sadp.notifyDataSetChanged();
        Cursor result = obj.getAllStudent();

        if(result.moveToFirst())
        {
            do
            {

                int id =result.getInt(0);
                String name = result.getString(1);
                String area = result.getString(2);
                String phone = result.getString(3);


//            Toast.makeText(getApplicationContext(),result.getColumnName(0) + " "+ name,Toast.LENGTH_LONG).show();

                sids.add(id);
                snames.add(name);
                sareas.add(area);
                sphones.add(phone);

            }while (result.moveToNext());
            sadp.notifyDataSetChanged();
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.mymenu,menu);
        return super.onCreateOptionsMenu(menu);


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        obj.Delete_Database();
        Toast.makeText(MainActivity.this,"Database Deleted",Toast.LENGTH_LONG).show();
        sadp.notifyDataSetChanged();
        return super.onOptionsItemSelected(item);
    }

}
