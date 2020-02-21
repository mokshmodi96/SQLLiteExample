package com.icon.moksh.sqliteexample;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Add_StudentActivity extends AppCompatActivity {

    EditText edname, edarea, edphno;
    Button btnsubmit;
    DBO obj;
    String action = "";
    int id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add__student);

        edname = findViewById(R.id.editText_name);
        edarea = findViewById(R.id.editText_area);
        edphno = findViewById(R.id.editText_phno);
        btnsubmit = findViewById(R.id.button_add);
        obj = new DBO(this);

        Intent i = getIntent();
        if(i.getStringExtra("action") != null )
        {
             action = i.getStringExtra("action");
             id = i.getExtras().getInt("id");
            String name = i.getStringExtra("name");
            String area = i.getStringExtra("area");
            String phone = i.getStringExtra("phone");

            edname.setText(name);
            edarea.setText(area);
            edphno.setText(phone);


//            Toast.makeText(getApplicationContext(),"id = "+id,Toast.LENGTH_LONG).show();
        }


        btnsubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = edname.getText().toString().trim();
                String area = edarea.getText().toString().trim();
                String phone = edphno.getText().toString().trim();

                if(action == null  || action == "" )
                {
                    obj.add(name, area, phone);
                    Toast.makeText(getApplicationContext(),"Data Inserted",Toast.LENGTH_LONG).show();
                }
                else {
                    obj.edit(id,name,area,phone);
                    Toast.makeText(getApplicationContext(),"Data Updated",Toast.LENGTH_LONG).show();

                }


                Intent i = new Intent(Add_StudentActivity.this,MainActivity.class);
                startActivity(i);
             //   finish();
            }
        });

    }

}
