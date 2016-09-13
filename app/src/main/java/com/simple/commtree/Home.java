package com.simple.commtree;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Home extends AppCompatActivity {

public Button search;
    private EditText et;
    private String name;
    private FirebaseDatabase database;
    private  DatabaseReference myRef ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        et = (EditText) findViewById(R.id.editText);
        database = FirebaseDatabase.getInstance();



      /*  Intent sender = getIntent();
        if(sender.getExtras().getString("ComingFrom") != null) {
            String extraData = sender.getExtras().getString("ComingFrom");

            setTitle("Hi " + extraData);
        }*/

search= (Button) findViewById(R.id.button2);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Intent intent = new Intent(Home.this, Results.class);
                startActivity(intent);
               /* name = et.getText().toString();
                myRef = database.getReference(name);

              myRef.setValue("Hello, World!");*/

            }


        });


    }
}
