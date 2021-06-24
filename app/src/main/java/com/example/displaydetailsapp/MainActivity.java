package com.example.displaydetailsapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerview;
    List<PersonDetails> detailsList;
    DetailsAdapter adaptery;
    SharedPreferences sp;
    Context context;

    EditText pemail,pcontact;
    Button displaybtn;
    String persomemailid,persomcontact;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pemail=findViewById(R.id.personemail);
        pcontact=findViewById(R.id.personcontact);

        displaybtn=findViewById(R.id.display);
        recyclerview=(RecyclerView) findViewById(R.id.recyclerview);

        detailsList=new ArrayList<>();
        LinearLayoutManager layoutManager=new LinearLayoutManager(getApplicationContext());
        recyclerview.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        recyclerview.setLayoutManager(layoutManager);
        adaptery=new DetailsAdapter(context,detailsList);
        recyclerview.setAdapter(adaptery);



        displaybtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String eid=pemail.getText().toString();
                String econtact=pcontact.getText().toString();


                sp=getSharedPreferences("persondetails",MODE_PRIVATE);
                SharedPreferences.Editor e=sp.edit();

                if(eid.isEmpty() && econtact.isEmpty()){
                    Toast.makeText(MainActivity.this,"Enter The details",Toast.LENGTH_SHORT).show();
                }
                else if (!sp.getString("useremail"+eid,"").toString().equals("")&&!sp.getString("contact"+eid,"").toString().equals("")){
                    Toast.makeText(getBaseContext(), "User is already Registered",Toast.LENGTH_SHORT).show();
                }
                else{
                    try {
                        detailsList.add(new PersonDetails(eid, econtact));
                        adaptery.notifyItemInserted(detailsList.size() - 1);
                        String restoredemail = sp.getString("useremail", null);
                        String restorecontact=sp.getString("contact",null);
                        if(restoredemail.matches(eid)&&restorecontact.matches(econtact))
                        {
                            Toast.makeText(MainActivity.this,"Already user exits",Toast.LENGTH_SHORT).show();
                        }
                        else
                        {
                            e.putString("useremail"+pemail.getText().toString(),pemail.getText().toString());
                            e.putString("contact"+pcontact.getText().toString(),pcontact.getText().toString());
                            e.commit();//---saves the values---
                            Toast.makeText(MainActivity.this, "User Details Registered",Toast.LENGTH_SHORT).show();
                            pemail.setText("");
                            pcontact.setText("");
                        }

                    }catch (NumberFormatException exception){
                        Toast.makeText(MainActivity.this,"Cannot Display",Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }


}