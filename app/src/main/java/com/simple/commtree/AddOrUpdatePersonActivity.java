package com.simple.commtree;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class AddOrUpdatePersonActivity extends AppCompatActivity {

    Button bOK,bCancel;
    Person person;
    int position;
    EditText pName,pEmail,pPhone,pAge;
    CoordinatorLayout cl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_or_update_person);

        position = getIntent().getIntExtra("Position", -1);

        cl = (CoordinatorLayout) findViewById(R.id.cdlayout);

        pName = (EditText) findViewById(R.id.pName);
        pEmail = (EditText) findViewById(R.id.pEmail);
        pPhone = (EditText) findViewById(R.id.pPhone);
        pAge = (EditText) findViewById(R.id.pAge);

        bOK = (Button) findViewById(R.id.bOk);
        bCancel = (Button) findViewById(R.id.bCancel);

        if(position != -1) {
            getSupportActionBar().setTitle("Edit Entry");
            searchPerson(position);
            person = new Person();
        }
        else {
            getSupportActionBar().setTitle("Add Entry");
            person = null;
        }

        bOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (pName.getText().toString().trim().equals("") || pEmail.getText().toString().trim().equals("") ||
                        pPhone.getText().toString().trim().equals("") || pAge.getText().toString().trim().equals("")) {
                    final Snackbar snackBar = Snackbar.make(cl, "Please enter all the fields.", Snackbar.LENGTH_LONG);
                    snackBar.setAction("CLOSE", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            snackBar.dismiss();
                        }
                    });
                    snackBar.show();
                }
                else {
                    Person p = new Person();
                    p.setName(pName.getText().toString());
                    p.setEmail(pEmail.getText().toString());
                    p.setPhone(pPhone.getText().toString());
                    p.setAge(Integer.parseInt(pAge.getText().toString()));
                    if (person == null)
                        Results.getInstance().addPerson(p);
                    else
                        Results.getInstance().updatePersonDetails(p, position);
                    finish();
                }
            }
        });

        bCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    public void searchPerson(int position) {
        String clickedKey = Results.getInstance().getKeysArray().get(position);
        Results.getInstance().getmDatabase().child(clickedKey).addListenerForSingleValueEvent(
                new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        // Get user value
                        Person person = dataSnapshot.getValue(Person.class);
                        pName.setText(person.getName());
                        pEmail.setText(person.getEmail());
                        pPhone.setText(person.getPhone());
                        pAge.setText(String.valueOf(person.getAge()));
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                    }
                });
    }
}